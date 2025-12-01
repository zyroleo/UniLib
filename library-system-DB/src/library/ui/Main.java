package library.ui;

import library.dao.StudentDAO;
import library.exceptions.AlreadyBorrowedException;
import library.exceptions.ItemNotFoundException;
import library.model.*;
import library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final LibraryService service = new LibraryService();
    private static final Scanner scanner = new Scanner(System.in);
    private static StudentDAO studentDAO = new StudentDAO();

    public static void main(String[] args) {
        seedIfEmpty();
        boolean running = true;
        System.out.println("\n=== Welcome to UniLib ===");
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": listItems(); break;
                    case "2": checkOut(); break;
                    case "3": checkIn(); break;
                    case "4": viewLoans(); break;
                    case "5": addItem(); break;
                    case "6": removeItem(); break;
                    case "7": updateItem(); break;
                    case "8": addStudent(); break;
                    case "9": removeStudent(); break;
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
        System.out.println("1) List items");
        System.out.println("2) Check out (by call number)");
        System.out.println("3) Check in (by call number)");
        System.out.println("4) View all borrowed items");
        System.out.println("5) Add item");
        System.out.println("6) Remove item");
        System.out.println("7) Update item");
        System.out.println("8) Add student");
        System.out.println("9) Remove student");
        System.out.println("0) Exit");
        System.out.print("Choice: ");
    }
    

    private static void addItem() {
        try {
            System.out.print("Type (book/thesis/laptop/tablet): ");
            String type = scanner.nextLine().trim().toLowerCase();
            System.out.print("Call number: ");
            String call = scanner.nextLine().trim();
            System.out.print("Title: ");
            String title = scanner.nextLine().trim();

            boolean ok = false;
            switch (type) {
                case "book":
                    System.out.print("Author: "); String author = scanner.nextLine().trim();
                    System.out.print("Publisher: "); String pub = scanner.nextLine().trim();
                    System.out.print("Year: "); Integer year = Integer.parseInt(scanner.nextLine().trim());
                    ok = service.addItem(new Book(call, title, author, pub, year));
                    break;
                case "thesis":
                    System.out.print("Student author: "); String sa = scanner.nextLine().trim();
                    System.out.print("Course: "); String course = scanner.nextLine().trim();
                    System.out.print("Year: "); Integer ty = Integer.parseInt(scanner.nextLine().trim());
                    ok = service.addItem(new Thesis(call, title, sa, course, ty));
                    break;
                case "laptop":
                    ok = service.addItem(new Laptop(call, title, null, null));
                    break;
                case "tablet":
                    ok = service.addItem(new Tablet(call, title, null, null));
                break;
                default:
                    System.out.println("Unknown type.");
            }
            System.out.println(ok ? "Item added." : "Failed to add item.");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number format.");
        }
    }

    private static void listItems() {
        List<Item> items = service.listAllItems();
    
        System.out.println("\n=== BOOKS ===");
        items.stream()
                .filter(i -> i instanceof Book)
                .forEach(i -> System.out.println(i + " -> " + i.getDetails()));
    
        System.out.println("\n=== THESES ===");
        items.stream()
                .filter(i -> i instanceof Thesis)
                .forEach(i -> System.out.println(i + " -> " + i.getDetails()));
    
        System.out.println("\n=== LAPTOPS ===");
        items.stream()
                .filter(i -> i instanceof Laptop)
                .forEach(i -> System.out.println(i + " -> " + i.getDetails()));
    
        System.out.println("\n=== TABLETS ===");
        items.stream()
                .filter(i -> i instanceof Tablet)
                .forEach(i -> System.out.println(i + " -> " + i.getDetails()));
    }
    
    

    private static void checkOut() {
        System.out.print("Student code (e.g., 24-00101): ");
        String studentCode = scanner.nextLine().trim();
        String name = service.findStudentName(studentCode);
        if (name == null) {
            System.out.println("Student not found. Add new? (y/n)");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.print("Student name: ");
                String newName = scanner.nextLine().trim();
                boolean added = service.addStudent(studentCode, newName);
                if (!added) { System.out.println("Failed to add student."); return; }
                name = newName;
            } else {
                return;
            }
        }
        System.out.println("Student: " + name);

        System.out.print("Call number to check out: ");
        String call = scanner.nextLine().trim();
        try {
            service.checkOut(call, studentCode);
            System.out.println("Checked out successfully.");
        } catch (ItemNotFoundException | AlreadyBorrowedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void checkIn() {
        System.out.print("Call number to check in: ");
        String call = scanner.nextLine().trim();
        try {
            service.checkIn(call);
            System.out.println("Checked in successfully.");
        } catch (ItemNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void viewLoans() {
        List<?> loans = service.listActiveLoans();
        System.out.println("\nActive Borrowed Items:");
        System.out.println("ID | Call# | StudentCode | StudentName | Title (Type) | BorrowedAt");
        for (Object o : loans) {
            library.dao.LoanDAO.ActiveLoanRow r = (library.dao.LoanDAO.ActiveLoanRow) o;
            System.out.printf("%d | %s | %s | %s | %s (%s) | %s\n",
                    r.id, r.callNumber, r.studentCode, r.studentName, r.title, r.type, r.borrowDate);
        }
    }

    private static void addStudent() {
        System.out.print("Student code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        boolean added = service.addStudent(code, name);
        System.out.println(added ? "Student added." : "Failed to add student (maybe already exists).");
    }

    private static void removeStudent() {
        System.out.print("Student code to remove: ");
        String code = scanner.nextLine().trim();
        boolean ok = studentDAO.removeStudent(code);
        System.out.println(ok ? "Student removed." : "Failed to remove student.");
    }
    

    private static void updateItem() {
        System.out.print("Call number of item to update: ");
        String call = scanner.nextLine().trim();
        Item item = service.findItem(call);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("New title: ");
        String title = scanner.nextLine().trim();
        item.setTitle(title);
    
        if (item instanceof Book) {
            Book b = (Book) item;
            System.out.print("New author: ");
            b.setAuthor(scanner.nextLine().trim());
            System.out.print("New publisher: ");
            b.setPublisher(scanner.nextLine().trim());
            System.out.print("New year: ");
            b.setYear(Integer.parseInt(scanner.nextLine().trim()));
        } else if (item instanceof Thesis) {
            Thesis t = (Thesis) item;
            System.out.print("New student author: ");
            t.setStudentAuthor(scanner.nextLine().trim());
            System.out.print("New course: ");
            t.setCourse(scanner.nextLine().trim());
            System.out.print("New year: ");
            t.setYear(Integer.parseInt(scanner.nextLine().trim()));
        }
        // Laptops and Tablets only update title
    
        boolean ok = service.updateItem(item);
        System.out.println(ok ? "Item updated." : "Failed to update item.");
    }
    

    private static void removeItem() {
        System.out.print("Call number to remove: ");
        String call = scanner.nextLine().trim();
        boolean removed = service.removeItem(call);
        System.out.println(removed ? "Removed." : "Not found or failed to remove.");
    }

    private static void seedIfEmpty() {
        // Ensure initial data exists (non-intrusive)
        try {
            if (service.findItem("B001") == null) {
                service.addItem(new Book("B001", "Introduction to Java", "John Doe", "TechPub", 2019));
            }
            if (service.findItem("B002") == null) {
                service.addItem(new Book("B002", "Data Structures", "Jane Roe", "CS House", 2018));
            }
            if (service.findItem("T100") == null) {
                service.addItem(new Thesis("T100", "Effect of Processed Food", "Alice Santos", "Nutrition", 2024));
            }
            if (service.findItem("L500") == null) {
                service.addItem(new Laptop("L500", "Dell Inspiron", "Dell", "i5, 8GB RAM"));
            }
            if (service.findItem("TB900") == null) {
                service.addItem(new Tablet("TB900", "Galaxy Tab", "Samsung", "8-inch"));
            }
        } catch (Exception e) {
            // ignore seed errors
        }
    }
}
