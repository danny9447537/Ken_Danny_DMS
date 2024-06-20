**Aircraft Database Management System (DMS)**

**Overview**

The Aircraft Database Management System (DMS) is a project designed to efficiently handle aircraft data within the U.S. Air Force. The system focuses on optimizing the processes of storing, retrieving, and managing aircraft information, including maintenance activities, mission history, pilot assignments, and more. This README provides an overview of the project structure, setup instructions, and usage guidelines.

**Project Structure**

**The project is organized into several key components:**

1.	Models: Contains the data model classes.
Aircraft: Represents an aircraft entity with various attributes such as model, serial number, maintenance status, mission history, pilot assignment, and last inspection date.
2.	Repository: Handles database operations.
AircraftRepository: Manages CRUD operations for the Aircraft entity.
3.	Service: Contains business logic for managing aircraft.
AircraftService: Provides methods for adding, removing, updating, retrieving aircraft data, and generating maintenance reports.
4.	Controller: Manages user interactions.
AircraftController: Provides methods to add, remove, update, display aircraft, and generate maintenance reports.
5.	User Interface: Manages the graphical user interface components.
UserInterfaceManager: Creates and manages the GUI components of the application.
6.	Main: Entry point for the application.
7.	AircraftRepositoryTest: Class specifically made for unit testing.

**Main:**

Provides a command-line interface and initializes the GUI.

**Setup Instructions**

Prerequisites
•	Java Development Kit (JDK) 8 or higher
•	MySQL Database
•	JDBC Driver for MySQL
•	IDE such as IntelliJ IDEA or Eclipse


**Database Setup**
1. The program will prompt the user to enter in their credentials.
2. If YOUR hostname connection starts with an ip address, then you will use that ip address within the URL in this location jdbc:mysql://"YOUR IP ADDRESS":3306/.
4. If YOUR hostname is just the default localhost, then you will use the name "localhost" within the URL in this location jdbc:mysql://localhost:3306/
5. It should be either jdbc:mysql://localhost:3306/ OR jdbc:mysql://127.0.0.1:3306/

**Running the Application**
1. Open the project in your preferred IDE.
2. Ensure all dependencies are resolved.
3. Run the Main.java class to start the application.
   

**Usage**

Command-Line Interface (CLI)

Upon running the application, you will be presented with a CLI menu with the following options:
1.	Add Aircraft
2.	Remove Aircraft
3.	Update Aircraft
4.	Display All Aircraft
5.	Generate Maintenance Report
6.	Exit

**Graphical User Interface (GUI)**

The GUI provides a more user-friendly way to interact with the application. It includes options for:
•	Adding, removing, updating aircraft
•	Displaying all aircraft
•	Generating maintenance reports
•	Uploading aircraft data via CSV
•	Deleting all aircraft data

**CSV Upload**

The CSV file should follow this format if you were to create your own CSV and add your own aircraft infromation:
Copy code
model,serialNumber,maintenanceStatus,missionHistory,pilotAssignment,lastInspectionDate

**Sample CSV Data**

Sample CSV data that can be used to generate data into the MySQL database can be found within Ken_Danny_DMS-master/sample data/sample_aircraft_data.csv
To use this sample data, you simply have to run the Java program and click 'Upload CSV' to upload aircraft information. 

**Error Handling**

The application includes basic error handling for common issues such as invalid input formats and database connection errors. Error messages will be displayed to guide the user.

**AircraftRepositoryTest Class**

This class is used for unit testing. There are multiple methods to test the CRUD functinality of the AircraftRepository class within the test class. 

**Contact**

For any questions or inquiries, please contact Danny Ken at dken@mail.valenciacollege.edu


   
