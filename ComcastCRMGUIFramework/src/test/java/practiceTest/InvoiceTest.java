package practiceTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseclass.BaseClass;
@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class InvoiceTest extends BaseClass{
	@Test(retryAnalyzer=com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void createInvoice() {
		System.out.println("Execute create Invoice Test");
		String actTitle=driver.getTitle();
		AssertJUnit.assertEquals(actTitle, "Login");
		
	}
	
	@Test
	public void createInvoiceWithContctTest() {
		System.out.println("Execute createInvoiceWithContactTest");
		
	}

}
