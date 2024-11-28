package Tests;

import PlanitPageObjects.*;
import TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {
    @Test
    public void submitOrder() throws IOException {
        String productName = "ZARA COAT 3";
        ProductCatalouge productCatalouge = landingPage.loginApplication("jj5380964@gmail.com", "Jagaeesh@777");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }
}
