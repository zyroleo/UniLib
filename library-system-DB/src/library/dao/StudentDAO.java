package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//Simple DAO for students.
 
public class StudentDAO {

    public String findNameByCode(String studentCode) {
        String sql = "SELECT name FROM students WHERE student_code = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addStudent(String studentCode, String name) {
        String sql = "INSERT INTO students (student_code, name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentCode);
            ps.setString(2, name);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            // could be duplicate
            // e.printStackTrace();
        }
        return false;
    }
    
    public boolean removeStudent(String studentCode) {
        String deleteLoans = "DELETE FROM loans WHERE student_code = ?";
        String deleteStudent = "DELETE FROM students WHERE student_code = ?";
    
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false); // start transaction
    
            // Delete all loans for the student
            try (PreparedStatement ps1 = conn.prepareStatement(deleteLoans)) {
                ps1.setString(1, studentCode);
                ps1.executeUpdate();
            }
    
            // Delete the student
            try (PreparedStatement ps2 = conn.prepareStatement(deleteStudent)) {
                ps2.setString(1, studentCode);
                ps2.executeUpdate();
            }
    
            conn.commit(); // commit transaction
            return true;
    
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
        
}

