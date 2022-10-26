package week6;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ServiceNow {
	
	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev137890.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Testleaf@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		shadow.findElementByXPath("//div[text()='All']").click();
		Shadow shadow1 = new Shadow(driver);
		shadow1.setImplicitWait(20);
		shadow1.findElementByXPath("//span[text()='Incidents']").click();
		
		
		//to Click on Create new option and fill the mandatory fields
		Shadow shadow2 = new Shadow(driver);
		WebElement frame = shadow2 .findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[@class='selected_action action_context btn btn-primary']")).click();
		String text = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("IncidentNo" + text);
		
		driver.findElement(By.id("incident.short_description")).sendKeys("Incident Recorded");
		driver.findElement(By.xpath("//button[@class='form_action_button header  action_context btn btn-default']")).click();
		driver.switchTo().defaultContent();
		
		//to Verify the newly created incident by getting the incident number and put it in search field and 
		// enter then verify the instance number created or not
		
		Shadow shadow3 = new Shadow(driver);
		WebElement frame2 = shadow3.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(text, Keys.ENTER);
		driver.switchTo().defaultContent();
		
		//to Confirm incident exists !
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//input[contains(@class,'list_header_search ')]")).getText();
		System.out.println("Incident exists" + text);
		
		
		
		
		
		
		
		
		
	}

}
