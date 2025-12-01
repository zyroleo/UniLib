package library.model;


 //Abstract superclass for all library items.
 
public abstract class Item {
    private String callNumber;
    private String title;
    private boolean available;

    public Item(String callNumber, String title) {
        this.callNumber = callNumber;
        this.title = title;
        this.available = true;
    }

    public String getCallNumber() { return callNumber; }
    public void setCallNumber(String callNumber) { this.callNumber = callNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public abstract String getDetails();

    @Override
    public String toString() {
        return String.format("%s - %s (call#: %s) %s",
                this.getClass().getSimpleName(),
                title,
                callNumber,
                available ? "(AVAILABLE)" : "(BORROWED)");
    }
}
