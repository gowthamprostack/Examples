package testbase;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes.Name;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;




public class launchDriver {

	public static WebDriver driver;
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports entent;
	public ExtentTest test;
	



	@BeforeTest
	public void dd() {
		System.out.println("success");
		
		try
		{
			
			
			htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir") +"/testReportone.html");
			entent=new ExtentReports();
			entent.attachReporter(htmlreporter);
			
			entent.setSystemInfo("OS", "ddf");
			entent.setSystemInfo("Browser","fdf");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
			// TODO: handle exception
		
		
		//entent.setSystemInfo("fsds", "sdsd");
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedrivertwo.exe");
		driver = new ChromeDriver();
		
		driver.get("https://www.google.co.in/");
	}
	

	
	public static WebDriver getDriver() {
		return driver;

	}
	
	
	public String takescreenshot(String nameone) throws IOException
	{
		
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);

      

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            

                File DestFile=new File(System.getProperty("user.dir")+"\\screenshot\\"+nameone+".png" );
                
                FileUtils.copyFile(SrcFile, DestFile);
                
                return "C:\\Users\\gowthamreddy\\eclipse-workspace\\examplesselenium\\screenshot\\test.png";
	}
	
	
	
	
	public void fullpage(String name) throws IOException
	{
		Screenshot sc=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
		
		ImageIO.write(sc.getImage(), "PNG", new File(System.getProperty("user.dir")+"\\screenshot\\"+name+".png"));
		
		
	}
	
	@AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
            
            
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
	
	
	@AfterTest
	public void end()
	{
		
		entent.flush();
		driver.close();
		
	}
}
