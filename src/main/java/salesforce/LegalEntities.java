package salesforce;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LegalEntities {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.id("username")).sendKeys("radhakrishnan@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Yuvan@123");
		driver.findElement(By.id("Login")).click();

		driver.findElement(By.xpath("//button[@class='slds-button slds-show']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();

		WebElement legalEntitiesWebElement = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		driver.executeScript("arguments[0].click();", legalEntitiesWebElement);

		// driver.findElement(By.xpath("//span[@title='Last Modified Date']")).click();
		// Thread.sleep(2000);

		driver.close();
	}

}
