
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RetrieveStudent {

    // Method to check if the students table exists
    private static boolean tableExists(Connection conn) throws SQLException {
        try (ResultSet rs = conn.getMetaData().getTables(null, null, "students", null)) {
            return rs.next();
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection(); Scanner scanner = new Scanner(System.in)) {

            // Check if table exists
            if (!tableExists(conn)) {
                System.err.println("Error: 'students' table does not exist in the database.");
                System.err.println("Please run InsertStudent first to set up the database and table.");
                return;
            }

            String choice = "";

            System.out.println("\n===== STUDENT RECORD RETRIEVAL =====");

            do {
                System.out.print("Enter student ID to retrieve: ");
                int studentId;

                try {
                    studentId = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error: Please enter a valid numeric ID");
                    continue;
                }

                String sql = "SELECT id, name, course, created_at FROM students WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, studentId);

                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String course = resultSet.getString("course");
                    String createdAt = resultSet.getString("created_at");

                    System.out.println("\n----- Student Found -----");
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Course: " + course);
                    System.out.println("Created At: " + createdAt);
                    System.out.println("------------------------\n");
                } else {
                    System.out.println("No student found with ID " + studentId);
                }

                System.out.print("Do you want to retrieve another record? (yes/no): ");
                choice = scanner.nextLine().trim().toLowerCase();

            } while (choice.equals("yes"));

            System.out.println("Finished retrieving student records.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
