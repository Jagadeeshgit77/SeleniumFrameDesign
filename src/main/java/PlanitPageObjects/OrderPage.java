package PlanitPageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> OrderProducts;

    @FindBy(css=".totalRow button")
    private WebElement checkoutEle;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // take cares of initializing driver through @FindBy
    }

    public Boolean VerifyOrderDisplay(String productName){
        Boolean match = OrderProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }




}
