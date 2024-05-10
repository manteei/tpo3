package page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    public static void createPost(WebDriver driver, String text) {
        PageFactory.initElements(driver, MainPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement choosePostForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mt-post-editor__switcher")));
        choosePostForm.click();
        WebElement editContetnt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mt-lexical-input")));
        editContetnt.sendKeys(text);
        WebElement post = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary:nth-child(2)")));
        post.click();

    }

    public static void deletePost(WebDriver driver) throws InterruptedException {
        PageFactory.initElements(driver, MainPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,300);");
        WebElement element = driver.findElement(By.cssSelector(".mt-post-editor__switcher"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        WebElement dropMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".post-card__footer:nth-child(7) > .post-controls > #mt-dropdown-menu .svg-icon")));
        dropMenu.click();
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mt-dropdown-menu__list li:nth-child(2)")));
        deleteButton.click();
        WebElement confirmDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-footer > .btn-primary")));
        confirmDelete.click();
    }
    public static void recoveryPost(WebDriver driver){
        PageFactory.initElements(driver, MainPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement restoreButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".post-card__fade > .btn")));
        restoreButton.click();
    }

    public static String readPost(WebDriver driver) throws InterruptedException {
        PageFactory.initElements(driver, MainPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement topic = driver.findElement(By.xpath("//div[@id='topics-menu']/a/span[2]"));
        topic.click();
        WebElement post = driver.findElement(By.xpath("//div[@id='index-content-column']/div[2]/div/div/div/div/a"));
        String link = post.getAttribute("href");
        post.click();
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;

    }

    public static String findBlog(WebDriver driver){
        WebElement search = driver.findElement(By.xpath("//input[@name='q']"));
        search.click();
        search.sendKeys("EarsPawsTail");
        search.sendKeys(Keys.ENTER);
        WebElement blog = driver.findElement(By.xpath("//div[@id='content-column']/div/div[2]/div/div/div/div/a/div/span"));
        blog.click();
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    public static void subscribe(WebDriver driver){
        WebElement subButton = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/button"));
        subButton.click();
    }
}
