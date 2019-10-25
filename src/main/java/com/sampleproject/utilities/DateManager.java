package com.sampleproject.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.Format;



public class DateManager {

	
	
	// Get Full Month Name from a given Date
	
	
	public String getMonthYearNameFromString(String value) throws Exception {
		
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date d1 = df.parse(value);

				Format formatter = new SimpleDateFormat("MMMM"); 
			    String s = formatter.format(d1);
			    System.out.println(s);
			    return s;
	}
	
	

	
	
	
}
//https://www.javatpoint.com/java-string-to-date



//String testDateString = "02/04/2014";
//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//Date d1 = df.parse(testDateString);
//         System.out.println("Date: " + d1);
//         System.out.println("Date in dd/MM/yyyy format is: "+df.format(d1));
//
//Format formatter = new SimpleDateFormat("MMMM"); 
//  String s = formatter.format(d1);
//  System.out.println(s);