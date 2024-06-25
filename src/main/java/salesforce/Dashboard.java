package salesforce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys("radhakrishnan@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Yuvan@123");
		driver.findElement(By.id("Login")).click();
		WebElement homepageverify = driver.findElement(By.xpath("(//span[text()='Home'])[3]"));
		wait.until(ExpectedConditions.visibilityOf(homepageverify));
		WebElement appbutton = driver.findElement(By.xpath("//div[@class='slds-r7']/following-sibling::div[1]"));
		wait.until(ExpectedConditions.visibilityOf(appbutton));
		appbutton.click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Dashboards");
		String verifydashboard = "Dashboards";
		WebElement Dashboardtext = driver.findElement(By.xpath("//mark[text()='Dashboards']"));
		wait.until(ExpectedConditions.visibilityOf(Dashboardtext));
		String dashboardgettext = driver.findElement(By.xpath("//mark[text()='Dashboards']")).getText();
		if(verifydashboard.equals(dashboardgettext)) {
			driver.findElement(By.xpath("//mark[text()='Dashboards']")).click();
		}
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();
		WebElement dashboardFrame = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(dashboardFrame);
		WebElement dashboardname = driver.findElement(By.id("dashboardNameInput"));
		wait.until(ExpectedConditions.visibilityOf(dashboardname));
		dashboardname.sendKeys("karthikpoco");
		driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("Hellow world");
		driver.findElement(By.id("submitBtn")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		try {
			WebElement doneButton = driver.findElement(By.xpath("//div[@class='toolbarActions']//button[text()='Done']"));
			driver.executeScript("arguments[0].click();", doneButton);
			try {
				Actions act = new Actions(driver);
				act.doubleClick(doneButton).perform();
				doneButton.click();
			}
			catch (Exception e) {
				System.out.println("RuntimeException");
			}
		}catch (Exception e) {
			System.err.println("Done button is not clicked");
		}

	}
}