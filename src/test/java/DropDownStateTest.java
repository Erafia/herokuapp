import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class DropDownStateTest {
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
    public void dropDownValidation() {
        String option1 = "Please select an option";
        String option2 = "Option 1";
        String option3 = "Option 2";

        driver.get("http://the-internet.herokuapp.com/dropdown");
        List<WebElement> dropdownOptions = driver.findElements(By.tagName("option"));

        assertEquals(dropdownOptions.get(0).getText(), option1, "Option \"Please select an option\" is not available");
        assertEquals(dropdownOptions.get(1).getText(), option2, "Option \"Option 1\" is not available");
        assertEquals(dropdownOptions.get(2).getText(), option3, "Option \"Option 2\" is not available");

        dropdownOptions.get(1).click();
        assertTrue(dropdownOptions.get(1).isSelected(), "Option 1 was not selected");
        dropdownOptions.get(2).click();
        assertTrue(dropdownOptions.get(2).isSelected(), "Option 2 was not selected");
    }
}
