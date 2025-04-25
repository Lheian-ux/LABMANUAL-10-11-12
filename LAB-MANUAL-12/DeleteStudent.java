
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteStudent {

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

            System.out.println("\n===== STUDENT RECORD DELETION =====");

            do {
                System.out.print("Enter student ID to delete: ");
                int studentId;

                try {
                    studentId = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error: Please enter a valid numeric ID");
                    continue;
                }

                // Check if the student exists
                String checkSql = "SELECT id, name, course FROM students WHERE id = ?";
PreparedStatement checkStmt = conn.prepareStatement(checkSql);
checkStmt.setInt(1, studentId);
ResultSet resultSet = checkStmt.executeQuery();

if (resultSet.next()) {
    System.out.println("\n----- Student Details -----");
    System.out.println("ID: " + resultSet.getInt("id"));
    System.out.println("Name: " + resultSet.getString("name"));
    System.out.println("Course: " + resultSet.getString("course"));
    System.out.println("--------------------------\n");

    System.out.print("Are you sure you want to delete this student? (yes/no): ");
    String confirm = scanner.nextLine().trim().toLowerCase();

    if (confirm.equals("yes")) {
        String deleteSql = "DELETE FROM students WHERE id = ?";
        PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
        deleteStmt.setInt(1, studentId);

        int rowsDeleted = deleteStmt.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Deletion failed.");
        }
    } else {
        System.out.println("Deletion cancelled.");
    }
} else {
    System.out.println("No student found with ID " + studentId);
}

                System.out.print("Do you want to delete another record? (yes/no): ");
                choice = scanner.nextLine().trim().toLowerCase();

            } while (choice.equals("yes"));

            System.out.println("Finished deleting records.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
