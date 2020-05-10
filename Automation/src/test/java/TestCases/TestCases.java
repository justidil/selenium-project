package TestCases;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import models.FavoritesPage;
import models.HomePage;
import models.LoginPage;
import models.SearchResultsPage;

@Test
public class TestCases {
	final String USERNAME="idilagabeyoglu@hotmail.com";
	final String PASSWORD="i123dil";
	final String SEARCH_TEXT="samsung";
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	SearchResultsPage searchPage;
	FavoritesPage favoritesPage;
	String latestFavoriteId;
	
	@BeforeTest
	public void setup()
	{
		latestFavoriteId="";
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		 driver= new ChromeDriver();
		driver.get("http://www.n11.com/");
		homePage=new HomePage(driver);
		loginPage=new LoginPage(driver);
		searchPage=new SearchResultsPage(driver);
		favoritesPage=new FavoritesPage(driver);
	}
	
    @Test(priority=0)	
    public void test_Home_Page_Appear_Correct()
    {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}    	    
		String title=homePage.getLoginTitle();
		Assert.assertTrue(title.contains(homePage.EXPECTED_HOME_TITLE));
	}
    @Test(priority=1)	
    public void test_Logged_In()
	{
    		homePage.clickOnHesabim();
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}    		
    		loginPage.Login(USERNAME, PASSWORD);
		String title=loginPage.getLoginTitle();
		Assert.assertTrue(title.contains(loginPage.EXPECTED_LOGGED_IN_TITLE));	
	}
    
    @Test(priority=2)	
    public void test_text_is_searched()
	{
		driver.get("http://www.n11.com/");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		homePage.Search(SEARCH_TEXT);	
	}
    
    @Test(priority=3)	
    public void test_search_results_are_related() throws InterruptedException
	{
		Thread.sleep(2000);
    		ArrayList<String> searchResults = searchPage.GetSearchResults();
    		for(int a=0;a<searchResults.size();a++) {
    			Assert.assertTrue(searchResults.get(a).toLowerCase().contains(SEARCH_TEXT));
    		}		
	}
    
    @Test(priority=4)	
    public void test_correctly_added_to_favorites() throws InterruptedException
	{
    		searchPage.skipPage();
    		Thread.sleep(1000);
    		latestFavoriteId=searchPage.AddResultToFavorites(2);
    		searchPage.GoToFavoritesPage();
    		Thread.sleep(1000);
    		favoritesPage.ClickOnFavorites();
    		Thread.sleep(1000);
    		boolean exist=favoritesPage.IsFavoriteExist(latestFavoriteId);
    		Assert.assertTrue(exist);
	}
    
    @Test(priority=5)	
    public void test_correctly_removed_from_favorites()
	{
		if(latestFavoriteId=="")
    			Assert.assertTrue(false);
		favoritesPage.RemoveFavorite(latestFavoriteId);
		boolean exist=favoritesPage.IsFavoriteExist(latestFavoriteId);
		latestFavoriteId="";
		Assert.assertTrue(exist);
		driver.close();
	}  
}