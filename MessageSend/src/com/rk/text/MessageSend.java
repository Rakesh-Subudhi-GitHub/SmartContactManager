package com.rk.text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class MessageSend {

	public static void sendSms(String message,String number)
	{

try {
				
		System.out.println("message is :: "+message);
		System.out.println("phone number :: "+number);
		
		//properties
		String apikey="vRYa1OwuA7XKxJ5F06GdmpsPI4HC8gkeyrVEhcjbziUtN9MqflwiO9loKqEgPt8cHaMdvUTjyNxAQr3h";
		String sendId="FSTSMS";
		
		//important step 
		//sending message is simple need encode
		message=URLEncoder.encode(message,"UTF-8");
		
		String language="english";
		String route="p";
		
		
		//send url 
		String myUrl="https://www.fast2sms.com/dev/bulkV2?authorization="+apikey+"&sender_id"+sendId+"&message="+message+"&language="+language+"&route="+route+"&number="+number;
		
	//sending get request
	URL url=new URL(myUrl);	
		
	HttpURLConnection con=(HttpURLConnection) url.openConnection();
	
	con.setRequestMethod("GET");
	
	con.setRequestProperty("User-Agent","Mozilla/5.0");
	con.setRequestProperty("cache-control", "no-cache");
	
	int code=con.getResponseCode();
	System.out.println("Responce code is ::" +code);
	
	
}//try

catch (Exception e) {
	// TODO: handle exception
}
	}//static method
	
public static void main(String[] args) {
	
	System.out.println("main method calling::::::");
	
	MessageSend.sendSms("this is testing message or time is...."+new Date().toString(),"7381272415");
	
 }//main

}//class
