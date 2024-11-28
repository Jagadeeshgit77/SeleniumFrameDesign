package TestComponents;

import PlanitPageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initalizeDriver() throws IOException {

        //properties class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
        prop.load(fis);
        String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        //System.getProperty() is used to read system level variables like user.dir, -Dbrowser=firefox.
        //String browserName= prop.getProperty("browser");

        //if (browserName.equalsIgnoreCase("chrome")) {
        if (browserName.contains("chrome")) { // for headless mode
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900)); //full screen
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            //FireFox
        }
        else if(browserName.equalsIgnoreCase("edge")) {
            //Edge
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        //read json to string
        String json= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
        //String to HashMap JacksonDataBind mvn dependency

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
        //{map, map}
    }

    public  String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts= (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
        FileUtils.copyFile(src,file);
        return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initalizeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
       return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
