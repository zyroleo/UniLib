package library.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//DAO to manage loans. Returns rows for active loans with student names.
 
public class LoanDAO {

    public boolean createLoan(String callNumber, String studentCode) {
        String insert = "INSERT INTO loans(call_number, student_code, borrow_date) VALUES (?, ?, NOW())";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, callNumber);
            ps.setString(2, studentCode);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean closeLoan(String callNumber) {
        String update = "UPDATE loans SET return_date = NOW() WHERE call_number = ? AND return_date IS NULL";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, callNumber);
            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasActiveLoanFor(String callNumber) {
        String sql = "SELECT id FROM loans WHERE call_number = ? AND return_date IS NULL";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, callNumber);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ActiveLoanRow> listActiveLoans() {
        List<ActiveLoanRow> out = new ArrayList<>();
        String sql = "SELECT l.id, l.call_number, l.student_code, s.name, i.title, i.type, l.borrow_date " +
                     "FROM loans l " +
                     "JOIN students s ON l.student_code = s.student_code " +
                     "JOIN items i ON i.call_number = l.call_number " +
                     "WHERE l.return_date IS NULL " +
                     "ORDER BY l.borrow_date DESC";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new ActiveLoanRow(
                        rs.getInt("id"),
                        rs.getString("call_number"),
                        rs.getString("student_code"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("type"),
                        rs.getTimestamp("borrow_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static class ActiveLoanRow {
        public final int id;
        public final String callNumber;
        public final String studentCode;
        public final String studentName;
        public final String title;
        public final String type;
        public final Timestamp borrowDate;

        public ActiveLoanRow(int id, String callNumber, String studentCode, String studentName,
                             String title, String type, Timestamp borrowDate) {
            this.id = id;
            this.callNumber = callNumber;
            this.studentCode = studentCode;
            this.studentName = studentName;
            this.title = title;
            this.type = type;
            this.borrowDate = borrowDate;
        }
    }
}
