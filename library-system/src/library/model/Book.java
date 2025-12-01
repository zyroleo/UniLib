package library.model;

public class Book extends Item implements Borrowable {
    private String author;
    private String publisher;
    private int year;

    public Book(String callNumber, String title, String author, String publisher, int year) {
        super(callNumber, title);
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    // getters & setters (encapsulation)
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String getDetails() {
        return String.format("Book: %s by %s, %d (%s)", getTitle(), author, year, publisher);
    }
}

