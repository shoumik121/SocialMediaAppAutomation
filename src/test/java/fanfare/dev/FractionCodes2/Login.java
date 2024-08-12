package fanfare.dev.FractionCodes2;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pro.truongsinh.appium_flutter.FlutterFinder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class Login {
    private AndroidDriver driver;
    private FlutterFinder find;

    public static AppiumDriverLocalService service;
    public static String NodeExePath = "C:\\Program Files\\nodejs\\node.exe";
    public static String AppiumMainJSPath = "C:\\Users\\TalentPro\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    public static String AppiumServerLogPath = "C:\\Users\\TalentPro\\Desktop\\AppiumServer.txt";
    public static String ServerAddress = "127.0.0.1";

    @BeforeTest
    public void startAppiumServer() {

//        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .usingDriverExecutable(new File(NodeExePath)).withAppiumJS(new File(AppiumMainJSPath)).withIPAddress(ServerAddress)
//                .withArgument(GeneralServerFlag.BASEPATH, "/").usingPort(4723)
//                .withArgument(GeneralServerFlag.ALLOW_INSECURE, "adb_shell")
//                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
//                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
//                .withLogFile(new File(AppiumServerLogPath))
//        );
//        System.out.println("🔥..🔥..🔥..(🌐Starting Appium Server)..🔥..🔥..🔥");
//
//        //starting appium server at http:\\127.0.0.1\4723
//        service.start();

        //appium --use-plugins=appium-reporter-plugin
    }
    @Test(priority = 1)
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
    public void clickProfileButton_Community() throws InterruptedException {

        Thread.sleep(20000);
        switchContext("NATIVE_APP");
        WebElement elementId = driver.findElement(AppiumBy.accessibilityId("Profile"));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", elementId));
        Thread.sleep(2000);
    }
    @Test(priority = 3)
    public void clickLoginEarnButton_Playlist() throws InterruptedException {

        switchContext("FLUTTER");
        find.text("Login & Earn").click();
        Thread.sleep(2000);
    }
    @Test(priority = 4)
    public void insertUserEmail_LoginPage() throws InterruptedException {

        switchContext("FLUTTER");
        find.bySemanticsLabel("Phone Number or Email").sendKeys("manmrmail1@gmail.comX");
        Assert.assertEquals(find.text("Phone Number or Email").getText(), "Phone Number or Email");
        Thread.sleep(2000);
    }
    @Test(priority = 6)
    public void insertUserPassword_LoginPage() throws InterruptedException {

        switchContext("FLUTTER");
        find.bySemanticsLabel("Password").sendKeys("123456X");
        Assert.assertEquals(find.text("Password").getText(), "Password");
        Thread.sleep(2000);
    }
    @Test(priority = 7)
    public void clickLoginButton_LoginPage() throws InterruptedException {

        switchContext("FLUTTER");
        find.text("Log in").click();
        Assert.assertEquals(find.text("Profile").getText(), "Profile");
        Thread.sleep(2000);
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
        driver.quit();
        //stops the service that is called in BeforeTest
        //service.stop();
        System.out.println("📛..📛..📛..Appium Server Closed!..📛..📛..📛");
    }
}
