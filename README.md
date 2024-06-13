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

**PLEASE NOTE:** 

In Intellij, after you create your own database in MySQL, you will have to manually point the project to the .jar file that is already included under lib. Please follow these instructions to get the database connected to the Java program. 

1. Go to ---> Main Menu

![image](https://github.com/danny9447537/Ken_Danny_DMS/assets/92329761/ada74998-b5d0-4110-9a47-672721e7721c)

2. Navigate to --> Project Structure
   
![image](https://github.com/danny9447537/Ken_Danny_DMS/assets/92329761/81395906-ca8d-4cff-a24b-15b9257ca33c)

3. Click on ---> Modules --> Dependencies --> '+' sign to add the .jar file --> navigate to 

![image](https://github.com/danny9447537/Ken_Danny_DMS/assets/92329761/adb32150-d8a5-4014-b5e0-774356f94133)

4. Navigate to the lib folder in this project to find mysql-connector-j-8.4.0.jar and add it as a dependency

![image](https://github.com/danny9447537/Ken_Danny_DMS/assets/92329761/49784262-7588-4402-97b6-ab7e56e0161c)

5. Click 'Apply' and then 'OK'

6. Run the Java program. 


**Running the Application**
1. Open the project in your preferred IDE.
2. Ensure all dependencies are resolved.
3. Run the Main class to start the application.
   

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

**Contact**

For any questions or inquiries, please contact Danny Ken at dken@mail.valenciacollege.edu


   
