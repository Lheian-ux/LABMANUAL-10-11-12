
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateStudent {

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

            System.out.println("\n===== STUDENT RECORD UPDATE =====");

            do {
                System.out.print("Enter student ID to update: ");
                int studentId;

                try {
                    studentId = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error: Please enter a valid numeric ID");
                    continue;
                }

                // Check if the student exists
                String checkSql = "SELECT id, name, course, created_at FROM students WHERE id = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setInt(1, studentId);
                ResultSet resultSet = checkStmt.executeQuery();

                if (resultSet.next()) {
                    System.out.println("\n----- Current Student Details -----");
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Current Name: " + resultSet.getString("name"));
                    System.out.println("Current Course: " + resultSet.getString("course"));
                    System.out.println("Created At: " + resultSet.getString("created_at"));
                    System.out.println("----------------------------------\n");

                    System.out.print("Enter new name (leave blank to keep current): ");
                    String newName = scanner.nextLine().trim();

                    if (newName.isEmpty()) {
                        newName = resultSet.getString("name");
                    }

                    System.out.print("Enter new course (leave blank to keep current): ");
                    String newCourse = scanner.nextLine().trim();

                    if (newCourse.isEmpty()) {
                        newCourse = resultSet.getString("course");
                    }

                    String updateSql = "UPDATE students SET name = ?, course = ? WHERE id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setString(1, newName);
                    updateStmt.setString(2, newCourse);
                    updateStmt.setInt(3, studentId);

                    int rowsUpdated = updateStmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Student record updated successfully!");
                    } else {
                        System.out.println("Failed to update the student record.");
                    }

                } else {
                    System.out.println("No student found with ID " + studentId);
                }

                System.out.print("Do you want to update another record? (yes/no): ");
                choice = scanner.nextLine().trim().toLowerCase();

            } while (choice.equals("yes"));

            System.out.println("Finished updating student records.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
