package hw5;

import base.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureAttachmentListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.DatesPage;
import pageObjects.SelenideHomePage;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Users.PITER_CHAILOVSKII;

@Story("Check UI elements on HomePage and Dates Page (modified)")
@Listeners({AllureAttachmentListener.class})
@Feature("Home page menus; Dates page sliders (modified)")
public class ModifiedDatesPageTest extends TestBase {


    private SelenideHomePage homePage;
    private DatesPage datesPage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = page(SelenideHomePage.class);
        datesPage = page(DatesPage.class);
    }

    @AfterMethod
    public void afterMethod() {
        getWebDriver().close();
    }


    @Test
    public void checkDatesAndHomePageUIElements() {

        // S1: Open test site by URL
        homePage.openPage();

        // S2: Assert browser title
        homePage.checkTitle();

        // S3: Perform login
        homePage.login(PITER_CHAILOVSKII.getLogin(), PITER_CHAILOVSKII.getPassword());

        // S4: Assert User name in the left-top side of screen that user is logged in
        homePage.checkUserName(PITER_CHAILOVSKII.getName());

        // S5: Open Dates page through Service in the header -> Dates button
        homePage.openDatesPage();

        // S6: Set left slider to the most left position, right slider - to the most right
        datesPage.setSlider(0, 100);

        // S7: Assert that for "From" and "To" sliders on the most left and right positions
        // there are logs rows with corresponding values
        datesPage.checkSliderLogEntries(0, 100);

        // S8: Set left and right sliders to the most left position
        datesPage.setSlider(0, 0);

        // S9: Assert that for "From" and "To" sliders on the most left position
        // there are logs rows with corresponding values
        datesPage.checkSliderLogEntries(0, 0);

        // S10: Set left and right sliders to the most right position
        datesPage.setSlider(100, 100);

        // S11: Assert that for "From" and "To" sliders on the most right position
        // there are logs rows with corresponding values
        datesPage.checkSliderLogEntries(100, 100);

        // S12: Set left and right sliders to some not extreme positions
        datesPage.setSlider(30, 70);

        // S13: Assert that for "From" and "To" sliders on the some not extreme positions
        // there are logs rows with corresponding values
        datesPage.checkSliderLogEntries(30, 70);
    }
}


