/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The AircraftService class contains business logic for managing aircraft. This includes methods
 for adding, removing, updating, retrieving aircraft data, and generating maintenance reports.
 */

import java.util.Date;
import java.util.List;

public class AircraftService {
    private AircraftRepository repository;

    public AircraftService(String url, String user, String password) {
        this.repository = new AircraftRepository(url, user, password);
    }

    public void addAircraft(Aircraft aircraft) {
        repository.addAircraft(aircraft);
    }

    public void updateAircraft(String serialNumber, String model, String newSerialNumber, String maintenanceStatus,
                               String missionHistory, String pilotAssignment, Date lastInspectionDate,
                               String discrepancies) {
        repository.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory,
                pilotAssignment, lastInspectionDate, discrepancies);
    }

    public void removeAircraft(String serialNumber) {
        repository.removeAircraft(serialNumber);
    }

    public List<Aircraft> getAllAircraft() {
        return repository.getAllAircraft();
    }

    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        return repository.getAircraftBySerialNumber(serialNumber);
    }

    public void deleteAllAircraft() {
        repository.deleteAllAircraft();
    }
}
