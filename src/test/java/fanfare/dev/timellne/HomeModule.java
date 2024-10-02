package fanfare.dev.timellne;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import pro.truongsinh.appium_flutter.FlutterFinder;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeModule {
    private AndroidDriver driver;
    private FlutterFinder find;

    public static String myProfileAccessId = "Ice Cube 2.0 \uD83E\uDDCA\n" +
            "a moment ago";
    public static String userNametext = "Channel";

    public static String showall1 = "(//android.view.View[@content-desc=\"Show all >\"])[1]";
    public static String showall2 = "(//android.view.View[@content-desc=\"Show all >\"])[2]";
    public static String showall = "//android.view.View[@content-desc=\"Show all >\"]";
    public static String showallId = "Show all >";
    public static String videoContestXpath = "//android.view.View[@content-desc=\"FF Test VC2\n" + "F:points\"]/android.widget.ImageView[1]";
    public static String videoFile = "(//android.widget.ImageView[@resource-id=\"com.google.android.providers.media.module:id/icon_thumbnail\"])[2]";
    public static String followiconXpath1 = "//android.widget.ImageView[@content-desc=\"Games\n" +
            "Contests\n" +
            "(Participate & Win)\n" +
            "Brands\n" +
            "(Follow for Offers)\n" +
            "Offers\n" +
            "(Click & Get)\n" +
            "Top Selling\n" +
            "Earn F:Points\n" +
            "Refer & Earn\n" +
            "Invite Your Friends & Earn \n" +
            "50\n" +
            " F:Points\n" +
            "Top Users\"]/android.view.View[6]/android.view.View/android.view.View[4]/android.widget.ImageView[2]";

    public static String shareNowBtnXpath = "//android.view.View[@content-desc=\"Share Now\"]";
    public static String buyNowBtnXpath = "//android.view.View[@content-desc=\"Buy Now\"]";

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

    @Test(priority = 1)
    @Description("Home contest Tabs And Buttons Test")
    @Step("Home contest Tabs And Buttons Test")
    public void contestTabsAndButtonsTest() throws InterruptedException {

        //waiting 35 Sec for the app to load community
        testEssentials.switchContext("NATIVE_APP");
        Duration timeout = Duration.ofSeconds(35);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //click home
        WebElement ClickHomeId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Home")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ClickHomeId));
        Thread.sleep(2000);

        //select showall1 selector
        WebElement showall1ElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showall1)));


        //click contest video tab
        WebElement ContestVideoTabId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Video")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ContestVideoTabId));
        //click show all
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showall1ElementId));
        Thread.sleep(5000);
        //click showall>
        WebElement showallVideoId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(showallId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showallVideoId));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //click contest quiz tab
        WebElement ContestQuizTabId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Quiz")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ContestQuizTabId));
        //click show all
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showall1ElementId));
        Thread.sleep(5000);
        //click showall>
        WebElement showallQuizId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(showallId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showallQuizId));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //click contest poll tab
        WebElement ContestPollTabId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Poll")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ContestPollTabId));
        //click show all
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showall1ElementId));
        Thread.sleep(5000);
        //click showall>
        WebElement showallPoolId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(showallId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showallPoolId));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //click contest all tab
        WebElement ContestAllTabId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("All")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ContestAllTabId));
        //click show all
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showall1ElementId));
        Thread.sleep(5000);
        //click showall>
        WebElement showallAllId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(showallId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showallAllId));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);
        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);


    }

    //@Test(priority = 2)
    @Description("Participate video contest Test")
    @Step("Participate video contest Test")
    public void joinVideoContestTest() throws InterruptedException {

        testEssentials.switchContext("NATIVE_APP");
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //click videoContest Open
        WebElement videoContestElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoContestXpath)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", videoContestElementId));
        Thread.sleep(2000);

        //click Join Contest
        WebElement joinContestId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Join Contest")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", joinContestId));
        Thread.sleep(2000);

        //check Error Toast to follow
        testEssentials.toastchecker("Oops!\n" +
                "You are not following the brand. Please follow the brand to continue with the contest");
        Thread.sleep(2000);

        //click Follow Now
        WebElement followNowId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Follow Now")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", followNowId));
        Thread.sleep(2000);

        //click Join Contest
        WebElement joinContestID = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Join Contest")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", joinContestID));
        Thread.sleep(2000);

        //click Gallery icon
        WebElement GalleryId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gallery")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", GalleryId));
        Thread.sleep(2000);

        //select file from video tab
        WebElement videoFileElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoFile)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", videoFileElementId));
        Thread.sleep(2000);

        //select tick mark
        WebElement tickMarkElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[4]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", tickMarkElementId));
        Thread.sleep(2000);

        //insert Title
        WebElement titleElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]")));
        titleElementId.click();
        Thread.sleep(1000);
        titleElementId.sendKeys("Automation-Joining-Contest!");
        Thread.sleep(2000);

        //insert Description
        WebElement descriptionElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[2]")));
        descriptionElementId.click();
        descriptionElementId.sendKeys("Automation.. Joining.. Video.. Contest.. Description..");
        Thread.sleep(2000);

        //click publish
        WebElement publishElementId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Publish")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", publishElementId));
        Thread.sleep(7000);

        testEssentials.toastchecker("Latest submission");

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //click videoContest Open
        WebElement videoContestElementIdR = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoContestXpath)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", videoContestElementIdR));
        Thread.sleep(2000);

        //unfollow fanfare
        WebElement fanfareElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ScrollView/android.widget.ImageView[2]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", fanfareElementId));
        Thread.sleep(4000);

        //click unfollow
        WebElement unfollowElementId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Unfollow")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", unfollowElementId));
        Thread.sleep(2000);


        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    @Description("follow unfollow brands Test")
    @Step("follow unfollow brands Test")
    public void brandsFollowTest() throws InterruptedException {

        testEssentials.switchContext("NATIVE_APP");
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        int startX = 320; //change
        int endX = 320;   //these
        int startY = 1200; //to match your
        int endY = 1000;   //swipe direction
        Map<String, Object> params = new HashMap<>();
        params.put("command", "input");
        params.put("args", Lists.newArrayList("swipe", startX, startY, endX, endY));
        while (driver.findElements(By.xpath(followiconXpath1)).size() == 0) {
            driver.executeScript("mobile: shell", params);
            Thread.sleep(2000);
        }

        //select HOME FOLLOW ICON
        WebElement followElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(followiconXpath1)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", followElementId));
        Thread.sleep(3000);

        //select showall2 selector
        WebElement showall2ElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showall2)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showall2ElementId));
        Thread.sleep(2000);

        //click showall>
        WebElement showallVideoId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(showallId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showallVideoId));
        Thread.sleep(2000);

        //click following brand page
        WebElement OgerioId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Manlyy")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", OgerioId));
        Thread.sleep(2000);

        //click unfollow
        WebElement unfollowElementId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Unfollow")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", unfollowElementId));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    @Description("top selling products Test")
    @Step("top selling products Test")
    public void topSellingTest() throws InterruptedException {

        testEssentials.switchContext("NATIVE_APP");
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //scroll to the Top Selling buy now btn
        testEssentials.scrollToText(buyNowBtnXpath);
        Thread.sleep(2000);

        //click buy now btn
        WebElement buyNowElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(buyNowBtnXpath)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", buyNowElementId));
        Thread.sleep(2000);

        //add product to cart btn
        WebElement addToCartId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Add to Cart")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", addToCartId));
        Thread.sleep(2000);

        //click continue shopping btn
        WebElement continueShoppingId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Continue Shopping")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", continueShoppingId));
        Thread.sleep(2000);

        //go back home
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //click showAll btn on Top Selling
        WebElement showall1ElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showall1)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showall1ElementId));
        Thread.sleep(2000);

        //click buy now 1
        WebElement buyNow1ElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.view.View[@content-desc=\"Buy Now\"])[1]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", buyNow1ElementId));
        Thread.sleep(2000);

        //buy now in product page
        WebElement buyNowId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Buy Now")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", buyNowId));
        Thread.sleep(2000);

        //delete items from cart
        WebElement deleteCartItemsId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Delete")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", deleteCartItemsId));
        Thread.sleep(2000);

        //click ok in delete items from cart
        WebElement okDeleteCartItemsId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Ok")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", okDeleteCartItemsId));
        Thread.sleep(2000);

        WebElement cartItemsEmptyId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Your cart is empty!")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", cartItemsEmptyId));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);

        //go back
        ((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 4"));
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        //stops the service that is called in BeforeTest
        service.stop();
        System.out.println("📛..📛..📛..Appium Server Closed!..📛..📛..📛");
    }
}