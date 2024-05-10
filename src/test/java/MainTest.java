import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AuthPage;
import page.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MainTest {
    private static WebDriver driverChrome;
    private static WebDriverWait wait;

    @FindBy(xpath = "//div[@id='content-column']/div/div/div/div[2]/article/div[4]")
    private static WebElement deletedPostMessage;

    @FindBy(xpath = "//*[@id=\"content-column\"]/div/div/div[1]/div[2]/article/div[4]/button")
    private static WebElement restoreButton;


    @BeforeEach
    public void setUp(){
        driverChrome = new ChromeDriver();
        driverChrome.get("http://mirtesen.ru/");
        wait = new WebDriverWait(driverChrome, Duration.ofSeconds(10));
        driverChrome.manage().window().maximize();
    }


    @Test
    public void createPostTest(){
        AuthPage.loginByEmail(driverChrome);
        String text = "Hi!";
        MainPage.createPost(driverChrome, text);
        WebElement editContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mt-lexical-input")));
        Assert.notNull(editContent, "editContent");
        driverChrome.quit();
    }

    @Test
    public void deletePostTest() throws InterruptedException {
        AuthPage.loginByEmail(driverChrome);
        String text = "Hi!";
        MainPage.createPost(driverChrome, text);
        MainPage.deletePost(driverChrome);
       // ((JavascriptExecutor) driverChrome).executeScript("window.scrollBy(0, 00);");
        PageFactory.initElements(driverChrome, MainTest.class);
        Assert.notNull(deletedPostMessage, "Post deletion message");
        Assert.notNull(restoreButton, "Recovery button");
        driverChrome.quit();
    }

    @Test
    public void recoveryPostTest() throws InterruptedException {
        AuthPage.loginByEmail(driverChrome);
        String text = "Hi!";
        MainPage.createPost(driverChrome, text);
        MainPage.deletePost(driverChrome);
        MainPage.recoveryPost(driverChrome);
        WebElement recoveriedPost = driverChrome.findElement(By.cssSelector(".inft:nth-child(3) .post-card__header-bottom"));
        Assert.notNull(recoveriedPost, "recoveriedPost button");
        driverChrome.quit();
    }

    @Test
    public void testLinkNavigation() {
        WebElement element = driverChrome.findElement(By.xpath("//div[@id='topics-menu']/a/span[2]"));
        element.click();
        String currentUrl = driverChrome.getCurrentUrl();
        assertEquals("https://mirtesen.ru/topic/cooking?utm_medium=from_left_sidebar&page=2", currentUrl, "Incorrect URL");

        WebElement element2 = driverChrome.findElement(By.xpath("//div[@id='topics-menu']/a[2]/span[2]"));
        element2.click();
        currentUrl = driverChrome.getCurrentUrl();
        assertEquals("https://mirtesen.ru/topic/family?utm_medium=from_left_sidebar&page=2", currentUrl, "Incorrect URL");

        WebElement element3 = driverChrome.findElement(By.xpath("//div[@id='topics-menu']/a[5]/span[2]"));
        element3.click();
        currentUrl = driverChrome.getCurrentUrl();
        assertEquals("https://mirtesen.ru/topic/health?utm_medium=from_left_sidebar&page=2", currentUrl, "Incorrect URL");

        WebElement element4 = driverChrome.findElement(By.xpath("//div[@id='topics-menu']/a[4]/span[2]"));
        element4.click();
        currentUrl = driverChrome.getCurrentUrl();
        assertEquals("https://mirtesen.ru/topic/politics?utm_medium=from_left_sidebar&page=2", currentUrl, "Incorrect URL");

        WebElement element5 = driverChrome.findElement(By.xpath("//div[@id='topics-menu']/a[3]/span[2]"));
        element5.click();
        currentUrl = driverChrome.getCurrentUrl();
        assertEquals("https://mirtesen.ru/topic/show?utm_medium=from_left_sidebar&page=2", currentUrl, "Incorrect URL");

        driverChrome.quit();
    }
    @Test
    public void testReadPost() throws InterruptedException {
        String link = MainPage.readPost(driverChrome);
        String postUrl = (String) ((JavascriptExecutor) driverChrome).executeScript("return window.location.href");
        assertEquals(link, postUrl, "Incorrect URL");
    }

    @Test
    public void testFindBlog() {
        MainPage.findBlog(driverChrome);
        String currentUrl = driverChrome.getCurrentUrl();
        assertEquals("https://mirtesen.ru/search?q=EarsPawsTail", currentUrl, "Incorrect URL");
    }


}
