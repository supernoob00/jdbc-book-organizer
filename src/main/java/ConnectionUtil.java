import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static String defaultUrl = "jdbc:mysql://localhost:3306/library";
    private static String defaultUsername = "root";
    private static String defaultPassword = "password";

    public static Connection makeConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                ConnectionUtil.defaultUrl,
                ConnectionUtil.defaultUsername,
                ConnectionUtil.defaultPassword);
        return connection;
    }

    public static Connection makeConnection(String url, String username, String password)
        throws SQLException {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
    }
}
