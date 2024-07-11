/*
 Danny Ken
 202430-CEN-3024C-31950
 7/10/2024
 The AircraftRepositoryTest class is responsible for testing the functionality of the methods and classes within the DMS
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AircraftRepositoryTest {
    private static AircraftRepository aircraftRepository;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/aircraft_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @BeforeAll
    public static void setUp() {
        aircraftRepository = new AircraftRepository(DB_URL, DB_USER, DB_PASSWORD);
        // This ensures the database is clean before starting test
        aircraftRepository.deleteAllAircraft();
    }

    @Test
    @Order(1)
    @DisplayName("Test: Aircraft Exists")
    void aircraftExists() {
        Aircraft aircraft = new Aircraft("Model Exists", "SNEXISTS123", "Active", "Existence Test Mission", "Pilot Exists", new Date(), "No discrepancies");
        Aircraft aircraft2 = new Aircraft("Model Exists 2", "SNDOESNOTEXIST", "Active", "Existence Test Mission", "Pilot Exists", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft);
        aircraftRepository.addAircraft(aircraft2);
        aircraftRepository.removeAircraft("SNDOESNOTEXIST");

        assertTrue(aircraftRepository.aircraftExists("SNEXISTS123"));
        assertFalse(aircraftRepository.aircraftExists("SNDOESNOTEXIST"));
    }

    @Test
    @Order(2)
    @DisplayName("Test: Add Aircraft")
    void addAircraft() {
        Aircraft aircraft = new Aircraft("Model Add", "SNADD123", "Active", "Add Test Mission", "Pilot Add", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft);
        Aircraft retrieved = aircraftRepository.getAircraftBySerialNumber("SNADD123");

        assertNotNull(retrieved);
        assertEquals("Model Add", retrieved.getModel());
        assertEquals("SNADD123", retrieved.getSerialNumber());
        assertEquals("Active", retrieved.getMaintenanceStatus());
        assertEquals("Add Test Mission", retrieved.getMissionHistory());
        assertEquals("Pilot Add", retrieved.getPilotAssignment());
        assertEquals("No discrepancies", retrieved.getDiscrepancies());
    }

    @Test
    @Order(3)
    @DisplayName("Test: Remove Aircraft")
    void removeAircraft() {
        Aircraft aircraft1 = new Aircraft("Model Remove1", "SNREMOVE123", "Active", "Remove Test Mission", "Pilot Remove", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft1);

        Aircraft aircraft2 = new Aircraft("Model Remove2", "SNREMOVE1234", "Active", "Remove Test Mission", "Pilot Remove", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft2);

        Aircraft aircraft3 = new Aircraft("Model Remove3", "SNREMOVE12345", "Active", "Remove Test Mission", "Pilot Remove", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft3);

        aircraftRepository.removeAircraft("SNREMOVE123");
        aircraftRepository.removeAircraft("SNREMOVE1234");
        aircraftRepository.removeAircraft("SNREMOVE12345");

        Aircraft retrieved1 = aircraftRepository.getAircraftBySerialNumber("SNREMOVE123");
        Aircraft retrieved2 = aircraftRepository.getAircraftBySerialNumber("SNREMOVE1234");
        Aircraft retrieved3 = aircraftRepository.getAircraftBySerialNumber("SNREMOVE12345");

        assertNull(retrieved1);
        assertNull(retrieved2);
        assertNull(retrieved3);
    }

    @Test
    @Order(4)
    @DisplayName("Test: Update Aircraft")
    void updateAircraft() {
        Aircraft aircraft = new Aircraft("Model Update", "SNUPDATE123", "Inactive", "Update Mission", "Pilot Update", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft);
        aircraftRepository.updateAircraft("SNUPDATE123", "Updated Model", "SNUPDATE123", "Active", "Updated Mission", "Updated Pilot", new Date(), "Updated discrepancies");
        Aircraft updated = aircraftRepository.getAircraftBySerialNumber("SNUPDATE123");
        assertNotNull(updated);
        assertEquals("Updated Model", updated.getModel());
        assertEquals("Active", updated.getMaintenanceStatus());
        assertEquals("Updated discrepancies", updated.getDiscrepancies());
    }

    @Test
    @Order(5)
    @DisplayName("Test: Get All Aircraft")
    void getAllAircraft() {
        Aircraft aircraft1 = new Aircraft("Model 1", "SN1", "Active", "Mission 1", "Pilot 1", new Date(), "No discrepancies");
        Aircraft aircraft2 = new Aircraft("Model 2", "SN2", "Inactive", "Mission 2", "Pilot 2", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft1);
        aircraftRepository.addAircraft(aircraft2);
        List<Aircraft> aircraftList = aircraftRepository.getAllAircraft();
        assertNotNull(aircraftList);
        assertTrue(aircraftList.size() >= 2);
    }

    @Test
    @Order(6)
    @DisplayName("Test: Get Aircraft By Serial Number")
    void getAircraftBySerialNumber() {
        Aircraft aircraft = new Aircraft("Model Get", "SNGET123", "Active", "Get Test Mission", "Pilot Get", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft);
        Aircraft retrieved = aircraftRepository.getAircraftBySerialNumber("SNGET123");
        assertNotNull(retrieved);
        assertEquals("Model Get", retrieved.getModel());
        assertEquals("No discrepancies", retrieved.getDiscrepancies());
    }

    @Test
    @Order(7)
    @DisplayName("Test: Delete All Aircraft")
    void deleteAllAircraft() {
        Aircraft aircraft = new Aircraft("Model Delete All", "SNDELETEALL123", "Active", "Delete All Test Mission", "Pilot Delete All", new Date(), "No discrepancies");
        aircraftRepository.addAircraft(aircraft);
        aircraftRepository.deleteAllAircraft();
        List<Aircraft> aircraftList = aircraftRepository.getAllAircraft();
        assertTrue(aircraftList.isEmpty());
    }
}
