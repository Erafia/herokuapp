import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class HoverProfileTest {
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
    public void profileHoverValidation() throws IOException {
        String name = "name: user";
        Actions action = new Actions(driver);
        driver.get("http://the-internet.herokuapp.com/hovers");


        List<WebElement> userProfiles = driver.findElements(By.className("figure"));
        List<WebElement> userNames = driver.findElements(By.xpath("//h5"));
        List<WebElement> userLinks = driver.findElements(By.xpath("//a[text()='View profile']"));

        for (int i = 0; i < userProfiles.size(); i++) {
            System.out.print("Checking User Profile " + (i+1) +": ");
            assertFalse(userNames.get(i).isDisplayed(), "Hidden element 'userName' is displayed");
            assertFalse(userLinks.get(i).isDisplayed(), "Hidden element 'userLink' is displayed");
            action.moveToElement(userProfiles.get(i)).perform();
            assertTrue(userNames.get(i).isDisplayed(), "Element 'userName' is not displayed on hover");
            assertTrue(userLinks.get(i).isDisplayed(), "Element 'userLink' is not displayed on hover");
            assertEquals(userNames.get(i).getText(), name
                    +(i+1), "User names are not equal");

            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(userLinks.get(i).getAttribute("href"));
            CloseableHttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            assertEquals(statusCode, 404, "Suddenly, we have this page. And we should not :(");
            System.out.print("OK"+ "\n");
        }
    }
}
