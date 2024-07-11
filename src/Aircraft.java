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

    // Getters and setters
    public String getDiscrepancies() {
        return discrepancies;
    }

    public void setDiscrepancies(String discrepancies) {
        this.discrepancies = discrepancies;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public String getMissionHistory() {
        return missionHistory;
    }

    public void setMissionHistory(String missionHistory) {
        this.missionHistory = missionHistory;
    }

    public String getPilotAssignment() {
        return pilotAssignment;
    }

    public void setPilotAssignment(String pilotAssignment) {
        this.pilotAssignment = pilotAssignment;
    }

    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    // added to string functionality to fix the issue with temrinal not working
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
