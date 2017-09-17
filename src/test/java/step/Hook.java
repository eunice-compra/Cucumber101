package step;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import interactives.DriverFactory;

import java.util.concurrent.TimeUnit;

public class Hook extends DriverFactory{

    @Before
    public void initialiseTest() {
        initializeDriver();
        driver.navigate().to("https://gmail.com");
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDownTest() {
        driver.close();

    }
}
