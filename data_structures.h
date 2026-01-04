#pragma once
#include <iostream>
#include <string>
#include <fstream>
using namespace std;

// Patient Structure
struct Patient {
    string id;
    string name;
    int age;
    string bloodGroup;
    string contact;
    Patient* next; // For hash table chaining

    Patient(string i, string n, int a, string bg, string c) {
        id = i;
        name = n;
        age = a;
        bloodGroup = bg;
        contact = c;
        next = nullptr;
    }
};

// Hash Table Class
class HashTable {
private:
    static const int TABLE_SIZE = 100;
    Patient* table[TABLE_SIZE];

    int hashFunction(string id) {
        int hash = 0;
        for (char c : id) {
            hash += c;
        }
        return hash % TABLE_SIZE;
    }

public:
    HashTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = nullptr;
        }
    }

    void insert(string id, string name, int age, string bloodGroup, string contact) {
        int index = hashFunction(id);

        // Check for duplicate ID first
        Patient* temp = table[index];
        while (temp != nullptr) {
            if (temp->id == id) {
                return; // Silently ignore duplicates during loading
            }
            temp = temp->next;
        }

        Patient* newPatient = new Patient(id, name, age, bloodGroup, contact);

        if (table[index] == nullptr) {
            table[index] = newPatient;
        }
        else {
            temp = table[index];
            while (temp->next != nullptr) {
                temp = temp->next;
            }
            temp->next = newPatient;
        }
    }

    Patient* search(string id) {
        int index = hashFunction(id);
        Patient* temp = table[index];

        while (temp != nullptr) {
            if (temp->id == id) {
                return temp;
            }
            temp = temp->next;
        }
        return nullptr;
    }

    void deletePatient(string id) {
        int index = hashFunction(id);
        Patient* temp = table[index];
        Patient* prev = nullptr;

        while (temp != nullptr) {
            if (temp->id == id) {
                if (prev == nullptr) {
                    table[index] = temp->next;
                }
                else {
                    prev->next = temp->next;
                }
                delete temp;
                cout << "SUCCESS: Patient deleted successfully!" << endl;
                return;
            }
            prev = temp;
            temp = temp->next;
        }
        cout << "ERROR: Patient not found!" << endl;
    }

    void displayAll() {
        bool found = false;
        for (int i = 0; i < TABLE_SIZE; i++) {
            Patient* temp = table[i];
            while (temp != nullptr) {
                cout << "ID: " << temp->id << " | Name: " << temp->name
                    << " | Age: " << temp->age << " | Blood Group: " << temp->bloodGroup
                    << " | Contact: " << temp->contact << endl;
                found = true;
                temp = temp->next;
            }
        }
        if (!found) {
            cout << "No patients found in the system." << endl;
        }
    }

    void saveToFile(ofstream& file) {
        for (int i = 0; i < TABLE_SIZE; i++) {
            Patient* temp = table[i];
            while (temp != nullptr) {
                file << temp->id << "|" << temp->name << "|"
                    << temp->age << "|" << temp->bloodGroup << "|"
                    << temp->contact << endl;
                temp = temp->next;
            }
        }
    }
};

// AVL Tree Node
struct AVLNode {
    Patient* patient;
    AVLNode* left;
    AVLNode* right;
    int height;

    AVLNode(Patient* p) {
        patient = p;
        left = right = nullptr;
        height = 1;
    }
};

// AVL Tree Class
class AVLTree {
private:
    AVLNode* root;

    int getHeight(AVLNode* node) {
        return node ? node->height : 0;
    }

    int getBalance(AVLNode* node) {
        return node ? getHeight(node->left) - getHeight(node->right) : 0;
    }

    AVLNode* rightRotate(AVLNode* y) {
        AVLNode* x = y->left;
        AVLNode* T2 = x->right;

        x->right = y;
        y->left = T2;

        y->height = max(getHeight(y->left), getHeight(y->right)) + 1;
        x->height = max(getHeight(x->left), getHeight(x->right)) + 1;

        return x;
    }

    AVLNode* leftRotate(AVLNode* x) {
        AVLNode* y = x->right;
        AVLNode* T2 = y->left;

        y->left = x;
        x->right = T2;

        x->height = max(getHeight(x->left), getHeight(x->right)) + 1;
        y->height = max(getHeight(y->left), getHeight(y->right)) + 1;

        return y;
    }

    AVLNode* insert(AVLNode* node, Patient* patient) {
        if (node == nullptr) {
            return new AVLNode(patient);
        }

        if (patient->name < node->patient->name) {
            node->left = insert(node->left, patient);
        }
        else if (patient->name > node->patient->name) {
            node->right = insert(node->right, patient);
        }
        else {
            return node;
        }

        node->height = 1 + max(getHeight(node->left), getHeight(node->right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && patient->name < node->left->patient->name) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && patient->name > node->right->patient->name) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && patient->name > node->left->patient->name) {
            node->left = leftRotate(node->left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && patient->name < node->right->patient->name) {
            node->right = rightRotate(node->right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(AVLNode* node) {
        if (node != nullptr) {
            inorder(node->left);
            cout << "Name: " << node->patient->name << " | ID: " << node->patient->id
                << " | Age: " << node->patient->age << endl;
            inorder(node->right);
        }
    }

public:
    AVLTree() {
        root = nullptr;
    }

    void insert(Patient* patient) {
        root = insert(root, patient);
    }

    void displaySorted() {
        if (root == nullptr) {
            cout << "No patients in AVL tree." << endl;
        }
        else {
            inorder(root);
        }
    }
};