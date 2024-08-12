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

    @Test(priority = 7)
    @Description("Community Top Fresh Post Like & Unlike Positive Test")
    @Step("Community Top Fresh Post Like & Unlike Positive Test")
    public void CommunityTopFreshPostLikeTest() throws InterruptedException {

        // Initialize the driver (Assuming it's done elsewhere in your actual code)
        // Set the desired capabilities and initialize the driver properly
        Thread.sleep(20000);
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

            // Top One post after reload post 1 without like
            testEssentials.switchContext("NATIVE_APP");
            driver.findElements(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                    +".scrollIntoView(new UiSelector()"+".textMatches(\""+"Like"+"\").instance(0))"));

            // Click the like button & check "Liked" status --LIKE TEST--
            WebElement likeId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Like")));
            driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", likeId));
            testEssentials.switchContext("FLUTTER");
            Assert.assertEquals(find.text("Liked").getText(), "Liked");
            Thread.sleep(2000);

            // SamePost Click the unlike button & check "Like" status --UNLIKE TEST--
            testEssentials.switchContext("NATIVE_APP");
            WebElement unlikeId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Liked")));
            driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", unlikeId));
            testEssentials.switchContext("FLUTTER");
            Assert.assertEquals(find.text("Like").getText(), "Like");
            Thread.sleep(2000);
    }

    @Test(priority = 8)
    @Description("Community Top Fresh Post Like & Unlike Positive Test")
    @Step("Community Top Fresh Post Like & Unlike Positive Test")
    public void CommunityTopFreshPostUnlikeTest() throws InterruptedException {

        // Initialize the driver (Assuming it's done elsewhere in your actual code)
        // Set the desired capabilities and initialize the driver properly
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        // Top One post after reload post 1 without like
        testEssentials.switchContext("NATIVE_APP");
        driver.findElements(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                +".scrollIntoView(new UiSelector()"+".textMatches(\""+"Liked"+"\").instance(0))"));

        // Click the like button & check "Liked" status --LIKE TEST--
        WebElement likeId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Liked")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", likeId));
        Thread.sleep(2000);
        testEssentials.switchContext("FLUTTER");
        Assert.assertEquals(find.text("Like").getText(), "Like");
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
