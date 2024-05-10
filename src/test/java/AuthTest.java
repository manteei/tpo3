import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AuthPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {
    private static WebDriver driverChrome;
    private static WebDriverWait wait;
    @FindBy(xpath = "//div[@id='content-column']/div/div/div/div/span")
    private static WebElement whatsNew;


    @BeforeAll
    public static void init(){
        driverChrome = new ChromeDriver();
        driverChrome.get("http://mirtesen.ru/");
        wait = new WebDriverWait(driverChrome, Duration.ofSeconds(10));
    }

    @Test
    public void logInChrome(){
        AuthPage.loginByEmail(driverChrome);
        PageFactory.initElements(driverChrome, AuthTest.class);
        Assert.notNull(whatsNew, "whatsNew");
    }

    @Test
    public void logOutChrome(){
        AuthPage.loginByEmail(driverChrome);
        AuthPage.logOut(driverChrome);
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-secondary")));
        Assert.notNull(loginButton, "loginButton");
    }


}
