package com.Capgemini.ownProjs.Common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public boolean sendEmail(String toMailId) {
           boolean isSendSuccess=false;
		try {
			String emailPort = "587";//gmail's smtp port

			emailProperties = System.getProperties();
			emailProperties.put("mail.smtp.port", emailPort);
			emailProperties.put("mail.smtp.auth", "true");
			emailProperties.put("mail.smtp.starttls.enable", "true");
			
			
			String[] toEmails = { toMailId };
			String emailSubject = "Password Recovery";
			String emailBody = "Your New password is \"TestPassword\"";

			mailSession = Session.getDefaultInstance(emailProperties, null);
			emailMessage = new MimeMessage(mailSession);

			for (int i = 0; i < toEmails.length; i++) {
				emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
			}

			emailMessage.setSubject(emailSubject);
			emailMessage.setContent(emailBody, "text/html");//for a html email
			//emailMessage.setText(emailBody);// for a text email
			
			
			String emailHost = "smtp.gmail.com";
			String fromUser = "madhuryanmadhu";//just the id alone without @gmail.com
			String fromUserEmailPassword = "madhuryanagp1905";

			Transport transport = mailSession.getTransport("smtp");

			transport.connect(emailHost, fromUser, fromUserEmailPassword);
			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
			isSendSuccess=true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
 
		return isSendSuccess;
	}

}