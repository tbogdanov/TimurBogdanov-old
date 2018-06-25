package pageObjects;

import enums.Links;
import enums.Strings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Timur Bogdanov on 06.06.18.
 */

public class HomePage {

    @FindBy(css = ".profile-photo")
    private WebElement userIcon;

    @FindBy(css = "#Name")
    private WebElement loginInput;

    @FindBy(css = "#Password")
    private WebElement passwordInput;

    @FindBy(css = ".form-horizontal button[type = 'submit']")
    private WebElement submitButton;

    @FindBy(css = ".profile-photo span")
    private WebElement userName;

    @FindBy(css = ".uui-navigation.nav.navbar-nav.m-l8>li")
    private List<WebElement> headerMenuItems;

    @FindBy(css = ".icons-benefit")
    private List<WebElement> benefitIcons;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> benefitTexts;

    @FindBy(css = ".main-title.text-center")
    private WebElement mainHeaderTitle;

    @FindBy(css = ".main-txt.text-center")
    private WebElement mainHeaderText;

    @FindBy(css = ".main-content a")
    private WebElement subHeader;

    @FindBy(css = ".sidebar-menu")
    private WebElement sidebarMenu;

    @FindBy(css = ".footer-content.overflow")
    private WebElement footer;

    public void openHomePage(WebDriver driver) {
        driver.navigate().to(Links.HOME_PAGE.toString());
    }

    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public void checkHomePageTitle(WebDriver driver) {
        assertEquals(driver.getTitle(), Strings.HOME_PAGE_TITLE.toString());
    }

    public void checkUsername(String expected) {
        Assert.assertEquals(userName.getText(), expected);
        Assert.assertTrue(userName.isDisplayed());
    }

    public void checkHeaderMenuItems() {
        List<String> headerItemStrings = new LinkedList<>();

        for (WebElement item : headerMenuItems) {
            Assert.assertTrue(item.isDisplayed());
            headerItemStrings.add(item.getText());
        }

        Assert.assertEquals(headerItemStrings.size(), 4);
        Assert.assertEquals(headerItemStrings,
                Arrays.asList(Strings.HEADER_NAVBAR.toString().split("; ")));
    }

    public void checkBenefitIcons() {
        for (WebElement item : benefitIcons) {
            Assert.assertTrue(item.isDisplayed());
        }
    }

    public void checkBenefitTexts() {
        List<String> benefitTextStrings = new LinkedList<>();

        for (WebElement item : benefitTexts) {
            Assert.assertTrue(item.isDisplayed());
            benefitTextStrings.add(item.getText());
        }

        Assert.assertEquals(benefitTexts.size(), 4);
        Assert.assertEquals(benefitTextStrings,
                Arrays.asList(Strings.BENEFITS.toString().split("; ")));
    }

    public void checkMainHeaderTitle() {
        Assert.assertTrue(mainHeaderTitle.isDisplayed());
        Assert.assertEquals(mainHeaderTitle.getText(), Strings.MAIN_HEADER_TITLE.toString());
    }

    public void checkMainHeaderText() {
        Assert.assertTrue(mainHeaderText.isDisplayed());
        Assert.assertEquals(mainHeaderText.getText(), Strings.MAIN_HEADER_TEXT.toString());
    }

    public void checkSubHeader() {
        Assert.assertTrue(subHeader.isDisplayed());
        Assert.assertEquals(subHeader.getText(), Strings.SUB_HEADER_TITLE.toString());
    }

    public void checkSubHeaderLink() {
        Assert.assertEquals(subHeader.getAttribute("href"), Links.JDI_PAGE.toString());
    }

    public void checkLeftSection() {
        Assert.assertTrue(sidebarMenu.isDisplayed());
    }

    public void checkFooter() {
        Assert.assertTrue(footer.isDisplayed());
    }
}

