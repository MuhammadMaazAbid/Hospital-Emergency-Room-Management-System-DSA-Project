# Hospital Emergency Room Management System

## 📋 Project Overview

The **Hospital Emergency Room Management System** is a comprehensive software solution designed to streamline emergency room operations through efficient patient management, priority-based triage, and optimal resource allocation. This system demonstrates practical applications of advanced data structures and algorithms in a real-world healthcare context.

### Key Features
- **Patient Records Management**: Fast patient registration and retrieval using Hash Tables and AVL Trees
- **Emergency Triage System**: Priority-based patient queue using Min Heap for critical case handling
- **Doctor Assignment & Navigation**: Intelligent resource allocation and hospital pathfinding using Graphs
- **Secure Authentication**: Admin access control with password hashing
- **Data Persistence**: File-based storage ensuring no data loss between sessions

### Technology Stack
- **Frontend**: Java Swing (GUI)
- **Backend**: C++ (Data Structures & Algorithms)
- **Integration**: File-based I/O communication
- **Development Tools**: 
  - IntelliJ IDEA (Java)
  - Visual Studio (C++)

---

## 👥 Team Members

| Name | Registration No. | Role |
|------|------------------|------|
| **Soban Farooque**| Patient Records Module Lead, Hash Table & AVL Tree Implementation, GUI Design |
| **Muhammad Maaz** | Emergency Triage & Doctor Routing Lead, Priority Queue & Graph Implementation, Integration & Testing |

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────┐
│         Java GUI Layer (Swing)          │
│   ┌──────────┬──────────┬──────────┐   │
│   │ Patient  │  Triage  │  Routing │   │
│   │  Module  │  Module  │  Module  │   │
│   └──────────┴──────────┴──────────┘   │
└──────────────┬──────────────────────────┘
               │ File I/O Communication
┌──────────────▼──────────────────────────┐
│         C++ Processing Layer            │
│   ┌──────────┬──────────┬──────────┐   │
│   │Hash Table│ Min Heap │  Graph   │   │
│   │AVL Tree  │  Queue   │ Sorting  │   │
│   └──────────┴──────────┴──────────┘   │
└─────────────────────────────────────────┘
```

---

## 📂 Project Structure

```
Hospital_ER_System/
│
├── cpp/                          # C++ Backend Projects
│   ├── PatientRecords/
│   │   ├── PatientRecords.sln
│   │   └── PatientRecords/
│   │       ├── patient_records.cpp
│   │       └── data_structures.h
│   ├── PriorityQueue/
│   │   └── priority_queue.cpp
│   ├── DoctorRouting/
│   │   └── doctor_routing.cpp
│   └── Authentication/
│       └── authentication.cpp
│
├── java/                         # Java Frontend Project
│   └── HospitalERSystem/
│       └── src/
│           ├── Config/
│           │   └── Config.java
│           ├── gui/
│           │   ├── MainGUI.java
│           │   ├── LoginScreen.java
│           │   ├── PatientModule.java
│           │   ├── TriageModule.java
│           │   └── RoutingModule.java
│           ├── integration/
│           │   └── CppBridge.java
│           └── models/
│               └── Patient.java
│
├── data/                         # Data Files
│   ├── patient_input.txt
│   ├── patient_output.txt
│   ├── queue_input.txt
│   ├── queue_output.txt
│   ├── routing_input.txt
│   ├── routing_output.txt
│   ├── auth_input.txt
│   ├── auth_output.txt
│   ├── patients_database.txt
│   └── users_database.txt
│
├── executables/                  # Compiled C++ Executables
│   ├── PatientRecords.exe
│   ├── PriorityQueue.exe
│   ├── DoctorRouting.exe
│   └── Authentication.exe
│
├── docs/                         # Documentation & Diagrams
│   ├── use_case_diagram.png
│   ├── class_diagram.png
│   └── sequence_diagram.png
│
└── README.md                     # This file
```

---

## 🚀 Installation & Setup Instructions

### Prerequisites

#### For C++ Backend:
- **Visual Studio 2019/2022** (with C++ development tools)
- **MinGW-w64** (if using alternative compiler)
- **Windows OS** (recommended) or equivalent C++ compiler for other OS

#### For Java Frontend:
- **Java JDK 8 or higher**
- **IntelliJ IDEA** (Community Edition is sufficient)
- **Java Runtime Environment (JRE)**

---

## 📥 Step-by-Step Setup Guide

### Part 1: Setting Up C++ Backend

#### 1. Open Visual Studio Projects
```
Navigate to: Hospital_ER_System/cpp/
Open each .sln file:
  - PatientRecords/PatientRecords.sln
  - PriorityQueue/PriorityQueue.sln
  - DoctorRouting/DoctorRouting.sln
  - Authentication/Authentication.sln
```

#### 2. Configure Output Directory (IMPORTANT)
For **each** C++ project:
1. Right-click on project name → **Properties**
2. Navigate to: **Configuration Properties → General**
3. Change **Output Directory** to: `$(SolutionDir)..\..\executables\`
4. Click **Apply** → **OK**

#### 3. Build All C++ Projects
```
For each project:
  - Press Ctrl + Shift + B (Build Solution)
  - Ensure 0 errors
  - Verify .exe files appear in Hospital_ER_System/executables/
```

**Expected Output:**
```
executables/
  ├── PatientRecords.exe ✓
  ├── PriorityQueue.exe ✓
  ├── DoctorRouting.exe ✓
  └── Authentication.exe ✓
```

---

### Part 2: Setting Up Java Frontend

#### 1. Open IntelliJ IDEA
```
File → Open → Navigate to: Hospital_ER_System/java/HospitalERSystem
Click OK
```

#### 2. Configure Project SDK
```
File → Project Structure → Project
Set Project SDK: Java 8 or higher
Set Language Level: 8 or higher
Click Apply → OK
```

#### 3. Update Config.java (CRITICAL)
Open: `src/Config/Config.java`

**Change this line to your actual path:**
```java
public static final String BASE_PATH = "C:/Users/YourName/Desktop/Hospital_ER_System/";
```

**Example for different locations:**
```java
// If on Desktop:
public static final String BASE_PATH = "C:/Users/YourUsername/Desktop/Hospital_ER_System/";

// If on D: drive:
public static final String BASE_PATH = "D:/Projects/Hospital_ER_System/";

// If on U: drive (network):
public static final String BASE_PATH = "U:/Hospital_ER_System/";
```

**Note:** Use forward slashes `/` not backslashes `\` in Java!

#### 4. Verify Data Files Exist
Check that these files exist in `Hospital_ER_System/data/`:
```
✓ patient_input.txt
✓ patient_output.txt
✓ queue_input.txt
✓ queue_output.txt
✓ routing_input.txt
✓ routing_output.txt
✓ auth_input.txt
✓ auth_output.txt
✓ patients_database.txt (can be empty initially)
✓ users_database.txt (can be empty initially)
```

---

## ▶️ Running the Application

### Method 1: Run from IntelliJ (Recommended)

1. In IntelliJ, navigate to: `src/gui/MainGUI.java`
2. Right-click on `MainGUI.java`
3. Click **Run 'MainGUI.main()'**
4. Login screen should appear

### Method 2: Run from Command Line

```bash
# Navigate to Java project directory
cd Hospital_ER_System/java/HospitalERSystem/src

# Compile all Java files
javac Config/*.java gui/*.java integration/*.java models/*.java

# Run the application
java gui.MainGUI
```

---

## 🔐 First Time Setup

### Creating Admin Account

1. When login screen appears, click **"CREATE NEW ACCOUNT"**
2. Enter desired username (minimum 3 characters)
3. Enter password (minimum 4 characters)
4. Click **Register**
5. After successful registration, login with your credentials

**Example:**
```
Username: admin
Password: hospital123
```

---

## 📚 Module Descriptions

### Module 1: Patient Registration & Medical Records

**Purpose:** Efficient storage and retrieval of patient information

**Data Structures Used:**
- **Hash Table (Level-1)**: O(1) patient lookup by ID
  - Size: 100 buckets
  - Collision handling: Chaining with linked lists
  - Hash function: Sum of ASCII values mod 100
  
- **AVL Tree (Level-2)**: Sorted patient records by name
  - Self-balancing binary search tree
  - Rotations: Left, Right, Left-Right, Right-Left
  - O(log n) insertion and traversal

**Features:**
- Add new patient with validation
- Search patient by ID (instant lookup)
- Delete patient with confirmation
- View all patients (sorted or unsorted)
- Data persists across sessions

**Operations:**
```
ADD → Insert into Hash Table → Insert into AVL Tree → Save to file
SEARCH → Hash Table lookup → Return patient details
DELETE → Remove from Hash Table → Update file
VIEWALL → Traverse Hash Table → Display all records
```

---

### Module 2: Emergency Triage & Priority Queue

**Purpose:** Manage patient queue based on medical severity

**Data Structures Used:**
- **Queue (Level-1)**: General waiting list (FIFO)
  - Array-based circular queue
  - Operations: Enqueue, Dequeue
  
- **Min Heap / Priority Queue (Level-2)**: Priority-based patient ordering
  - Priority levels:
    - **1 = Critical** (cardiac arrest, severe trauma)
    - **2 = Urgent** (severe pain, breathing difficulty)
    - **3 = Moderate** (fractures, infections)
    - **4 = Non-urgent** (minor injuries, cold)
  - Ensures critical patients treated first
  - O(log n) insertion and extraction

**Features:**
- Add patient to queue with priority level
- Treat next patient (highest priority first)
- View current queue status
- Visual priority guide with color coding

**Operations:**
```
ADD → Insert into Priority Queue → Heapify → Save
TREAT_NEXT → Extract minimum → Update queue → Save
VIEW_QUEUE → Display sorted by priority
```

---

### Module 3: Doctor Assignment & Department Routing

**Purpose:** Assign specialized doctors and find optimal hospital paths

**Data Structures Used:**
- **Sorting Algorithm (Level-1)**: Quick Sort
  - Sort doctors by specialty and availability
  - O(n log n) complexity
  
- **Graph with Dijkstra's Algorithm (Level-2)**: Hospital navigation
  - Nodes: Hospital locations (Entrance, Triage, Corridors, ICU, etc.)
  - Edges: Pathways with distances (meters)
  - Finds shortest path between any two locations
  - O(V²) complexity (optimized with priority queue)

**Features:**
- Assign doctor by specialty
- View all available doctors
- Find shortest path between locations
- Display path with distance and directions

**Hospital Map Structure:**
```
Entrance ←15m→ Triage ←10m→ Corridor A ←20m→ ICU
   ↓                          ↓
  25m                        15m
   ↓                          ↓
Corridor B ←12m→ X-Ray ←18m→ Surgery
```

**Operations:**
```
ASSIGN → Sort by specialty → Return available doctor
VIEW_DOCTORS → Display all doctors with specialties
FIND_PATH → Dijkstra's algorithm → Return shortest path with distance
```

---

## 🔧 Data Structures & Algorithms Per Student

### Student A Contributions

#### 1. Hash Table Implementation
- **File**: `cpp/PatientRecords/data_structures.h`
- **Purpose**: O(1) patient lookup
- **Operations**: Insert, Search, Delete, Display
- **Complexity**: 
  - Average: O(1)
  - Worst: O(n) with chaining

#### 2. AVL Tree Implementation
- **File**: `cpp/PatientRecords/data_structures.h`
- **Purpose**: Sorted patient storage
- **Operations**: Insert, Rotations, Inorder Traversal
- **Complexity**: O(log n) for all operations

#### 3. GUI Development
- **Files**: `PatientModule.java`, `MainGUI.java`, `LoginScreen.java`
- **Responsibility**: User interface design and validation

---

### Student B Contributions

#### 1. Queue (Circular Array)
- **File**: `cpp/PriorityQueue/priority_queue.cpp`
- **Purpose**: General waiting list
- **Operations**: Enqueue, Dequeue
- **Complexity**: O(1)

#### 2. Min Heap / Priority Queue
- **File**: `cpp/PriorityQueue/priority_queue.cpp`
- **Purpose**: Priority-based patient ordering
- **Operations**: Insert, Extract Min, Heapify
- **Complexity**: O(log n)

#### 3. Graph (Dijkstra's Algorithm)
- **File**: `cpp/DoctorRouting/doctor_routing.cpp`
- **Purpose**: Shortest path calculation
- **Operations**: Add edges, Find shortest path
- **Complexity**: O(V²) or O((V+E) log V) with priority queue

#### 4. Quick Sort
- **File**: `cpp/DoctorRouting/doctor_routing.cpp`
- **Purpose**: Sort doctors by specialty
- **Complexity**: O(n log n) average

#### 5. GUI Development
- **Files**: `TriageModule.java`, `RoutingModule.java`
- **Responsibility**: Emergency and routing interfaces

---

## 🔗 Integration Method Explanation

### Communication Architecture

Our system uses **File-Based I/O Communication** to connect Java GUI with C++ backend:

```
Java GUI → Write Input File → Execute C++ .exe → C++ Reads Input 
    ↓
C++ Processes using DSAs → Writes Output File → Java Reads Output → Display Results
```

### Step-by-Step Integration Flow

#### 1. Java Sends Request (CppBridge.java)
```java
public static String processRequest(String exePath, String inputFile, 
                                     String outputFile, String... inputData) {
    // Step 1: Write data to input file
    writeInputFile(inputFile, inputData);
    
    // Step 2: Execute C++ program
    ProcessBuilder pb = new ProcessBuilder(exePath);
    Process process = pb.start();
    process.waitFor();
    
    // Step 3: Read output file
    return readOutputFile(outputFile);
}
```

#### 2. C++ Processes Request
```cpp
// Read input file
ifstream inputFile("../../data/patient_input.txt");
string operation;
getline(inputFile, operation); // "ADD", "SEARCH", "DELETE", etc.

// Process using data structures
if (operation == "ADD") {
    patientTable.insert(id, name, age, bloodGroup, contact);
}

// Write results to output file
ofstream outputFile("../../data/patient_output.txt");
outputFile << "SUCCESS: Patient added!" << endl;
```

#### 3. Java Displays Results
```java
String result = CppBridge.processRequest(...);
txtOutput.setText(result); // Display in GUI
```

### Data Format

**Input File Format (patient_input.txt):**
```
ADD
P001
John Doe
45
O+
03001234567
```

**Output File Format (patient_output.txt):**
```
SUCCESS: Patient added!
ID: P001
Name: John Doe
Age: 45
Blood Group: O+
Contact: 03001234567
```

### Why File-Based Integration?

✅ **Simple & Reliable**: No complex networking required  
✅ **Language Agnostic**: Works between any languages  
✅ **Debuggable**: Can view files to troubleshoot  
✅ **No Dependencies**: No external libraries needed  
✅ **Cross-Platform**: Works on Windows, Mac, Linux  

---

## 👥 Team Collaboration Notes

### Development Approach

Our team followed an **Agile-inspired methodology** with the following practices:

#### Communication & Coordination
- **Primary Platform**: WhatsApp group chat
- **Code Sharing**: Files shared via WhatsApp
- **Meetings**: Daily voice calls for progress updates
- **Duration**: 2 weeks of active development

#### Work Distribution

**Week 1: Foundation**
- **Both members**: System design and architecture planning
- **Student A**: C++ Hash Table and AVL Tree implementation
- **Student B**: C++ Priority Queue and Graph implementation
- **Joint**: File-based integration protocol design

**Week 2: Integration & UI**
- **Student A**: Patient Records module GUI + Login system
- **Student B**: Triage and Routing module GUI
- **Both members**: Testing, bug fixes, and documentation

#### Collaboration Workflow

1. **Daily Standups (WhatsApp Voice Calls)**
   - What was completed yesterday
   - What will be done today
   - Any blockers or issues

2. **Code Sharing Process**
   - Complete module functionality locally
   - Test thoroughly
   - Share .java or .cpp files via WhatsApp
   - Partner reviews and integrates

3. **Integration Testing**
   - Both members tested each other's modules
   - Reported bugs via WhatsApp
   - Fixed issues and shared updated files

4. **Final Integration**
   - Merged all modules
   - Comprehensive system testing
   - Performance testing with 1000+ records
   - UI polish and refinement

#### Key Decisions Made Together

- **Technology Stack**: Java Swing + C++ (chosen for DSA focus)
- **Integration Method**: File-based I/O (simplest and most reliable)
- **Data Structures**: Optimized for healthcare context
- **UI Design**: Modern, professional, user-friendly
- **Color Scheme**: Medical theme (blue, green, red for priorities)

#### Challenges & Solutions

| Challenge | Solution |
|-----------|----------|
| Java-C++ communication | Designed simple file-based protocol |
| Data persistence | Implemented database files with load/save |
| Hash collisions | Used chaining with linked lists |
| UI responsiveness | Used GridBagLayout for adaptive sizing |
| Path synchronization | Used Config.java for centralized paths |

---

## 🧪 Testing Instructions

### Test Case 1: Add Patient
```
1. Login to system
2. Go to "Patient Records" tab
3. Fill in:
   - Patient ID: P001
   - Name: Test Patient
   - Age: 35
   - Blood Group: O+
   - Contact: 03001234567
4. Click "Add Patient"
5. Expected: Success message in green
```

### Test Case 2: Priority Queue
```
1. Go to "Emergency Triage" tab
2. Add Patient P001 with Priority 1 (Critical)
3. Add Patient P002 with Priority 3 (Non-urgent)
4. Click "View Queue"
5. Expected: P001 appears before P002
```

### Test Case 3: Find Shortest Path
```
1. Go to "Doctor Routing" tab
2. Select Start: Entrance
3. Select End: ICU
4. Click "Find Shortest Path"
5. Expected: Display path with distance in meters
```

---

## 📊 Performance Metrics

| Operation | Time Complexity | Tested With | Result |
|-----------|----------------|-------------|---------|
| Patient Search | O(1) | 1000 records | Instant (<0.1s) |
| Add Patient | O(1) + O(log n) | 1000 records | <0.2s |
| Priority Queue Insert | O(log n) | 100 patients | <0.05s |
| Treat Next Patient | O(log n) | 100 patients | <0.05s |
| Find Shortest Path | O(V²) | 10 locations | <0.1s |
| View All Patients | O(n) | 1000 records | <0.5s |

---

## 🐛 Troubleshooting

### Common Issues & Solutions

#### Issue 1: "Cannot find executables"
**Solution**: 
- Verify all 4 .exe files exist in `executables/` folder
- Rebuild C++ projects in Visual Studio
- Check Output Directory configuration

#### Issue 2: "File not found" error
**Solution**:
- Update `BASE_PATH` in Config.java to your actual path
- Use forward slashes `/` not backslashes `\`
- Ensure all data files exist in `data/` folder

#### Issue 3: "Process cannot be executed"
**Solution**:
- Install Visual C++ Redistributable
- Run as Administrator
- Check antivirus is not blocking .exe files

#### Issue 4: No data displayed
**Solution**:
- Check `patients_database.txt` has data
- Verify file format: `ID|Name|Age|BloodGroup|Contact`
- Ensure no extra spaces or special characters

---

## 📖 Additional Resources

### Documentation
- **Complete Project Report**: See `docs/documentation.pdf`
- **UML Diagrams**: Located in `docs/` folder
- **Presentation Slides**: Available in `docs/presentation.pptx`

### References
- Cormen, T. H., et al. (2009). *Introduction to Algorithms*
- Goodrich, M. T., et al. (2014). *Data Structures and Algorithms in Java*
- Oracle Java Swing Documentation
- GeeksforGeeks DSA Resources

---

## 📝 Future Enhancements

- [ ] Database integration (MySQL/PostgreSQL)
- [ ] Network support for multi-user access
- [ ] Patient medical history tracking
- [ ] Appointment scheduling system
- [ ] Mobile app (iOS/Android)
- [ ] Real-time notifications
- [ ] Advanced reporting and analytics
- [ ] Integration with medical devices

---

## 📞 Contact Information

**Team Members:**
- **Student A**: [Email] | [Phone]
- **Student B**: [Email] | [Phone]

**Course**: Data Structures and Algorithms  
**Semester**: Fall 2024  
**Institution**: [Your University Name]

---

## 📄 License

This project was developed as an academic assignment for educational purposes.

---
**© 2025 Hospital ER Management System | DSA Project**

*Built with ❤️ using Java, C++, and dedication*
