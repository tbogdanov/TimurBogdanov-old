package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Danila_Morokov on 5/22/2018.
 */
public class TestBase {

    private long initTime;

    @BeforeSuite
    public void beforeSuite() {
        initTime = System.currentTimeMillis();

        Configuration.browser = "chrome";
        Configuration.screenshots = false;
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Suite time is: " + (System.currentTimeMillis() - initTime));
    }
}