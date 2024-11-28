package Tests;

import PlanitPageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTestRef {
    public static void main(String[] args) {
        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//Declaring globally for sync issues


        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductCatalouge productCatalouge = landingPage.loginApplication("jj5380964@gmail.com", "Jagadeesh@777");


        List<WebElement> products=productCatalouge.getProductList();
        productCatalouge.addProductToCart(productName);
        CartPage cartPage= productCatalouge.goToCartPage();

        Boolean match =cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        ConfirmationPage confirmationPage = checkoutPage.ssubmitOrder();
        confirmationPage.verifyConfirmationMessage();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();

    }
}
