package collegemanagement;

import java.sql.*;

public class StudentDAO {

    public void addStudent(String name, String course, int semester, int attendance, int marks, String feeStatus) {
        String query = "INSERT INTO student(name, course, semester, attendance, marks, fee_status) VALUES (?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, semester);
            ps.setInt(4, attendance);
            ps.setInt(5, marks);
            ps.setString(6, feeStatus);
            ps.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void viewStudents() {
        String query = "SELECT * FROM student";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getInt("semester"),
                        rs.getInt("attendance"),
                        rs.getInt("marks"),
                        rs.getString("fee_status")
                );
                s.displayDetails();
            }
        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }
    }

    public Student getStudentById(int id) {
        String query = "SELECT * FROM student WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("course"),
                            rs.getInt("semester"),
                            rs.getInt("attendance"),
                            rs.getInt("marks"),
                            rs.getString("fee_status")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }
        return null;
    }

    public void updateAttendance(int id, int newAttendance) {
        String query = "UPDATE student SET attendance=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, newAttendance);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Attendance updated.");
            } else {
                System.out.println("No student found with that ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating attendance: " + e.getMessage());
        }
    }

    public void updateMarks(int id, int newMarks) {
        String query = "UPDATE student SET marks=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, newMarks);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Marks updated.");
            } else {
                System.out.println("No student found with that ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating marks: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM student WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted.");
            } else {
                System.out.println("No student found with that ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}
