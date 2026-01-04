#pragma once
#include <iostream>
#include <string>
#include <fstream>
#include <vector>
using namespace std;

// Patient Node for Triage
struct TriagePatient {
    string id;
    int priority; // 1=Critical, 2=Urgent, 3=Normal, 4=Non-Urgent
    string symptoms;
    TriagePatient* next; // For Standard Queue

    TriagePatient(string i, int p, string s) {
        id = i;
        priority = p;
        symptoms = s;
        next = nullptr;
    }

    // Default constructor for array allocation
    TriagePatient() : id(""), priority(0), symptoms(""), next(nullptr) {}
};

// --- Level-2 DSA: Min Heap (Priority Queue) ---
// Used for High Priority Patients (Levels 1 & 2)
class PriorityQueue {
private:
    vector<TriagePatient> heap;

    int parent(int i) { return (i - 1) / 2; }
    int left(int i) { return (2 * i) + 1; }
    int right(int i) { return (2 * i) + 2; }

    void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;

        if (l < heap.size() && heap[l].priority < heap[smallest].priority)
            smallest = l;
        if (r < heap.size() && heap[r].priority < heap[smallest].priority)
            smallest = r;

        if (smallest != i) {
            swap(heap[i], heap[smallest]);
            heapify(smallest);
        }
    }

public:
    void insert(string id, int priority, string symptoms) {
        TriagePatient newPatient(id, priority, symptoms);
        heap.push_back(newPatient);

        // Bubble up
        int i = heap.size() - 1;
        while (i != 0 && heap[parent(i)].priority > heap[i].priority) {
            swap(heap[i], heap[parent(i)]);
            i = parent(i);
        }
    }

    TriagePatient extractMin() {
        if (heap.size() == 0) return TriagePatient("", -1, ""); // Error indicator

        TriagePatient root = heap[0];
        heap[0] = heap.back();
        heap.pop_back();

        if (!heap.empty()) {
            heapify(0);
        }
        return root;
    }

    bool isEmpty() {
        return heap.empty();
    }

    void display(ofstream& out) {
        if (heap.empty()) return;
        out << "--- CRITICAL / URGENT (Priority Queue) ---\n";
        // Create a copy to display without destroying the heap
        vector<TriagePatient> temp = heap;
        // Simple sort for display purposes (since heap array isn't perfectly sorted)
        for (size_t i = 0; i < temp.size(); i++) {
            for (size_t j = i + 1; j < temp.size(); j++) {
                if (temp[i].priority > temp[j].priority) swap(temp[i], temp[j]);
            }
            out << "Priority " << temp[i].priority << " | ID: " << temp[i].id << " | " << temp[i].symptoms << "\n";
        }
    }

    // Helper to save to file
    void save(ofstream& file) {
        for (const auto& p : heap) {
            file << p.id << "|" << p.priority << "|" << p.symptoms << endl;
        }
    }
};

// --- Level-1 DSA: Standard Queue ---
// Used for Normal Priority Patients (Levels 3 & 4)
class StandardQueue {
private:
    TriagePatient* front;
    TriagePatient* rear;

public:
    StandardQueue() {
        front = rear = nullptr;
    }

    void enqueue(string id, int priority, string symptoms) {
        TriagePatient* temp = new TriagePatient(id, priority, symptoms);
        if (rear == nullptr) {
            front = rear = temp;
            return;
        }
        rear->next = temp;
        rear = temp;
    }

    TriagePatient dequeue() {
        if (front == nullptr) return TriagePatient("", -1, "");

        TriagePatient* temp = front;
        TriagePatient data = *front;
        front = front->next;

        if (front == nullptr) rear = nullptr;

        delete temp;
        return data;
    }

    bool isEmpty() {
        return front == nullptr;
    }

    void display(ofstream& out) {
        if (front == nullptr) return;
        out << "--- NORMAL PRIORITY (Standard FIFO Queue) ---\n";
        TriagePatient* temp = front;
        while (temp != nullptr) {
            out << "Priority " << temp->priority << " | ID: " << temp->id << " | " << temp->symptoms << "\n";
            temp = temp->next;
        }
    }

    // Helper to save to file
    void save(ofstream& file) {
        TriagePatient* temp = front;
        while (temp != nullptr) {
            file << temp->id << "|" << temp->priority << "|" << temp->symptoms << endl;
            temp = temp->next;
        }
    }
};