package library.dao;

import library.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//DAO for CRUD on items. call_number is primary key.

public class ItemDAO {

    public boolean addItem(Item item) {
        String sql = "INSERT INTO items(call_number,type,title,author,publisher,year,brand,specs,available) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getCallNumber());
            if (item instanceof Book) {
                Book b = (Book) item;
                ps.setString(2, "BOOK");
                ps.setString(3, b.getTitle());
                ps.setString(4, b.getAuthor());
                ps.setString(5, b.getPublisher());
                ps.setObject(6, b.getYear(), Types.INTEGER);
                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);
            } else if (item instanceof Thesis) {
                Thesis t = (Thesis) item;
                ps.setString(2, "THESIS");
                ps.setString(3, t.getTitle());
                ps.setString(4, t.getStudentAuthor());
                ps.setString(5, t.getCourse());
                ps.setObject(6, t.getYear(), Types.INTEGER);
                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);
            } else if (item instanceof Laptop) {
                Laptop l = (Laptop) item;
                ps.setString(2, "LAPTOP");
                ps.setString(3, l.getTitle());
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.VARCHAR);
                ps.setNull(6, Types.INTEGER);
                ps.setString(7, l.getBrand());
                ps.setString(8, l.getSpecs());
            } else if (item instanceof Tablet) {
                Tablet t = (Tablet) item;
                ps.setString(2, "TABLET");
                ps.setString(3, t.getTitle());
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.VARCHAR);
                ps.setNull(6, Types.INTEGER);
                ps.setString(7, t.getBrand());
                ps.setString(8, t.getSpecs());
            } else {
                return false;
            }
            ps.setBoolean(9, item.isAvailable());
            ps.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException dup) {
            System.out.println("An item with that call number already exists.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateItem(Item item) {
        String sql = "UPDATE items SET type=?, title=?, author=?, publisher=?, year=?, brand=?, specs=?, available=? WHERE call_number=?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (item instanceof Book) {
                Book b = (Book) item;
                ps.setString(1, "BOOK");
                ps.setString(2, b.getTitle());
                ps.setString(3, b.getAuthor());
                ps.setString(4, b.getPublisher());
                ps.setObject(5, b.getYear(), Types.INTEGER);
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.VARCHAR);
            } else if (item instanceof Thesis) {
                Thesis t = (Thesis) item;
                ps.setString(1, "THESIS");
                ps.setString(2, t.getTitle());
                ps.setString(3, t.getStudentAuthor());
                ps.setString(4, t.getCourse());
                ps.setObject(5, t.getYear(), Types.INTEGER);
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.VARCHAR);
            } else if (item instanceof Laptop) {
                Laptop l = (Laptop) item;
                ps.setString(1, "LAPTOP");
                ps.setString(2, l.getTitle());
                ps.setNull(3, Types.VARCHAR);
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.INTEGER);
                ps.setString(6, l.getBrand());
                ps.setString(7, l.getSpecs());
            } else {
                Tablet t = (Tablet) item;
                ps.setString(1, "TABLET");
                ps.setString(2, t.getTitle());
                ps.setNull(3, Types.VARCHAR);
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.INTEGER);
                ps.setString(6, t.getBrand());
                ps.setString(7, t.getSpecs());
            }
            ps.setBoolean(8, item.isAvailable());
            ps.setString(9, item.getCallNumber());
            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeItem(String callNumber) {
        String checkActiveLoanSql =
                "SELECT COUNT(*) FROM loans WHERE call_number = ? AND return_date IS NULL";
    
        String deleteLoanHistorySql =
                "DELETE FROM loans WHERE call_number = ?";
    
        String deleteItemSql =
                "DELETE FROM items WHERE call_number = ?";
    
        try (Connection conn = DatabaseConnector.getConnection()) {
    
            // Check if the item is currently borrowed
            try (PreparedStatement ps = conn.prepareStatement(checkActiveLoanSql)) {
                ps.setString(1, callNumber);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Cannot remove item. It is currently borrowed.");
                    return false;
                }
            }
    
            // Delete loan history (old returned entries)
            try (PreparedStatement ps = conn.prepareStatement(deleteLoanHistorySql)) {
                ps.setString(1, callNumber);
                ps.executeUpdate();
            }
    
            // Delete the item
            try (PreparedStatement ps = conn.prepareStatement(deleteItemSql)) {
                ps.setString(1, callNumber);
                int deleted = ps.executeUpdate();
                return deleted > 0;
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public Item findByCallNumber(String callNumber) {
        String sql = "SELECT * FROM items WHERE call_number = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, callNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToItem(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> listAll() {
        List<Item> out = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY type, title";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(mapRowToItem(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public boolean setAvailability(String callNumber, boolean available) {
        String sql = "UPDATE items SET available = ? WHERE call_number = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, available);
            ps.setString(2, callNumber);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Item mapRowToItem(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        String call = rs.getString("call_number");
        String title = rs.getString("title");
        boolean available = rs.getBoolean("available");

        switch (type) {
            case "BOOK":
                return new Book(call, title, rs.getString("author"), rs.getString("publisher"),
                        rs.getObject("year") == null ? null : rs.getInt("year")) {{
                            setAvailable(available);
                        }};
            case "THESIS":
                return new Thesis(call, title, rs.getString("author"), rs.getString("publisher"),
                        rs.getObject("year") == null ? null : rs.getInt("year")) {{
                            setAvailable(available);
                        }};
            case "LAPTOP":
                return new Laptop(call, title, rs.getString("brand"), rs.getString("specs")) {{
                    setAvailable(available);
                }};
            case "TABLET":
                return new Tablet(call, title, rs.getString("brand"), rs.getString("specs")) {{
                    setAvailable(available);
                }};
            default:
                return null;
        }
    }
}
