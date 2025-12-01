package library.ui;

import library.exceptions.AlreadyBorrowedException;
import library.exceptions.InvalidInputException;
import library.exceptions.ItemNotFoundException;
import library.model.*;
import library.service.LibraryService;
import library.service.Loan;

import java.util.List;
import java.util.Scanner;

/**
 * Console UI demonstrating user interactions and exception handling.
 * Keep it simple for demonstration.
 */
public class Main {
    private static LibraryService library = new LibraryService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        boolean running = true;

        System.out.println("=== Simple Library System ===");
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": addItemMenu(); break;
                    case "2": listAllItems(); break;
                    case "3": checkout(); break;
                    case "4": checkin(); break;
                    case "5": viewLoans(); break;
                    case "6": removeItem(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        System.out.println("Bye!");
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1) Add item (Book/Thesis/Laptop/Tablet)");
        System.out.println("2) List items");
        System.out.println("3) Check out (use call number)");
        System.out.println("4) Check in (use call number)");
        System.out.println("5) View all loans");
        System.out.println("6) Remove item (call number)");
        System.out.println("0) Exit");
        System.out.print("Choice: ");
    }

    private static void addItemMenu() throws InvalidInputException {
        System.out.print("Type (book/thesis/laptop/tablet): ");
        String type = scanner.nextLine().trim().toLowerCase();
        System.out.print("Call number: ");
        String call = scanner.nextLine().trim();
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        switch (type) {
            case "book":
                System.out.print("Author: "); String author = scanner.nextLine().trim();
                System.out.print("Publisher: "); String pub = scanner.nextLine().trim();
                System.out.print("Year: "); int year = Integer.parseInt(scanner.nextLine().trim());
                library.addBook(new Book(call, title, author, pub, year));
                System.out.println("Book added.");
                break;
            case "thesis":
                System.out.print("Student author: "); String sa = scanner.nextLine().trim();
                System.out.print("Course: "); String course = scanner.nextLine().trim();
                System.out.print("Year: "); int ty = Integer.parseInt(scanner.nextLine().trim());
                library.addThesis(new Thesis(call, title, sa, course, ty));
                System.out.println("Thesis added.");
                break;
            case "laptop":
                System.out.print("Brand: "); String lb = scanner.nextLine().trim();
                System.out.print("Specs: "); String ls = scanner.nextLine().trim();
                library.addLaptop(new Laptop(call, title, lb, ls));
                System.out.println("Laptop added.");
                break;
            case "tablet":
                System.out.print("Brand: "); String tb = scanner.nextLine().trim();
                System.out.print("Specs: "); String ts = scanner.nextLine().trim();
                library.addTablet(new Tablet(call, title, tb, ts));
                System.out.println("Tablet added.");
                break;
            default:
                throw new InvalidInputException("Unknown type: " + type);
        }
    }

    private static void listAllItems() {
        System.out.println("\nBooks:");
        for (Book b : library.listBooks()) System.out.println("  " + b);

        System.out.println("\nTheses:");
        for (Thesis t : library.listTheses()) System.out.println("  " + t);

        System.out.println("\nLaptops:");
        for (Laptop l : library.listLaptops()) System.out.println("  " + l);

        System.out.println("\nTablets:");
        for (Tablet t : library.listTablets()) System.out.println("  " + t);
    }

    private static void checkout() {
        System.out.print("Call number to check out: ");
        String call = scanner.nextLine().trim();
        System.out.print("Student code / patron code: ");
        String patron = scanner.nextLine().trim();
        try {
            library.checkOutItem(call, patron);
            System.out.println("Checked out successfully.");
        } catch (ItemNotFoundException | AlreadyBorrowedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void checkin() {
        System.out.print("Call number to check in: ");
        String call = scanner.nextLine().trim();
        try {
            library.checkInItem(call);
            System.out.println("Checked in successfully.");
        } catch (ItemNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void viewLoans() {
        System.out.println("Loans:");
        for (Loan loan : library.getLoanManager().getAllLoans()) {
            System.out.println("  " + loan);
        }
    }

    private static void removeItem() {
        System.out.print("Call number to remove: ");
        String call = scanner.nextLine().trim();
        // try removing from each catalog; ignore not found until all tested
        boolean removed = false;
        try { library.getBook(call); library.listBooks(); } catch (Exception ignored) {}
        try {
            library.getBook(call);
            library.listBooks().removeIf(item -> item.getCallNumber().equalsIgnoreCase(call));
            System.out.println("Removed from books.");
            removed = true;
        } catch (Exception ignored) {}
        try {
            library.getThesis(call);
            library.listTheses().removeIf(item -> item.getCallNumber().equalsIgnoreCase(call));
            System.out.println("Removed from theses.");
            removed = true;
        } catch (Exception ignored) {}
        try {
            library.getLaptop(call);
            library.listLaptops().removeIf(item -> item.getCallNumber().equalsIgnoreCase(call));
            System.out.println("Removed from laptops.");
            removed = true;
        } catch (Exception ignored) {}
        try {
            library.getTablet(call);
            library.listTablets().removeIf(item -> item.getCallNumber().equalsIgnoreCase(call));
            System.out.println("Removed from tablets.");
            removed = true;
        } catch (Exception ignored) {}
        if (!removed) System.out.println("Item not found to remove.");
    }

    private static void seedData() {
        library.addBook(new Book("B001", "Introduction to Java", "John Doe", "TechPub", 2019));
        library.addBook(new Book("B002", "Data Structures", "Jane Roe", "CS House", 2018));
        library.addThesis(new Thesis("T100", "Effect of Processed Food", "Alice Santos", "Nutrition", 2024));
        library.addLaptop(new Laptop("L500", "Dell Inspiron", "Dell", "i5, 8GB RAM"));
        library.addTablet(new Tablet("TB900", "Galaxy Tab", "Samsung", "8-inch"));
    }
}
