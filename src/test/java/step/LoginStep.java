package step;

import cucumber.api.java8.En;
import interactives.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class LoginStep extends PageBase implements En {
    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    private WebElement txtUserName;

    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
    private WebElement txtPassword;

    @FindBy(xpath = "//*[@id=\"identifierNext\"]/content/span")
    private WebElement btnUserNext;

    @FindBy(xpath = "//*[@id=\"passwordNext\"]/content/span")
    private WebElement btnPwdNext;

    @FindBy(xpath = "//*[@id=\"headingText\"]")
    private WebElement lblSignIn;

    @FindBy(xpath = "//*[@id=\":46\"]/div/div[2]/span/a")
    private WebElement lnkInbox;

    public LoginStep() {
        PageFactory.initElements(driver,this);

        Given("^I navigate to the login page of Gmail$", () -> {
            assertTrue(verifyLoginPage());
        });
        And("^I enter the username as \"([^\"]*)\"$", (String username) -> {
            sendKeysToElement(txtUserName,10,username);
        });
        And("^I click the user Next button$", () -> {
            clickButton(btnUserNext,10);
        });
        And("^I enter the password as \"([^\"]*)\"$", (String password) -> {
            sendKeysToElement(txtPassword,10,password);
        });
        And("^I click the password Next button$", () -> {
            clickButton(btnPwdNext,15);
        });
        Then("^I should see my mailbox$", () -> {
            assertTrue(verifyMailboxPage());
        });
    }

    private boolean verifyLoginPage() {
        return findElement(lblSignIn,10);
    }

    private boolean verifyMailboxPage() {
        return findElement(lnkInbox,10);
    }
}
