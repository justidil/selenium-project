package models;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	public final String EXPECTED_HOME_TITLE="n11.com";
	WebDriver driver;

	@FindBy(id="searchData")
	WebElement searchBox;
	
	@FindBy(className="searchBtn")
	WebElement searchButton;
	
	@FindBy(tagName="title")
	WebElement title;
	
	@FindBy(xpath="//*[@id=\"header\"]/div/div/div[2]/div[2]/div[2]/div/a")
	WebElement hesabim;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
        PageFactory.initElements(driver, this);
	}
	
	public void Search(String searchText) {
		typeSearchBox(searchText);
		clickSearchButton();
	}
	
	private void typeSearchBox(String str){
		searchBox.sendKeys(str);
	}
	
	public void clickOnHesabim() {
		hesabim.click();
	}
	
	private void clickSearchButton(){
		searchButton.click();
	}
	
    public String getLoginTitle(){
    		String titleText= driver.getTitle();
        return   titleText;
      }
}
