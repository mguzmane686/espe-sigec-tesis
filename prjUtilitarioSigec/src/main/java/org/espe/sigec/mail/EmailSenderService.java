package org.espe.sigec.mail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;

public class EmailSenderService
{
  private static final EmailSenderService EMAIL_SENDER_SERVICE = new EmailSenderService();
  
  public static EmailSenderService getInstance()
  {
    return EMAIL_SENDER_SERVICE;
  }
  
  public void sendMessage(String subject, String text, String destinatario)
  {
    String username = "desarrollosigec@gmail.com";
    String password = "Password_01";
    String[] destinatariosList = StringUtils.split(destinatario, ",");
    
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props, new Authenticator()
    {
      protected PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication("maniac787@gmail.com", "wxr787gmx");
      }
    });
    try
    {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("maniac787@gmail.com"));
      
      Collection<InternetAddress> addressTo = new ArrayList();
      for (int i = 0; i < destinatariosList.length; i++) {
        addressTo.add(new InternetAddress(destinatariosList[i]));
      }
      message.setRecipients(Message.RecipientType.TO, (Address[])addressTo.toArray(new InternetAddress[0]));
      message.setSubject(subject);
      message.setText(text);
      
      Transport.send(message);
    }
    catch (MessagingException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public static void main(String[] args)
  {
    getInstance().sendMessage("subject", "hola mundo", "maniac787@hotmail.com, maniac7872@yahoo.com");
  }
}
