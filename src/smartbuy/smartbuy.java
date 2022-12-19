package smartbuy;

import java.time.Duration;
import java.util.Iterator;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class smartbuy {

	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	public int numperOfTry = 5;
	// public int numberOfInventroy = 4;

	@BeforeTest()
	public void this_is_before_test() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();

	}

	@Test()
	public void addtest() {

		for (int i = 0; i < numperOfTry; i++) {

			driver.findElement(By.xpath(
					"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button"))
					.click();

			WebElement cartmessage = driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/div[1]"));
			String message = cartmessage.getText();
			System.out.println(message);

			if (message.contains("Sorry")) {

				driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[1]")).click();
				numperOfTry = i;

			} else {
				driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();
			}

		}
	}

	@Test()
	public void chick_correct_price() {
		driver.navigate().back();
		String unitPrice = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]"))
				.getText();

		String[] splitUnitPrice = unitPrice.split("JOD");
		String finalSplitUnitPrice = splitUnitPrice[0].trim();
		String replacePrice = finalSplitUnitPrice.replace(",", ".");
		double finalUnitPrice = Double.parseDouble(replacePrice);

		System.out.println(finalSplitUnitPrice);

		driver.findElement(By.className("mini-cart-link")).click();
		String totalcartprice = driver
				.findElement(By.xpath("//*[@id=\"cboxLoadedContent\"]/div/div/div[2]/div[1]/h4[2]")).getText();
		System.out.println(totalcartprice);
		String replacetotalcartprice = totalcartprice.replace(",", ".");
		String[] splittotalprice = replacetotalcartprice.split(".000 JOD");
		String finalsplittotalprice = splittotalprice[0].trim();
		System.out.println(finalsplittotalprice + " " + "final total price");
		double parsefinalsplittotalprice = Double.parseDouble(finalsplittotalprice);
		driver.findElement(By.xpath("//*[@id=\"cboxLoadedContent\"]/div/div/div[2]/div[2]/a[2]")).click();

		double final_total_item_price = (numperOfTry * finalUnitPrice);
		System.out.println(final_total_item_price + "  " + "this is total to compare");

		softassert.assertEquals(parsefinalsplittotalprice, final_total_item_price);

		softassert.assertAll();

//		String unitPrice = driver
//				.findElement(By.xpath(
//						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]"))
//				.getText();
//
//		String[] splitUnitPrice = unitPrice.split("JOD");
//		String finalSplitUnitPrice = splitUnitPrice[0].trim();
//		String replacePrice = finalSplitUnitPrice.replace(",", ".");
//		double finalUnitPrice = Double.parseDouble(replacePrice);
//
//		System.out.println(finalSplitUnitPrice);
//
//		driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[1]")).click();
//
//		String orderTotal = driver
//				.findElement(By.xpath("/html/body/main/div[3]/div[1]/div[2]/div[4]/div/div[2]/div/div[1]/div[4]"))
//				.getText();
//		System.out.println("order total is " + orderTotal);
//
//		String[] orderTotalSplit = orderTotal.split(".000 JOD");
//		String finalSplitOrderTotal = orderTotalSplit[0].trim();
//		String replaceCartTolal = finalSplitOrderTotal.replace(",", ".");
//
//		double parseOrderTotal = Double.parseDouble(replaceCartTolal);
//		System.out.println(parseOrderTotal + "after parse in cart cotal price");
//
//		String aa = driver.findElement(By.className("nav-items-total")).getText();
//		int aa2 = Integer.parseInt(aa);
//		System.out.println(aa2);
//		Double totalPrice = aa2 * finalUnitPrice;

//		softassert.assertEquals(totalPrice, parseOrderTotal);
//
//		softassert.assertAll();

	}

	@Test()
	public void discount() {
		String discountnumber = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[1]"))
				.getText();
		System.out.println(discountnumber);
		String[] spltDiscount = discountnumber.split(" %");
		String finalsplitdiscount = spltDiscount[0].trim();
		System.out.println(finalsplitdiscount);
		double finaldiscount = Double.parseDouble(finalsplitdiscount);
		System.out.println(finaldiscount);

		String orginalprice = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[2]"))
				.getText();
		
		
		
		String[] split_orgenal_price = orginalprice.split(" JOD");
		String final_split_orginal_price = split_orgenal_price[0].trim();
		System.out.println(final_split_orginal_price);
		String replace_orginal_price = final_split_orginal_price.replace(",", "").trim();
		
//		double final_orginal_price = Double.parseDouble(replace_orginal_price);
		double final_orginal_price = Double.parseDouble(replace_orginal_price);
		System.out.println(final_orginal_price+"mamamammamamamam");
		
		
		String unitPrice = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]"))
				.getText();

		String[] splitUnitPrice = unitPrice.split("JOD");
		String finalSplitUnitPrice = splitUnitPrice[0].trim();
		String replacePrice = finalSplitUnitPrice.replace(",", "").trim();
//		double finalUnitPrice = Double.parseDouble(replacePrice);
		double finalUnitPrice =Double.parseDouble(replacePrice);
		
		System.out.println(finalUnitPrice);
		
		double discount = final_orginal_price * finaldiscount /100;
		double final_dicount=final_orginal_price-discount;
		System.out.println(final_dicount+"  "+"this is final discount");
		double mm=Math.round(final_dicount);
		
		System.out.println(mm+"  "+"this is final discount after round");
		softassert.assertEquals(finalUnitPrice, mm);
		softassert.assertAll();
		
		

	}

}
