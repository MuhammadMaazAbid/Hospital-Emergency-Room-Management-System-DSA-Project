#include <iostream>
#include <fstream>
#include <sstream>
#include "data_structures.h"

using namespace std;

HashTable patientTable;
AVLTree patientTree;

// File to store all patient data persistently
const string DATA_FILE = "../../data/patients_database.txt";

// Load existing patients from file
void loadPatientsFromFile() {
    ifstream dataFile(DATA_FILE);
    if (!dataFile.is_open()) {
        return; // File doesn't exist yet, that's okay
    }

    string line;
    while (getline(dataFile, line)) {
        if (line.empty()) continue;

        stringstream ss(line);
        string id, name, bloodGroup, contact, ageStr;
        int age;

        getline(ss, id, '|');
        getline(ss, name, '|');
        getline(ss, ageStr, '|');
        age = stoi(ageStr);
        getline(ss, bloodGroup, '|');
        getline(ss, contact);

        patientTable.insert(id, name, age, bloodGroup, contact);
        Patient* p = patientTable.search(id);
        if (p != nullptr) {
            patientTree.insert(p);
        }
    }

    dataFile.close();
}

// Save all patients to file
void savePatientsToFile() {
    ofstream dataFile(DATA_FILE);
    if (!dataFile.is_open()) {
        return;
    }

    patientTable.saveToFile(dataFile);
    dataFile.close();
}

void readInputFile() {
    // Load existing data first
    loadPatientsFromFile();

    ifstream inputFile("../../data/patient_input.txt");
    ofstream outputFile("../../data/patient_output.txt");

    if (!inputFile.is_open()) {
        outputFile << "ERROR: Could not open input file!" << endl;
        outputFile.close();
        return;
    }

    string operation;
    getline(inputFile, operation);

    if (operation == "ADD") {
        string id, name, bloodGroup, contact;
        int age;

        getline(inputFile, id);
        getline(inputFile, name);
        inputFile >> age;
        inputFile.ignore();
        getline(inputFile, bloodGroup);
        getline(inputFile, contact);

        // Check if patient already exists
        Patient* existing = patientTable.search(id);
        if (existing != nullptr) {
            outputFile << "ERROR: Patient ID already exists!" << endl;
        }
        else {
            patientTable.insert(id, name, age, bloodGroup, contact);

            Patient* p = patientTable.search(id);
            if (p != nullptr) {
                patientTree.insert(p);
                savePatientsToFile(); // Save after adding
                outputFile << "SUCCESS: Patient added!" << endl;
                outputFile << "ID: " << id << endl;
                outputFile << "Name: " << name << endl;
                outputFile << "Age: " << age << endl;
                outputFile << "Blood Group: " << bloodGroup << endl;
                outputFile << "Contact: " << contact << endl;
            }
        }

    }
    else if (operation == "SEARCH") {
        string id;
        getline(inputFile, id);

        Patient* p = patientTable.search(id);
        if (p != nullptr) {
            outputFile << "FOUND: Patient Details" << endl;
            outputFile << "ID: " << p->id << endl;
            outputFile << "Name: " << p->name << endl;
            outputFile << "Age: " << p->age << endl;
            outputFile << "Blood Group: " << p->bloodGroup << endl;
            outputFile << "Contact: " << p->contact << endl;
        }
        else {
            outputFile << "ERROR: Patient not found!" << endl;
        }

    }
    else if (operation == "DELETE") {
        string id;
        getline(inputFile, id);

        Patient* p = patientTable.search(id);
        if (p != nullptr) {
            patientTable.deletePatient(id);
            savePatientsToFile(); // Save after deleting
            outputFile << "SUCCESS: Patient deleted!" << endl;
        }
        else {
            outputFile << "ERROR: Patient not found!" << endl;
        }

    }
    else if (operation == "VIEWALL") {
        outputFile << "=== ALL PATIENTS ===" << endl;
        streambuf* coutbuf = cout.rdbuf();
        cout.rdbuf(outputFile.rdbuf());
        patientTable.displayAll();
        cout.rdbuf(coutbuf);
    }

    inputFile.close();
    outputFile.close();
}

int main() {
    readInputFile();
    return 0;
}