package integration;

import java.io.*;

public class CppBridge {

    // Method to write input data to file
    public static void writeInputFile(String filePath, String... lines) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to call C++ executable
    public static boolean executeCpp(String exePath) {
        try {
            ProcessBuilder pb = new ProcessBuilder(exePath);
            Process process = pb.start();

            // Wait for process to complete
            int exitCode = process.waitFor();

            return exitCode == 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to read output file
    public static String readOutputFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR: Could not read output file!";
        }
        return content.toString();
    }

    // Complete workflow: Write input → Execute C++ → Read output
    public static String processRequest(String exePath, String inputFile, String outputFile, String... inputData) {
        // Step 1: Write input
        writeInputFile(inputFile, inputData);

        // Step 2: Execute C++
        boolean success = executeCpp(exePath);

        if (!success) {
            return "ERROR: C++ execution failed!";
        }

        // Step 3: Read output
        return readOutputFile(outputFile);
    }
}