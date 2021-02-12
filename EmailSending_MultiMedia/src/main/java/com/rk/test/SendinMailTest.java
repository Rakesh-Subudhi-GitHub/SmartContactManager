package com.rk.test;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendinMailTest {

	//this method for attachment 
	public static void sendMailAttach(String message,String subject,String to,String from)
	{
	
		//variable for gmail
		String host="smtp.gmail.com";  //host of mail use this
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES:: "+properties);
		
		//setting important information to properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");//google port for mail
		properties.put("mail.smtp.ssl.enable","true");//security ssl protocol is enable
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
       Session session=Session.getInstance(properties,new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
		
			return new PasswordAuthentication("rakesh.testuser2021@gmail.com", "devibhai@123");//set email or password 
		
		}
		
		});
	
		session.setDebug(true);
		
		//step 2: compose the message[text,multimedia]
		MimeMessage mime = new MimeMessage(session);
		
	try {
		//from email id
		mime.setFrom(from);  //set "from" mail address
		
		//adding recipient to
		mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//pass "to" mail address
		
		//adding subject
		mime.setSubject(subject);//set subject of mail
		
//==============================  changing  ====================================================================		
		
		//attachment file
		
		String path="C:\\Users\\star\\Desktop\\project\\user.png"; //path of the file to send
		
		MimeMultipart mimeMultipart = new MimeMultipart();
		//mimeMultipart his carry 2 part (text,file)
		
		MimeBodyPart textMime = new MimeBodyPart();
		MimeBodyPart fileMime = new MimeBodyPart();
		
		//add textMime or fileMime
		try {
			
			//fast text
			textMime.setText(message);
			
			//file
			File file=new File(path);
			fileMime.attachFile(file);
		
			
			//add both in mimeMultipart
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
		}//try
		catch (Exception e) {
		e.printStackTrace();
		}
		//normal pass the multipart
		mime.setContent(mimeMultipart);
		
		//send the mail
		
	//Step 3:send the message using Transport class
	Transport.send(mime);
		
	System.out.println("send the mail succesfully :::");
	
	}//try
	
	catch (Exception e) {
		e.printStackTrace();
	}	
	
	}//static method
	
	
	
public static void main(String[] args) {

	String message="hello this is for testing purposes....";
	String subject="mail testing";
	
	String to="Rakesh.subudhi40@gmail.com";
	String from="rakesh.testuser2021@gmail.com";
	
	//send this all properties in static sendMail method
	sendMailAttach(message,subject,to,from);
	
  }//main
	
}//class
