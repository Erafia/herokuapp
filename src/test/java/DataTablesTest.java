import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertEquals;

//Under maintenance. Not finished
public class DataTablesTest {
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
    public void TablesValidation() {
        driver.get("http://the-internet.herokuapp.com/typos");
        List<WebElement> sentences = driver.findElements(By.xpath("//p"));
        String expectedSentence1 = "This example demonstrates a typo being introduced. It does it randomly on each page load.";
        String expectedSentence2 = "Sometimes you'll see a typo, other times you won't.";
        assertEquals(sentences.get(0).getText(), expectedSentence1, "Typo occurred");
        assertEquals(sentences.get(1).getText(), expectedSentence2, "Typo occurred");


    }
}
