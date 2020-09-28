package propeller.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import propeller.Const;

/**
 * @author Konovalov Mihail
 * @date 20.09.2020
 * @Description:
 **/


public class WebPageMain {
    WebPageLogin webPageLogin;
    private WebDriver driver;
    private String webPageMainURL = Const.MAINPAGE_URL.getValue();
    private WebDriverWait driverWait;
    private final int TIME_OF_WAIT = 10;

    @FindBy(xpath = "//button[contains(text(),'Advertisers')]")
    private WebElement buttonAdvertisers;

    @FindBy(xpath = "//button[contains(text(),'Publishers')]")
    private WebElement buttonPublishers;

    @FindBy(xpath = "//button[contains(text(),'Top level clients')]")
    private WebElement buttonTopLevelClients;
}
