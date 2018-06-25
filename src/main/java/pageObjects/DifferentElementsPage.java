package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.Strings;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Timur Bogdanov on 18.06.18.
 */

public class DifferentElementsPage {

    private final String pageUrl = "https://epam.github.io/JDI/different-elements.html";

    @FindBy(css = "title")
    private SelenideElement title;

    @FindBy(css = ".label-checkbox")
    private ElementsCollection checkboxes;

    @FindBy(css = ".label-radio")
    private ElementsCollection radios;

    @FindBy(css = "select")
    private SelenideElement dropdown;

    @FindBy(css = "div.main-content .uui-button")
    private ElementsCollection buttons;

    @FindBy(css = "div[name='navigation-sidebar']")
    private SelenideElement leftSection;

    @FindBy(css = "div[name='log-sidebar']")
    private SelenideElement rightSection;

    @FindBy(css = ".logs li")
    private ElementsCollection logEntries;

    public void openDifferentElements() {
        open(pageUrl);
    }

    public void checkTitle() {
        title.shouldHave(text(Strings.DIFFERENT_ELEMENTS_TITLE.toString()));
    }

    public void checkInternalElements() {
        checkboxes.shouldHaveSize(4);
        radios.shouldHaveSize(4);
        buttons.shouldHaveSize(2);
        dropdown.shouldBe(visible);
    }

    public void checkLeftSection() {
        leftSection.shouldBe(visible);
    }

    public void checkRightSection() {
        rightSection.shouldBe(visible);
    }

    public void checkCheckboxLogEntries(String status, String[] elements) {
        /* We should check log entries in the reverse order.
           $$(...) was used instead of dedicated ElementsCollection because
           the latter one doesn't update itself and we get older lines.
           $$(...) always updates the requested node!
         */
        int logIndex = elements.length-1;
        for (String element : elements) {
            String expectedEntry = String.format("%s: condition changed to %s", element, status);
            $$(".logs li").get(logIndex).shouldHave(text(expectedEntry));
            logIndex--;
        }
    }
    public void checkRadioLogEntry(String value) {
        String expectedEntry = String.format("metal: value changed to %s", value);
        $$(".logs li").get(0).shouldHave(text(expectedEntry));
    }

    public void checkDropdownLogEntry(String value) {
        String expectedEntry = String.format("Colors: value changed to %s", value);
        $$(".logs li").get(0).shouldHave(text(expectedEntry));
    }

    public void toggleCheckboxes(String[] checkboxNames) {
        for (String name : checkboxNames) {
            checkboxes.findBy(text(name)).$("[type = checkbox]").click();
        }
    }

    public void selectRadioButton(String radioName) {
        radios.findBy(text(radioName)).$("[type = radio]").click();
    }

    public void selectDropdownOption(String name) {
        dropdown.click();
        dropdown.selectOption(name);
    }

}