import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    @Test
    public void sameBookAreEqual() {
        Book myBook = new Book("The Shining", "Stephen King", 1987);
        Book anotherBook = new Book("The Shining", "Stephen King", 1987);
        Assert.assertTrue(myBook.equals(anotherBook));
    }
}
