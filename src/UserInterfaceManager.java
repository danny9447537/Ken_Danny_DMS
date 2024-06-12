/*
 Danny Ken
 202430-CEN-3024C-31950
 6/5/2024
 The UserInterfaceManager class is responsible for creating and managing the graphical user interface (GUI)
 components of the Aircraft Database Management System (DMS) application.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserInterfaceManager {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JTextField modelField;
    private JTextField serialNumberField;
    private JComboBox<String> maintenanceStatusField;
    private JTextField missionHistoryField;
    private JTextField pilotAssignmentField;
    private JTextField lastInspectionDateField;
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton displayButton;
    private JButton reportButton;
    private JButton uploadCSVButton;
    private JButton deleteAllButton;

    private AircraftController controller;
    /*
     Constructor for the UserInterfaceManager class. Initializes the AircraftController.
     */
    public UserInterfaceManager(AircraftController controller) {
        this.controller = controller;
    }

    // Method to initialize the user interface components.
    public void initializeUI() {
        createMainFrame();
        createMenu();
        createFormPanel();
        createButtonsPanel();
        addInstructions();
    }

    // Method to create the main frame of the application.
    private void createMainFrame() {
        mainFrame = new JFrame("Aircraft Database Management System");
        mainFrame.setSize(1800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    // Method to create the menu bar with relevant options.
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        // Menu items for all functionalities
        JMenuItem addMenuItem = new JMenuItem("Add Aircraft");
        addMenuItem.addActionListener(e -> handleAddAircraft());

        JMenuItem removeMenuItem = new JMenuItem("Remove Aircraft");
        removeMenuItem.addActionListener(e -> handleRemoveAircraft());

        JMenuItem updateMenuItem = new JMenuItem("Update Aircraft");
        updateMenuItem.addActionListener(e -> handleUpdateAircraft());

        JMenuItem displayMenuItem = new JMenuItem("Display All Aircraft");
        displayMenuItem.addActionListener(e -> handleDisplayAllAircraft());

        JMenuItem reportMenuItem = new JMenuItem("Generate Maintenance Report");
        reportMenuItem.addActionListener(e -> handleGenerateMaintenanceReport());

        JMenuItem uploadCSVMenuItem = new JMenuItem("Upload CSV");
        uploadCSVMenuItem.addActionListener(e -> handleUploadCSV());

        JMenuItem deleteAllMenuItem = new JMenuItem("Delete All Aircraft Data");
        deleteAllMenuItem.addActionListener(e -> handleDeleteAllAircraft());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));

        menu.add(addMenuItem);
        menu.add(removeMenuItem);
        menu.add(updateMenuItem);
        menu.add(displayMenuItem);
        menu.add(reportMenuItem);
        menu.add(uploadCSVMenuItem);
        menu.add(deleteAllMenuItem);
        menu.add(exitMenuItem);

        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);
    }

    // Method to create the form panel with labeled text fields and combo boxes.
    private void createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Model:"));
        modelField = new JTextField();
        formPanel.add(modelField);

        formPanel.add(new JLabel("Serial Number:"));
        serialNumberField = new JTextField();
        formPanel.add(serialNumberField);

        formPanel.add(new JLabel("Maintenance Status:"));
        maintenanceStatusField = new JComboBox<>(new String[]{"Active", "Inactive"});
        formPanel.add(maintenanceStatusField);

        formPanel.add(new JLabel("Mission History:"));
        missionHistoryField = new JTextField();
        formPanel.add(missionHistoryField);

        formPanel.add(new JLabel("Pilot Assignment:"));
        pilotAssignmentField = new JTextField();
        formPanel.add(pilotAssignmentField);

        formPanel.add(new JLabel("Last Inspection Date (yyyy-MM-dd):"));
        lastInspectionDateField = new JTextField();
        formPanel.add(lastInspectionDateField);

        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    // Method to create the buttons panel.
    private void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 7, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addButton = new JButton("Add Aircraft");
        removeButton = new JButton("Remove Aircraft");
        updateButton = new JButton("Update Aircraft");
        displayButton = new JButton("Display All Aircraft");
        reportButton = new JButton("Generate Maintenance Report");
        uploadCSVButton = new JButton("Upload CSV");
        deleteAllButton = new JButton("Delete All Aircraft Data");

        addButton.addActionListener(new ButtonClickListener());
        removeButton.addActionListener(new ButtonClickListener());
        updateButton.addActionListener(new ButtonClickListener());
        displayButton.addActionListener(new ButtonClickListener());
        reportButton.addActionListener(new ButtonClickListener());
        uploadCSVButton.addActionListener(new ButtonClickListener());
        deleteAllButton.addActionListener(new ButtonClickListener());

        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(displayButton);
        buttonsPanel.add(reportButton);
        buttonsPanel.add(uploadCSVButton);
        buttonsPanel.add(deleteAllButton);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    // Method to add instructions for the user.
    private void addInstructions() {
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BorderLayout());
        instructionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel instructions = new JLabel("<html>"
                + "<b>Instructions:</b><br>"
                + "1. Add Aircraft: Enter all fields and click 'Add Aircraft'.<br>"
                + "2. Remove Aircraft: Enter the serial number and click 'Remove Aircraft'.<br>"
                + "3. Update Aircraft: Enter the serial number, new maintenance status, and last inspection date, then click 'Update Aircraft'.<br>"
                + "4. Display All Aircraft: Click 'Display All Aircraft' to view all aircraft.<br>"
                + "5. Generate Maintenance Report: Enter the serial number and click 'Generate Maintenance Report'.<br>"
                + "6. Upload CSV: Click 'Upload CSV' to upload a CSV file with aircraft details.<br>"
                + "7. Delete All Aircraft Data: Click 'Delete All Aircraft Data' to remove all aircraft records from the database."
                + "</html>");
        instructionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructionsPanel.add(instructions, BorderLayout.CENTER);
        mainPanel.add(instructionsPanel, BorderLayout.NORTH);
    }

    // Method to get the main frame of the application.
    public JFrame getMainFrame() {
        return mainFrame;
    }

    // Method to handle adding aircraft.
    private void handleAddAircraft() {
        try {
            String model = modelField.getText().trim();
            String serialNumber = serialNumberField.getText().trim();
            String maintenanceStatus = (String) maintenanceStatusField.getSelectedItem();
            String missionHistory = missionHistoryField.getText().trim();
            String pilotAssignment = pilotAssignmentField.getText().trim();
            String dateString = lastInspectionDateField.getText().trim();

            // Validate that all fields are filled out
            if (model.isEmpty() || serialNumber.isEmpty() || missionHistory.isEmpty() || pilotAssignment.isEmpty() || dateString.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "All fields must be filled out to add an aircraft.");
                return;
            }

            // Parse and validate the date
            Date lastInspectionDate = parseDate(dateString);
            if (lastInspectionDate.after(new Date())) {
                JOptionPane.showMessageDialog(mainFrame, "Last Inspection Date cannot be in the future.");
                return;
            }

            // Add the aircraft
            controller.addAircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate);
            JOptionPane.showMessageDialog(mainFrame, "Aircraft added successfully.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    // Method to handle removing aircraft.
    private void handleRemoveAircraft() {
        try {
            String serialNumber = serialNumberField.getText().trim();
            if (serialNumber.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Serial number must be provided to remove an aircraft.");
                return;
            }
            controller.removeAircraft(serialNumber);
            JOptionPane.showMessageDialog(mainFrame, "Aircraft removed successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    // Method to handle updating aircraft.
    private void handleUpdateAircraft() {
        try {
            String serialNumber = serialNumberField.getText().trim();
            String model = modelField.getText().trim();
            String newSerialNumber = serialNumberField.getText().trim();
            String maintenanceStatus = (String) maintenanceStatusField.getSelectedItem();
            String missionHistory = missionHistoryField.getText().trim();
            String pilotAssignment = pilotAssignmentField.getText().trim();
            String dateString = lastInspectionDateField.getText().trim();

            // Validate that the serial number and date are provided
            if (serialNumber.isEmpty() || dateString.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Serial number and date must be provided to update an aircraft.");
                return;
            }

            // Parse and validate the date
            Date lastInspectionDate = parseDate(dateString);
            if (lastInspectionDate.after(new Date())) {
                JOptionPane.showMessageDialog(mainFrame, "Last Inspection Date cannot be in the future.");
                return;
            }

            // Update the aircraft
            controller.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate);
            JOptionPane.showMessageDialog(mainFrame, "Aircraft updated successfully.");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    // Method to handle displaying all aircraft.
    private void handleDisplayAllAircraft() {
        try {
            List<Aircraft> aircraftList = controller.getAllAircraft();
            if (aircraftList.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "No aircraft found.");
            } else {
                StringBuilder aircraftDetails = new StringBuilder("<html>");
                for (Aircraft aircraft : aircraftList) {
                    aircraftDetails
                            .append("<b>MODEL:</b> ").append(aircraft.getModel()).append("<br/>")
                            .append("<b>SERIAL NUMBER:</b> ").append(aircraft.getSerialNumber()).append("<br/>")
                            .append("<b>MAINTENANCE STATUS:</b> ").append(aircraft.getMaintenanceStatus()).append("<br/>")
                            .append("<b>MISSION HISTORY:</b> ").append(aircraft.getMissionHistory()).append("<br/>")
                            .append("<b>PILOT ASSIGNMENT:</b> ").append(aircraft.getPilotAssignment()).append("<br/>")
                            .append("<b>LAST INSPECTION DATE:</b> ").append(aircraft.getLastInspectionDate()).append("<br/><br/>");
                }
                aircraftDetails.append("</html>");

                // Displaying in a pop-up window
                JEditorPane textPane = new JEditorPane("text/html", aircraftDetails.toString());
                textPane.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textPane);
                scrollPane.setPreferredSize(new Dimension(800, 600));
                JOptionPane.showMessageDialog(mainFrame, scrollPane, "All Aircraft", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    // Method to handle generating a maintenance report.
    private void handleGenerateMaintenanceReport() {
        try {
            // Get all aircraft to display in a selection dialog
            List<Aircraft> aircraftList = controller.getAllAircraft();
            if (aircraftList.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "No aircraft found.");
                return;
            }

            // Create a selection dialog to choose an aircraft
            String[] aircraftOptions = new String[aircraftList.size()];
            for (int i = 0; i < aircraftList.size(); i++) {
                aircraftOptions[i] = aircraftList.get(i).getSerialNumber() + " - " + aircraftList.get(i).getModel();
            }

            String selectedAircraft = (String) JOptionPane.showInputDialog(mainFrame,
                    "Select an aircraft to generate a maintenance report:",
                    "Generate Maintenance Report",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    aircraftOptions,
                    aircraftOptions[0]);

            if (selectedAircraft == null || selectedAircraft.trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "No aircraft selected.");
                return;
            }

            // Extract the serial number from the selected option
            String serialNumber = selectedAircraft.split(" - ")[0];
            Aircraft aircraft = controller.getAircraftBySerialNumber(serialNumber);

            if (aircraft == null) {
                JOptionPane.showMessageDialog(mainFrame, "Selected aircraft not found.");
                return;
            }

            // Generate and display the maintenance report
            StringBuilder report = new StringBuilder();
            report.append("<html>")
                    .append("<h2>Air Force Database Management System Report(s)</h2>")
                    .append("<b>MODEL:</b> ").append(aircraft.getModel()).append("<br/>")
                    .append("<b>SERIAL NUMBER:</b> ").append(aircraft.getSerialNumber()).append("<br/>")
                    .append("<b>MAINTENANCE STATUS:</b> ").append(aircraft.getMaintenanceStatus()).append("<br/>")
                    .append("<b>MISSION HISTORY:</b> ").append(aircraft.getMissionHistory()).append("<br/>")
                    .append("<b>PILOT ASSIGNMENT:</b> ").append(aircraft.getPilotAssignment()).append("<br/>")
                    .append("<b>LAST INSPECTION DATE:</b> ").append(aircraft.getLastInspectionDate()).append("<br/>")
                    .append("</html>");

            JEditorPane textPane = new JEditorPane("text/html", report.toString());
            textPane.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textPane);
            scrollPane.setPreferredSize(new Dimension(800, 600));
            JOptionPane.showMessageDialog(mainFrame, scrollPane, "Maintenance Report", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    // Method to handle uploading a CSV file.
    private void handleUploadCSV() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    // Skip the header row
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    String[] values = line.split(",");
                    if (values.length != 6) {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid CSV format. Each line must have 6 values.");
                        continue;
                    }
                    String model = values[0].trim();
                    String serialNumber = values[1].trim();
                    String maintenanceStatus = values[2].trim();
                    String missionHistory = values[3].trim();
                    String pilotAssignment = values[4].trim();
                    String dateString = values[5].trim();
                    try {
                        // Debugging line
                        System.out.println("Attempting to parse date: " + dateString);
                        Date lastInspectionDate = parseDate(dateString);
                        controller.addAircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate);
                        // Success message
                        System.out.println("Successfully parsed and added aircraft with date: " + dateString);
                    } catch (ParseException ex) {
                        // Failure message
                        System.out.println("Failed to parse date: " + dateString);
                        JOptionPane.showMessageDialog(mainFrame, "Invalid date format in CSV for date: " + dateString + ". Please use yyyy-MM-dd format.");
                    } catch (Exception ex) {
                        // Handle duplicate entries or other errors gracefully
                        // Error message
                        System.out.println("Error while adding aircraft with date: " + dateString + ". " + ex.getMessage());
                        JOptionPane.showMessageDialog(mainFrame, "An error occurred while adding aircraft: " + ex.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(mainFrame, "CSV upload completed successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainFrame, "An error occurred while reading the CSV file: " + ex.getMessage());
            }
        }
    }

    // Method to handle deleting all aircraft data.
    private void handleDeleteAllAircraft() {
        int confirmation = JOptionPane.showConfirmDialog(mainFrame,
                "Are you sure you want to delete all aircraft data?",
                "Confirm Delete All",
                JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                controller.deleteAllAircraft();
                JOptionPane.showMessageDialog(mainFrame, "All aircraft data deleted successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
            }
        }
    }

    // Method to parse a date string.
    private Date parseDate(String dateString) throws ParseException {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new ParseException("Date string cannot be null or empty.", 0);
        }
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }

    // Inner class to handle button click events.
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                handleAddAircraft();
            } else if (e.getSource() == removeButton) {
                handleRemoveAircraft();
            } else if (e.getSource() == updateButton) {
                handleUpdateAircraft();
            } else if (e.getSource() == displayButton) {
                handleDisplayAllAircraft();
            } else if (e.getSource() == reportButton) {
                handleGenerateMaintenanceReport();
            } else if (e.getSource() == uploadCSVButton) {
                handleUploadCSV();
            } else if (e.getSource() == deleteAllButton) {
                handleDeleteAllAircraft();
            }
        }
    }
}
