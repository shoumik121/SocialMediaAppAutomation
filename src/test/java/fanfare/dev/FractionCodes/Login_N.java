package fanfare.dev.FractionCodes;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pro.truongsinh.appium_flutter.FlutterFinder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class Login_N {

    private AndroidDriver driver;
    private FlutterFinder find;

    @BeforeTest
    public void startAppiumServer() {
     //appium --use-plugins=appium-reporter-plugin
    }

    @Test(priority = 1)
    public void launchApp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("automationName", "Flutter");
        caps.setCapability("newCommandTimeout", "3000");
        caps.setCapability("deviceName", "5HT8OBE6BIVSCIAM");

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