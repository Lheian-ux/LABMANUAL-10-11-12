
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver: " + e.getMessage());
            System.err.println("Please make sure the MySQL connector JAR is in your classpath");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            if (e.getCause() instanceof ConnectException) {
                System.err.println("\n===== DATABASE CONNECTION ERROR =====");
                System.err.println("Cannot connect to MySQL server. Please make sure:");
                System.err.println("1. MySQL server is running on localhost:3306");
                System.err.println("2. The 'school' database exists");
                System.err.println("3. The username 'root' has access with the configured password");
                System.err.println("4. Your firewall is not blocking MySQL connections");
                System.err.println("=====================================\n");
            }
            throw e;
        }
    }

    // Optional: test the connection
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        try (Connection conn = getConnection()) {
            System.out.println("Connected to MySQL database successfully!");
            System.out.println("Database connection is working correctly.");
        } catch (SQLException e) {
            System.err.println("Connection failed! " + e.getMessage());
            e.printStackTrace();
        }
    }
}
