package com.assignment;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment {
	public	static	void	main(String[]args) throws InterruptedException, AWTException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ELCOT\\ec\\Selenium\\driver\\chromedriver(1).exe");
		WebDriver	driver	=	new	ChromeDriver();
		driver.manage().window().maximize();
//1.fitpeopage homepage
		driver.get("https://fitpeo.com/");
		
//2.navigate to RevenueCalculatorpage
		driver.navigate().to("https://fitpeo.com/revenue-calculator");
		
//3.scrolldown
		WebElement slider = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)", slider);
	
//4.adjust the slider(820)
			Actions ac = new Actions(driver);
			WebElement slider1 = driver.findElement(By.xpath("//*[@data-index='0']"));
			ac.dragAndDropBy(slider1, 94, 0).perform(); // here the result is 823 if we set the position of 107 the result is 816
		    System.out.println("slider 820 is failed beacause 820 position cannot be set ");

//5.update the text field       
		WebElement text = driver.findElement(By.id(":R57alklff9da:"));
		Thread.sleep(1000);
		Actions act1 = new Actions(driver);
		act1.click(text).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
		act1.sendKeys(text, "560").build().perform();
		Thread.sleep(1000);
		act1.click();
		
//6.validate slider value
		System.out.println(text.getText());
		
//7.cptcodes
		WebElement	e	=	driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[1]"));
		e.click();
		WebElement	f	=	driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[2]"));
		f.click();
		WebElement	g	=	driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[3]"));
		g.click();
		WebElement	h	=	driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[8]"));
		h.click();
		Thread.sleep(1000);
//8.validate
		WebElement j = driver.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']"));
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].scrollIntoView(true)", j);

		act1.click(text).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
		Thread.sleep(2000);
		act1.sendKeys(text, "820").build().perform();
		WebElement Grossrevenue = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-12bch19'])[3]"));
		System.out.println(Grossrevenue.getText());
		
//9.verify
	 		WebElement finalresult=driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-12bch19'])[3]"));
	 		if(finalresult.getText().equals("$110700")) {
	 			System.out.println("final validation is passed ");
	 			TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File destination = new File("C:\\Users\\ELCOT\\ec\\Selenium\\Screenshots\\fitpeoresult.png");
				FileHandler.copy(source,destination);
				
	 			driver.quit();
	 		}
	 		else {
			System.out.println("final validation is failed ");
		}

	}
	
}
