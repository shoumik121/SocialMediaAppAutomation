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
import pro.truongsinh.appium_flutter.FlutterFinder;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CreatePost {

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

    //@Test(priority = 1)
    @Test (enabled = false)
    @Description("Create Post With Text Positive Test")
    @Step("Create Post With Text Positive Test")
    public void createPostWithTextPositiveTest() throws InterruptedException {

        //waiting 35 Sec for the app to load community
        testEssentials.switchContext("NATIVE_APP");
        Duration timeout = Duration.ofSeconds(35);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //tap '+' button
        switchContext("NATIVE_APP");
        WebElement postElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[3]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", postElementId));
        Thread.sleep(2000);

        //insert Title
        var title = "⛰️ Discover the Beauty of Nature: Weekend Hiking Adventure!";
        switchContext("FLUTTER");
        find.bySemanticsLabel("Title").sendKeys(title);
        Assert.assertEquals(find.text(title).getText(), title);
        Thread.sleep(2000);

        //insert Description
        var description = "💫 Join us this weekend for an unforgettable hiking experience! " +
                "👽 Explore scenic trails, enjoy breathtaking views, and connect with fellow nature enthusiasts." +
                "🍎 Don't miss out on this chance to rejuvenate your mind and body. See you on the trails!";

        find.bySemanticsLabel("Description").sendKeys(description);
        Assert.assertEquals(find.text(description).getText(), description);
        Thread.sleep(2000);

        //insert HashTags
        var hashtag = "Hiking ";
        find.bySemanticsLabel("Add HashTag").sendKeys(hashtag);
        //((AndroidDriver) driver).executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 62"));
        Thread.sleep(2000);

        //click Submit
        switchContext("NATIVE_APP");
        WebElement SubmitId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId));
        Thread.sleep(5000);

        //Open User Profile
        var profileAccessibilityId = "Fanfare bot\uD83C\uDFB8(aut...\n" +
                "a moment ago";
        WebElement ProfileId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(profileAccessibilityId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ProfileId));
        Thread.sleep(2000);

        //verify User Profile Name
        switchContext("FLUTTER");
        Assert.assertEquals(find.text("Fanfare bot🎸(automation)").getText(), "Fanfare bot🎸(automation)");
        Thread.sleep(2000);
    }

    //@Test(priority = 2)
    @Test (enabled = false)
    @Description("Create Post With Text Negative Test")
    @Step("Create Post With Text Negative Test")
    public void createPostWithTextNegativeTest() throws InterruptedException {

        //waiting 35 Sec for the app to load community
        testEssentials.switchContext("NATIVE_APP");
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //tap 'Tap here. Let's create a post...' button
        WebElement AddPostId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Tap here. Let's create a post...")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", AddPostId));
        Thread.sleep(2000);

        //click Submit without hashtag
        WebElement SubmitId1 = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId1));
        Thread.sleep(1000);

        //check blank post toasts "Post can't be empty"
        testEssentials.toastchecker("Post can't be empty");

        //insert HashTags
        switchContext("FLUTTER");
        var hashtag = "Hiking ";
        find.bySemanticsLabel("Add HashTag").sendKeys(hashtag);
        Thread.sleep(2000);

        //click Submit
        switchContext("NATIVE_APP");
        WebElement SubmitId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId));
        Thread.sleep(1000);

        //check blank post toasts "At least one of title, description and content must be provided."
        testEssentials.toastchecker("At least one of title, description and content must be provided.");
    }

    //@Test(priority = 3)
    @Test (enabled = false)
    @Description("Create Post With Text & Image(s) Positive Test")
    @Step("Create Post With Text & Image(s) Positive Test")
    public void createPostWithTextImagesPositiveTest() throws InterruptedException {

        Duration timeout = Duration.ofSeconds(6);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //tap 'Tap here. Let's create a post...' button
        switchContext("NATIVE_APP");
        WebElement AddPostId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Tap here. Let's create a post...")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", AddPostId));
        Thread.sleep(2000);

        //tap addImage/video (+) button
        WebElement addImageElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", addImageElementId));
        Thread.sleep(2000);

        //tap gallery
        WebElement galleryId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gallery")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", galleryId));
        Thread.sleep(2000);

        //tap Images in Recents
        WebElement recentVideosId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"Images\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", recentVideosId));
        Thread.sleep(2000);

        //mark four IMGs (4)
        for (int i = 1; i < 5; i++) {

            //IMG selector
            var hexpath = ("(//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"])[1]");
            WebElement IMGId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(hexpath)));

            // Perform a long press gesture on the element
            driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) IMGId).getId(),
                    "duration", 2000 // duration in milliseconds
            ));
        }

        //tap select
        WebElement selectId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id=\"com.google.android.documentsui:id/action_menu_select\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", selectId));
        Thread.sleep(30000);

        //insert Title
        var title = "🌍 Unleash the power of Mother Nature: Ice Cubes ️‍🧊";
        switchContext("FLUTTER");
        find.bySemanticsLabel("Title").sendKeys(title);
        Assert.assertEquals(find.text(title).getText(), title);
        Thread.sleep(2000);

        //insert Description
        var description = "🍃 Experience the natural cooling and refreshing effects of ice cubes, harnessing the pure and simple power of 🌬️ water transformed by freezing temperatures. Perfect for chilling drinks, soothing minor burns, and preserving perishables, ice cubes are a versatile and essential element in everyday life.";

        find.bySemanticsLabel("Description").sendKeys(description);
        Assert.assertEquals(find.text(description).getText(), description);
        Thread.sleep(2000);

        //insert HashTags
        var hashtag = "Science ";
        find.bySemanticsLabel("Add HashTag").sendKeys(hashtag);
        Thread.sleep(2000);

        //click Submit
        switchContext("NATIVE_APP");
        WebElement SubmitId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId));
        Thread.sleep(5000);

        //Open User Profile
        var profileAccessibilityId = "Fanfare bot\uD83C\uDFB8(aut...\n" +
                "a moment ago";
        WebElement ProfileId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(profileAccessibilityId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ProfileId));
        Thread.sleep(2000);

        //verify User Profile Name
        switchContext("FLUTTER");
        Assert.assertEquals(find.text("Fanfare bot🎸(automation)").getText(), "Fanfare bot🎸(automation)");
        Thread.sleep(2000);
    }

    //@Test(priority = 4)
    @Test (enabled = false)
    @Description("Create Post With Text & Image(s) Negative Test")
    @Step("Create Post With Text & Image(s) Negative Test")
    public void createPostWithTextImagesNegativeTest() throws InterruptedException {

        //waiting 35 Sec for the app to load community
        testEssentials.switchContext("NATIVE_APP");
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //tap 'Tap here. Let's create a post...' button
        WebElement AddPostId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Tap here. Let's create a post...")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", AddPostId));
        Thread.sleep(2000);

        //tap addImage/video (+) button
        WebElement addImageElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", addImageElementId));
        Thread.sleep(2000);

        //tap gallery
        WebElement galleryId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gallery")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", galleryId));
        Thread.sleep(2000);

        //tap Images in Recents
        WebElement recentVideosId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"Images\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", recentVideosId));
        Thread.sleep(2000);

        //Corrupted IMG selector
        var corruptedImgId = ("//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_mime\"]");
        WebElement IMGId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(corruptedImgId)));

        // Perform a long press gesture on the element
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) IMGId).getId(),
                "duration", 2000 // duration in milliseconds
        ));

        //tap select
        WebElement selectId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id=\"com.google.android.documentsui:id/action_menu_select\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", selectId));
        Thread.sleep(15000);

        //insert Title
        var title = "❤️‍🔥 Unleash the power of Mother Nature: Ice Cubes ️‍🧊";
        switchContext("FLUTTER");
        find.bySemanticsLabel("Title").sendKeys(title);
        Assert.assertEquals(find.text(title).getText(), title);
        Thread.sleep(2000);

        //insert Description
        var description = "🥶 Experience the natural cooling and refreshing effects of ice cubes, harnessing the pure and simple power of 🌬️ water transformed by freezing temperatures. Perfect for chilling drinks, soothing minor burns, and preserving perishables, ice cubes are a versatile and essential element in everyday life.";

        find.bySemanticsLabel("Description").sendKeys(description);
        Assert.assertEquals(find.text(description).getText(), description);
        Thread.sleep(2000);

        //insert HashTags
        var hashtag = "CorruptedJPG ";
        find.bySemanticsLabel("Add HashTag").sendKeys(hashtag);
        Thread.sleep(2000);

        //click Submit
        switchContext("NATIVE_APP");
        WebElement SubmitId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId));
        Thread.sleep(5000);

        //Open User Profile
        var profileAccessibilityId = "Fanfare bot\uD83C\uDFB8(aut...\n" + "a moment ago";
        WebElement ProfileId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(profileAccessibilityId)));

        //Handeling App crash with exception handler
        for (int i = 0; i < 2; i++) {
            try {
                driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ProfileId));
                break; // Exit the loop if the script execution is successful
            } catch (Exception e) {
                e.printStackTrace();
                break; // Exit the loop if an exception occurs
            }
        }
        Thread.sleep(2000);

        //verify User Profile Name
        switchContext("FLUTTER");
        Assert.assertEquals(find.text("Fanfare bot🎸(automation)").getText(), "Fanfare bot🎸(automation)");
        Thread.sleep(2000);
    }

    //@Test(priority = 5)
    @Test (enabled = false)
    @Description("Create Post With Text & Video(s) Positive Test")
    @Step("Create Post With Text & Video(s) Positive Test")
    public void createPostWithTextVideosPositiveTest() throws InterruptedException {

        //Taking existing user from community To User Timeline
        testEssentials.communityToUserTimeline();

        //wait time
        Duration timeout = Duration.ofSeconds(300);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //tap 'Tap here. Let's create a post...' button
        switchContext("NATIVE_APP");
        WebElement AddPostId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Tap here. Let's create a post...")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", AddPostId));
        Thread.sleep(2000);

        //tap addImage/video (+) button
        WebElement addImageElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", addImageElementId));
        Thread.sleep(2000);

        //tap gallery
        WebElement galleryId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gallery")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", galleryId));
        Thread.sleep(2000);

        //tap Show roots in gallery
        WebElement showRootsId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Show roots")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", showRootsId));
        Thread.sleep(2000);

        //tap Recent in roots
        WebElement recentId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Recent\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", recentId));
        Thread.sleep(2000);

        //tap Videos in Recents
        WebElement recentVideosId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"Videos\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", recentVideosId));
        Thread.sleep(2000);

        //mark four VideoClips (3) 2.02mb + 1.41mb + 662kb
        for (int i = 1; i < 4; i++) {

            //video selector
            var videoXpathStr = ("(//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"])[2]");
            WebElement IMGId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoXpathStr)));

            // Perform a long press gesture on the element
            driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) IMGId).getId(),
                    "duration", 2000 // duration in milliseconds
            ));
        }

        //tap select
        WebElement selectId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id=\"com.google.android.documentsui:id/action_menu_select\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", selectId));
        Thread.sleep(2000);

        //insert Title
        var title = "📽️Three Random clips from Enemy 4.092mb : Ice Cube Productions🧊";
        switchContext("FLUTTER");
        find.bySemanticsLabel("Title").sendKeys(title);
        Assert.assertEquals(find.text(title).getText(), title);
        Thread.sleep(2000);

        //insert Description
        var description = "🍃Clip 1: Me with the twins breaking a leg 992kb." +
                "📲Clip 2: Snapping my phone 1.41mb." +
                "🚔Clip 3: Flexing in a car (uuuu.. i am so pretty 🧸) 662kb.";
        find.bySemanticsLabel("Description").sendKeys(description);
        Assert.assertEquals(find.text(description).getText(), description);
        Thread.sleep(2000);

        //insert HashTags
        var hashtag = "iamsoviolet ";
        find.bySemanticsLabel("Add HashTag").sendKeys(hashtag);
        Thread.sleep(2000);

        //click Submit
        switchContext("NATIVE_APP");
        WebElement SubmitId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId));
        Thread.sleep(20000);

        //Open User Profile
        var profileAccessibilityId = "Fanfare bot\uD83C\uDFB8(aut...\n" + "a moment ago";
        WebElement ProfileId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(profileAccessibilityId)));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ProfileId));
        Thread.sleep(2000);

        //verify User Profile Name
        switchContext("FLUTTER");
        Assert.assertEquals(find.text("Fanfare bot🎸(automation)").getText(), "Fanfare bot🎸(automation)");
        Thread.sleep(2000);
    }

    //@Test(priority = 6)
    @Test (enabled = false)
    @Description("Create Post With Text & Video(s) Negative Test")
    @Step("Create Post With Text & Video(s) Negative Test")
    public void createPostWithTextVideosNegativeTest() throws InterruptedException {

        //wait time
        Duration timeout = Duration.ofSeconds(60);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        //tap 'Tap here. Let's create a post...' button
        switchContext("NATIVE_APP");
        WebElement AddPostId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Tap here. Let's create a post...")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", AddPostId));
        Thread.sleep(2000);

        //tap addImage/video (+) button
        WebElement addImageElementId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", addImageElementId));
        Thread.sleep(2000);

        //tap gallery
        WebElement galleryId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gallery")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", galleryId));
        Thread.sleep(2000);

        //tap Videos in Recents
        WebElement recentVideosId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text=\"Videos\"]")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", recentVideosId));
        Thread.sleep(2000);

        //mark four VideoClip (1) 232kb
        for (int i = 1; i < 2; i++) {

            //video selector
            var videoXpathStr = ("(//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"])[1]");
            WebElement IMGId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoXpathStr)));

            // Perform a long press gesture on the element
            driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) IMGId).getId(),
                    "duration", 2000 // duration in milliseconds
            ));
        }

        //tap select
        WebElement selectId = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id=\"com.google.android.documentsui:id/action_menu_select\"]")));


        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", selectId));
        Thread.sleep(2000);

        //insert Title
        var title = "📽️Corrupted Video mp4 from Enemy 232kb : Ice Cube Productions🧊";
        switchContext("FLUTTER");
        find.bySemanticsLabel("Title").sendKeys(title);
        Assert.assertEquals(find.text(title).getText(), title);
        Thread.sleep(2000);

        //insert Description
        var description = "💀Corrupted Video mp4 from Enemy 232kb.🥷" +
                "💀Corrupted Video mp4 from Enemy 232kb.🧑‍🚀" +
                "💀Corrupted Video mp4 from Enemy 232kb.🧕";
        find.bySemanticsLabel("Description").sendKeys(description);
        Assert.assertEquals(find.text(description).getText(), description);
        Thread.sleep(2000);

        //insert HashTags
        var hashtag = "CorruptedVideo ";
        find.bySemanticsLabel("Add HashTag").sendKeys(hashtag);
        Thread.sleep(2000);

        //click Submit
        switchContext("NATIVE_APP");
        WebElement SubmitId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Submit")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", SubmitId));
        Thread.sleep(2000);

        //Open User Profile
        var profileAccessibilityId = "Fanfare bot\uD83C\uDFB8(aut...\n" + "a moment ago";
        WebElement ProfileId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(profileAccessibilityId)));

        //Handeling App crash with exception handler
        for (int i = 0; i < 2; i++) {
            try {
                driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", ProfileId));
                break; // Exit the loop if the script execution is successful
            } catch (Exception e) {
                e.printStackTrace();
                break; // Exit the loop if an exception occurs
            }
        }
        Thread.sleep(2000);

        //verify User Profile Name
        switchContext("FLUTTER");
        Assert.assertEquals(find.text("Fanfare bot🎸(automation)").getText(), "Fanfare bot🎸(automation)");
        Thread.sleep(2000);
    }

    @Test(priority = 7)
    //@Test (enabled = false)
    @Description("Community Top Fresh Post Like Positive Test")
    @Step("Community Top Fresh Post Like Positive Test")
    public void CommunityTopFreshPostLikeTest() throws InterruptedException {

        // Initialize the driver (Assuming it's done elsewhere in your actual code)
        // Set the desired capabilities and initialize the driver properly
        //Thread.sleep(20000);
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

    }

    @Test(priority = 8)
    //@Test (enabled = false)
    @Description("Community Top Fresh Post Unlike Positive Test")
    @Step("Community Top Fresh Post Unlike Positive Test")
    public void CommunityTopFreshPostUnlikeTest() throws InterruptedException {

        // Initialize the driver (Assuming it's done elsewhere in your actual code)
        // Set the desired capabilities and initialize the driver properly
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        // SamePost Click the unlike button & check "Like" status --UNLIKE TEST--
        testEssentials.switchContext("NATIVE_APP");
        WebElement unlikeId = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Liked")));
        driver.executeScript("mobile: clickGesture", ImmutableMap.of("elementId", unlikeId));
        testEssentials.switchContext("FLUTTER");
        Assert.assertEquals(find.text("Like").getText(), "Like");
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
