import java.util.Date;
import java.util.List;

/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The AircraftService class contains business logic for managing aircraft. This includes methods
 for adding, removing, updating, retrieving aircraft data, and generating maintenance reports.
 */
public class AircraftService {
    private AircraftRepository repository;

    /*
     Constructor for aircraftService which initializes AircraftRepository
     */
    public AircraftService() {
        this.repository = new AircraftRepository();
    }

    // Adds a new aircraft
    public void addAircraft(Aircraft aircraft) {
        repository.addAircraft(aircraft);
    }

    // Adds a new aircraft using individual parameters
    public void addAircraft(String model, String serialNumber, String maintenanceStatus, String missionHistory, String pilotAssignment, Date lastInspectionDate) {
        Aircraft aircraft = new Aircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate);
        repository.addAircraft(aircraft);
    }

    // Removes an aircraft by its serial number
    public void removeAircraft(String serialNumber) {
        repository.removeAircraft(serialNumber);
    }

    public void updateAircraft(String serialNumber, String maintenanceStatus, Date lastInspectionDate) {
        repository.updateAircraft(serialNumber, maintenanceStatus, lastInspectionDate);
    }

    // Retrieves all aircraft from the database
    public List<Aircraft> getAllAircraft() {
        return repository.getAllAircraft();
    }

    // Retrieves Aircraft by its serial number
    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        return repository.getAircraftBySerialNumber(serialNumber);
    }

    // Generate a maintenance report
    public void generateMaintenanceReport(String serialNumber) {
        Aircraft aircraft = repository.getAircraftBySerialNumber(serialNumber);
        if (aircraft != null) {
            System.out.println("Maintenance Report for Aircraft: " + aircraft.getSerialNumber());
            System.out.println("Model: " + aircraft.getModel());
            System.out.println("Maintenance Status: " + aircraft.getMaintenanceStatus());
            System.out.println("Mission History: " + aircraft.getMissionHistory());
            System.out.println("Pilot Assignment: " + aircraft.getPilotAssignment());
            System.out.println("Last Inspection Date: " + aircraft.getLastInspectionDate());
        } else {
            System.out.println("Aircraft with serial number " + serialNumber + " not found.");
        }
    }
    // Method to delete all aircraft data
    public void deleteAllAircraft() {
        repository.deleteAllAircraft();
    }
}
