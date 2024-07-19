/*
 Danny Ken
 202430-CEN-3024C-31950
 7/10/2024
 The UserInterfaceManager class is responsible for creating and managing the graphical user interface (GUI)
 components of the Aircraft Database Management System (DMS) application.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
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
    private JTextField discrepanciesField; // Added field for discrepancies
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton displayButton;
    private JButton reportButton;
    private JButton uploadCSVButton;
    private JButton deleteAllButton;
    private AircraftController controller;

    /**
     * Constructs a new UserInterfaceManager
     *
     * @param controller The controller to use for aircraft operations.
     */
    public UserInterfaceManager(AircraftController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the user interface components.
     */
    public void initializeUI() {
        createMainFrame();
        createMenu();
        createFormPanel();
        createButtonsPanel();
        addInstructions();
    }

    /**
     * Creates the mainframe of the application
     */
    private void createMainFrame() {
        mainFrame = new JFrame("Aircraft Database Management System");
        mainFrame.setSize(1800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        // background color for main panel
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the menu bar with relevant options.
     */
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

    /**
     * Creates the form panel with labeled text fields and combo boxes.
     */
    private void createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10)); // Updated grid layout to include discrepancies field
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // white form panel
        formPanel.setBackground(Color.WHITE);

        // setting text blue for Model
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setForeground(Color.BLUE);
        formPanel.add(modelLabel);
        modelField = new JTextField();
        formPanel.add(modelField);

        // setting text blue for serial number
        JLabel serialNumberLabel = new JLabel("Serial Number:");
        serialNumberLabel.setForeground(Color.BLUE);
        formPanel.add(serialNumberLabel);
        serialNumberField = new JTextField();
        formPanel.add(serialNumberField);

        // setting text blue for maintenance status
        JLabel maintenanceStatusLabel = new JLabel("Maintenance Status:");
        maintenanceStatusLabel.setForeground(Color.BLUE);
        formPanel.add(maintenanceStatusLabel);
        maintenanceStatusField = new JComboBox<>(new String[]{"Active", "Inactive"});
        formPanel.add(maintenanceStatusField);

        // setting text blue for mission history
        JLabel missionHistoryLabel = new JLabel("Mission History:");
        missionHistoryLabel.setForeground(Color.BLUE);
        formPanel.add(missionHistoryLabel);
        missionHistoryField = new JTextField();
        formPanel.add(missionHistoryField);

        // setting text blue for pilot assignment
        JLabel pilotAssignmentLabel = new JLabel("Pilot Assignment:");
        pilotAssignmentLabel.setForeground(Color.BLUE);
        formPanel.add(pilotAssignmentLabel);
        pilotAssignmentField = new JTextField();
        formPanel.add(pilotAssignmentField);

        // setting text blue for last inspection date
        JLabel lastInspectionDateLabel = new JLabel("Last Inspection Date (yyyy-MM-dd):");
        lastInspectionDateLabel.setForeground(Color.BLUE);
        formPanel.add(lastInspectionDateLabel);
        lastInspectionDateField = new JTextField();
        formPanel.add(lastInspectionDateField);

        // setting text blue for discrepancies
        JLabel discrepanciesLabel = new JLabel("Discrepancies:");
        discrepanciesLabel.setForeground(Color.BLUE);
        formPanel.add(discrepanciesLabel);
        discrepanciesField = new JTextField();
        formPanel.add(discrepanciesField);

        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the button panel.
     */
    private void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 8, 10, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonsPanel.setBackground(Color.GRAY);

        addButton = new JButton("Add Aircraft");
        // blue foreground color
        addButton.setForeground(Color.BLUE);
        removeButton = new JButton("Remove Aircraft");
        // blue foreground color
        removeButton.setForeground(Color.BLUE);
        updateButton = new JButton("Update Aircraft");
        // blue foreground color
        updateButton.setForeground(Color.BLUE);
        displayButton = new JButton("Display All Aircraft");
        // blue foreground color
        displayButton.setForeground(Color.BLUE);
        reportButton = new JButton("Generate Maintenance Report");
        // blue foreground color
        reportButton.setForeground(Color.BLUE);
        uploadCSVButton = new JButton("Upload CSV");
        // blue foreground color
        uploadCSVButton.setForeground(Color.BLUE);
        deleteAllButton = new JButton("Delete All Aircraft Data");
        // blue foreground color
        deleteAllButton.setForeground(Color.BLUE);

        //setupDBButton = new JButton("Setup Database");

        addButton.addActionListener(new ButtonClickListener());
        removeButton.addActionListener(new ButtonClickListener());
        updateButton.addActionListener(new ButtonClickListener());
        displayButton.addActionListener(new ButtonClickListener());
        reportButton.addActionListener(new ButtonClickListener());
        uploadCSVButton.addActionListener(new ButtonClickListener());
        deleteAllButton.addActionListener(new ButtonClickListener());
        //setupDBButton.addActionListener(new ButtonClickListener());

        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(displayButton);
        buttonsPanel.add(reportButton);
        buttonsPanel.add(uploadCSVButton);
        buttonsPanel.add(deleteAllButton);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds instructions for the user.
     */
    private void addInstructions() {
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BorderLayout());
        instructionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructionsPanel.setBackground(Color.LIGHT_GRAY);
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
        instructions.setForeground(Color.DARK_GRAY);
        instructionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructionsPanel.add(instructions, BorderLayout.CENTER);
        mainPanel.add(instructionsPanel, BorderLayout.NORTH);
    }

    /**
     * Gets the main frame of the application.
     *
     * @return The main frame.
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Handles adding an aircraft.
     */
    private void handleAddAircraft() {
        try {
            String model = modelField.getText().trim();
            String serialNumber = serialNumberField.getText().trim();
            String maintenanceStatus = (String) maintenanceStatusField.getSelectedItem();
            String missionHistory = missionHistoryField.getText().trim();
            String pilotAssignment = pilotAssignmentField.getText().trim();
            String dateString = lastInspectionDateField.getText().trim();
            String discrepancies = discrepanciesField.getText().trim(); // Added discrepancies

            // Validates that all fields are filled out
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

            // Add the aircraft using the controller
            controller.addAircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
            JOptionPane.showMessageDialog(mainFrame, "Aircraft added successfully.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    /**
     * Handles removing an aircraft.
     */
    private void handleRemoveAircraft() {
        try {
            String serialNumber = serialNumberField.getText().trim();
            if (serialNumber.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Serial number must be provided to remove an aircraft.");
                return;
            }
            // Remove the aircraft using the controller
            controller.removeAircraft(serialNumber);
            JOptionPane.showMessageDialog(mainFrame, "Aircraft removed successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    /**
     * Handles updating an aircraft.
     */
    private void handleUpdateAircraft() {
        try {
            String serialNumber = serialNumberField.getText().trim();
            String model = modelField.getText().trim();
            String newSerialNumber = serialNumberField.getText().trim();
            String maintenanceStatus = (String) maintenanceStatusField.getSelectedItem();
            String missionHistory = missionHistoryField.getText().trim();
            String pilotAssignment = pilotAssignmentField.getText().trim();
            String dateString = lastInspectionDateField.getText().trim();
            String discrepancies = discrepanciesField.getText().trim(); // Added discrepancies

            // Validates that the serial number and date are provided
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

            // Updates the aircraft using the controller
            controller.updateAircraft(serialNumber, model, newSerialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
            JOptionPane.showMessageDialog(mainFrame, "Aircraft updated successfully.");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "An unexpected error occurred: " + ex.getMessage());
        }
    }

    /**
     * Handles displaying all aircraft.
     */
    private void handleDisplayAllAircraft() {
        try {
            List<Aircraft> aircraftList = controller.getAllAircraft();
            if (aircraftList.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "No aircraft found.");
            } else {
                StringBuilder aircraftDetails = new StringBuilder("<html>");
                for (Aircraft aircraft : aircraftList) {
                    aircraftDetails
                            .append("<b>MODEL:</b> ").append(aircraft.getModel()).append("<br/><br/>");
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

    /**
     * Handles generating a maintenance report.
     */
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
                    .append("<b>DISCREPANCIES:</b> ").append(aircraft.getDiscrepancies()).append("<br/>") // Added discrepancies
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

    /**
     * Handles uploading a CSV file.
     */
    private void handleUploadCSV() {
        JFileChooser fileChooser = new JFileChooser();

        // Add file filter to accept only CSV files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String fileName = f.getName().toLowerCase();
                return fileName.endsWith(".csv");
            }

            @Override
            public String getDescription() {
                return "CSV Files (*.csv)";
            }
        });

        int result = fileChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
                JOptionPane.showMessageDialog(mainFrame, "Invalid file format. Please select a CSV file.");
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    String[] values = line.split(",");
                    if (values.length != 7) { // Updated to check for 7 values including discrepancies
                        JOptionPane.showMessageDialog(mainFrame, "Invalid CSV format. Each line must have 7 values.");
                        continue;
                    }
                    String model = values[0].trim();
                    String serialNumber = values[1].trim();
                    String maintenanceStatus = values[2].trim();
                    String missionHistory = values[3].trim();
                    String pilotAssignment = values[4].trim();
                    String dateString = values[5].trim();
                    String discrepancies = values[6].trim();
                    try {
                        Date lastInspectionDate = parseDate(dateString);
                        controller.addAircraft(model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate, discrepancies);
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid date format in CSV for date: " + dateString + ". Please use yyyy-MM-dd format.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainFrame, "An error occurred while adding aircraft: " + ex.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(mainFrame, "CSV upload completed successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainFrame, "An error occurred while reading the CSV file: " + ex.getMessage());
            }
        }
    }

    /**
     * Handles deleting all aircraft data.
     */
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

    /**
     * Parses a date string.
     *
     * @param dateString The date string to parse.
     * @return The parsed Date object.
     * @throws ParseException If the date string is invalid.
     */
    private Date parseDate(String dateString) throws ParseException {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new ParseException("Date string cannot be null or empty.", 0);
        }
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }

    /**
     * Inner class to handle click events.
     */
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
