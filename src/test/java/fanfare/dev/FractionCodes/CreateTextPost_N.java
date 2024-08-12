package fanfare.dev.FractionCodes;

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
import org.openqa.selenium.remote.UnreachableBrowserException;
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

public class CreateTextPost_N {

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
    @Description("Create Post With Text Negative Test")
    @Step("Create Post With Text Negative Test")
    public void createPostWithTextNegativeTest() throws InterruptedException {

        //tap profile
        switchContext("NATIVE_APP");

        Duration timeout = Duration.ofSeconds(35);
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
        Assert.assertEquals(find.text("Community").getText(), "Community");
        Thread.sleep(2000);

        //tap '+' button
        switchContext("NATIVE_APP");
        WebElement postElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[3]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", postElementId));
        Thread.sleep(2000);

        //insert HashTags
        switchContext("FLUTTER");
        var hashtag = "Hiking ";
        find.bySemanticsLabel("Add HashTag").sendKeys(hashtag);
        //((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 62"));
        Thread.sleep(2000);

        //click Submit
        switchContext("NATIVE_APP");
        WebElement SubmitId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId));
        Thread.sleep(1000);

        //check blank post toasts
        // Capture the screen and check for the toast message
        boolean isToastFound = false;
        String toastMessage = "At least one of title, description and content must be provided.";

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

    @AfterTest
    public void tearDown() {
        try {
            System.out.println("📛..📛..📛..Stopping the driver!..📛..📛..📛");
            driver.quit();
            System.out.println("📛..📛..📛..Driver stopped successfully!..📛..📛..📛");
        } catch (UnreachableBrowserException e) {
            System.err.println("📛..📛..📛..Error: UnreachableBrowserException - The browser may have died!..📛..📛..📛");
            e.printStackTrace();
        } finally {
            if (service != null && service.isRunning()) {
                System.out.println("📛..📛..📛..Stopping the Appium service!..📛..📛..📛");
                service.stop();
                System.out.println("📛..📛..📛..Appium service stopped successfully!..📛..📛..📛");
            }
        }
    }
}
