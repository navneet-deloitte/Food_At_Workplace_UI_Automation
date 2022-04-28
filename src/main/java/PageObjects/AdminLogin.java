package PageObjects;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import resources.baseClass.BaseClass;
import resources.helperClasses.HandleCSV;
import testAutomationListner.Log;
import java.io.IOException;
import resources.helperClasses.Utils;

public class AdminLogin extends BaseClass {

    static By Login_Page=By.xpath("//a[contains(text(),'Login')]");
    static By mail = By.xpath("//input[@placeholder='Enter Email address']");
    static By password = By.xpath("//input[@placeholder='Enter Password']");
    static By login = By.xpath("//button[contains(text(),'Login')]");
    static By failedMessage = By.xpath("//div[contains(text(),'Admin Unauthorized')]");
    static By successMessage = By.xpath("//div[contains(text(),'Success')]");
    static By userLogin = By.xpath("//a[contains(text(),'Login Here')]");
    static By adminLoginPage = By.xpath("//a[contains(@class,'link2') and contains(text(),'Login Here')]");

    public static ExtentTest logInfo = null;
    public static String loginDetails;

    static String Invalid_login_csv = properties.getProperty("admin_invalid_login_csv");
    static String loginData_csv = properties.getProperty("admin_login_data_csv");


    //    This method is for successful login with valid credentials
    public static void login(ExtentTest test) {
        logInfo = test.createNode("Valid User Login");

        getAndFillLoginData(loginData_csv);

        Utils.extentScreenShotCapture(logInfo,"Login Successfully",successMessage);

        Utils.wait(1500);

    }



    public static void loginHereFunctionality(){

        driver.findElement(userLogin).click();
    }
//    This method is for go to login page
    public static void goto_login_page()
    {
        driver.findElement(Login_Page).click();
    }
//    This method is for go to Admin Login Page
    public static void go_to_adminLoginPage()
    {
        driver.findElement(adminLoginPage).click();
    }


    //    This method is for Invalid login with invalid credentials
    public static void invalid_login(ExtentTest test){

        logInfo=test.createNode("Invalid User Login");

        getAndFillLoginData(Invalid_login_csv);

        logInfo.fail("Invalid Admin credentials");

        Utils.highlightElement(failedMessage,"blue");

        Utils.extentScreenShotCapture(logInfo,"Login Failed");

    }

    // This method is for fill email id and password in their respective fields
    public static void getAndFillLoginData(String data_csv) {

        Log.info("Reading login data from csv file...");

        // get data from csv file
        loginDetails = HandleCSV.readFromLast(data_csv);

        String[] credentials = loginDetails.split(",");

        Log.info("Entering admin mailId...");
        driver.findElement(mail).clear();
        driver.findElement(mail).sendKeys(credentials[0]);
        Log.info("Entering password...");
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(credentials[1]);
        Log.info("Clicking on Login");
        driver.findElement(login).click();

        Utils.wait(1000);

    }

}
