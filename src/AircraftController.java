/*
 Danny Ken
 202430-CEN-3024C-31950
 7/10/2024
 The AircraftController class handles user interactions for the Aircraft Database Management System (DMS).
 It provides methods to add, remove, update, display aircraft, and generate maintenance reports.
 */

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AircraftController {
    private AircraftService service;
    private Scanner scanner;

    public AircraftController(AircraftService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void addAircraft(String model, String serialNumber, String maintenanceStatus, String missionHistory,
                            String pilotAssignment, Date lastInspectionDate, String discrepancies) {
        Aircraft aircraft = new Aircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
        service.addAircraft(aircraft);
    }

    public void removeAircraft(String serialNumber) {
        service.removeAircraft(serialNumber);
    }

    public void updateAircraft(String serialNumber, String model, String newSerialNumber, String maintenanceStatus, String missionHistory, String pilotAssignment, Date lastInspectionDate, String discrepancies) {
        service.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
    }

    public void displayAllAircraft() {
        List<Aircraft> aircraftList = service.getAllAircraft();
        if (aircraftList.isEmpty()) {
            System.out.println("No aircraft found.");
        } else {
            for (Aircraft aircraft : aircraftList) {
                System.out.println("Model: " + aircraft.getModel());
            }
        }
    }

    public void generateMaintenanceReport(String serialNumber) {
        Aircraft aircraft = service.getAircraftBySerialNumber(serialNumber);
        if (aircraft != null) {
            System.out.println("Maintenance Report for Aircraft: " + aircraft.getSerialNumber());
            System.out.println("Model: " + aircraft.getModel());
            System.out.println("Maintenance Status: " + aircraft.getMaintenanceStatus());
            System.out.println("Mission History: " + aircraft.getMissionHistory());
            System.out.println("Pilot Assignment: " + aircraft.getPilotAssignment());
            System.out.println("Last Inspection Date: " + aircraft.getLastInspectionDate());
            System.out.println("Discrepancies: " + aircraft.getDiscrepancies());
        } else {
            System.out.println("Aircraft with serial number " + serialNumber + " not found.");
        }
    }

    public List<Aircraft> getAllAircraft() {
        return service.getAllAircraft();
    }

    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        return service.getAircraftBySerialNumber(serialNumber);
    }

    public void deleteAllAircraft() {
        service.deleteAllAircraft();
    }
}