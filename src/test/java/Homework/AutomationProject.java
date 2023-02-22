package Homework;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class AutomationProject {

    public static void main(String[] args)  {

        //1. navigate to //http://duotify.us-east-2.elasticbeanstalk.com/register.php
        WebDriver driver = new ChromeDriver();
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        //2. Verify  the title is "Welcome to Duotify!"

        String expectedTitle = "Welcome to Duotify!";
        String actualTitle = driver.getTitle();

        //3. Click on Signup here
        driver.findElement(By.id("hideLogin")).click();
//
//    //4. Fill out the form with the required info using Faker class
//    //5. Click on Sign up
        driver.findElement(By.id("username")).sendKeys("jd" + Keys.ENTER);
        driver.findElement(By.id("firstName")).sendKeys("jekaterina" + Keys.ENTER);
        driver.findElement(By.id("lastName")).sendKeys("demi" + Keys.ENTER);
        driver.findElement(By.id("email")).sendKeys("jekaterina_demidova@hotmail.com" + Keys.ENTER);
        driver.findElement(By.id("email2")).sendKeys("jekaterina_demidova@hotmail.com" + Keys.ENTER);
        driver.findElement(By.id("password")).sendKeys("Password1234" + Keys.ENTER);
        driver.findElement(By.id("password2")).sendKeys("Password1234" + Keys.ENTER);
        driver.findElement(By.name("registerButton")).click();

        //6. Once logged in to the application, verify that the URL is:
        //http://duotify.us-east-2.elasticbeanstalk.com/browse.php?
        String username = "jd";
        String password = "Password1234";
        if (!driver.getCurrentUrl().equals("http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?")) {
            driver.findElement(By.id("hideRegister")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("loginUsername")).sendKeys(username, Keys.TAB, password, Keys.TAB, Keys.ENTER);
            Thread.sleep(1000);

//        //7. In the left navigation bar, verify that your first and last name is the same
//        // the first and last name that you used when signing up.
//        // (use getText() method to extract the text of the element)
            String firstName = "jekaterina";
            String lastName = "demi";
            assertEquals(driver.findElement(By.id("firstName" +  "lastName")).getText(), firstName + " " + lastName);

            // 8. Click on the first and last name on the left navigation bar and
            // verify the first and last name on the main window is correct and then click logout.

            driver.findElement(By.id("firstName"+"lastName")).click();

            try {
                assertTrue(driver.findElement(By.tagName("h2")).getText().contains("jekaterina demi"));
            } catch (AssertionError ae) {
                ae.printStackTrace();
            }
            driver.findElement(By.id("h2")).click();

            //9. Verify that you are logged out by verifying the URL is:
            try {
                assertEquals(driver.getCurrentUrl(), "http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php");
            } catch (AssertionError ae) {
                ae.printStackTrace();
                Thread.sleep(5000);

                //10. Login using the same username and password when you signed up.

                driver.findElement(By.id("loginUsername")).sendKeys(username, Keys.TAB);
                Thread.sleep(1000);
                driver.findElement(By.id("loginPassword")).sendKeys("Password1234", Keys.ENTER);
                Thread.sleep(5000);
            }
            //11. Verify successful login by verifying that the home page contains the text "You Might Also Like".
            try {
                assertTrue(driver.getPageSource().contains("You Might Also Like"));
            } catch (AssertionError ae) {
                ae.printStackTrace();

                Thread.sleep(5000);

                // 12. Log out once again and verify that you are logged out.
                driver.findElement(By.id("firstName And lastName")).click();
                Thread.sleep(1000);
                driver.findElement(By.id("h2")).click();
                Thread.sleep(1000);
                try {
                    assertEquals(driver.getCurrentUrl(), "http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php");
                } catch (AssertionError ae2) {
                    ae2.printStackTrace();
                    Thread.sleep(5000);
                }

                driver.quit();

            }
        }
    }
}







