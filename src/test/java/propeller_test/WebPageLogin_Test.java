package propeller_test;
/**
 * @author Konovalov Mihail
 * @date 03.09.2020
 * @Description: Test class
 **/
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import propeller.*;
import propeller.webpages.WebPageLogin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

@RunWith(Parameterized.class)
public class WebPageLogin_Test {
    WebDriver driver;
    WebPageLogin webPageLogin;

    @Parameterized.Parameter
    public Class currentDriver;

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection data() {
        return Arrays.asList(new Object[][] {{ ChromeDriver.class }/*, {FirefoxDriver.class}*/});
    }

    @BeforeClass
    public static void init(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        System.setProperty("webdriver.ie.driver", "./src/test/resources/drivers/IEDriverServer.exe");
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
    }

    @Before
    public void setup() throws IllegalAccessException, InstantiationException {
        driver = (WebDriver) currentDriver.newInstance();
        driver.manage().window().maximize();
        webPageLogin = new WebPageLogin(driver);
    }

    @After
    public void close() {
        if (driver != null)
            driver.quit();
    }

//  Checking that Login and Password fields become active after clicking.
    @Test
    public void testEnablingLoginPasswordAfterClick() {
        Assert.assertTrue(webPageLogin.enterLoginAndPasswordRightWay(Const.LOGIN_CORRECT.getValue(),
                Const.PASSWORD_CORRECT.getValue()));
    }

//  Checking that after entering correct credentials, the user gets to main page URL.
    @Test
    public void testCorrectLoginAndPassword() {
        webPageLogin.goToMainPage();
        Assert.assertTrue(driver.getCurrentUrl().equals(Const.MAINPAGE_URL.getValue()));
    }

    // Checking that after entering wrong credentials, the user gets to Login_Error URL.
    @Test
    public void testWrongLoginAndPassword() {
        webPageLogin.enterLoginAndPassword(Const.LOGIN_WRONG.getValue(), Const.PASSWORD_WRONG.getValue());
        webPageLogin.clickSignInButton();
        webPageLogin.alertAccept();
        webPageLogin.alertAccept();
        webPageLogin.waitForLoadingMainPage();

        Assert.assertTrue(driver.getCurrentUrl().equals(Const.LOGIN_ERROR_URL.getValue()));

    }

    // Checking that after dismissing first alert, the user gets to Login URL.
    @Test
    public void testFirstAlertDismiss() {
        webPageLogin.enterLoginAndPassword(Const.LOGIN_CORRECT.getValue(), Const.PASSWORD_CORRECT.getValue());
        webPageLogin.clickSignInButton();
        webPageLogin.alertDismiss();

        Assert.assertTrue(driver.getCurrentUrl().equals(Const.LOGINPAGE_URL.getValue()));

    }

    // Checking that after dismissing second alert, the user gets to Login URL.
    @Test
    public void testSecondAlertDismiss() {
        webPageLogin.enterLoginAndPassword(Const.LOGIN_CORRECT.getValue(), Const.PASSWORD_CORRECT.getValue());
        webPageLogin.clickSignInButton();
        webPageLogin.alertAccept();
        webPageLogin.alertDismiss();

        Assert.assertTrue(driver.getCurrentUrl().equals(Const.LOGINPAGE_URL.getValue()));

    }

    // Checking that SignIn button is not active after clearing fields
    @Test
    public void testSignInButtonDisable() {
        webPageLogin.enterLoginAndPassword(Const.LOGIN_CORRECT.getValue(), Const.PASSWORD_CORRECT.getValue());
        webPageLogin.focusOnHoverButton();
        webPageLogin.enterLoginAndPassword("", "");

        Assert.assertFalse(webPageLogin.waitSignInDisplayed().isEnabled());

    }
}
