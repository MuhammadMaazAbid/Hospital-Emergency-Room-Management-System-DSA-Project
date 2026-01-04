package Config;

public class Config {
    public static final String BASE_PATH = "U:/Hospital_ER_System/";

    // Data files
    public static final String PATIENT_INPUT = BASE_PATH + "data/patient_input.txt";
    public static final String PATIENT_OUTPUT = BASE_PATH + "data/patient_output.txt";
    public static final String QUEUE_INPUT = BASE_PATH + "data/queue_input.txt";
    public static final String QUEUE_OUTPUT = BASE_PATH + "data/queue_output.txt";
    public static final String ROUTING_INPUT = BASE_PATH + "data/routing_input.txt";
    public static final String ROUTING_OUTPUT = BASE_PATH + "data/routing_output.txt";

    // ADD THESE NEW LINES FOR AUTHENTICATION:
    public static final String AUTH_INPUT = BASE_PATH + "data/auth_input.txt";
    public static final String AUTH_OUTPUT = BASE_PATH + "data/auth_output.txt";

    // Executables
    public static final String PATIENT_EXE = BASE_PATH + "executables/PatientRecords.exe";
    public static final String QUEUE_EXE = BASE_PATH + "executables/PriorityQueue.exe";
    public static final String ROUTING_EXE = BASE_PATH + "executables/DoctorRouting.exe";

    // ADD THIS FOR AUTHENTICATION:
    public static final String AUTH_EXE = BASE_PATH + "executables/Authentication.exe";
}