import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The AircraftRepository class handles database operations for the Aircraft entity. This includes
 methods for adding, removing, updating, and retrieving aircraft data from the MySQL database.
 */
public class AircraftRepository {
    private Connection connect() {
        String url = "jdbc:mysql://127.0.0.1:3306/aircraft_db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // add aircraft method
    public void addAircraft(Aircraft aircraft) {
        String sql = "INSERT INTO aircraft (model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aircraft.getModel());
            pstmt.setString(2, aircraft.getSerialNumber());
            pstmt.setString(3, aircraft.getMaintenanceStatus());
            pstmt.setString(4, aircraft.getMissionHistory());
            pstmt.setString(5, aircraft.getPilotAssignment());
            pstmt.setDate(6, new java.sql.Date(aircraft.getLastInspectionDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Remove aircraft method
    public void removeAircraft(String serialNumber) {
        String sql = "DELETE FROM aircraft WHERE serialNumber = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serialNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update aircraft method
    public void updateAircraft(String serialNumber, String maintenanceStatus, Date lastInspectionDate) {
        String sql = "UPDATE aircraft SET maintenanceStatus = ?, lastInspectionDate = ? WHERE serialNumber = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maintenanceStatus);
            pstmt.setDate(2, new java.sql.Date(lastInspectionDate.getTime()));
            pstmt.setString(3, serialNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Aircraft> getAllAircraft() {
        String sql = "SELECT * FROM aircraft";
        List<Aircraft> aircraftList = new ArrayList<>();

        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aircraft aircraft = new Aircraft(
                        rs.getString("model"),
                        rs.getString("serialNumber"),
                        rs.getString("maintenanceStatus"),
                        rs.getString("missionHistory"),
                        rs.getString("pilotAssignment"),
                        rs.getDate("lastInspectionDate")
                );
                aircraftList.add(aircraft); // Ensure to add aircraft to the list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return aircraftList; // Return the list properly
    }

    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        String sql = "SELECT * FROM aircraft WHERE serialNumber = ?";
        Aircraft aircraft = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serialNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                aircraft = new Aircraft(
                        rs.getString("model"),
                        rs.getString("serialNumber"),
                        rs.getString("maintenanceStatus"),
                        rs.getString("missionHistory"),
                        rs.getString("pilotAssignment"),
                        rs.getDate("lastInspectionDate")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return aircraft;
    }

    // Method to delete all aircraft data
    public void deleteAllAircraft() {
        String sql = "DELETE FROM aircraft";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
