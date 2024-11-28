package PlanitPageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent{
    WebDriver driver;



    @FindBy(css="[placeholder='Select Country']")
    WebElement country;

    @FindBy(css="(//button[contains(@class, 'ta-item')])[2]")
    WebElement selectCountry;

    @FindBy(css=".action__submit")
    WebElement submit;


    By results = By.cssSelector(".ta-results");


    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // take cares of initializing driver through @FindBy
    }

    public void  selectCountry(String countryName){

        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();

        
    }

    public ConfirmationPage ssubmitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

    public String Confirmation(){
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        return confirmMessage;
    }

}
