package propeller.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import propeller.Const;

/**
 * @author Konovalov Mihail
 * @date 10.09.2020
 * @Description: Page Login model
 **/

public class WebPageLogin {
    private WebDriver driver;
    private String webPageLoginURL = Const.MAIN_PAGE_URL.getValue();
    private WebDriverWait driverWait;
    private final int TIME_OF_WAIT = 10;

    @FindBy(xpath = "//*[contains(text(),'Welcome to Propeller Championship!')]")
    private WebElement textWelcome;

    @FindBy(xpath = "//*[contains(text(),'Fill login form to sign in')]")
    private WebElement textFillLogin;

    @FindBy(xpath = "//*[contains(text(),'never share your email')]")
    private WebElement textNeverShareEmail;

    @FindBy(xpath = "//*[contains(text(),'never save your password')]")
    private WebElement textNeverSavePassword;

    @FindBy(xpath = "//div[@onclick='startInputLogin()']")
    private WebElement fieldOverLogin;

    @FindBy(xpath = "//div[@onclick='startInputPassword()']")
    private WebElement fieldOverPassword;

    @FindBy(id = "loginInput")
    private WebElement loginField;

    @FindBy(id = "passwordInput")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(), 'Hover me faster!')]")
    private WebElement buttonHover;

    @FindBy(xpath = "//img[@src='sign.png']")
    private WebElement buttonSignIn;


    public WebPageLogin(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, TIME_OF_WAIT);
        driver.manage().deleteAllCookies();
        driver.get(webPageLoginURL);
        PageFactory.initElements(driver, this);
    }

    public boolean elementsPresents(){
        if (loginField.isDisplayed() &&
            passwordField.isDisplayed() &&
            buttonHover.isDisplayed() &&
            textWelcome.isDisplayed() &&
            textFillLogin.isDisplayed() &&
            textNeverShareEmail.isDisplayed() &&
            textNeverSavePassword.isDisplayed())
            return true;
        else return false;
    }

    public boolean enterLoginAndPasswordRightWay(String login, String password) {
        if (loginField.isEnabled() && passwordField.isEnabled()) {
            loginField.clear();
            loginField.sendKeys(login);
            passwordField.clear();
            passwordField.sendKeys(password);
            return true;
        } else
            return false;

    }

    public void enterLoginAndPassword(String login, String password) {
        fieldOverLogin.click();
        loginField.clear();
        loginField.sendKeys(login);

        fieldOverPassword.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void focusOnHoverButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(buttonHover);
        actions.perform();
    }

    public WebElement waitSignInDisplayed() {
        return driverWait.until(ExpectedConditions.visibilityOf(buttonSignIn));
    }

    public void clickSignInButton() {
        focusOnHoverButton();
        waitSignInDisplayed().click();
    }

    public void goToMainPage() {
        enterLoginAndPassword(Const.LOGIN_CORRECT.getValue(),Const.PASSWORD_CORRECT.getValue());
        clickSignInButton();
        alertAccept();
        alertAccept();
        waitForLoadingMainPage();
    }

    public void alertAccept() {
        driverWait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void alertDismiss() {
        driverWait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public void waitForLoadingMainPage() {
        driverWait.until(ExpectedConditions.urlToBe(Const.MAIN_PAGE_URL.getValue()));
    }


}
