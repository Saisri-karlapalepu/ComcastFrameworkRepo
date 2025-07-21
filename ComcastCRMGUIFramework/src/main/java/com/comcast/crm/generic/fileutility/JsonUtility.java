package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
       public String getDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException {
    	//step 1:parse Json physical file into java object using json parse class
   		JSONParser parser=new JSONParser();
   		Object obj=parser.parse(new FileReader("./configAppData/appCommonData.json"));
   		
   		//step 2:convert java object into JSON object using downcasting
           JSONObject map=(JSONObject)obj;

    	 String data=(String) map.get(key);  
    	   return data;
       }
}
