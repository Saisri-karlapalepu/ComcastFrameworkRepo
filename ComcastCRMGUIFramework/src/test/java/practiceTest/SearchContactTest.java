package practiceTest;

import org.testng.annotations.Test;

import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 *test class for contact module
 * @author saisri
 * 
 */
public class SearchContactTest extends BaseClass{
	@Test
	public void searchContactTest() {
		/*
		 * Scenario : login()==>navigateContact==>createContact()==verify
		 * Step 1:login to application
		 */
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}

}
