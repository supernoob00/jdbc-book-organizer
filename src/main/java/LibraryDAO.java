import java.util.List;

public interface LibraryDAO {

    public void createTable();

    public void deleteTable();

    public Book getBookById(int id);

    public List<Book> getAllBooks();

    public List<Book> getBooksByTitle(String title);

    public List<Book> getBooksByAuthor(String author);

    public List<Book> getBooksByYear(int startYear, int endYear);

    public int addBook(Book book);

    public void updateBook(Book book);

    public void deleteBookById(int id);
}
