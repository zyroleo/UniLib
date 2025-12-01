package library.service;

import library.dao.ItemDAO;
import library.dao.LoanDAO;
import library.dao.StudentDAO;
import library.exceptions.AlreadyBorrowedException;
import library.exceptions.ItemNotFoundException;
import library.model.Item;

import java.util.List;

public class LibraryService {
    private final ItemDAO itemDAO = new ItemDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final LoanDAO loanDAO = new LoanDAO();

    // Catalog operations
    public boolean addItem(Item item) { return itemDAO.addItem(item); }
    public boolean updateItem(Item item) { return itemDAO.updateItem(item); }
    public boolean removeItem(String callNumber) { return itemDAO.removeItem(callNumber); }
    public Item findItem(String callNumber) { return itemDAO.findByCallNumber(callNumber); }
    public List<Item> listAllItems() { return itemDAO.listAll(); }

    // Student lookup and add
    public String findStudentName(String studentCode) { return studentDAO.findNameByCode(studentCode); }
    public boolean addStudent(String studentCode, String name) { return studentDAO.addStudent(studentCode, name); }

    // Borrow & return
    public void checkOut(String callNumber, String studentCode) throws ItemNotFoundException, AlreadyBorrowedException {
        Item item = itemDAO.findByCallNumber(callNumber);
        if (item == null) throw new ItemNotFoundException("Item not found: " + callNumber);
        if (!item.isAvailable() || loanDAO.hasActiveLoanFor(callNumber)) throw new AlreadyBorrowedException("Item is already borrowed.");

        // create loan and mark unavailable
        boolean loaned = loanDAO.createLoan(callNumber, studentCode);
        if (loaned) itemDAO.setAvailability(callNumber, false);
    }

    public void checkIn(String callNumber) throws ItemNotFoundException {
        Item item = itemDAO.findByCallNumber(callNumber);
        if (item == null) throw new ItemNotFoundException("Item not found: " + callNumber);
        if (item.isAvailable()) throw new ItemNotFoundException("Item is not currently borrowed: " + callNumber);

        boolean closed = loanDAO.closeLoan(callNumber);
        if (closed) itemDAO.setAvailability(callNumber, true);
    }

    public List<LoanDAO.ActiveLoanRow> listActiveLoans() {
        return loanDAO.listActiveLoans();
    }
}
