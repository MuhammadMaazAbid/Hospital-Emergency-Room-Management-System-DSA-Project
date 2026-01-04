#pragma once
#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <map>
#include <climits>
#include <stack>
#include <algorithm> // For std::swap

using namespace std;

// --- Level-1 DSA: Sorting (Doctor Management) ---
struct Doctor {
    string id;
    string name;
    string specialty;
    bool available;

    Doctor(string i, string n, string s, bool a) : id(i), name(n), specialty(s), available(a) {}
};

class DoctorManager {
public:
    vector<Doctor> doctors;

    void addDoctor(string id, string name, string specialty, bool available) {
        doctors.push_back(Doctor(id, name, specialty, available));
    }

    // BUBBLE SORT - Sorts doctors alphabetically by Name
    void sortDoctorsByName() {
        int n = doctors.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (doctors[j].name > doctors[j + 1].name) {
                    swap(doctors[j], doctors[j + 1]);
                }
            }
        }
    }

    // Helper to format output
    void displayDoctors(ofstream& out) {
        out << "--- AVAILABLE DOCTORS (Sorted A-Z) ---\n";
        for (const auto& d : doctors) {
            out << (d.available ? "[AVAILABLE] " : "[BUSY]      ")
                << d.name << " (" << d.specialty << ")\n";
        }
    }

    string assignDoctor(string specialty) {
        for (auto& d : doctors) {
            if (d.specialty == specialty && d.available) {
                d.available = false; // Mark as busy
                return "ASSIGNED: Dr. " + d.name + " (" + d.id + ")";
            }
        }
        return "NO AVAILABLE DOCTOR for " + specialty;
    }
};

// --- Level-2 DSA: Graph & Dijkstra (Navigation) ---
class HospitalMap {
private:
    // Adjacency list: Node -> Vector of pairs (Neighbor, Distance)
    map<string, vector<pair<string, int>>> adjList;

public:
    void addPath(string u, string v, int dist) {
        adjList[u].push_back({ v, dist });
        adjList[v].push_back({ u, dist }); // Undirected graph (can walk both ways)
    }

    // Dijkstra's Algorithm for Shortest Path
    void findShortestPath(string start, string end, ofstream& out) {
        if (adjList.find(start) == adjList.end() || adjList.find(end) == adjList.end()) {
            out << "ERROR: Invalid location name.\n";
            return;
        }

        map<string, int> distances;
        map<string, string> previous; // To reconstruct path
        for (auto const& [node, neighbors] : adjList) {
            distances[node] = INT_MAX;
        }

        distances[start] = 0;
        priority_queue<pair<int, string>, vector<pair<int, string>>, greater<pair<int, string>>> pq;
        pq.push({ 0, start });

        while (!pq.empty()) {
            int currentDist = pq.top().first;
            string current = pq.top().second;
            pq.pop();

            if (currentDist > distances[current]) continue;
            if (current == end) break; // Found destination

            for (auto& edge : adjList[current]) {
                string neighbor = edge.first;
                int weight = edge.second;

                if (distances[current] + weight < distances[neighbor]) {
                    distances[neighbor] = distances[current] + weight;
                    previous[neighbor] = current;
                    pq.push({ distances[neighbor], neighbor });
                }
            }
        }

        // Reconstruct path
        if (distances[end] == INT_MAX) {
            out << "No path found from " << start << " to " << end << ".\n";
            return;
        }

        stack<string> pathStack;
        string curr = end;
        while (curr != start) {
            pathStack.push(curr);
            curr = previous[curr];
        }
        pathStack.push(start);

        out << "SHORTEST PATH FOUND (" << distances[end] << " meters):\n";
        while (!pathStack.empty()) {
            out << pathStack.top();
            pathStack.pop();
            if (!pathStack.empty()) out << " -> ";
        }
        out << "\n";
    }
};