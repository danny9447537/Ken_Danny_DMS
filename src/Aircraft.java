/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The Aircraft class represents an aircraft entity with various attributes such as model, serial number,
 maintenance status, mission history, pilot assignment, and last inspection date. This class serves as
 the fundamental object within the Aircraft Database Management System.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
public class Aircraft {
    private String model;
    private String serialNumber;
    private String maintenanceStatus;
    private String missionHistory;
    private String pilotAssignment;
    private Date lastInspectionDate;

    /*
    Constructor for the Aircraft object
    Initializing model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, and lastInspectionDate
     */
    public Aircraft(String model, String serialNumber, String maintenanceStatus, String missionHistory,
                    String pilotAssignment, Date lastInspectionDate) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.maintenanceStatus = maintenanceStatus;
        this.missionHistory = missionHistory;
        this.pilotAssignment = pilotAssignment;
        this.lastInspectionDate = lastInspectionDate;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "<html>" +
                "<h2>Air Force Database Management System Report(s)</h2>" +
                "<b>MODEL:</b> " + model + "<br/>" +
                "<b>SERIAL NUMBER:</b> " + serialNumber + "<br/>" +
                "<b>MAINTENANCE STATUS:</b> " + maintenanceStatus + "<br/>" +
                "<b>MISSION HISTORY:</b> " + missionHistory + "<br/>" +
                "<b>PILOT ASSIGNMENT:</b> " + pilotAssignment + "<br/>" +
                "<b>LAST INSPECTION DATE:</b> " + lastInspectionDate + "<br/>" +
                "<b>REPORT DATE:</b> " + currentDate.format(formatter) + "<br/>" +
                "</html>";
    }
}
