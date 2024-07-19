/*
 Danny Ken
 202430-CEN-3024C-31950
 7/10/2024
 The Main class serves as the entry point for the Aircraft Database Management System application.
 It provides a simple command-line interface to interact with the system.
 */
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

    /**
     * The main method serves as the entry point for the Aircraft Database Management System application.
     * It provides a simple command-line interface to interact with the system.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner credentialScanner = new Scanner(System.in);

        // Continuously prompt for MySQL credentials until a successful connection is established
        while (true) {
            // Prompting the user to enter the MySQL server URL
            System.out.print("Enter MySQL Server URL (e.g., jdbc:mysql://127.0.0.1:3306/ or " +
                    "jdbc:mysql://localhost:3306/ ): ");
            DB_URL = credentialScanner.nextLine();

            // Prompting the user to enter the MySQL username
            System.out.print("Enter MySQL User: ");
            DB_USER = credentialScanner.nextLine();

            // Prompting the user to enter the MySQL password
            System.out.print("Enter MySQL Password: ");
            DB_PASSWORD = credentialScanner.nextLine();

            // Attempt to validate the connection and set up the database if necessary
            if (validateConnectionAndSetup(DB_URL, DB_USER, DB_PASSWORD)) {
                System.out.println("Connection established and database setup successfully.");
                break; // Exit loop once a successful connection is established
            } else {
                System.out.println("Failed to connect to the database. Please check your credentials and try again.");
            }
        }

        // Define the specific database URL
        String databaseUrl = DB_URL + "aircraft_db";
        // Initialize the AircraftService with the database URL and credentials
        service = new AircraftService(databaseUrl, DB_USER, DB_PASSWORD);

        // Initialize the AircraftController with the service and scanner
        controller = new AircraftController(service, scanner);

        // Initialize the UserInterfaceManager with the controller
        uiManager = new UserInterfaceManager(controller);

        // Sets up the user interface
        uiManager.initializeUI();
        uiManager.getMainFrame().setVisible(true);
        uiManager.getMainFrame().toFront();
        uiManager.getMainFrame().requestFocus();

        // Main loop to display options and handle user input.
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

            // Handle user input based on the selected option.
            switch (choice) {
                case 1:
                    // Prompt the user to enter details for a new aircraft.
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
                        // Validate and parse the date string
                        if (dateString == null || dateString.trim().isEmpty()) {
                            System.out.println("Date string cannot be null or empty.");
                            break;
                        }
                        Date lastInspectionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        // Add the aircraft using the controller
                        controller.addAircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
                        System.out.println("Aircraft added successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                    }
                    break;
                case 2:
                    // Prompt the user to enter the serial number of the aircraft to remove
                    System.out.print("Enter serial number: ");
                    serialNumber = scanner.nextLine();
                    // Remove the aircraft using the controller
                    controller.removeAircraft(serialNumber);
                    System.out.println("Aircraft removed successfully.");
                    break;
                case 3:
                    // Prompt the user to enter updated details for an existing aircraft
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
                    // Validate and parse the new date string
                    try {
                        Date lastInspectionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        controller.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
                        System.out.println("Aircraft updated successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                    }
                    break;
                case 4:
                    // Display all aircraft using the controller
                    controller.displayAllAircraft();
                    break;
                case 5:
                    // Prompt the user to enter the serial number of the aircraft to generate a maintenance report
                    System.out.print("Enter serial number: ");
                    serialNumber = scanner.nextLine();
                    controller.generateMaintenanceReport(serialNumber);
                    break;
                case 6:
                    // Exit the application
                    System.out.println("Exiting...");
                    uiManager.getMainFrame().dispose();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Other methods (getDbUrl, getDbUser, getDbPassword, validateConnectionAndSetup, createDatabaseIfNotExists, useDatabase, setupDatabase)
    /**
     * Gets the database URL.
     *
     * @return The database URL.
     */
    public static String getDbUrl() {
        return DB_URL;
    }

    /**
     * Gets the database user.
     *
     * @return The database user.
     */
    public static String getDbUser() {
        return DB_USER;
    }

    /**
     * Gets the database password.
     *
     * @return The database password.
     */
    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    /**
     * Validates the connection and sets up the database.
     *
     * @param url The URL of the database.
     * @param user The database user.
     * @param password The database password.
     * @return true if the connection is successful and the database is set up, false otherwise.
     */
    private static boolean validateConnectionAndSetup(String url, String user, String password) {
        String initialUrl = url;
        try (Connection connection = DriverManager.getConnection(initialUrl, user, password)) {
            // Create the database if it doesn't exist
            createDatabaseIfNotExists(connection);

            // Reconnect with the specific database URL
            String databaseUrl = initialUrl + "aircraft_db";
            try (Connection dbConnection = DriverManager.getConnection(databaseUrl, user, password)) {
                // Switch to the created database
                useDatabase(dbConnection);
                // Set up the database schema using the setup.sql file
                setupDatabase(dbConnection);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Creates the database if it doesn't exist.
     *
     * @param connection The connection to the database.
     */
    private static void createDatabaseIfNotExists(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS aircraft_db");
            System.out.println("Database 'aircraft_db' created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating database: " + e.getMessage());
        }
    }

    /**
     * Switches to the created database.
     *
     * @param connection The connection to the database.
     */
    private static void useDatabase(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("USE aircraft_db");
            System.out.println("Switched to database 'aircraft_db'.");
        } catch (SQLException e) {
            System.out.println("Error switching to database: " + e.getMessage());
        }
    }

    /**
     * Sets up the database using the setup.sql file.
     *
     * @param connection The connection to the database.
     */
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
                    // Execute the SQL statement
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

