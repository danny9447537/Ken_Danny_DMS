/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The AircraftController class handles user interactions for the Aircraft Database Management System (DMS).
 It provides methods to add, remove, update, display aircraft, and generate maintenance reports.
 */

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AircraftController {
    private AircraftService service;
    private Scanner scanner;

    // Constructor for AircraftController which initializes the AircraftService and Scanner.
    public AircraftController(AircraftService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    // Method to add a new aircraft via GUI.
    public void addAircraft(String model, String serialNumber, String maintenanceStatus, String missionHistory,
                            String pilotAssignment, Date lastInspectionDate) {
        Aircraft aircraft = new Aircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate);
        service.addAircraft(aircraft);
    }

    // Method to remove an aircraft via GUI.
    public void removeAircraft(String serialNumber) {
        service.removeAircraft(serialNumber);
    }

    // Method to update an aircraft's maintenance status and last inspection date via GUI.
    public void updateAircraft(String serialNumber, String model, String newSerialNumber, String maintenanceStatus, String missionHistory, String pilotAssignment, Date lastInspectionDate) {
        service.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate);
    }

    // Method to display all aircraft via CLI.
    public void displayAllAircraft() {
        List<Aircraft> aircraftList = service.getAllAircraft();
        if (aircraftList.isEmpty()) {
            System.out.println("No aircraft found.");
        } else {
            for (Aircraft aircraft : aircraftList) {
                System.out.println(aircraft.toString());
            }
        }
    }

    // Method to generate a maintenance report for a specific aircraft via CLI.
    public void generateMaintenanceReport(String serialNumber) {
        Aircraft aircraft = service.getAircraftBySerialNumber(serialNumber);
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

    // Method to get all the aircraft.
    public List<Aircraft> getAllAircraft() {
        return service.getAllAircraft();
    }

    // Method to get an aircraft by its serial number.
    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        return service.getAircraftBySerialNumber(serialNumber);
    }

    // Method to delete all aircraft data
    public void deleteAllAircraft() {
        service.deleteAllAircraft();
    }

}
