import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class LibraryManagerTest {
    LibraryManager lib;

    @Before
    public void setUp() {
        lib = new LibraryManager();
        lib.deleteTable();
        lib.createTable();
    }

    @Test
    public void emptyTableOnStart() {
        List<Book> actual = lib.getAllBooks();
        List<Book> expected = new ArrayList<Book>();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void booksAdded() {
        for (Book book : Book.sampleBooks) {
            lib.addBook(book);
        }
        List<Book> expected = Book.sampleBooks;
        List<Book> actual = lib.getAllBooks();
        Assert.assertEquals(expected, actual);
    }

}
