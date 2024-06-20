-- noinspection SqlNoDataSourceInspectionForFile

-- setup.sql
CREATE DATABASE IF NOT EXISTS aircraft_db;
USE aircraft_db;

CREATE TABLE IF NOT EXISTS aircraft (
    model VARCHAR(100) NOT NULL,
    serialNumber VARCHAR(100) PRIMARY KEY,
    maintenanceStatus VARCHAR(50) NOT NULL,
    missionHistory TEXT,
    pilotAssignment VARCHAR(100),
    lastInspectionDate DATE
);

-- clear existing data in the aircraft table
DELETE FROM aircraft;

-- Insert initial data
INSERT INTO aircraft (model, serialNumber, maintenanceStatus, missionHistory, pilotAssignment, lastInspectionDate) VALUES
    ('MODEL X', 'SN123456', 'Active', 'Test Mission', 'Pilot A', '2024-06-01'),
    ('MODEL Y', 'SN123457', 'Inactive', 'Mission 1', 'Pilot B', '2024-05-15'),
    ('MODEL Z', 'SN123458', 'Active', 'Mission 2', 'Pilot C', '2024-04-20'),
    ('MODEL A', 'SN123459', 'Inactive', 'Mission 3', 'Pilot D', '2024-03-30'),
    ('MODEL B', 'SN123460', 'Active', 'Mission 4', 'Pilot E', '2024-02-25'),
    ('MODEL C', 'SN123461', 'Inactive', 'Mission 5', 'Pilot F', '2024-01-18'),
    ('MODEL D', 'SN123462', 'Active', 'Mission 6', 'Pilot G', '2023-12-10'),
    ('MODEL E', 'SN123463', 'Inactive', 'Mission 7', 'Pilot H', '2023-11-05'),
    ('MODEL F', 'SN123464', 'Active', 'Mission 8', 'Pilot I', '2023-10-22'),
    ('MODEL G', 'SN123465', 'Inactive', 'Mission 9', 'Pilot J', '2023-09-14'),
    ('MODEL H', 'SN123466', 'Active', 'Mission 10', 'Pilot K', '2023-08-08');