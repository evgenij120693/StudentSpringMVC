package ru.svetozarov.common.utils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class sendMail {

   private static Logger logger = Logger.getLogger(sendMail.class);
    private  String username = "evgenij.svetozarov@yandex.ru";
    private   String password = "";

    public   void sendMail(String email, String subject, String text) {

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.yandex.ru" );
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {
            logger.debug("email "+email);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(text);
            logger.debug("email "+email);


            Transport.send(message);
            logger.trace("Send message to "+email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
