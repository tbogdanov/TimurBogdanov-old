package hw2.ex1;

import enums.Links;
import enums.Strings;
import org.openqa.selenium.WebElement;
import utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static java.lang.System.setProperty;


/**
 * Created by Timur Bogdanov on 28.05.18.
 */
public class DataProvidedTest {

    private ChromeOptions options;
    private HashMap<String, Object> chromePrefs;
    private final String driverPath = "src/test/resources/chromedriver.exe";


    @DataProvider
    public Object[][] benefitDataProvider() {
        return TestUtils.extractTestParamsFromString(Strings.BENEFITS.getText(), "; ");
    }

    @BeforeMethod
    public void beforeClass() {
        setProperty("webdriver.chrome.driver", driverPath);

        // Preparing chromePrefs for a driver
        chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", "target");

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

    }

    @Test(dataProvider = "benefitDataProvider", threadPoolSize = 4)
    public void siteHasSpecificBenefitStringTest(int id, String expectedBenefit) {

        // Open test site by URL
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(Links.HOME_PAGE.getUrl());

        // Assert that there is a specific benefit test under the specific icon
        WebElement benefitItem = driver.findElement(By.xpath("//div[@class='col-sm-3'][" + id + "]/div/span[@class='benefit-txt']"));

        Assert.assertTrue(benefitItem.isDisplayed());
        Assert.assertEquals(benefitItem.getText(), expectedBenefit);

        // Close the driver
        driver.close();
    }


}
