package com.comcast.crm.generic.webdriverutility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random random=new Random();
		int num=random.nextInt(10000);
		return num;
		
	}
	public String getSystemdateyyyyddMM() {
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateObj);
		return date;
	}
	public String getRequiredDateyyyyMMdd(int days) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String endDate=sim.format(cal.getTime());
		return endDate;
	}

}
