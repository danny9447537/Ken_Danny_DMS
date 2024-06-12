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

**Main:**

Provides a command-line interface and initializes the GUI.

**Setup Instructions**

Prerequisites
•	Java Development Kit (JDK) 8 or higher
•	MySQL Database
•	JDBC Driver for MySQL
•	IDE such as IntelliJ IDEA or Eclipse


**Database Setup**

1.	Install MySQL and create a database named aircraft_db.
2.	Create a table named aircraft with the following schema:
   
![image](https://github.com/danny9447537/Ken_Danny_DMS/assets/92329761/8fe7bcec-b78d-4604-ad5b-81740dcf6b9e)

4.	Update the database connection details in the Main class.

![image](https://github.com/danny9447537/Ken_Danny_DMS/assets/92329761/4de7ef59-e840-4f0a-95bb-6c9172af1342)


**Running the Application**
1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Ensure all dependencies are resolved.
4. Run the Main class to start the application.
   

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

The CSV file should follow this format:
Copy code
model,serialNumber,maintenanceStatus,missionHistory,pilotAssignment,lastInspectionDate

**Error Handling**

The application includes basic error handling for common issues such as invalid input formats and database connection errors. Error messages will be displayed to guide the user.

**Testing**

The project includes a comprehensive test plan covering functional, performance, security, reliability, usability, compatibility, integration, data integrity, scalability, and regression testing. Refer to the Software Test Plan document for detailed information.

**Contact**

For any questions or inquiries, please contact Danny Ken at dken@mail.valenciacollege.edu


   
