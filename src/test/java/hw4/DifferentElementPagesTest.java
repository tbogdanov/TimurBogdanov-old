package hw4;


import base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.DifferentElementsPage;
import pageObjects.SelenideHomePage;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Users.PITER_CHAILOVSKII;
import static enums.Strings.*;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureAttachmentListener;

/**
 * Created by Timur Bogdanov on 23.06.18.
 */

@Story("Check UI elements on Home Page and Different Elements Page")
@Listeners({AllureAttachmentListener.class})
@Feature("Home page menus; Different elements page checkboxes, radios, and dropdowns")
public class DifferentElementPagesTest extends TestBase {

    private SelenideHomePage homePage;
    private DifferentElementsPage differentElementsPage;

    @BeforeMethod
    public void beforeMethod() {
        homePage = page(SelenideHomePage.class);
        differentElementsPage = page(DifferentElementsPage.class);
    }

    @AfterMethod
    public void afterMethod() {
        getWebDriver().close();
    }

    @Test
    public void checkHomeDifferentPagesFunctionalityTest() {

        //S1: Open test site by URL
        homePage.openPage();

        //S2: Assert browser title
        homePage.checkTitle();

        //S3: Perform login
        homePage.login(PITER_CHAILOVSKII.getLogin(), PITER_CHAILOVSKII.getPassword());

        //S4: Assert User name in the left-top side of screen that user is loggined
        homePage.checkUserName(PITER_CHAILOVSKII.getName());

        //S5: Check interface on Home Page
        homePage.checkBenefitElements();
        homePage.checkHeaderTexts();

        //S6: Check that dropdown "Service" in the header contains property options
        homePage.checkHeaderServiceMenu();

        //S7: Check that dropdown "Service" in the left section contains property options
        homePage.checkLeftServiceMenu();

        //S8: Open Different Elements page through Service in the header -> Different Element button
        homePage.openDifferentElementsPage();

        //S9: Check interface on Different Elements page
        differentElementsPage.checkInternalElements();

        //S10: Assert that there is Right Section
        differentElementsPage.checkRightSection();

        //S11: Assert that there is Left Section
        differentElementsPage.checkLeftSection();

        //S12: Select checkboxes
        String[] testedCheckboxes = new String[] {CB_WATER.toString(), CB_WIND.toString()};
        differentElementsPage.toggleCheckboxes(testedCheckboxes);

        //S13: Assert that for each checkbox there is an individual log row
        // and value corresponded to the checked status of checkbox
        differentElementsPage.checkCheckboxLogEntries(TRUE.toString(), testedCheckboxes);

        //S14: Select radio button
        differentElementsPage.selectRadioButton(RB_SELEN.toString());

        //S15: Assert that there is a log row for that radio button
        differentElementsPage.checkRadioLogEntry(RB_SELEN.toString());

        //16 Select in dropdown
        differentElementsPage.selectDropdownOption(DD_YELLOW.toString());

        //17 Assert that for dropdown there is an individual log row
        // and value corresponded to the selected value
        differentElementsPage.checkDropdownLogEntry(DD_YELLOW.toString());

        //18 Unselect in checkboxes
        differentElementsPage.toggleCheckboxes(testedCheckboxes);

        //19 Assert that for each checkbox there is an individual log row
        // and value corresponded to the unchecked status of checkbox
        differentElementsPage.checkCheckboxLogEntries(FALSE.toString(), testedCheckboxes);
    }

}
