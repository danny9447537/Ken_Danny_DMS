/*
 Danny Ken
 202430-CEN-3024C-31950
 7/10/2024
 The AircraftService class contains business logic for managing aircraft. This includes methods
 for adding, removing, updating, retrieving aircraft data, and generating maintenance reports.
 */

import java.util.Date;
import java.util.List;


public class AircraftService {
    private AircraftRepository repository;

    /**
     * Constructs a new AircraftService
     *
     * @param url The URL of the database.
     * @param user The database user.
     * @param password The database password.
     */
    public AircraftService(String url, String user, String password) {
        this.repository = new AircraftRepository(url, user, password);
    }

    /**
     * Adds a new aircraft.
     *
     * @param aircraft The aircraft to add.
     */
    public void addAircraft(Aircraft aircraft) {
        repository.addAircraft(aircraft);
    }

    /**
     * Updates an existing aircraft.
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
                               String missionHistory, String pilotAssignment, Date lastInspectionDate,
                               String discrepancies) {
        repository.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory,
                pilotAssignment, lastInspectionDate, discrepancies);
    }

    /**
     * Removes an aircraft by its serial number.
     *
     * @param serialNumber The serial number of the aircraft to remove.
     */
    public void removeAircraft(String serialNumber) {
        repository.removeAircraft(serialNumber);
    }

    /**
     * Retrieves a list of all aircraft.
     *
     * @return A list of all aircraft.
     */
    public List<Aircraft> getAllAircraft() {
        return repository.getAllAircraft();
    }

    /**
     * Retrieves an aircraft by its serial number.
     *
     * @param serialNumber The serial number of the aircraft.
     * @return The aircraft with the specified serial number.
     */
    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        return repository.getAircraftBySerialNumber(serialNumber);
    }

    /**
     * Deletes all aircraft from the database.
     */
    public void deleteAllAircraft() {
        repository.deleteAllAircraft();
    }
}
