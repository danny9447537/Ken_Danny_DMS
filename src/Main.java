import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    private static AircraftService service;
    private static Scanner scanner = new Scanner(System.in);
    private static AircraftController controller;
    private static UserInterfaceManager uiManager;

    public static void main(String[] args) {
        Scanner credentialScanner = new Scanner(System.in);

        // Loop until a successful connection is established
        while (true) {
            // Prompting for MySQL credentials
            System.out.print("Enter MySQL Server URL (e.g., jdbc:mysql://127.0.0.1:3306/ or " +
                    "jdbc:mysql://localhost:3306/ ): ");
            DB_URL = credentialScanner.nextLine();

            System.out.print("Enter MySQL User: ");
            DB_USER = credentialScanner.nextLine();

            System.out.print("Enter MySQL Password: ");
            DB_PASSWORD = credentialScanner.nextLine();

            // Validate the connection and setup database if necessary
            if (validateConnectionAndSetup(DB_URL, DB_USER, DB_PASSWORD)) {
                System.out.println("Connection established and database setup successfully.");
                break; // Exit loop once a successful connection is established
            } else {
                System.out.println("Failed to connect to the database. Please check your credentials and try again.");
            }
        }

        // Connecting the user to the database with the specific database URL
        String databaseUrl = DB_URL + "aircraft_db";
        service = new AircraftService(databaseUrl, DB_USER, DB_PASSWORD);
        controller = new AircraftController(service, scanner);
        uiManager = new UserInterfaceManager(controller);

        uiManager.initializeUI();
        uiManager.getMainFrame().setVisible(true);
        uiManager.getMainFrame().toFront();
        uiManager.getMainFrame().requestFocus();

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
                    System.out.print("Enter discrepancies: ");
                    String discrepancies = scanner.nextLine();
                    try {
                        if (dateString == null || dateString.trim().isEmpty()) {
                            System.out.println("Date string cannot be null or empty.");
                            break;
                        }
                        Date lastInspectionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        controller.addAircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
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
                    System.out.print("Enter new model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter new serial number: ");
                    String newSerialNumber = scanner.nextLine();
                    System.out.print("Enter new maintenance status (Active/Inactive): ");
                    maintenanceStatus = scanner.nextLine();
                    System.out.print("Enter new mission history: ");
                    missionHistory = scanner.nextLine();
                    System.out.print("Enter new pilot assignment: ");
                    pilotAssignment = scanner.nextLine();
                    System.out.print("Enter new last inspection date (yyyy-MM-dd): ");
                    dateString = scanner.nextLine();
                    System.out.print("Enter new discrepancies: ");
                    discrepancies = scanner.nextLine();
                    try {
                        Date lastInspectionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        controller.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
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

    // getter methods to access the private fields for the DB
    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    // Method to validate the connection and setup the database
    private static boolean validateConnectionAndSetup(String url, String user, String password) {
        String initialUrl = url;
        try (Connection connection = DriverManager.getConnection(initialUrl, user, password)) {
            // Create the database if it doesn't exist
            createDatabaseIfNotExists(connection);

            // Reconnect with the specific database URL
            String databaseUrl = initialUrl + "aircraft_db";
            try (Connection dbConnection = DriverManager.getConnection(databaseUrl, user, password)) {
                // Execute the use statement
                useDatabase(dbConnection);
                // Execute setup.sql if the database doesn't exist
                setupDatabase(dbConnection);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Method to create the database if it doesn't exist
    private static void createDatabaseIfNotExists(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS aircraft_db");
            System.out.println("Database 'aircraft_db' created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating database: " + e.getMessage());
        }
    }

    // Method to switch to the created database
    private static void useDatabase(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("USE aircraft_db");
            System.out.println("Switched to database 'aircraft_db'.");
        } catch (SQLException e) {
            System.out.println("Error switching to database: " + e.getMessage());
        }
    }

    // Method to setup the database using the setup.sql file
    private static void setupDatabase(Connection connection) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("resources/setup.sql");
        if (inputStream == null) {
            System.out.println("Error: setup.sql file not found in the classpath.");
            return;
        }

        try (Statement stmt = connection.createStatement();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("--")) {
                    continue; // Skip empty lines and comments
                }
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    // debugging line
                    System.out.println("Executing SQL: " + sql.toString());
                    stmt.execute(sql.toString());
                    sql.setLength(0); // Reset the string builder for the next statement
                }
            }
            System.out.println("Database setup completed successfully.");
        } catch (IOException | SQLException e) {
            System.out.println("Error during database setup: " + e.getMessage());
        }
    }
}

