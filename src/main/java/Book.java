import java.util.Objects;
import java.util.List;

public class Book {
    public static List<Book> sampleBooks = List.of(
        new Book("The Lord of the Rings", "J.R.R Tolkien", 1950),
        new Book("Pride and Prejudice", "Jane Austen", 1878),
        new Book("The Cat in the Hat", "Dr. Seuss", 1950)
    );

    static {
        for (int i = 0; i < sampleBooks.size(); i++) {
            sampleBooks.get(i).setId(i+1);
        }
    }

    private int id = -1;
    private String title;
    private String author;
    private int year;

    public Book() {

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYear() {
        return this.year;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, year);
    }
}
