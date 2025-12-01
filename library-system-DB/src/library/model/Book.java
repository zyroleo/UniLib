package library.model;

public class Book extends Item implements Borrowable {
    private String author;
    private String publisher;
    private Integer year;

    public Book(String callNumber, String title, String author, String publisher, Integer year) {
        super(callNumber, title);
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    @Override
    public String getDetails() {
        return String.format("Book: %s by %s (%d) - %s",
                getTitle(),
                author == null ? "Unknown" : author,
                year == null ? 0 : year,
                publisher == null ? "" : publisher);
    }
}
