package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AuthPage {
    @FindBy(xpath="//input[@id='authFormLoginByEmailEmail']")
    private static WebElement emailInput;

    @FindBy(xpath="//input[@id='authFormLoginByEmailPassword']")
    private static WebElement passwordInput;

    @FindBy(xpath = "//div[@id='content-column']/div/div/div/div/span")
    private static WebElement whatsNew;


    public static WebElement loginByEmail(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-secondary")));
        loginButton.click();
        WebElement loginByEmailButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auth-form-group:nth-child(6) > .btn")));
        loginByEmailButton.click();
        PageFactory.initElements(driver, AuthPage.class);
        emailInput.sendKeys("gromilova.maria@yandex.ru");
        passwordInput.sendKeys("Aaaaaa1");
        WebElement goToAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auth-form__form__submit > .btn")));
        goToAccountButton.click();
        return whatsNew;
    }

    public static void logOut(WebDriver driver){
        PageFactory.initElements(driver, AuthPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".angle-down")));
        profileTooltip.click();
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header-profile__item_exit")));
        logoutButton.click();
        WebElement confirmLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".popup-btn__submit")));
        confirmLogout.click();

    }

}
