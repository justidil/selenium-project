package models;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	public final String EXPECTED_TITLE="n11.com - Alışverişin Uğurlu Adresi";	
	
	WebDriver driver;
    By listingGroupBy = By.cssSelector(".group.listingGroup.resultListGroup");
    By resultListBy = By.className("column");
    By loginBy = By.id("loginButton");
    By resultTextBy=By.cssSelector(".productName");
    By addFavoriteButtonBy=By.cssSelector(".textImg.followBtn");
    By hesabimMenuBy = By.xpath("//*[@id=\"header\"]/div/div/div[2]/div[2]/div[2]/div[1]/a[1]");
    By favoritesButtonBy= By.xpath("//*[@id=\"header\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/a[2]");
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public ArrayList<String> GetSearchResults()
	{
		ArrayList<WebElement> searchResults= (ArrayList<WebElement>) driver.findElement(listingGroupBy).findElements(resultListBy);
		ArrayList<String> resultTexts=new ArrayList<String>();
		for(int a=0;a<searchResults.size();a++) {
			WebElement textElement=searchResults.get(a).findElement(resultTextBy);
			String text=textElement.getText();
			resultTexts.add(text);
		}
		return resultTexts;
	}	
	
	public void GoToFavoritesPage()
	{
		Actions action = new Actions(driver);
		WebElement hesabim = driver.findElement(hesabimMenuBy);
		action.moveToElement(hesabim).build().perform();
		WebElement favorites=driver.findElement(favoritesButtonBy);
		favorites.click();
	}
	
	public void skipPage()
	{
		driver.findElement(By.cssSelector(".next.navigation")).click();    
	}
	
	public String AddResultToFavorites(int index) 
	{
		WebElement result=getResult(index);
		Actions action= new Actions(driver);
		WebElement favoriteButton =result.findElement(addFavoriteButtonBy);
		String favoriteId=result.findElement(By.cssSelector(".columnContent")).getAttribute("id");
		action.moveToElement(favoriteButton).click().perform();
		return favoriteId;
	}
	
	private WebElement getResult(int index) 
	{
		ArrayList<WebElement> searchResults= (ArrayList<WebElement>) driver.findElement(listingGroupBy).findElements(resultListBy);
		return searchResults.get(index);
	}
}
