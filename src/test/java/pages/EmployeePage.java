package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeePage {
    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> empMenuBar;
    @FindBy(className = "oxd-button--medium")
    public List<WebElement> submitButton;
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> selectBloodType;

    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
