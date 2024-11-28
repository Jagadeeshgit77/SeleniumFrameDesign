package PlanitPageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

//    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();


    @FindBy(css=".hero-primary")
    WebElement confirmationMessage;



    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // take cares of initializing driver through @FindBy
    }

    public String verifyConfirmationMessage(){
       return confirmationMessage.getText();
    }
}
