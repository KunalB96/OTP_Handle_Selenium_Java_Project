package Util;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import java.time.Duration;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.twilio.base.ResourceSet;
import java.util.stream.StreamSupport;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Amazon_OTP_Handle {
	
	public static final String ACCOUNT_SID = "aasfasa";
	public static final String AUTH_TOKEN = "advdvds";

	public static void main(String[] args) throws InterruptedException {
		
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
	     driver.get("https://www.amazon.com/");
	     
	     driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div/div/form/div/div/span/span/button")).click();
	     
	   //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	     
	     driver.findElement(By.xpath("//div[@id='nav-link-accountList']//a[1]")).click();
	     
	     driver.findElement(By.id("ap_email_login")).sendKeys("8167047425");
	     
	     driver.findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
	     
	     driver.findElement(By.xpath("//*[@id=\"intention-submit-button\"]/span/input")).click();
	     
	     
	   //  driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("8167047425");
	     
	     
	     driver.findElement(By.xpath("//*[@id=\"ap_customer_name\"]")).sendKeys("Alan Mark");
	     
	 
	     driver.findElement(By.xpath("//*[@id=\"ap_password\"]")).sendKeys("KhsuhAlan@1");
	     
	     driver.findElement(By.xpath("//*[@id=\"ap_password_check\"]")).sendKeys("KhsuhAlan@1");
	     
	     driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
	     
	     
	     System.out.println("Please solve the Amazon puzzle manually...");
	     Thread.sleep(20000);
	     
	     
	     /*
	     driver.findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
	     
	     ////span[@id='intention-submit-button']
	     ///
	     driver.findElement(By.xpath("//*[@id=\"intention-submit-button\"]/span/input")).click();
	     
	     //select countory for mobile number dropdown
	     driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).click();
	     
	     //select countory as United State
	     driver.findElement(By.xpath("//a[@id='auth-country-picker_216']")).click();
	
	     
	     //type mobile number in filed
	     try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	     driver.findElement(By.xpath("//input[@id='ap_phone_number']")).sendKeys("8167047425");
	     
	     
	     //type your first and last name
	   //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	     try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys("Alan Mark");
	     
	     
	     //type password
	//     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	     try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("KhsuhAlan@1");
	
	   
	     //click on verify mobile number
	     driver.findElement(By.xpath("//span[@id='auth-continue']")).click();
	     
	     
	     try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
	  //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	     //click on start puzzle button
	   //  driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/button")).click();
	       
	     
	     //wait
	  //   Thread.sleep(20000);
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
	  
	          */
	       
	  
	   //get OTP using Twilio APIs:
	     Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
	     String smsBody = getMessage();
	     System.out.println(smsBody);
	     
	     
	}
	
	/*
	 public static String getMessage() {
	        return getMessages()
	                .filter(m -> m.getDirection() == Message.Direction.INBOUND)
	                .filter(m -> m.getTo().equals("+18167047425"))
	                .map(Message::getBody)
	                .findFirst()
	                .orElseThrow(IllegalStateException::new);
	    }
	 
	        */
	
	
	public static String getMessage() {
	    return getMessages()
	            .filter(m -> m.getTo().equals("+18167047425"))
	            .map(Message::getBody)
	            .findFirst()
	            .orElse("NO OTP RECEIVED");
	}

	
	     
	   public static Stream<Message> getMessages() {
	        ResourceSet<Message> messages =
	                Message.reader(ACCOUNT_SID).read();
	        return StreamSupport.stream(messages.spliterator(), false);
	    }
}
