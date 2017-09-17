package interactives;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by amruta.patel on 12/07/2017.
 */
public class DriverFactory {
    protected static WebDriver driver;
    protected static Logger Log = Logger.getLogger(DriverFactory.class);
    public DriverFactory(){
        initializeDriver();
//        PropertyConfigurator.configure("src\\test\\java\\utilities\\config\\log4j.properties");
    }

    public void initializeDriver(){
        if (driver==null)
            createNewDriverInstance();
    }

    private void createNewDriverInstance(){
        System.setProperty("webdriver.chrome.driver","src//test//java//utilities//drivers//chromedriver");
        driver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void destoryDriver(){
        driver.quit();
        driver = null;
    }

  }
