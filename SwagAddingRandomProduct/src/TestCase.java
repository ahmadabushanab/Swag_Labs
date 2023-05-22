import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase extends Parameters {
	@BeforeTest
   public void BeforeTest() {
	   driver.get(WebPage);
	   driver.findElement(By.id("user-name")).sendKeys("standard_user");
	   driver.findElement(By.id("password")).sendKeys("secret_sauce");
	   driver.findElement(By.id("login-button")).click();
   }
   @Test
   public void MyTestCase() {
		List<WebElement> cart = driver.findElements(By.className("inventory_item"));
		Random rand = new Random();
		int myRandomIndex = rand.nextInt(0, cart.size());
		for(int i = 0;i<myRandomIndex;i++) {
			WebElement mycart=cart.get(i).findElement(By.tagName("button"));
			mycart.click();
			 
		}

   }
   @AfterTest
   public void AfterTest() throws InterruptedException, IOException {
	   JavascriptExecutor MyScroller=(JavascriptExecutor)driver;
		MyScroller.executeScript("window.scrollTo(0,5)");
		Date CurentDate=new Date();
		String before = CurentDate.toString();
		String After=before.replace(":", "-");
		Thread.sleep(2000);
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(".//"+After+".png");
		FileUtils.copyFile(SrcFile, DestFile);

   }
}
