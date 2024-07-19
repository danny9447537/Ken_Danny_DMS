/*
 Danny Ken
 202430-CEN-3024C-31950
 7/10/2024
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

    /**
     * Constructs a new AircraftRepository
     *
     * @param url The URL of the database.
     * @param user The database user.
     * @param password The database password.
     */
    public AircraftRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Establishes a connection to the database.
     *
     * @return A Connection object representing the database connection.
     */
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Checks if an aircraft with the specified serial number exists in the database.
     *
     * @param serialNumber The serial number of the aircraft.
     * @return true if the aircraft exists, false otherwise.
     */
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

    /**
     * Adds a new aircraft to the database.
     *
     * @param aircraft The aircraft to add.
     */
    public void addAircraft(Aircraft aircraft) {
        String sql = "INSERT INTO aircraft (model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, " +
                "lastInspectionDate, discrepancies) VALUES(?, ?, ?, ?, ?, ?, ?)";
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
            pstmt.setString(7, aircraft.getDiscrepancies());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes an aircraft from the database by its serial number.
     *
     * @param serialNumber The serial number of the aircraft to remove.
     */
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

    //Updates an existing aircraft in the database.

    /**
     * Updates an existing aircraft in the database.
     *
     * @param serialNumber The serial number of the aircraft to update.
     * @param model The new model of the aircraft.
     * @param newSerialNumber The new serial number of the aircraft.
     * @param maintenanceStatus The new maintenance status of the aircraft.
     * @param missionHistory The new mission history of the aircraft.
     * @param pilotAssignment The new pilot assignment for the aircraft.
     * @param lastInspectionDate The new last inspection date of the aircraft.
     * @param discrepancies The new discrepancies found in the aircraft.
     */
    public void updateAircraft(String serialNumber, String model, String newSerialNumber, String maintenanceStatus,
                               String missionHistory, String pilotAssignment, Date lastInspectionDate, String discrepancies) {
        if (!aircraftExists(serialNumber)) {
            throw new RuntimeException("Aircraft with serial number " + serialNumber + " does not exist.");
        }

        String sql = "UPDATE aircraft SET model = ?, serialNumber = ?, maintenanceStatus = ?, missionHistory = ?, " +
                "pilotAssignment = ?, lastInspectionDate = ?, discrepancies = ? WHERE serialNumber = ?";
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
            pstmt.setString(7, discrepancies);
            pstmt.setString(8, serialNumber);
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

    /**
     * Retrieves a lsit of all aircraft from the database.
     *
     * @return A list of all aircraft.
     */
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
                        rs.getDate("lastInspectionDate"),
                        rs.getString("discrepancies")
                );
                aircraftList.add(aircraft);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return aircraftList;
    }

    /**
     * Retrieves an aircraft by its serial number from the database.
     *
     * @param serialNumber The serial number of the aircraft.
     * @return The aircraft with the specified serial number.
     */
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
                        rs.getDate("lastInspectionDate"),
                        rs.getString("discrepancies")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return aircraft;
    }

    /**
     * Deletes all aircraft from the database.
     */
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