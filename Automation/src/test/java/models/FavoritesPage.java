package models;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FavoritesPage {
	public final String EXPECTED_TITLE="n11.com ";
	public final int FAVORITED_NUMBER=3;
    public String latestFavoriedElementId;
	WebDriver driver;	
    By favoriesButtonBy=By.cssSelector(".listItemTitle");
    By favoriteItemsBy=By.cssSelector(".column.wishListColumn");
    By removeButtonBy=By.cssSelector(".deleteProFromFavorites");
	
	public FavoritesPage(WebDriver driver)
	{
		this.driver=driver;
		latestFavoriedElementId="";
	}
	
	public void ClickOnFavorites()
	{
		driver.findElement(favoriesButtonBy).click();
	}

	public boolean IsFavoriteExist(String id)
	{
		ArrayList<WebElement> favorites= (ArrayList<WebElement>) driver.findElements(favoriteItemsBy);
		for(int a=0;a<favorites.size();a++) {	
			String favoriteId=favorites.get(a).findElement(By.cssSelector(".columnContent")).getAttribute("id");
			if(id.equals(favoriteId)) {
				return true;
			}
		}
		return false;
	}	
	
	public boolean RemoveFavorite(String id)
	{
		ArrayList<WebElement> favorites= (ArrayList<WebElement>) driver.findElements(favoriteItemsBy);
		for(int a=0;a<favorites.size();a++) {
			String favoriteId=favorites.get(a).findElement(By.cssSelector(".columnContent")).getAttribute("id");
			if(id.equals(favoriteId)) {
				favorites.get(a).findElement(removeButtonBy).click();
				return true;
			}	
		}
		return false;
	}		
}
