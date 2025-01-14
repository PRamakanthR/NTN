package stepDefintions;

import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.driverfactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class Register {
	WebDriver driver=driverfactory.getDriver();

	@Given("User navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@When("User enters the details into below fields")
	public void user_enters_the_details_into_below_fields(DataTable dataTable) {
		Map<String, String> data=dataTable.asMap(String.class,String.class);
		driver.findElement(By.id("input-firstname")).sendKeys(data.get("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(data.get("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(generateRandomString(10));
		driver.findElement(By.id("input-telephone")).sendKeys(data.get("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(data.get("password"));
		driver.findElement(By.id("input-confirm")).sendKeys(data.get("password"));

	}
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        String email= sb.toString()+"@gmail.com";
        return email;
    }

	@When("User selects Privacy Policy")
	public void user_selects_privacy_policy() {
		driver.findElement(By.name("agree")).click();
	}

	@When("User clicks on Continue button")
	public void user_clicks_on_continue_button() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

	}

	@Then("User account should get created successfully")
	public void user_account_should_get_created_successfully() {
		Assert.assertEquals("Your Account Has Been Created!", driver.findElement(By.xpath("//div[@id='content']/h1")).getText());
	}

	@When("User selects Yes for Newsletter")
	public void user_selects_yes_for_newsletter() {
		driver.findElement(By.xpath("(//input[@name='newsletter'])[1]")).click();
	}

	@When("User enters the details into below fields with duplicate email")
	public void user_enters_the_details_into_below_fields_with_duplicate_email(DataTable dataTable) {
		Map<String, String> data=dataTable.asMap(String.class,String.class);
		driver.findElement(By.id("input-firstname")).sendKeys(data.get("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(data.get("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(data.get("email"));
		driver.findElement(By.id("input-telephone")).sendKeys(data.get("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(data.get("password"));
		driver.findElement(By.id("input-confirm")).sendKeys(data.get("password"));
	}

	@Then("User should get a proper warning about duplicate email")
	public void user_should_get_a_proper_warning_about_duplicate_email() {
		driver.findElement(By.xpath("//div[contains(text(),'Warning: E-Mail Address is already registered!')]"));
	}
	

	@When("User dont enter any details into fields")
	public void user_dont_enter_any_details_into_fields() {

	}

	@Then("User should get proper warning messages for every mandatory field")
	public void user_should_get_proper_warning_messages_for_every_mandatory_field() {
		Assert.assertEquals("First Name must be between 1 and 32 characters!",driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText());
		Assert.assertEquals("Last Name must be between 1 and 32 characters!",driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText());
		Assert.assertEquals("E-Mail Address does not appear to be valid!",driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText());
		Assert.assertEquals("Telephone must be between 3 and 32 characters!",driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText());
		Assert.assertEquals("Password must be between 4 and 20 characters!",driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText());

		driver.findElement(By.xpath("//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]"));

	}

}
