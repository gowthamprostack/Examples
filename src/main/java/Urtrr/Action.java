package Urtrr;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testbase.launchDriver;



public class Action {
	public static void click(By loc)	{
		launchDriver.getDriver().findElement(loc).click();
	}
	public static void doubleClick(By loc)	{
		Actions actions = new Actions(launchDriver.getDriver());
		WebElement elementLocator = launchDriver.getDriver().findElement(loc);
		actions.doubleClick(elementLocator).perform();
	}
	public static void sendKeys(By loc, String value)	{
		launchDriver.getDriver().findElement(loc).sendKeys(value);
	}
	
	public static void sendKeys(By loc, Keys value)	{
		launchDriver.getDriver().findElement(loc).sendKeys(value);
	}
	public static void clear(By loc)	{
		launchDriver.getDriver().findElement(loc).clear();
	}
	public static void waitTime(int waitingtime)	{
		try {
			Thread.sleep(waitingtime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void explicitWait(By loc, long time)	{
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(), time);
		wait.until(ExpectedConditions.elementToBeClickable(loc));
	}
	public static void moveToWindow(String window)	{
		launchDriver.getDriver().switchTo().window(window);
	}
	public static void clickWithJS(By loc)	{
		WebElement element = launchDriver.getDriver().findElement(loc);
		JavascriptExecutor executor = (JavascriptExecutor)launchDriver.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void clickWithJS(WebElement loc)	{
		
		JavascriptExecutor executor = (JavascriptExecutor)launchDriver.getDriver();
		executor.executeScript("arguments[0].click();",loc);
	}
	public static WebElement findElement(By loc)	{
		return launchDriver.getDriver().findElement(loc);
	}
	public static List<WebElement> findElements(By loc)	{
		return launchDriver.getDriver().findElements(loc);
	}
	public static void naviagteBack()	{
		launchDriver.getDriver().navigate().back();
	}
	public static void naviagteforward()	{
		launchDriver.getDriver().navigate().refresh();
	}
	public static void scrollToElement(By loc)	{
		((JavascriptExecutor) launchDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", launchDriver.getDriver().findElement(loc));
		waitTime(1000);
	}
	
	
	public static void scrollToElement(WebElement loc)	{
		((JavascriptExecutor) launchDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",loc);
		waitTime(1000);
	}
	
	public static void scrollToElementclick(WebElement loc)	{
		((JavascriptExecutor) launchDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",loc);
		waitTime(1000);
		try
		{
		Action.waitforelement(loc);
		loc.click();
		}
		catch (Exception e) {
			
			Action.clickWithJS(loc);
			// TODO: handle exception
		}
	}
	
	
	public static void scrollDown(long value)	{
		((JavascriptExecutor) launchDriver.getDriver()).executeScript("window.scrollBy(0,'"+value+"')","");
	}
	public static void scrollUp(long value)	{
		((JavascriptExecutor) launchDriver.getDriver()).executeScript("window.scrollBy(0,-'"+value+"')","");
	}
	public static void scrollToTop(){
		scrollUp(100000);
	}
	public static void openNewTab()	{
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_T);
		} catch (AWTException e) {
			launchDriver.getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
			System.out.println("-----Exception while opening new tab-----");
			Assert.fail("failed while opening new tab");
		}
	}
	public static void uploadFile(String filepath)	{
		StringSelection stringSelection = new StringSelection(filepath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public static void clearCache()	{
		
		Action.waitTime(2000);
		launchDriver.getDriver().manage().deleteAllCookies();
		Action.waitTime(2000);
		Action.refresh();
		Action.waitTime(2000);
	}
	public static void refresh()	{
		launchDriver.getDriver().navigate().refresh();
	}
	//new
	public static String getText(By loc)	{
		return launchDriver.getDriver().findElement(loc).getText().toString();
	}
	public static String getattribute(By loc)	{
		return launchDriver.getDriver().findElement(loc).getAttribute("value").toString();
	}
	public static String getattribute(String attributeType, By loc)	{
		return launchDriver.getDriver().findElement(loc).getAttribute(attributeType).toString();
	}
	public static Boolean getRequired(By loc)	{
		Boolean flag ;
		try {
		String status = launchDriver.getDriver().findElement(loc).getAttribute("required");
		if(status.equalsIgnoreCase("true"))	
			flag = true;
		else 
			flag = false;
		}
		catch(NullPointerException e)	{
			flag=false;
		}
		return flag;
	}
	public static String getreadOnlyProperty(By loc)	{
		return launchDriver.getDriver().findElement(loc).getAttribute("readonly").toString();
	}
	public static String getAppid(By loc)	{
		return launchDriver.getDriver().findElement(loc).getAttribute("ng-reflect-model").toString();
	}
	public static String getClassName(By loc)	{
		return launchDriver.getDriver().findElement(loc).getAttribute("class").toString();
	}
	//public static Boolean isContinueButtonEnabled()	{
		/*System.out.println(Action.getClassName(PageFactory.uat_Sales_UserCreation.continueButton));
		if(Action.getClassName(PageFactory.uat_Sales_UserCreation.continueButton).contains("disabled"))	{
			System.out.println("Not enabled");
			return false;
		}
		return true;*/
	//}
	public static boolean isClickable(By loc)	{
		try {
			WebDriverWait wait= new WebDriverWait(launchDriver.getDriver(),2);
			wait.until(ExpectedConditions.elementToBeClickable(Action.findElement(loc)));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	public static void pressBackSpace()	{
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void presstab()	{
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void pressEnter()	{
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Boolean isDisplayed(By loc)	{
		
		try
		{
		Action.explicitWait(loc, 30);	
		if(Action.findElement(loc).isDisplayed())	 {
			return true;
		}
		else
			return false;
		}
		catch (Exception e) {
			
			return false;
			// TODO: handle exception
		}
	}
	public static boolean isElementPresent(By loc) {
		
		try
		{
		Action.explicitWait(loc, 20);
	    if(Action.findElements(loc).size()>0)	
	    {
	    	System.out.println(Action.findElements(loc).size()+"size element present");
	    	
	    	return true;
	    }
	    else
	    	return false;
		}
		catch (Exception e) {
			
			Assert.assertEquals(true, true);
			return false;
			// TODO: handle exception
		}
	}
	

	public static void submitform(By loc)	{
		launchDriver.getDriver().findElement(loc).submit();
	}
	
	public static void waitforelement(By ele)
	{		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),50);
	  
		 wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		
	}
	
	
	public static void waitforelement(WebElement ele)
	{		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),50);
	  
		 wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	public static void waitforelement(WebElement ele,int time)
	{		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),time);
	  
		 wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	
	public static void waitforelementclick(By ele)
	{		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),60);
		
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		
	}
	public static List<WebElement> waitforallelement(List<WebElement> ele)
	{
		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),20);
		return wait.until(ExpectedConditions.visibilityOfAllElements(ele));
			
	}
	
	public static List<WebElement> waitforallelement(List<WebElement> ele,int a)
	{
		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),a);
		return wait.until(ExpectedConditions.visibilityOfAllElements(ele));
			
	}
	
	public static WebElement waitforelementclick(WebElement ele)
	{		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),60);
		
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
		
	}
	
	public static WebElement waitforelementclick(WebElement ele,int a)
	{		
		WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),a);
		
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
		
	}
	
	
	public static void dropdoen(By loc)
	{
		
		Action.waitforelement(loc);
	}
	
	
	public static void selectdropdown(WebElement loc,int index1)
	{
		Action.waitforelement(loc);
		Select sc=new Select(loc);
		sc.selectByIndex(index1);
		Action.waitTime(2000);
		
		
		
	}
	
	public static void selectdropdown(WebElement loc,String name)
	{
		Action.waitforelement(loc);
		Select sc=new Select(loc);
		sc.selectByVisibleText(name);
		Action.waitTime(2000);
		
	}
	
	public static Alert alertpresent()
	{
		
      WebDriverWait wait=new WebDriverWait(launchDriver.getDriver(),20);
		
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public void datausingscript()
	{
		
		JavascriptExecutor js = (JavascriptExecutor)launchDriver.getDriver();
		 
		//js.executeScript(“document.getElementsById(‘some_id’).value=’Avinash Mishra’;”);
	}
	
	
	public static void clickwithtry(WebElement ele)
	{
		
		try
		{
			Action.waitTime(1000);
			Action.waitforelementclick(ele);
			ele.click();
		}
		
		catch (Exception e) {
			try
			{
			Action.clickWithJS(ele);
			}
			catch (Exception e1) {
				
				Actions a=new Actions(launchDriver.getDriver());
				a.moveToElement(ele).click().build().perform();
				Assert.assertEquals(true, true);
				// TODO: handle exception
			}
			
			// TODO: handle exception
		}
		
	}
	
	
	public static void clickwithtry(By ele)
	{
		
		try
		{
			Action.waitTime(3000);
			Action.waitforelement(ele);
			Action.click(ele);
			
		}
		
		catch (Exception e) {
			try
			{
			Action.clickWithJS(ele);
			}
			catch (Exception e1) {
				
				Assert.assertEquals(true, true);
				// TODO: handle exception
			}
			
			// TODO: handle exception
		}
		
	}


	public static void doclickfromlist(List<WebElement>l1,int a)
	{
		Action.waitTime(2000);
		try
		{
			Action.waitforallelement(l1);
			Action.waitTime(1000);
			Action.waitforelement(l1.get(a), 20);
			Action.waitTime(1000);
			l1.get(a).click();
		}
		
		catch (Exception e) {
			
			Action.waitTime(2000);
			Action.clickWithJS(l1.get(a));
			// TODO: handle exception
		}
	}
	
	
	
	public static void selectdate(String id,String year)
	{
		
		
		((JavascriptExecutor)launchDriver.getDriver())
		.executeScript("document.getElementById('"+id+"').removeAttribute('readonly',0)"); // Enables
																									// the from
																									// date box

WebElement toDateBox = launchDriver.getDriver().findElement(By.id(id));
toDateBox.clear();

toDateBox.sendKeys(year);
		
	}
	
	
	
	
	
	public static void selectdropdownelement(WebElement clickele,WebElement selectele)
	{
		
		Action.waitforelementclick(clickele);
		Action.clickwithtry(clickele);
		Action.waitTime(3000);
		Action.clickwithtry(selectele);
			
	}
	
	
	public static void clickuntilvisible(WebElement elementtoclick,List<WebElement>elementtoappear)
	{
	
		int loopCount1=0,maxLoopCount1=20;
		
	  
		while((elementtoappear.size()<=0) && (loopCount1<maxLoopCount1))	{
						
			Action.clickwithtry(elementtoclick);	
			loopCount1++;
			}	
		
		
		
	
	}
	
	public static void clickwithjs(WebElement ele)
	{
		
		try
		{
			
			ele.click();
		}
		
		catch (Exception e) {
			
			Action.waitTime(3000);
			Action.clickwithjs(ele);
			
			// TODO: handle exception
		}
		
	}
	
	
	
	
	public static void selectdropdown(By loc,int index1)
	{
		
		Action.waitforelement(loc);
		WebElement name=Action.findElement(loc);
		Action.waitTime(2000);
		Select sc=new Select(name);
		sc.selectByIndex(index1);
		Action.waitTime(2000);
		
	}
	
	
	
}
