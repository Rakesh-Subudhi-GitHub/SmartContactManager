package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	 public boolean sendEmail(String subject,String message,String to)
	    {
	        boolean flag=false;

	        //from mail is as it is so put here
	        String from="rakesh.testuser2021@gmail.com";

	        //rest code
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
	            protected
	            PasswordAuthentication getPasswordAuthentication() {

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

	            //adding text to message
	                // mime.setText(message); //set the messsage

	            //Transferring html file also
	            mime.setContent(message,"text/html");
	            
	            //send the mail

	            //Step 3:send the message using Transport class
	            Transport.send(mime);

	            System.out.println("send the mail succesfully :::");
	            flag=true;

	        }//try

	        catch (Exception e) {
	            e.printStackTrace();
	        }

	        return flag;

	    }//method
	
}//class
