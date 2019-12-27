import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddRemoveElementTest {
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
    public void addRemoveElement() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element'] ")).click();
        driver.findElement(By.xpath("//button[text()='Add Element'] ")).click();
        driver.findElement(By.className("added-manually")).click();
        int numberOfDeleteButtons = driver.findElements(By.className("added-manually")).size();
        assertEquals(numberOfDeleteButtons, 1, "Number of remaining elements is not correct.");

    }
}
