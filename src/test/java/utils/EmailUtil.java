package utils;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;
public class EmailUtil {
	private static final String FROM_EMAIL = "nikhil.jain28.1199@gmail.com";
	private static final String PASSWORD    = "tzpo ehzi fpkv dtyz";   // App password
	private static final String TO_EMAIL    = "nikhil.jain28.1199@gmail.com";

	public static void sendReportEmail(String reportPath) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_EMAIL));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(TO_EMAIL));
			message.setSubject("API Automation Report — " + 
					new java.util.Date());
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hi,\n\nPlease find the attached API Automation Report.\n\nRegards,\nNikhil");
			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.attachFile(new File(reportPath));
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Report email sent successfully!");

		} catch (Exception e) {
			System.out.println("Email sending failed: " + e.getMessage());
		}
	}
}
