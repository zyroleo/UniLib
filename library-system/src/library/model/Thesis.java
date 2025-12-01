package library.model;

public class Thesis extends Item implements Borrowable {
    private String studentAuthor;
    private String course;
    private int year;

    public Thesis(String callNumber, String title, String studentAuthor, String course, int year) {
        super(callNumber, title);
        this.studentAuthor = studentAuthor;
        this.course = course;
        this.year = year;
    }

    public String getStudentAuthor() { return studentAuthor; }
    public void setStudentAuthor(String studentAuthor) { this.studentAuthor = studentAuthor; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String getDetails() {
        return String.format("Thesis: %s by %s, %d (%s)", getTitle(), studentAuthor, year, course);
    }
}

