package fanfare.dev.FractionCodes2;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pro.truongsinh.appium_flutter.FlutterFinder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AutoReportGeneratedClass {

    private AndroidDriver driver;
    private FlutterFinder find;
    public static AppiumDriverLocalService service;
    public static String NodeExePath = "C:\\Program Files\\nodejs\\node.exe";
    public static String AppiumMainJSPath = "C:\\Users\\TalentPro\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    public static String AppiumServerLogPath = "C:\\Users\\TalentPro\\Desktop\\AppiumServer.txt";
    public static String ServerAddress = "127.0.0.1";

    @BeforeTest
    @Description("Starting the Appium server and setting up capabilities")
    public void startAppiumServer() {

        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(NodeExePath)).withAppiumJS(new File(AppiumMainJSPath)).withIPAddress(ServerAddress)
                .withArgument(GeneralServerFlag.BASEPATH, "/").usingPort(4723)
                .withArgument(GeneralServerFlag.ALLOW_INSECURE, "adb_shell")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withLogFile(new File(AppiumServerLogPath))
        );
        System.out.println("🔥..🔥..🔥..(🌐Starting Appium Server)..🔥..🔥..🔥");

        //starting appium server at http:\\127.0.0.1\4723
        service.start();

        //appium --use-plugins=appium-reporter-plugin
    }

    @Test(priority = 1)
    @Description("Launching the app")
    @Step("Launch the app with given capabilities")
    public void launchApp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0"); //Mi device android "11.0"
        caps.setCapability("automationName", "Flutter");
        caps.setCapability("newCommandTimeout", "3000");
        caps.setCapability("deviceName", "5HT8OBE6BIVSCIAM"); //Mi device id "5HT8OBE6BIVSCIAM"

        caps.setCapability("unlockType", "pin");
        caps.setCapability("unlockKey", "0000");
        caps.setCapability("appPackage", "com.fanfare.android.dev");
        caps.setCapability("appActivity", "com.fanfare.android.MainActivity");
        caps.setCapability("noReset", false);

        //Auto-Accept-Alerts📱
        caps.setCapability("autoAcceptAlerts", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        find = new FlutterFinder(driver);
    }


    @Test(priority = 2)
    @Description("E-MAIL LOGIN : POSITIVE")
    @Step("E-MAIL LOGIN SUCCESSFUL")
    public void loginPositiveTest() throws InterruptedException {

        //tap profile
        switchContext("NATIVE_APP");

        Duration timeout = Duration.ofSeconds(25);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement elementId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Profile")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", elementId));
        Thread.sleep(2000);

        //click Login & Earn
        switchContext("FLUTTER");
        find.text("Login & Earn").click();
        Thread.sleep(2000);

        //insert email
        find.bySemanticsLabel("Phone Number or Email").sendKeys("manmrmail1@gmail.com");
        Assert.assertEquals(find.text("Phone Number or Email").getText(), "Phone Number or Email");
        Thread.sleep(2000);

        //insert password
        find.bySemanticsLabel("Password").sendKeys("123456");
        Assert.assertEquals(find.text("Password").getText(), "Password");
        Thread.sleep(2000);

        //click login button
        find.text("Log in").click();
        Assert.assertEquals(find.text("Home").getText(), "Home");
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    @Description("E-MAIL LOGIN : NEGATIVE")
    @Step("E-MAIL LOGIN FAILED")
    public void loginNegativeTest() throws InterruptedException {

        //click profile
        switchContext("NATIVE_APP");
        WebElement elementId = driver.findElement(AppiumBy.accessibilityId("Profile"));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", elementId));
        Thread.sleep(2000);

        //click settings
        WebElement elementIdSettings = driver.findElement(AppiumBy.accessibilityId("Settings"));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", elementIdSettings));
        Thread.sleep(2000);

        //scroll to sign out
        driver.findElements(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                +".scrollIntoView(new UiSelector()"+".textMatches(\""+"Sign Out"+"\").instance(0))"));
        Thread.sleep(2000);

        //click sign out
        WebElement elementIdSignOut = driver.findElement(AppiumBy.accessibilityId("Sign Out"));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", elementIdSignOut));
        Thread.sleep(2000);

        //insert wrong email
        switchContext("FLUTTER");
        find.bySemanticsLabel("Phone Number or Email").sendKeys("manmrmail1@gmail..com");
        Assert.assertEquals(find.text("Phone Number or Email").getText(), "Phone Number or Email");
        Thread.sleep(2000);

        //insert wrong password
        find.bySemanticsLabel("Password").sendKeys("12345678");
        Assert.assertEquals(find.text("Password").getText(), "Password");
        Thread.sleep(2000);

        //click login button
        find.text("Log in").click();
        Thread.sleep(2000);

        //check toasts
        switchContext("NATIVE_APP");
        // Capture the screen and check for the toast message
        boolean isToastFound = false;
        String toastMessage = "Email and Password Doesn't Match";

        // Poll for the toast message
        for (int i = 0; i < 5; i++) {
            String pageSource = driver.getPageSource();
            if (pageSource.contains(toastMessage)) {
                isToastFound = true;
                break;
            }
            // Wait a bit before the next check
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Assert that the toast message was found
        Assert.assertTrue(isToastFound, "Toast message not found: " + toastMessage);

    }

    public void switchContext(String context) {
        Set<String> contexts = driver.getContextHandles();
        for (String appContext : contexts) {
            if (appContext.contains(context)) {
                driver.context(appContext);
                break;
            }
        }
    }

    private static void executeCommand(String command) throws IOException, InterruptedException {
        // Use ProcessBuilder to run the command
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\git-bash.exe", "-c", command);

        // Redirect error stream to the standard output
        processBuilder.redirectErrorStream(true);

        // Start the process
        Process process = processBuilder.start();

        // Read and print the process output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

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

    private static void closeGitBash() throws IOException, InterruptedException {
        // Use ProcessBuilder to run the command
        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\git-bash.exe", "-c", "exit");

        // Redirect error stream to the standard output
        processBuilder.redirectErrorStream(true);

        // Start the process
        Process process = processBuilder.start();

        // Read and print the process output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {

            // Send the exit command to close the Git Bash terminal
            writer.write("exit\n");
            writer.flush();
            process.waitFor(5, TimeUnit.SECONDS); // Wait for up to 5 seconds for the process to exit
        } finally {
            process.destroy();
        }
    }

    public void reporter() {
        try {
            // Run 'allure generate target/allure-results --clean' command
            executeCommand("allure generate target/allure-results --clean");

            // Run 'allure serve target/allure-results' command
            executeCommand("allure serve target/allure-results");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            // Call the reporter method here
            reporter();

            // Close the Git Bash terminal
            closeGitBash();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
        // Stops the service that is called in BeforeTest
        service.stop();

        System.out.println("📛..📛..📛..Appium Server Closed!..📛..📛..📛");
    }
}
