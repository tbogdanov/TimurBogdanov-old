package pageObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Timur Bogdanov on 18.06.18.
 */

public class DatesPage {

    private final String pageUrl = "https://epam.github.io/JDI/dates.html";

    private final String sliderName = "Range 2";
    private final int sliderMaxSteps = 100;


    @FindBy(css = ".ui-slider")
    private SelenideElement slider;

    @FindBy(css = ".ui-slider-handle:nth-of-type(1)")
    private SelenideElement sliderLeftHandle;

    @FindBy(css = ".ui-slider-handle:nth-of-type(2)")
    private SelenideElement sliderRightHandle;

    @FindBy(css = ".ui-slider-handle:nth-of-type(1) > span")
    private SelenideElement sliderLeftLabel;

    @FindBy(css = ".ui-slider-handle:nth-of-type(2) > span")
    private SelenideElement sliderRightLabel;

    public void setSlider(int newPositionLeft, int newPositionRight) {

        int positionLeft = Integer.parseInt(sliderLeftLabel.getText());
        int positionRight = Integer.parseInt(sliderRightLabel.getText());

        int leftXOffset, rightXOffset;

        if (newPositionLeft <= newPositionRight) {
            /* Example:
             * Left: 20 -> 60
             * Right: 100 -> 70 */
            leftXOffset = getXOffset(positionLeft, newPositionLeft);
            rightXOffset = getXOffset(positionRight, newPositionRight);
        } else {
            /* Example:
             * Left: 20 -> 80
             * Right: 100 -> 60
             * We should swap sliders so it were
             * Left: 20 -> 60
             * Right: 100 -> 80 */
            leftXOffset = getXOffset(positionRight, newPositionLeft);
            rightXOffset = getXOffset(positionLeft, newPositionRight);
        }

        /* Ensure we don't move the left slider to the right slider
         * because the right slider will stop the movement of the left one.
         * If we do, move the right slider first.
         * We have a special condition where positionLeft == positionRight == 100,
         * in this case we move the left slider first.
         */
        if (newPositionLeft <= positionRight) {
            Selenide.actions().dragAndDropBy(sliderLeftHandle, leftXOffset, 0).build().perform();
            Selenide.actions().dragAndDropBy(sliderRightHandle, rightXOffset, 0).build().perform();
        } else {
            Selenide.actions().dragAndDropBy(sliderRightHandle, rightXOffset, 0).build().perform();
            Selenide.actions().dragAndDropBy(sliderLeftHandle, leftXOffset, 0).build().perform();
        }



    }

    private int getXOffset(int fromPosition, int newPosition) {
        double sliderWidth = Double.parseDouble(slider.getCssValue("width").replace("px", ""));

        double step = sliderWidth / sliderMaxSteps;

        int positionShift = newPosition - fromPosition - 1;

        return (int) (positionShift * step);
    }

    public void checkSliderLogEntries(int positionLeft, int positionRight) {

        /* We should check log entries in the reverse order.
         * $$(...) was used instead of dedicated ElementsCollection because
         * the latter one doesn't update itself and we get older lines.
         * $$(...) always updates the requested node!
         */

        String expectedEntryFrom = String.format("%s(From):%d link clicked", sliderName, positionLeft);
        String expectedEntryTo = String.format("%s(To):%d link clicked", sliderName, positionRight);

        SelenideElement firstEntry = $$(".logs li").get(0);
        SelenideElement secondEntry = $$(".logs li").get(1);

        if (firstEntry.getText().contains(expectedEntryFrom)) {
            secondEntry.shouldHave(text(expectedEntryTo));
        } else if (firstEntry.getText().contains(expectedEntryTo)){
            secondEntry.shouldHave(text(expectedEntryFrom));
        } else {
            Assert.fail(String.format("Expected first log entry: %s or %s\nActual first log entry: %s",
                    expectedEntryFrom, expectedEntryTo, firstEntry.getText()));
        }
    }
}