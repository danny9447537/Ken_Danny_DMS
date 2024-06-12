import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The Main class serves as the entry point for the Aircraft Database Management System application.
 It provides a simple command-line interface to interact with the system.
 */
public class Main {
    private static AircraftService service = new AircraftService();
    private static Scanner scanner = new Scanner(System.in);
    private static AircraftController controller = new AircraftController(service, scanner);
    private static UserInterfaceManager uiManager = new UserInterfaceManager(controller);

    /**
     * Main method which serves as the entry point for the application.
     */
    public static void main(String[] args) {
        // Initializing the UI
        uiManager.initializeUI();
        uiManager.getMainFrame().setVisible(true);

        // Adding in the command-line interface for console options
        while (true) {
            System.out.println("Aircraft Database Management System");
            System.out.println("1. Add Aircraft");
            System.out.println("2. Remove Aircraft");
            System.out.println("3. Update Aircraft");
            System.out.println("4. Display All Aircraft");
            System.out.println("5. Generate Maintenance Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter serial number: ");
                    String serialNumber = scanner.nextLine();
                    System.out.print("Enter maintenance status (Active/Inactive): ");
                    String maintenanceStatus = scanner.nextLine();
                    System.out.print("Enter mission history: ");
                    String missionHistory = scanner.nextLine();
                    System.out.print("Enter pilot assignment: ");
                    String pilotAssignment = scanner.nextLine();
                    System.out.print("Enter last inspection date (yyyy-MM-dd): ");
                    String dateString = scanner.nextLine();
                    try {
                        if (dateString == null || dateString.trim().isEmpty()) {
                            System.out.println("Date string cannot be null or empty.");
                            break;
                        }
                        Date lastInspectionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        controller.addAircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate);
                        System.out.println("Aircraft added successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                    }
                    break;
                case 2:
                    System.out.print("Enter serial number: ");
                    serialNumber = scanner.nextLine();
                    controller.removeAircraft(serialNumber);
                    System.out.println("Aircraft removed successfully.");
                    break;
                case 3:
                    System.out.print("Enter serial number: ");
                    serialNumber = scanner.nextLine();
                    System.out.print("Enter new maintenance status (Active/Inactive): ");
                    maintenanceStatus = scanner.nextLine();
                    System.out.print("Enter new last inspection date (yyyy-MM-dd): ");
                    dateString = scanner.nextLine();
                    try {
                        Date lastInspectionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        controller.updateAircraft(serialNumber, maintenanceStatus, lastInspectionDate);
                        System.out.println("Aircraft updated successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                    }
                    break;
                case 4:
                    controller.displayAllAircraft();
                    break;
                case 5:
                    System.out.print("Enter serial number: ");
                    serialNumber = scanner.nextLine();
                    controller.generateMaintenanceReport(serialNumber);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    uiManager.getMainFrame().dispose();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
