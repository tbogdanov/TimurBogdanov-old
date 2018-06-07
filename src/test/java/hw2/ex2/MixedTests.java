package hw2.ex2;

import enums.Links;
import enums.Strings;
import enums.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.setProperty;

/**
 * Created by Timur Bogdanov on 05.06.18.
 */
public class MixedTests {

    private ChromeOptions options;
    private HashMap<String, Object> chromePrefs;
    private final String driverPath = "src/test/resources/chromedriver.exe";

    @Test(groups = {"Regression"})
    public void siteHasBasicElementsTest() {

        setProperty("webdriver.chrome.driver", driverPath);

        // Preparing chromePrefs for a driver
        chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", "target");

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        // S1: Open test site by URL
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(Links.HOME_PAGE.getUrl());

        // S2: Assert Browser title
        Assert.assertEquals(driver.getTitle(), Strings.TITLE.getText());

        // S3: Perform login
        WebElement userIcon = driver.findElement(By.cssSelector(".profile-photo"));
        userIcon.click();

        driver.findElement(By.cssSelector("#Name")).sendKeys(Users.PITER_CHAILOVSKII.getLogin());
        driver.findElement(By.cssSelector("#Password")).sendKeys(Users.PITER_CHAILOVSKII.getPassword());
        driver.findElement(By.cssSelector(".form-horizontal button[type = 'submit']")).click();

        // S4: Assert User name in the left-top side of screen that user is logged in
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(userName.isDisplayed());
        Assert.assertEquals(userName.getText(), Users.PITER_CHAILOVSKII.getName());

        // S5: Assert Browser title
        Assert.assertEquals(driver.getTitle(), Strings.TITLE.getText());

        // S6: Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerItems = driver.findElements(By.cssSelector(".uui-navigation.nav.navbar-nav.m-l8>li"));
        List<String> headerItemStrings = new LinkedList<>();

        for (WebElement item : headerItems) {
            Assert.assertTrue(item.isDisplayed());
            headerItemStrings.add(item.getText());
        }

        Assert.assertEquals(headerItems.size(), 4);
        /* This way it checks if all items of those lists are equal
           and shows specific strings that are inequal between expected and actual
           in Expected/Actual form.
           It's more useful than AssertTrue(headerItemStrings.containsAll(...))
           We demand the equality of order as well because the items shouldn't be misplaced. */
        Assert.assertEquals(headerItemStrings,
                Arrays.asList(Strings.HEADER_NAVBAR.getText().split("; ")));

        // S7: Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> benefitIcons = driver.findElements(By.cssSelector(".icons-benefit"));

        for (WebElement item : benefitIcons) {
            Assert.assertTrue(item.isDisplayed());
        }

        // S8: Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> benefitTextBlocks = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> benefitTextStrings = new LinkedList<>();

        for (WebElement item : benefitTextBlocks) {
            Assert.assertTrue(item.isDisplayed());
            benefitTextStrings.add(item.getText());
        }

        Assert.assertEquals(benefitTextBlocks.size(), 4);
        Assert.assertEquals(benefitTextStrings,
                Arrays.asList(Strings.BENEFITS.getText().split("; ")));

        // S9: Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector(".main-title.text-center"));
        Assert.assertTrue(mainHeader.isDisplayed());
        Assert.assertEquals(mainHeader.getText(), Strings.MAIN_HEADER_TITLE.getText());

        WebElement mainHeaderText = driver.findElement(By.cssSelector(".main-txt.text-center"));
        Assert.assertTrue(mainHeaderText.isDisplayed());
        Assert.assertEquals(mainHeaderText.getText(), Strings.MAIN_HEADER_TEXT.getText());

        // S10: Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector(".main-content"))
                .findElement(By.tagName("a"));
        Assert.assertTrue(subHeader.isDisplayed());
        Assert.assertEquals(subHeader.getText(), Strings.SUB_HEADER_TITLE.getText());

        // S11: Assert it has a proper URL
        Assert.assertEquals(subHeader.getAttribute("href"), Links.JDI_PAGE.getUrl());

        // S12: Assert that there is Left Section
        Assert.assertTrue(driver.findElement(By.cssSelector(".sidebar-menu")).isDisplayed());

        // S13: Assert that there is Footer
        Assert.assertTrue(driver.findElement(By.cssSelector(".footer-content.overflow")).isDisplayed());

        // Close the driver
        driver.close();
    }

    @Test(groups = {"Regression"})
    public void siteHasBasicElementsTest1() {

        setProperty("webdriver.chrome.driver", driverPath);

        // Preparing chromePrefs for a driver
        chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", "target");

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        // S1: Open test site by URL
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(Links.HOME_PAGE.getUrl());

        // S2: Assert Browser title
        Assert.assertEquals(driver.getTitle(), Strings.TITLE.getText());

        // S3: Perform login
        WebElement userIcon = driver.findElement(By.cssSelector(".profile-photo"));
        userIcon.click();

        driver.findElement(By.cssSelector("#Name")).sendKeys(Users.PITER_CHAILOVSKII.getLogin());
        driver.findElement(By.cssSelector("#Password")).sendKeys(Users.PITER_CHAILOVSKII.getPassword());
        driver.findElement(By.cssSelector(".form-horizontal button[type = 'submit']")).click();

        // S4: Assert User name in the left-top side of screen that user is logged in
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(userName.isDisplayed());
        Assert.assertEquals(userName.getText(), Users.PITER_CHAILOVSKII.getName());

        // S5: Assert Browser title
        Assert.assertEquals(driver.getTitle(), Strings.TITLE.getText());

        // S6: Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerItems = driver.findElements(By.cssSelector(".uui-navigation.nav.navbar-nav.m-l8>li"));
        List<String> headerItemStrings = new LinkedList<>();

        for (WebElement item : headerItems) {
            Assert.assertTrue(item.isDisplayed());
            headerItemStrings.add(item.getText());
        }

        Assert.assertEquals(headerItems.size(), 4);
        /* This way it checks if all items of those lists are equal
           and shows specific strings that are inequal between expected and actual
           in Expected/Actual form.
           It's more useful than AssertTrue(headerItemStrings.containsAll(...))
           We demand the equality of order as well because the items shouldn't be misplaced. */
        Assert.assertEquals(headerItemStrings,
                Arrays.asList(Strings.HEADER_NAVBAR.getText().split("; ")));

        // S7: Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> benefitIcons = driver.findElements(By.cssSelector(".icons-benefit"));

        for (WebElement item : benefitIcons) {
            Assert.assertTrue(item.isDisplayed());
        }

        // S8: Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> benefitTextBlocks = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> benefitTextStrings = new LinkedList<>();

        for (WebElement item : benefitTextBlocks) {
            Assert.assertTrue(item.isDisplayed());
            benefitTextStrings.add(item.getText());
        }

        Assert.assertEquals(benefitTextBlocks.size(), 4);
        Assert.assertEquals(benefitTextStrings,
                Arrays.asList(Strings.BENEFITS.getText().split("; ")));

        // S9: Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector(".main-title.text-center"));
        Assert.assertTrue(mainHeader.isDisplayed());
        Assert.assertEquals(mainHeader.getText(), Strings.MAIN_HEADER_TITLE.getText());

        WebElement mainHeaderText = driver.findElement(By.cssSelector(".main-txt.text-center"));
        Assert.assertTrue(mainHeaderText.isDisplayed());
        Assert.assertEquals(mainHeaderText.getText(), Strings.MAIN_HEADER_TEXT.getText());

        // S10: Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector(".main-content"))
                .findElement(By.tagName("a"));
        Assert.assertTrue(subHeader.isDisplayed());
        Assert.assertEquals(subHeader.getText(), Strings.SUB_HEADER_TITLE.getText());

        // S11: Assert it has a proper URL
        Assert.assertEquals(subHeader.getAttribute("href"), Links.JDI_PAGE.getUrl());

        // S12: Assert that there is Left Section
        Assert.assertTrue(driver.findElement(By.cssSelector(".sidebar-menu")).isDisplayed());

        // S13: Assert that there is Footer
        Assert.assertTrue(driver.findElement(By.cssSelector(".footer-content.overflow")).isDisplayed());

        // Close the driver
        driver.close();
    }

    @Test(groups = {"Smoke"})
    public void siteHasBasicElementsTest2() {

        setProperty("webdriver.chrome.driver", driverPath);

        // Preparing chromePrefs for a driver
        chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", "target");

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        // S1: Open test site by URL
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(Links.HOME_PAGE.getUrl());

        // S2: Assert Browser title
        Assert.assertEquals(driver.getTitle(), Strings.TITLE.getText());

        // S3: Perform login
        WebElement userIcon = driver.findElement(By.cssSelector(".profile-photo"));
        userIcon.click();

        driver.findElement(By.cssSelector("#Name")).sendKeys(Users.PITER_CHAILOVSKII.getLogin());
        driver.findElement(By.cssSelector("#Password")).sendKeys(Users.PITER_CHAILOVSKII.getPassword());
        driver.findElement(By.cssSelector(".form-horizontal button[type = 'submit']")).click();

        // S4: Assert User name in the left-top side of screen that user is logged in
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertTrue(userName.isDisplayed());
        Assert.assertEquals(userName.getText(), Users.PITER_CHAILOVSKII.getName());

        // S5: Assert Browser title
        Assert.assertEquals(driver.getTitle(), Strings.TITLE.getText());

        // S6: Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerItems = driver.findElements(By.cssSelector(".uui-navigation.nav.navbar-nav.m-l8>li"));
        List<String> headerItemStrings = new LinkedList<>();

        for (WebElement item : headerItems) {
            Assert.assertTrue(item.isDisplayed());
            headerItemStrings.add(item.getText());
        }

        Assert.assertEquals(headerItems.size(), 4);
        /* This way it checks if all items of those lists are equal
           and shows specific strings that are inequal between expected and actual
           in Expected/Actual form.
           It's more useful than AssertTrue(headerItemStrings.containsAll(...))
           We demand the equality of order as well because the items shouldn't be misplaced. */
        Assert.assertEquals(headerItemStrings,
                Arrays.asList(Strings.HEADER_NAVBAR.getText().split("; ")));

        // S7: Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> benefitIcons = driver.findElements(By.cssSelector(".icons-benefit"));

        for (WebElement item : benefitIcons) {
            Assert.assertTrue(item.isDisplayed());
        }

        // S8: Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> benefitTextBlocks = driver.findElements(By.cssSelector(".benefit-txt"));
        List<String> benefitTextStrings = new LinkedList<>();

        for (WebElement item : benefitTextBlocks) {
            Assert.assertTrue(item.isDisplayed());
            benefitTextStrings.add(item.getText());
        }

        Assert.assertEquals(benefitTextBlocks.size(), 4);
        Assert.assertEquals(benefitTextStrings,
                Arrays.asList(Strings.BENEFITS.getText().split("; ")));

        // S9: Assert a text of the main header
        WebElement mainHeader = driver.findElement(By.cssSelector(".main-title.text-center"));
        Assert.assertTrue(mainHeader.isDisplayed());
        Assert.assertEquals(mainHeader.getText(), Strings.MAIN_HEADER_TITLE.getText());

        WebElement mainHeaderText = driver.findElement(By.cssSelector(".main-txt.text-center"));
        Assert.assertTrue(mainHeaderText.isDisplayed());
        Assert.assertEquals(mainHeaderText.getText(), Strings.MAIN_HEADER_TEXT.getText());

        // S10: Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.cssSelector(".main-content"))
                .findElement(By.tagName("a"));
        Assert.assertTrue(subHeader.isDisplayed());
        Assert.assertEquals(subHeader.getText(), Strings.SUB_HEADER_TITLE.getText());

        // S11: Assert it has a proper URL
        Assert.assertEquals(subHeader.getAttribute("href"), Links.JDI_PAGE.getUrl());

        // S12: Assert that there is Left Section
        Assert.assertTrue(driver.findElement(By.cssSelector(".sidebar-menu")).isDisplayed());

        // S13: Assert that there is Footer
        Assert.assertTrue(driver.findElement(By.cssSelector(".footer-content.overflow")).isDisplayed());

        // Close the driver
        driver.close();
    }

}
