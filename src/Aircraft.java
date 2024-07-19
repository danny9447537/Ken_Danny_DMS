/*
 Danny Ken
 202430-CEN-3024C-31950
 7/10/2024
 The Aircraft class represents an aircraft entity with various attributes such as model, serial number,
 maintenance status, mission history, pilot assignment, and last inspection date. This class serves as
 the fundamental object within the Aircraft Database Management System.
 */

import java.util.Date;
public class Aircraft {
    private String model;
    private String serialNumber;
    private String maintenanceStatus;
    private String missionHistory;
    private String pilotAssignment;
    private Date lastInspectionDate;
    private String discrepancies;

    /**
     *
     * @param model The model of the aircraft.
     * @param serialNumber The serial number  of the aircraft.
     * @param maintenanceStatus The maintenance status of the aircraft.
     * @param missionHistory The mission history of the aircraft.
     * @param pilotAssignment The pilot assignment for the aircraft.
     * @param lastInspectionDate The date of the last inspection.
     * @param discrepancies Any discrepancies found in the aircraft.
     */
    public Aircraft(String model, String serialNumber, String maintenanceStatus, String missionHistory,
                    String pilotAssignment, Date lastInspectionDate, String discrepancies) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.maintenanceStatus = maintenanceStatus;
        this.missionHistory = missionHistory;
        this.pilotAssignment = pilotAssignment;
        this.lastInspectionDate = lastInspectionDate;
        this.discrepancies = discrepancies;
    }

    // Getters and Setters
    /**
     * Gets the discrepancies of the aircraft.
     *
     * @return The discrepancies of the aircraft.
     */
    public String getDiscrepancies() {
        return discrepancies;
    }

    /**
     * Sets the discrepancies of the aircraft.
     *
     * @param discrepancies The discrepancies to set.
     */
    public void setDiscrepancies(String discrepancies) {
        this.discrepancies = discrepancies;
    }

    /**
     * Gets the model of the aircraft.
     *
     * @return The model of the aircraft.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the aircraft.
     *
     * @param model The model to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the serial number of the aircraft.
     *
     * @return The serial number of the aircraft.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the serial number of the aircraft.
     *
     * @param serialNumber The serial number to set.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the maintenance status of the aircraft.
     *
     * @return The maintenance status of the aircraft.
     */
    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    /**
     * Sets the maintenance status of the aircraft.
     *
     * @param maintenanceStatus The maintenance status to set.
     */
    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    /**
     * Gets the mission history of the aircraft.
     *
     * @return The mission history of the aircraft.
     */
    public String getMissionHistory() {
        return missionHistory;
    }

    /**
     * Sets the mission history of the aircraft.
     *
     * @param missionHistory The mission history to set.
     */
    public void setMissionHistory(String missionHistory) {
        this.missionHistory = missionHistory;
    }

    /**
     * Gets the pilot assignment of the aircraft.
     *
     * @return The pilot assignment of the aircraft.
     */
    public String getPilotAssignment() {
        return pilotAssignment;
    }

    /**
     * Sets the pilot assignment of the aircraft.
     *
     * @param pilotAssignment The pilot assignment to set.
     */
    public void setPilotAssignment(String pilotAssignment) {
        this.pilotAssignment = pilotAssignment;
    }

    /**
     * Gets the last inspection date of the aircraft.
     *
     * @return The last inspection date of the aircraft.
     */
    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    /**
     * Returns a string representation of the Aircraft object.
     *
     * @return A string representation of the Aircraft object.
     */
    @Override
    public String toString() {
        return "Aircraft{" +
                "model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", maintenanceStatus='" + maintenanceStatus + '\'' +
                ", missionHistory='" + missionHistory + '\'' +
                ", pilotAssignment='" + pilotAssignment + '\'' +
                ", lastInspectionDate=" + lastInspectionDate +
                '}';
    }
}
