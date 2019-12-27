import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class InputsTest {
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
    public void inputsArrowValidation() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));

        inputField.click();

        inputField.sendKeys(Keys.ARROW_DOWN);
        int actualValue1 = Integer.parseInt(inputField.getAttribute("value"));

        inputField.sendKeys(Keys.ARROW_UP);
        int actualValue2 = Integer.parseInt(inputField.getAttribute("value"));

        inputField.sendKeys(Keys.ARROW_UP);
        int actualValue3 = Integer.parseInt(inputField.getAttribute("value"));

        assertEquals(actualValue1, -1, "Calculation`s not correct");
        assertEquals(actualValue2, 0, "Calculation`s not correct");
        assertEquals(actualValue3, 1, "Calculation`s not correct");

    }

    @Test
    public void inputsManualValidation() {
        String defaultOrInvalid = "";
        String validNumberWithSymbols1 = "-546";
        String validNumberWithSymbols2 = "546.6";

        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.xpath("//input"));

        inputField.click();
        String actualValue1 = inputField.getAttribute("value");

        inputField.sendKeys("hgfhfghfghf");
        String actualValue2 = inputField.getAttribute("value");
        inputField.clear();

        inputField.sendKeys("!@#$%^&*~`(){}[];:'\"<>/\\_=+");
        String actualValue3 = inputField.getAttribute("value");
        inputField.clear();

        inputField.sendKeys("¶«ª¨§¦¥¢¡£© ");
        String actualValue4 = inputField.getAttribute("value");
        inputField.clear();

        inputField.sendKeys(" булок бы тех французских");
        String actualValue5 = inputField.getAttribute("value");
        inputField.clear();

        inputField.sendKeys("园丁杀手");
        String actualValue6 = inputField.getAttribute("value");
        inputField.clear();

        inputField.sendKeys("  ");
        String actualValue7 = inputField.getAttribute("value");
        inputField.clear();


        inputField.sendKeys(validNumberWithSymbols1);
        String actualValue8 = inputField.getAttribute("value");
        inputField.clear();

        inputField.sendKeys(validNumberWithSymbols2);
        String actualValue9 = inputField.getAttribute("value");
        inputField.clear();

        assertEquals(actualValue1, defaultOrInvalid, "Field is not empty by default");
        assertEquals(actualValue2, defaultOrInvalid, "Invalid input possible");
        assertEquals(actualValue3, defaultOrInvalid, "Invalid input possible");
        assertEquals(actualValue4, defaultOrInvalid, "Invalid input possible");
        assertEquals(actualValue5, defaultOrInvalid, "Invalid input possible");
        assertEquals(actualValue6, defaultOrInvalid, "Invalid input possible");
        assertEquals(actualValue7, defaultOrInvalid, "Invalid input possible");
        assertEquals(actualValue8, validNumberWithSymbols1, "Field does not display entered numeric value correctly");
        assertEquals(actualValue9, validNumberWithSymbols2, "Field does not display entered numeric value correctly");
    }
}