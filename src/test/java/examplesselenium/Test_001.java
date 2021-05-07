package examplesselenium;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testbase.launchDriver;
import Urtrr.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class Test_001 extends launchDriver {
	
	
	@Test
	public void testabsdc()  {
		
		try
		{
		driver.manage().window().maximize();
		Thread.sleep(7000);
		WebElement e=driver.findElement(By.name("q"));
		driver.findElement(By.name("q")).sendKeys("demo");
		
		Screenshot s=new AShot().takeScreenshot(driver, e);
		ImageIO.write(s.getImage(), "jpg", new File("C:\\Users\\gowthamreddy\\eclipse-workspace\\examplesselenium\\screenshot\\name.png"));
		}
		
		catch (Exception e) {
			
			System.out.println("dsds");
			
			// TODO: handle exception
		}
		
	}
	
	

	
}
