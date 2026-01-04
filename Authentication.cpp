#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
using namespace std;

const string USERS_FILE = "../../data/users_database.txt";

// Hash function for password (simple but functional)
string hashPassword(string password) {
    int hash = 0;
    for (char c : password) {
        hash = hash * 31 + c;
    }
    return to_string(hash);
}

bool userExists(string username) {
    ifstream file(USERS_FILE);
    if (!file.is_open()) return false;

    string line;
    while (getline(file, line)) {
        if (line.empty()) continue;

        stringstream ss(line);
        string user;
        getline(ss, user, '|');

        if (user == username) {
            file.close();
            return true;
        }
    }
    file.close();
    return false;
}

void registerUser(string username, string password) {
    ofstream outputFile("../../data/auth_output.txt");

    if (userExists(username)) {
        outputFile << "ERROR: Username already exists!" << endl;
        outputFile.close();
        return;
    }

    // Hash the password
    string hashedPass = hashPassword(password);

    // Append to users file
    ofstream usersFile(USERS_FILE, ios::app);
    if (!usersFile.is_open()) {
        outputFile << "ERROR: Could not save user data!" << endl;
        outputFile.close();
        return;
    }

    usersFile << username << "|" << hashedPass << endl;
    usersFile.close();

    outputFile << "SUCCESS: Account created successfully!" << endl;
    outputFile << "Username: " << username << endl;
    outputFile << "You can now login with your credentials." << endl;
    outputFile.close();
}

void loginUser(string username, string password) {
    ofstream outputFile("../../data/auth_output.txt");

    ifstream usersFile(USERS_FILE);
    if (!usersFile.is_open()) {
        outputFile << "ERROR: No users registered yet!" << endl;
        outputFile.close();
        return;
    }

    string hashedPass = hashPassword(password);

    string line;
    bool found = false;

    while (getline(usersFile, line)) {
        if (line.empty()) continue;

        stringstream ss(line);
        string user, pass;

        getline(ss, user, '|');
        getline(ss, pass);

        if (user == username && pass == hashedPass) {
            found = true;
            break;
        }
    }

    usersFile.close();

    if (found) {
        outputFile << "SUCCESS: Login successful!" << endl;
        outputFile << "Welcome back, " << username << "!" << endl;
        outputFile << "Access granted to Hospital ER Management System." << endl;
    }
    else {
        outputFile << "ERROR: Invalid username or password!" << endl;
        outputFile << "Please check your credentials and try again." << endl;
    }

    outputFile.close();
}

int main() {
    ifstream inputFile("../../data/auth_input.txt");
    ofstream outputFile("../../data/auth_output.txt");

    if (!inputFile.is_open()) {
        outputFile << "ERROR: Could not open input file!" << endl;
        outputFile.close();
        return 1;
    }

    string operation;
    getline(inputFile, operation);

    if (operation == "REGISTER") {
        string username, password;
        getline(inputFile, username);
        getline(inputFile, password);

        registerUser(username, password);

    }
    else if (operation == "LOGIN") {
        string username, password;
        getline(inputFile, username);
        getline(inputFile, password);

        loginUser(username, password);

    }
    else {
        outputFile << "ERROR: Invalid operation!" << endl;
        outputFile.close();
    }

    inputFile.close();
    return 0;
}