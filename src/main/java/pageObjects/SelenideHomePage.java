package pageObjects;

import enums.Strings;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;


/**
 * Created by Timur Bogdanov on 18.06.18.
 */


public class SelenideHomePage {

    private final String pageUrl = "https://epam.github.io/JDI/index.html";

    @FindBy(css = "title")
    private SelenideElement title;

    @FindBy(css = ".profile-photo")
    private SelenideElement userIcon;

    @FindBy(css = "#Name")
    private SelenideElement loginInput;

    @FindBy(css = "#Password")
    private SelenideElement passwordInput;

    @FindBy(css = ".form-horizontal button[type = 'submit']")
    private SelenideElement submitButton;

    @FindBy(css = ".profile-photo span")
    private SelenideElement userName;

    @FindBy(css = ".uui-navigation.nav.navbar-nav.m-l8>li")
    private ElementsCollection headerMenuItems;

    @FindBy(css = ".icons-benefit")
    private ElementsCollection benefitIcons;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection benefitTexts;

    @FindBy(css = ".main-title.text-center")
    private SelenideElement mainHeaderTitle;

    @FindBy(css = ".main-txt.text-center")
    private SelenideElement mainHeaderText;

    @FindBy(css = ".main-content a")
    private SelenideElement subHeader;

    @FindBy(css = ".sidebar-menu")
    private SelenideElement sidebarMenu;

    @FindBy(css = ".footer-content.overflow")
    private SelenideElement footer;

    @FindBy(css = ".nav .dropdown-toggle")
    private SelenideElement headerServiceDropdown;

    @FindBy(css = ".nav .dropdown-menu > li > a")
    private ElementsCollection headerServiceMenu;

    @FindBy(css = ".fa-caret-down")
    private SelenideElement leftServiceDropdown;

    @FindBy(css = ".wrapper .sub > li > a")
    private ElementsCollection leftServiceMenu;

    @FindBy(css = ".nav a[href = 'different-elements.html']")
    private SelenideElement difElements;

    @FindBy(css = ".nav a[href = 'dates.html']")
    private SelenideElement dates;

    @Step("Open the Home Page")
    public void openPage() {
        open(pageUrl);
    }

    @Step("Log into the Home Page")
    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    @Step("Check the user name on the top-right")
    public void checkUserName(String expectedName) {
        userName.shouldBe(visible);
        userName.shouldHave(text(expectedName));
    }

    @Step("Check the browser tab title for the page")
    public void checkTitle() {
        Assert.assertEquals(getWebDriver().getTitle(), "Home Page");
    }

    @Step("Check Benefit elements")
    public void checkBenefitElements() {

        benefitIcons.shouldHaveSize(4);
        for (SelenideElement icon : benefitIcons) {
            icon.shouldBe(visible);
        }

        benefitTexts.shouldHaveSize(4);
        for (SelenideElement text : benefitTexts) {
            text.shouldBe(visible);
        }
        benefitTexts.shouldHave(texts(Arrays.asList(Strings.BENEFITS.toString().split("; "))));
    }

    @Step("Check the texts on the header")
    public void checkHeaderTexts() {
        mainHeaderText.shouldBe(visible);
        mainHeaderText.shouldHave(text(Strings.MAIN_HEADER_TEXT.toString()));

        subHeader.shouldBe(visible);
        subHeader.shouldHave(text(Strings.SUB_HEADER_TITLE.toString()));
    }

    @Step("Check the items on the left Service submenu")
    public void checkLeftServiceMenu() {
        leftServiceDropdown.click();

        leftServiceMenu.shouldHaveSize(8);
        leftServiceMenu.shouldHave(texts(Arrays.asList(Strings.SERVICE_MENU.toString().split("; "))));

        for (SelenideElement element : leftServiceMenu) {
            element.shouldBe(visible);
        }
    }

    @Step("Check the items on the header Service submenu")
    public void checkHeaderServiceMenu() {
        headerServiceDropdown.click();

        headerServiceMenu.shouldHaveSize(8);
        headerServiceMenu.shouldHave(texts(Arrays.asList(Strings.SERVICE_MENU
                .toString().toUpperCase().split("; "))));
        for (SelenideElement element : headerServiceMenu) {
            element.shouldBe(visible);
        }
    }

    @Step("Go to the Different Elements page")
    public void openDifferentElementsPage() {
        headerServiceDropdown.click();
        difElements.click();
    }

    @Step("Go to the Dates page")
    public void openDatesPage() {
        headerServiceDropdown.click();
        dates.click();
    }

    @Step("Check the visibility of the Left section")
    public void checkLeftSection() {
        Assert.assertTrue(sidebarMenu.isDisplayed());
    }

}

