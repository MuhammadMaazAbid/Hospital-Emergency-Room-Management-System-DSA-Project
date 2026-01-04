#include <iostream>
#include <fstream>
#include <sstream>
#include "triage_structures.h"

using namespace std;

PriorityQueue pq;      // For Priority 1 & 2
StandardQueue sq;      // For Priority 3 & 4

const string DATA_FILE = "../../data/triage_database.txt";

// Load data from file to maintain state
void loadData() {
    ifstream file(DATA_FILE);
    if (!file.is_open()) return;

    string line;
    while (getline(file, line)) {
        if (line.empty()) continue;
        stringstream ss(line);
        string id, symptoms, priStr;
        int priority;

        getline(ss, id, '|');
        getline(ss, priStr, '|');
        priority = stoi(priStr);
        getline(ss, symptoms);

        if (priority <= 2) {
            pq.insert(id, priority, symptoms);
        }
        else {
            sq.enqueue(id, priority, symptoms);
        }
    }
    file.close();
}

// Save data to file
void saveData() {
    ofstream file(DATA_FILE);
    if (!file.is_open()) return;

    pq.save(file);
    sq.save(file);

    file.close();
}

int main() {
    loadData(); // Load previous state

    ifstream inputFile("../../data/queue_input.txt");
    ofstream outputFile("../../data/queue_output.txt");

    if (!inputFile.is_open()) {
        return 0;
    }

    string operation;
    getline(inputFile, operation);

    if (operation == "ADD") {
        string id, symptoms, priStr;
        int priority;

        getline(inputFile, id);
        getline(inputFile, priStr);
        priority = stoi(priStr);
        getline(inputFile, symptoms);

        if (priority <= 2) {
            pq.insert(id, priority, symptoms);
            outputFile << "SUCCESS: Patient added to CRITICAL Priority Queue.";
        }
        else {
            sq.enqueue(id, priority, symptoms);
            outputFile << "SUCCESS: Patient added to Standard Waiting Queue.";
        }
        saveData();
    }
    else if (operation == "TREAT_NEXT") {
        // Always check Priority Queue (Min Heap) first
        if (!pq.isEmpty()) {
            TriagePatient p = pq.extractMin();
            outputFile << "TREATING CRITICAL PATIENT:\n";
            outputFile << "ID: " << p.id << "\n";
            outputFile << "Priority: " << p.priority << "\n";
            outputFile << "Symptoms: " << p.symptoms;
            saveData();
        }
        // If no critical patients, check Standard Queue
        else if (!sq.isEmpty()) {
            TriagePatient p = sq.dequeue();
            outputFile << "TREATING NORMAL PATIENT:\n";
            outputFile << "ID: " << p.id << "\n";
            outputFile << "Priority: " << p.priority << "\n";
            outputFile << "Symptoms: " << p.symptoms;
            saveData();
        }
        else {
            outputFile << "No patients waiting.";
        }
    }
    else if (operation == "VIEW_QUEUE") {
        if (pq.isEmpty() && sq.isEmpty()) {
            outputFile << "Queue is empty.";
        }
        else {
            pq.display(outputFile);
            outputFile << "\n"; // Separator
            sq.display(outputFile);
        }
    }

    inputFile.close();
    outputFile.close();
    return 0;
}