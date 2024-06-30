/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The AircraftRepository class handles database operations for the Aircraft entity. This includes
 methods for adding, removing, updating, and retrieving aircraft data from the MySQL database.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AircraftRepository {
    private String url;
    private String user;
    private String password;

    public AircraftRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public boolean aircraftExists(String serialNumber) {
        String sql = "SELECT COUNT(*) FROM aircraft WHERE serialNumber = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serialNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void addAircraft(Aircraft aircraft) {
        String sql = "INSERT INTO aircraft (model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                throw new SQLException("Connection is null. Check your database connection settings.");
            }
            pstmt.setString(1, aircraft.getModel());
            pstmt.setString(2, aircraft.getSerialNumber());
            pstmt.setString(3, aircraft.getMaintenanceStatus());
            pstmt.setString(4, aircraft.getMissionHistory());
            pstmt.setString(5, aircraft.getPilotAssignment());
            pstmt.setDate(6, new java.sql.Date(aircraft.getLastInspectionDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAircraft(String serialNumber) {
        String sql = "DELETE FROM aircraft WHERE serialNumber = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                throw new SQLException("Connection is null. Check your database connection settings.");
            }
            pstmt.setString(1, serialNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAircraft(String serialNumber, String model, String newSerialNumber, String maintenanceStatus, String missionHistory, String pilotAssignment, Date lastInspectionDate) {
        if (!aircraftExists(serialNumber)) {
            throw new RuntimeException("Aircraft with serial number " + serialNumber + " does not exist.");
        }

        String sql = "UPDATE aircraft SET model = ?, serialNumber = ?, maintenanceStatus = ?, missionHistory = ?, pilotAssignment = ?, lastInspectionDate = ? WHERE serialNumber = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                throw new SQLException("Connection is null. Check your database connection settings.");
            }
            pstmt.setString(1, model);
            pstmt.setString(2, newSerialNumber);
            pstmt.setString(3, maintenanceStatus);
            pstmt.setString(4, missionHistory);
            pstmt.setString(5, pilotAssignment);
            pstmt.setDate(6, new java.sql.Date(lastInspectionDate.getTime()));
            pstmt.setString(7, serialNumber);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating aircraft failed, no rows affected.");
            } else {
                System.out.println("Aircraft updated successfully.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Aircraft> getAllAircraft() {
        String sql = "SELECT * FROM aircraft";
        List<Aircraft> aircraftList = new ArrayList<>();

        try (Connection conn = this.connect();
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
                aircraftList.add(aircraft);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return aircraftList;
    }

    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        String sql = "SELECT * FROM aircraft WHERE serialNumber = ?";
        Aircraft aircraft = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                throw new SQLException("Connection is null. Check your database connection settings.");
            }
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

    public void deleteAllAircraft() {
        String sql = "DELETE FROM aircraft";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            if (conn == null) {
                throw new SQLException("Connection is null. Check your database connection settings.");
            }
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}