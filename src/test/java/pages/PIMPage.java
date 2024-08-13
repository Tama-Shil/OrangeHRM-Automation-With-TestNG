package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {

    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> leftMenubar;

    @FindBy(className = "oxd-button")
   public List<WebElement> button;

    @FindBy(className = "oxd-input")
    public List<WebElement> textField;
    @FindBy(className = "oxd-input")
    public List<WebElement> textElem;

    @FindBy(className = "oxd-input--active")
    public List<WebElement> searchbar;

    @FindBy(className = "oxd-button--medium")
    public List<WebElement> searchbutton;


    public PIMPage(WebDriver driver)
    {
      PageFactory.initElements(driver,this);
    }
    public void createNewEmployee(String firstname, String lastname,  String username, String password){
        textField.get(1).sendKeys(firstname);
        textField.get(3).sendKeys(lastname);
        textField.get(5).sendKeys(username);
        textField.get(6).sendKeys(password);
        textField.get(7).sendKeys(password);
        button.get(1).click();

    }
}
