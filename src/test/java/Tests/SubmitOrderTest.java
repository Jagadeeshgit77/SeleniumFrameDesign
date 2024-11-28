package Tests;

import PlanitPageObjects.*;
import TestComponents.BaseTest;
import TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"},retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String, String> input) throws IOException {

        ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products=productCatalouge.getProductList();
        productCatalouge.addProductToCart(input.get("product"));
        CartPage cartPage= productCatalouge.goToCartPage();

        Boolean match =cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        ConfirmationPage confirmationPage = checkoutPage.ssubmitOrder();
        confirmationPage.verifyConfirmationMessage();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods={"submitOrder"})
    public void OrderHistoryTest(){
        ProductCatalouge productCatalouge = landingPage.loginApplication("jj5380964@gmail.com", "Jagadeesh@777");
        OrderPage orderPage = productCatalouge.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }



    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
         return new Object [] [] {{data.get(0)}, {data.get(1)}};
    }

//    @DataProvider
//    public  Object[][] getData(){
//        return new Object[][] {{"jj5380964@gmail.com","Jagadeesh@77", "ZARA COAT 3"}, {"shetty@gmail.com", "Jagadeesh@777", "ADIDAS ORIGINAL"}};
//    }

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email", "jj5380964@gmail.com");
//        map.put("password","Jagadeesh@777");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email", "shetty@gmail.com");
//        map1.put("password","Jagadeesh@777");
//        map1.put("product","ADIDAS ORIGINAL");
}
