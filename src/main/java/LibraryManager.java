import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager implements LibraryDAO {
    public static void printTable(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String line = rsmd.getColumnName(i) + ": " + rs.getString(i) + " ";
                    System.out.println(line);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
     }

    @Override
    public boolean tableExists(String tableName) {
        return false;
    }

    // creates a new library table
    public void createTable() {
        String createString = "CREATE table library (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "title VARCHAR(50), " +
                "author VARCHAR(50), " +
                "year INT, " +
                "PRIMARY KEY(id));";
        try (Connection connection = ConnectionUtil.makeConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createString);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable() {
        String deleteString = "DROP table library;";
        try (Connection connection = ConnectionUtil.makeConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteString);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;
        String queryString = "SELECT * FROM library WHERE id=?";
        try (Connection connection = ConnectionUtil.makeConnection()) {
            PreparedStatement ps = connection.prepareStatement(queryString);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setYear(rs.getInt(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
     }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        String query = "SELECT * FROM library;";
        try (Connection connection = ConnectionUtil.makeConnection();) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String author = result.getString("author");
                int year = result.getInt("year");
                Book book = new Book(id, title, author, year);
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Not yet implemented
    @Override
    public List<Book> getBooksByTitle(String title) {
        return null;
    }

    // Not yet implemented
    @Override
    public List<Book> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> getBooksByYear(int startYear, int endYear) {
        List<Book> books = new ArrayList<Book>();
        String queryString = "SELECT * FROM library WHERE ? <= year AND year <= ?;";
        try (Connection connection = ConnectionUtil.makeConnection()) {
            PreparedStatement ps = connection.prepareStatement(queryString);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                String author = result.getString("author");
                int year = result.getInt("year");
                Book book = new Book(name, author, year);
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int addBook(Book book) {
        int bookId = -1;
        String createString = "INSERT INTO library (title, author, year) VALUES (?, ?, ?);";
        try (Connection connection = ConnectionUtil.makeConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    createString, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            bookId = rs.getInt(1);
            System.out.println("BOOK ID: " + bookId);
            book.setId(bookId);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bookId;
    }

    @Override
    public void updateBook(Book book) {
        String updateString = "UPDATE library SET title=?, author=?, year=? WHERE id=?;";
        try (Connection connection = ConnectionUtil.makeConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateString);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setInt(4, book.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBookById(int id) {
        String deleteString = "DELETE FROM library WHERE id=?;";
        try (Connection connection = ConnectionUtil.makeConnection()) {
            PreparedStatement ps = connection.prepareStatement(deleteString);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
