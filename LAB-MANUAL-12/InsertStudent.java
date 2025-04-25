
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertStudent {

    // Method to ensure the database and table exist
    private static void setupDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Create database if not exists
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS school");

            // Use the school database
            stmt.executeUpdate("USE school");

            // Create students table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "course VARCHAR(100) NOT NULL, "
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            stmt.executeUpdate(createTableSQL);

            System.out.println("Database and table setup completed successfully");
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection(); Scanner scanner = new Scanner(System.in)) {

            System.out.println("Setting up database and tables if needed...");
            setupDatabase(conn);

            String choice;

            do {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                System.out.print("Enter student course: ");
                String course = scanner.nextLine();

                String sql = "INSERT INTO students (name, course) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, course);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Student inserted successfully!");
                }

                System.out.print("Do you want to add another record? (yes/no): ");
                choice = scanner.nextLine().trim().toLowerCase();
            } while (choice.equals("yes"));

            System.out.println("Finished adding student records.");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
