package collegemanagement;

import java.sql.*;

public class FacultyDAO {

    public void addFaculty(String name, String department, String subject) {
        String query = "INSERT INTO faculty(name, department, subject) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setString(3, subject);
            ps.executeUpdate();
            System.out.println("Faculty added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding faculty: " + e.getMessage());
        }
    }

    public void viewFaculty() {
        String query = "SELECT * FROM faculty";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("ID\tName\tDepartment\tSubject");
            while (rs.next()) {
                Faculty f = new Faculty(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("subject")
                );
                f.displayDetails();
            }
        } catch (SQLException e) {
            System.out.println("Error fetching faculty: " + e.getMessage());
        }
    }

    public void deleteFaculty(int id) {
        String query = "DELETE FROM faculty WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Faculty deleted.");
            } else {
                System.out.println("No faculty found with that ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting faculty: " + e.getMessage());
        }
    }
}
