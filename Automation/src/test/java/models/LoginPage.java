package models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	public final String EXPECTED_LOGGED_IN_TITLE="n11.com";

    WebDriver driver;

    By email = By.id("email");
    
    By password = By.name("password");


    By login = By.id("loginButton");
    

    public LoginPage(WebDriver driver){

        this.driver = driver;

    }
	public void WaitUntilLoad() {
		WebDriverWait wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(email));
		wait.until(ExpectedConditions.elementToBeClickable(password));
		wait.until(ExpectedConditions.elementToBeClickable(login));
	}

    public void Login(String username,String password) {
    	
    	setEmail(username);
    	setPassword(password);
    	clickLogin();
    }
    //Set user name in textbox

    private void setEmail(String emailText){

		driver.findElement(email).sendKeys(emailText);

    }

    //Set password in password textbox

    private void setPassword(String strPassword){

         driver.findElement(password).sendKeys(strPassword);

    }

    public String getLoginTitle(){

    	String titleText= driver.getTitle();
        return   titleText;
       }

    private void clickLogin(){

            driver.findElement(login).click();

    }

}
