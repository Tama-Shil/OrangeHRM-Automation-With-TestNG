package testrunner;

import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;


public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1, description = "Admin cannot login with invalid creds")
    public void doLoginWithWrongUsername(){
        loginPage =new LoginPage(driver);
        loginPage.doLogin("Admin234","admin123");
        WebElement alertTextElem= driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertTextElem.getText(), "Invalid credentials");
    }
  @Test(priority = 2, description = "Admin cannot login with invalid creds")
    public void doLoginWithWrongPassword(){
        loginPage =new LoginPage(driver);
        loginPage.doLogin("Admin","admin13");
        WebElement alertTextElem= driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertTextElem.getText(), "Invalid credentials");
    }

    @Test(priority = 3, description = "Admin can login with valid creds")
    public void doLogin(){
        loginPage =new LoginPage(driver);
        //loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        loginPage.doLogin("Admin","admin123");
        Assert.assertTrue(loginPage.btnPfofileImage.isDisplayed());
    }
       @Test(priority = 4, description = "Admin can logout by clicking logout button")
    public void doLogout(){
        loginPage =new LoginPage((driver));
        loginPage.doLogout();
        WebElement forgotPasswordLink= driver.findElement(By.className("orangehrm-login-forgot-header"));
        Assert.assertTrue(forgotPasswordLink.isDisplayed());
    }
   @Test(priority = 5, description = "Admin can login with uppercase or lowercase username")
    public void doLoginWithWrongUsername2(){
        loginPage =new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
        Assert.assertTrue(loginPage.btnPfofileImage.isDisplayed());

    }


}
