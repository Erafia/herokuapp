import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class NotificationTest {
    private WebDriver driver;

    @BeforeMethod
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void NotificationValidation() {
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
        driver.findElement(By.xpath("//a[text()='Click here']")).click();
        WebElement notification = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        String actualMessage = notification.getText().trim().substring(0,notification.getText().trim().length()-2);
        List<String> expectedMessages = new ArrayList<String>();
        expectedMessages.add("Action unsuccesful, please try again");
        expectedMessages.add("Action successful");
        assertTrue(expectedMessages.contains(actualMessage), "Notification`s message does not meet requirements");
    }
}
