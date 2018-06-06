package hw3;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import pageObjects.HomePage;

import enums.Strings;
import enums.Users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.HashMap;

import static java.lang.System.setProperty;

/**
 * Created by Timur Bogdanov on 28.05.18.
 */
public class BasicElementsTest {

    private final String driverPath = "src/test/resources/chromedriver.exe";

    private ChromeOptions options;
    private WebDriver driver;
    private HomePage homePage;

    @DataProvider
    public Object[][] headerMenuDataProvider() {
        return TestUtils.extractTestParamsFromString(Strings.HEADER_NAVBAR.getText(), "; ");
    }

    @DataProvider
    public Object[][] benefitDataProvider() {
        return TestUtils.extractTestParamsFromString(Strings.BENEFITS.getText(), "; ");
    }

    @BeforeClass
    public void beforeClass() {
        setProperty("webdriver.chrome.driver", driverPath);

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", "target");

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterClass
    public void afterClass() {
        // Close the driver
        driver.close();
    }

    @Test
    public void siteHasBasicElementsTest() {

        // S1: Open test site by URL
        homePage.openHomePage(driver);

        // S2: Assert Browser title
        homePage.checkHomePageTitle(driver);

        // S3: Perform login
        homePage.login(Users.PITER_CHAILOVSKII.getLogin(), Users.PITER_CHAILOVSKII.getPassword());

        // S4: Assert User name in the left-top side of screen that user is logged in
        homePage.checkUsername(Users.PITER_CHAILOVSKII.getName());

        // S5: Assert Browser title
        homePage.checkHomePageTitle(driver);

        // S6: Assert that there are 4 items on the header section are displayed and they have proper texts
        //homePage.checkAllHeaderMenuItems(); - there is a parameterized form

        // S7: Assert that there are 4 images on the Index Page and they are displayed
        homePage.checkBenefitIcons();

        // S8: Assert that there are 4 texts on the Index Page under icons and they have proper text
        //homePage.checkAllBenefitTexts(); - there is a parameterized form

        // S9: Assert a text of the main header
        homePage.checkMainHeaderTitle();
        homePage.checkMainHeaderText();

        // S10: Assert a text of the sub header
        homePage.checkSubHeader();

        // S11: Assert it has a proper URL
        homePage.checkSubHeaderLink();

        // S12: Assert that there is Left Section
        homePage.checkLeftSection();

        // S13: Assert that there is Footer
        homePage.checkFooter();


    }

    @Test(dataProvider = "headerMenuDataProvider", threadPoolSize = 4)
    public void siteHasProperHeaderMenuItemTest(int id, String expected) {
        // S6 in the parametrized form
        homePage.checkHeaderMenuItem(driver, id, expected);
    }

    @Test(dataProvider = "benefitDataProvider", threadPoolSize = 4)
    public void siteHasProperBenefitStringTest(int id, String expected) {
        // S8 in the parametrized form
        homePage.checkBenefitText(driver, id, expected);
    }


}
