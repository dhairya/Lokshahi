package beans;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;



public final class Mailbean extends Object implements Serializable {

  

  private String emailMsgTxt      = null;
  private String emailSubjectTxt  = null;
  

  // Add List of Email address to who email needs to be sent to
  private String emailList[] = null;
  
  public void setTo(String to[]) {
	  this.emailList = to;
	}
  public void setSubject(String subject) {
		this.emailSubjectTxt = subject;
	}
  public void setMessage(String message) {
		this.emailMsgTxt = message;
	}


  public void SendMail() throws MessagingException
  { try{
	  
	String SMTP_HOST_NAME = "smtp.gmail.com";
	String SMTP_AUTH_USER = "enterusername";
	String SMTP_AUTH_PWD  = "enterpassword";
	String emailFromAddress = "username@gmail.com";
      int SMTP_PORT  = 465;
    boolean debug = false;

     //Set the host smtp address
     Properties props = new Properties();
     props.put("mail.transport.protocol", "smtp");
     props.put("mail.smtp.port", SMTP_PORT);
     props.put("mail.smtp.host", SMTP_HOST_NAME);
     
     
    props.put("mail.smtp.starttls.enable","true");
     props.put("mail.smtp.auth", "true");
    
    
    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getInstance(props, auth);
    //Session session = Session.getDefaultInstance(props, auth);

    session.setDebug(debug);
   
    // create a message
    Message msg = new MimeMessage(session);

    // set the from and to address
    InternetAddress addressFrom = new InternetAddress(emailFromAddress);
    msg.setFrom(addressFrom);
    
    InternetAddress addressTo[] = new InternetAddress[emailList.length];
    for (int i = 0; i < emailList.length; i++) 
    {    
    	addressTo[i] = new InternetAddress(emailList[i]);
    }
                                                  	

    msg.setRecipients(Message.RecipientType.TO, addressTo);

    
    // Setting the Subject and Content Type
    msg.setText(emailMsgTxt);
    msg.setSubject(emailSubjectTxt);
    msg.setContent(emailMsgTxt, "text/plain");
    Transport.send(msg);
    
  }
  catch(Exception e)
  {
	  System.out.println(e);
  }
 }
  





/**
* SimpleAuthenticator is used to do simple authentication
* when the SMTP server requires it.
*/
private class SMTPAuthenticator extends javax.mail.Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {   String SMTP_AUTH_USER = "enterusername";
	    String SMTP_AUTH_PWD  = "enterpassword";
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;
        
        return new PasswordAuthentication(username, password);
    }
}

}
