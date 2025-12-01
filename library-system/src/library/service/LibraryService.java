package library.service;

import library.catalog.BookCatalog;
import library.catalog.ThesisCatalog;
import library.catalog.LaptopCatalog;
import library.catalog.TabletCatalog;
import library.exceptions.ItemNotFoundException;
import library.exceptions.AlreadyBorrowedException;
import library.model.Item;
import library.model.Book;
import library.model.Thesis;
import library.model.Laptop;
import library.model.Tablet;

import java.util.List;

/**
 * High-level facade that ties catalogs and loan manager together.
 */
public class LibraryService {
    private BookCatalog bookCatalog = new BookCatalog();
    private ThesisCatalog thesisCatalog = new ThesisCatalog();
    private LaptopCatalog laptopCatalog = new LaptopCatalog();
    private TabletCatalog tabletCatalog = new TabletCatalog();
    private LoanManager loanManager = new LoanManager();

    // CRUD helpers for different catalogs
    public void addBook(Book b) { bookCatalog.add(b); }
    public void addThesis(Thesis t) { thesisCatalog.add(t); }
    public void addLaptop(Laptop l) { laptopCatalog.add(l); }
    public void addTablet(Tablet t) { tabletCatalog.add(t); }

    public Book getBook(String call) throws ItemNotFoundException { return bookCatalog.findByCallNumber(call); }
    public Thesis getThesis(String call) throws ItemNotFoundException { return thesisCatalog.findByCallNumber(call); }
    public Laptop getLaptop(String call) throws ItemNotFoundException { return laptopCatalog.findByCallNumber(call); }
    public Tablet getTablet(String call) throws ItemNotFoundException { return tabletCatalog.findByCallNumber(call); }

    public List<Book> listBooks() { return bookCatalog.getAll(); }
    public List<Thesis> listTheses() { return thesisCatalog.getAll(); }
    public List<Laptop> listLaptops() { return laptopCatalog.getAll(); }
    public List<Tablet> listTablets() { return tabletCatalog.getAll(); }

    // Borrowing
    public void checkOutItem(String callNumber, String patronCode) throws ItemNotFoundException, AlreadyBorrowedException {
        // find item in any catalog
        Item it = findItemAcrossCatalogs(callNumber);
        if (it == null) throw new ItemNotFoundException("Item not found: " + callNumber);
        if (it.isBorrowed()) throw new AlreadyBorrowedException("Item already borrowed: " + callNumber);

        // mark borrowed and create loan
        it.setBorrowed(true);
        loanManager.checkOut(callNumber, patronCode);
    }

    public void checkInItem(String callNumber) throws ItemNotFoundException {
        Item it = findItemAcrossCatalogs(callNumber);
        if (it == null) throw new ItemNotFoundException("Item not found: " + callNumber);
        if (!it.isBorrowed()) {
            // not borrowed
            throw new ItemNotFoundException("Item is not currently borrowed: " + callNumber);
        }
        it.setBorrowed(false);
        loanManager.checkIn(callNumber);
    }

    private Item findItemAcrossCatalogs(String callNumber) {
        try { return getBook(callNumber); } catch (ItemNotFoundException ignored) {}
        try { return getThesis(callNumber); } catch (ItemNotFoundException ignored) {}
        try { return getLaptop(callNumber); } catch (ItemNotFoundException ignored) {}
        try { return getTablet(callNumber); } catch (ItemNotFoundException ignored) {}
        return null;
    }

    public LoanManager getLoanManager() { return loanManager; }
}
