package library.model;

/**
 * Abstract superclass for all library items.
 * Demonstrates abstraction, encapsulation and polymorphism.
 */
public abstract class Item {
    private String callNumber;
    private String title;
    private boolean borrowed;

    public Item(String callNumber, String title) {
        this.callNumber = callNumber;
        this.title = title;
        this.borrowed = false;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    // each subclass must implement details display
    public abstract String getDetails();

    @Override
    public String toString() {
        return String.format("[%s] %s (call#: %s) %s",
                this.getClass().getSimpleName(),
                title,
                callNumber,
                borrowed ? "(BORROWED)" : "(AVAILABLE)");
    }
}
