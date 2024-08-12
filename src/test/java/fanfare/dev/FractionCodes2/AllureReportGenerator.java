package fanfare.dev.FractionCodes2;

import java.io.IOException;

public class AllureReportGenerator {
    public static void main(String[] args) {
        try {
            // Run 'allure generate target/allure-results --clean' command
            executeCommand("allure generate C:\\Users\\TalentPro\\Documents\\fanfare-allure-reporter\\target\\allure-results --clean");

            // Run 'allure serve target/allure-results' command
            executeCommand("allure serve C:\\Users\\TalentPro\\Documents\\fanfare-allure-reporter\\target\\allure-results");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String command) throws IOException, InterruptedException {
        // Use ProcessBuilder to run the command
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\git-bash.exe", "-c", command);

        // Redirect error stream to the standard output
        processBuilder.redirectErrorStream(true);

        // Start the process
        Process process = processBuilder.start();

        // Wait for the process to complete
        int exitCode = process.waitFor();

        // Check the exit code to determine if the command was successful
        if (exitCode == 0) {
            System.out.println("Command executed successfully: " + command);
        } else {
            System.err.println("Command failed with exit code " + exitCode + ": " + command);
        }
    }
}

