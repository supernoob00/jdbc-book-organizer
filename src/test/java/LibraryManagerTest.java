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

        for (int i = 0; i < expected.size(); i++) {
            System.out.println(expected.get(i).toString());
            System.out.println(actual.get(i).toString());
        }

        Assert.assertTrue(expected.get(0).equals(actual.get(0)));
    }

}
