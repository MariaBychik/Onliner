package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.Map;

public class BaseTest {
    protected static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.onliner.by");
    }

    public static String getTestData(String parameter) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(new File("src/test/resources/dataJson.json"), Map.class);
        return map.get(parameter);
    }

    @AfterSuite
    public void TeardownTest() {
        driver.quit();
    }
}





