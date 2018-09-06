package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	LoginPage loginpage;
	HomePage homePage;
	TestUtil util;
	ContactsPage contactsPage;
	String sheetName= "Contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		util= new TestUtil();
		contactsPage = new ContactsPage();
		loginpage= new LoginPage();
		homePage = loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		util.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();		
	}
	
	@Test(priority=1)
	public void verifyContactsPageLableTest() {
		Assert.assertTrue(contactsPage.verifyContactsLable(),"contacts lable is missing on the page");
		
	}
	
	@Test(priority=2)
	public void selectSingleContactsByNameTest() {
		contactsPage.selectContactsByName("test2 test2");
	}
	@Test(priority=3)
	public void selectMultipleContactsByNameTest() {
		contactsPage.selectContactsByName("test2 test2");
		contactsPage.selectContactsByName("ui uiii");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
	    Object data[][] = TestUtil.getTestData(sheetName);
	    return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContactsTest(String title, String firstName, String lastName, String company) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
