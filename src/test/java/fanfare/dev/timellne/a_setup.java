package fanfare.dev.timellne;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pro.truongsinh.appium_flutter.FlutterFinder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class a_setup {

    private  AndroidDriver driver;
    private  FlutterFinder find;

    // Getter for driver
    public AndroidDriver getAndroidDriver() {
        return driver;
    }
    // Getter for driver
    public FlutterFinder getFlutterFinder() {
        return find;
    }

    public static AppiumDriverLocalService service;
    public static String NodeExePath = "C:\\Program Files\\nodejs\\node.exe";
    public static String AppiumMainJSPath = "C:\\Users\\TalentPro\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    public static String AppiumServerLogPath = "C:\\Users\\TalentPro\\Desktop\\AppiumServer.txt";
    public static String ServerAddress = "127.0.0.1";

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

    public void loginToFanfareBotAccount() throws InterruptedException {


        switchContext("NATIVE_APP");
        //tap profile
        Duration timeout = Duration.ofSeconds(30);  //waiting 30 Sec for the app to load community
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
    }

    public void communityToUserTimeline() throws InterruptedException {

        //waiting 30 Sec for the app to load community
        Duration timeout = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //tap Profile
        switchContext("NATIVE_APP");
        WebElement elementId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Profile")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", elementId));
        Thread.sleep(2000);

        //tap My Channel
        WebElement myChannelId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("My Channel")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", myChannelId));
        Thread.sleep(2000);

    }

    public void toastchecker(String toastMessage) throws InterruptedException{

        //check blank post toasts
        // Capture the screen and check for the toast message
        boolean isToastFound = false;

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
}
