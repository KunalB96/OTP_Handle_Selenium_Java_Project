package otp;

import com.twilio.Twilio;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TwilioOtpSeleniumDemo {

    // ===================== TWILIO CONFIG =====================
    private static final String ACCOUNT_SID = "TWILIO_ACCOUNT_SID";
    private static final String AUTH_TOKEN = "TWILIO_AUTH_TOKEN";
    private static final String TWILIO_NUMBER = "+181670474256"; // Twilio SMS number
    private static final String USER_MOBILE   = "+919866"; // Your mobile

    private static String generatedOtp;

    // ===================== OTP SENDER =====================
    public static void sendOtp() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        generatedOtp = String.valueOf((int)(Math.random() * 900000) + 100000);

        Message.creator(
                new PhoneNumber(USER_MOBILE),
                new PhoneNumber(TWILIO_NUMBER),
                "Your OTP is: " + generatedOtp
        ).create();

        System.out.println("OTP Sent: " + generatedOtp);
    }

    // ===================== OTP READER =====================
    public static String readOtpFromTwilio() {
        ResourceSet<Message> messages = Message.reader().read();

        return StreamSupport.stream(messages.spliterator(), false)
                .filter(m -> m.getTo().equals(USER_MOBILE))
                .map(Message::getBody)
                .filter(body -> body.contains("OTP"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("OTP not received"));
    }

    // ===================== SELENIUM TEST =====================
    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Demo HTML page (replace with your test app)
        driver.get("file:///E:/OTP Automation/OTP_Handler/src/main/java/otp/demo.html");

        // Trigger OTP
        driver.findElement(By.id("sendOtp")).click();
        sendOtp();

        Thread.sleep(5000); // wait for SMS

        String sms = readOtpFromTwilio();
        String otp = sms.replaceAll("\\D+", "");

        System.out.println("OTP Read From SMS: " + otp);

        // Enter OTP
        driver.findElement(By.id("otp")).sendKeys(otp);
        driver.findElement(By.id("verify")).click();

        System.out.println("OTP Verification Completed");
    }
}
