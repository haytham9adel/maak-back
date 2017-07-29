package resources;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
 


import com.trackingbus.*;
import com.trackingbus.model.AdminSetupModel;
import com.trackingbus.service.SchoolService;


public class ApplicationMailer 
{
  
	@Autowired
	private SchoolService schoolservice;
	
	public void sendEmail(final String to,String msg,String Subject,final String password)
	{
	
	//	final String username = asm.getEmail_id();
	//	final String password = asm.getPassword();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(to, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@m3aak.net"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(Subject);
			//message.setText(msg);
			message.setContent(msg,"text/html;charset=utf-8");
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public void sendEmailPromotional(final String to,String msg,String Subject,final String password,final String sender)
	{
	
	//	final String username = asm.getEmail_id();
	//	final String password = asm.getPassword();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		try {
			
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		  });

		

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@m3aak.net"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(Subject);
			//message.setText(msg);
			message.setContent(msg,"text/html;charset=utf-8");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			
			System.out.println(e); 
		}
	}
	
}