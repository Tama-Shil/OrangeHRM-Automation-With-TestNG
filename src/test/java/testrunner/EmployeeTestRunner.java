package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

import static utils.Utils.readJSONData;


public class EmployeeTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Employee cannot login with invalid username")
    public void EmployeeLoginWithWrongUsername() throws IOException, ParseException {
        loginPage = new LoginPage(driver);

        JSONArray empArray = readJSONData();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size() - 1);
        String username = jsonObject.get("userName").toString();
        String password = jsonObject.get("password").toString();
        loginPage.doLogin("Admin234", password);
        WebElement alertTextElem = driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertTextElem.getText(), "Invalid credentials");
    }

    @Test(priority = 2, description = "Employee cannot login with invalid password")
    public void EmployeeLoginWithWrongPassword() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONArray empArray = readJSONData();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size() - 1);
        String username = jsonObject.get("userName").toString();
        String password = jsonObject.get("password").toString();
        loginPage.doLogin(username, "123");
        WebElement alertTextElem = driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertTextElem.getText(), "Invalid credentials");
    }

    @Test(priority = 3, description = "Employee can login with all uppsercase or lowercase username")
    public void EmployeeInvalidLoginWithUsername() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);

        JSONArray empArray = readJSONData();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size() - 1);
        String username = jsonObject.get("userName").toString().toUpperCase();
        String password = jsonObject.get("password").toString();

        loginPage.doLogin(username, password);
        Assert.assertTrue(loginPage.btnPfofileImage.isDisplayed());

        loginPage = new LoginPage((driver));
        loginPage.doLogout();
        WebElement forgotPasswordLink = driver.findElement(By.className("orangehrm-login-forgot-header"));
        Assert.assertTrue(forgotPasswordLink.isDisplayed());

    }

    @Test(priority = 4, description = "Employee can login with valid username and password ")
    public void EmployeeValidLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);

        JSONArray empArray = readJSONData();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size() - 1);
        String username = jsonObject.get("userName").toString();
        System.out.println(username);
        String password = jsonObject.get("password").toString();
        System.out.println(password);

        String firstname = jsonObject.get("firstName").toString();
        String lastname = jsonObject.get("lastName").toString();
        String expectedName = firstname + " " + lastname;

        loginPage.doLogin(username, password);

        String actualProfileName = loginPage.btnPfofileName.getText();
        Assert.assertEquals(actualProfileName, expectedName);

    }

    @Test(priority = 5, description = "Employee cannot type the blood type ")

    public void AddEmployeeDetailsWithInvalidBloodType() throws IOException, ParseException, InterruptedException {

        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.empMenuBar.get(2).click();
        Thread.sleep(3000);

        Utils.scroll(driver, 500);
        driver.findElements(By.className("oxd-radio-input--active")).get(0).click();
        employeePage.submitButton.get(0).click();
        Thread.sleep(2000);

        employeePage.selectBloodType.get(2).sendKeys("O+");
        employeePage.submitButton.get(1).click();

        Thread.sleep(1000);
        String txtActual = employeePage.selectBloodType.get(2).getText();
        String txtExpected = "-- Select --";
        Assert.assertEquals(txtActual, txtExpected);


    }

    @Test(priority = 6, description = "Employee  cannot add gender or bloodtype if he does click submit once or never ")

    public void AddEmployeeDetailsWithInvalidSubmitType() throws InterruptedException {

        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.empMenuBar.get(2).click();
        Thread.sleep(3000);

        Utils.scroll(driver, 500);
        driver.findElements(By.className("oxd-radio-input--active")).get(0).click();
        employeePage.submitButton.get(0).click();
        Thread.sleep(2000);

        employeePage.selectBloodType.get(2).click();
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).click();
        employeePage.submitButton.get(1).click();

        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        String txtActual = employeePage.selectBloodType.get(2).getText();
        String txtExpected = "-- Select --";
        Assert.assertEquals(txtActual, txtExpected);

    }

    @Test(priority = 7, description = "Employee can add gender and blood type")
    public void AddEmployeeBloodTyeAndGender() throws IOException, ParseException, InterruptedException {

        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.empMenuBar.get(2).click();
        Thread.sleep(3000);

        Utils.scroll(driver, 500);
        driver.findElements(By.className("oxd-radio-input--active")).get(0).click();
        employeePage.submitButton.get(0).click();
        Thread.sleep(2000);

        employeePage.selectBloodType.get(2).click();
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).sendKeys(Keys.ARROW_DOWN);
        employeePage.selectBloodType.get(2).click();
        employeePage.submitButton.get(1).click();

        Thread.sleep(1000);
        String txtActual = employeePage.selectBloodType.get(2).getText();
        String txtExpected = "O+";
        Assert.assertEquals(txtActual, txtExpected);

        loginPage = new LoginPage((driver));
        loginPage.doLogout();
        WebElement forgotPasswordLink = driver.findElement(By.className("orangehrm-login-forgot-header"));
        Assert.assertTrue(forgotPasswordLink.isDisplayed());

    }

}
