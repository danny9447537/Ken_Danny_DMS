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

    /**
     * Constructs a new AircraftController
     *
     * @param service The service to use for aircraft operations.
     * @param scanner The scanner to use for user input.
     */
    public AircraftController(AircraftService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Adds a new aircraft.
     *
     * @param model The model of the aircraft.
     * @param serialNumber The serial number of the aircraft.
     * @param maintenanceStatus The maintenance status of teh aircraft.
     * @param missionHistory The mission history of the aircraft.
     * @param pilotAssignment The pilot assignment for the aircraft.
     * @param lastInspectionDate The date of the last inspection.
     * @param discrepancies Any discrepancies found in the aircraft.
     */
    public void addAircraft(String model, String serialNumber, String maintenanceStatus, String missionHistory,
                            String pilotAssignment, Date lastInspectionDate, String discrepancies) {
        Aircraft aircraft = new Aircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
        service.addAircraft(aircraft);
    }

    /**
     * Remove an aircraft by its serial number.
     *
     * @param serialNumber The serial number of the aircraft to remove.
     */
    public void removeAircraft(String serialNumber) {
        service.removeAircraft(serialNumber);
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
    public void updateAircraft(String serialNumber, String model, String newSerialNumber, String maintenanceStatus, String missionHistory, String pilotAssignment, Date lastInspectionDate, String discrepancies) {
        service.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
    }

    /**
     * Displays all aircraft.
     */
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

    /**
     * Generates a maintenance report for an aircraft by its serial number.
     *
     * @param serialNumber The serial number of the aircraft.
     */
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

    /**
     * Retrieves a list of all aircraft.
     *
     * @return A list of all aircraft.
     */
    public List<Aircraft> getAllAircraft() {
        return service.getAllAircraft();
    }

    /**
     * Retrieves an aircraft by its serial number.
     *
     * @param serialNumber The serial number of the aircraft.
     * @return The aircraft with the specified serial number.
     */
    public Aircraft getAircraftBySerialNumber(String serialNumber) {
        return service.getAircraftBySerialNumber(serialNumber);
    }

    /**
     * Deletes all aircraft.
     */
    public void deleteAllAircraft() {
        service.deleteAllAircraft();
    }
}