import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckBoxesStateTest {
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
    public void checkBoxesValidation() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
        assertFalse(checkboxes.get(0).isSelected(), "First checkbox is selected.");
        checkboxes.get(0).click();
        assertTrue(checkboxes.get(0).isSelected(), "First checkbox is deselected.");
        assertTrue(checkboxes.get(1).isSelected(), "Second checkbox is deselected.");
        checkboxes.get(1).click();
        assertFalse(checkboxes.get(1).isSelected(), "Second checkbox is selected.");
    }
}


