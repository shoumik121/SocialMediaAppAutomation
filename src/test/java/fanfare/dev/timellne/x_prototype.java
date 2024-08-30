package fanfare.dev.timellne;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pro.truongsinh.appium_flutter.FlutterFinder;

import java.time.Duration;

public class x_prototype {

    private AndroidDriver driver;
    private FlutterFinder find;

    // Appium service
    public static AppiumDriverLocalService service;

    //instance of the file a_setup
    public a_setup testEssentials;

    @BeforeTest
    public void setUp() throws Exception {

        testEssentials = new a_setup();

        // Call the startAppiumServer method - Start Appium Server ⚡
        testEssentials.startAppiumServer();

        // Call the launchApp method - Launch Fanfare App ⚡
        testEssentials.launchApp();

        //call loginToFanfareBotAccount - Login To Fanfare Bot Account ⚡
        testEssentials.loginToFanfareBotAccount();

        // Set the driver from a_setup
        this.driver = testEssentials.getAndroidDriver();

        // Set the driver from a_setup
        this.find = testEssentials.getFlutterFinder();
    }


    @Test(priority = 9)
    //@Test (enabled = false)
    @Description("Community Top Post Comment Positive Test")
    @Step("Community Top Post Comment Positive Test")
    public void CommunityTopPostCommentTest() throws InterruptedException {

        // Initialize the driver (Assuming it's done elsewhere in your actual code)
        // Set the desired capabilities and initialize the driver properly
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        // top post click comment
        testEssentials.switchContext("NATIVE_APP");
        WebElement unlikeId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Comment")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", unlikeId));
        Thread.sleep(1000);

        //insert comment
        testEssentials.switchContext("NATIVE_APP");
        WebElement postCommentElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", postCommentElementId));
        //here is the comment
        postCommentElementId.sendKeys("Robots are commenting on your post this Twenty First Century Magic! 🤖");
        Thread.sleep(1000);

        // press comment send button
        WebElement sendElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@text=\"Robots are commenting on your post this Twenty First Century Magic! \uD83E\uDD16\"]/android.widget.Button")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", sendElementId));

        testEssentials.toastchecker("Post Comment to Post Successful");
        Thread.sleep(2000);
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
