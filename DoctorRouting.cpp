#include <iostream>
#include <fstream>
#include <string>
#include "routing_structures.h"

using namespace std;

DoctorManager docManager;
HospitalMap hospital;

// Initialize hardcoded data (The "Map" and the "Staff")
void setupSystem() {
    // 1. Add Doctors
    docManager.addDoctor("D01", "Dr. Strange", "Neurology", true);
    docManager.addDoctor("D02", "Dr. House", "Diagnostics", true);
    docManager.addDoctor("D03", "Dr. Grey", "Surgery", false);
    docManager.addDoctor("D04", "Dr. Watson", "General", true);
    docManager.addDoctor("D05", "Dr. Who", "Cardiology", true);

    // Sort them immediately for display
    docManager.sortDoctorsByName();

    // 2. Build Hospital Graph (Map)
    // Connecting rooms with distances in meters
    hospital.addPath("Entrance", "Triage", 10);
    hospital.addPath("Triage", "Corridor A", 15);
    hospital.addPath("Corridor A", "General Ward", 20);
    hospital.addPath("Corridor A", "ICU", 40);
    hospital.addPath("Triage", "Corridor B", 20);
    hospital.addPath("Corridor B", "X-Ray", 30);
    hospital.addPath("Corridor B", "Pharmacy", 25);
    hospital.addPath("ICU", "Surgery", 15);
    hospital.addPath("General Ward", "Pharmacy", 50);
}

int main() {
    setupSystem();

    ifstream inputFile("../../data/routing_input.txt");
    ofstream outputFile("../../data/routing_output.txt");

    if (!inputFile.is_open()) return 0;

    string operation;
    getline(inputFile, operation);

    if (operation == "VIEW_DOCTORS") {
        docManager.displayDoctors(outputFile);
    }
    else if (operation == "ASSIGN") {
        string specialty;
        getline(inputFile, specialty);
        outputFile << docManager.assignDoctor(specialty);
    }
    else if (operation == "FIND_PATH") {
        string start, end;
        getline(inputFile, start);
        getline(inputFile, end);
        hospital.findShortestPath(start, end, outputFile);
    }

    inputFile.close();
    outputFile.close();
    return 0;
}