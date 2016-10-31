package tn.esprit.R2S.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by evilkid on 10/30/2016.
 */
@MessageDriven(name = "emailServiceEJB", mappedName = "emailServiceEJB", activationConfig = {
        @ActivationConfigProperty(propertyName = "messagingType", propertyValue = "javax.jms.MessageListener"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/R2S"),
        @ActivationConfigProperty(propertyName = "ConnectionFactoryName", propertyValue = "ConnectionFactory"),
        @ActivationConfigProperty(propertyName = "MaxPoolSize", propertyValue = "1"),
        @ActivationConfigProperty(propertyName = "MaxMessages", propertyValue = "1"),
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
})
public class EmailServiceBean implements MessageListener {

    private static final String HOST = "smtp.mail.com";
    private static final String FROM = "espritpi@mail.com";
    private static final String LOGIN = "espritpi@mail.com";
    private static final String PASS = "Visualize1-";

    public EmailServiceBean() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Message revieced!");
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            if (objectMessage.getObject() instanceof HashMap) {
                sendMail((HashMap) objectMessage.getObject());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void sendMail(HashMap vars) {
        Transport transport = null;
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(LOGIN, PASS);
                    }
                });
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(FROM));

            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(vars.get("recipient").toString()));

            message.setSubject(vars.get("subject").toString());

            message.setText(vars.get("content").toString(), "utf-8", "html");

            System.out.println("Login attempt...");

            transport = session.getTransport("smtp");
            transport.connect(HOST, LOGIN, PASS);

            System.out.println("Sending mail...");

            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("Mail sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
