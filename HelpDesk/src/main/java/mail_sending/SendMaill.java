package mail_sending;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class SendMaill {

	public static void email(String numbertest, String fromMail, String tomail, Exception e) throws UnsupportedEncodingException {

	String attachment = "C:\\Users\\seluser\\screenshot.png" ; 
	final String ENCODING = "UTF-8"; 

	Properties props = new Properties();
	props.put("mail.smtp.host", "10.103.1.208");
	props.put("mail.smtp.socketFactory.port", "25");
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "false");
	props.put("mail.smtp.port", "25");

	Session session = Session.getDefaultInstance(props,
	new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication("Email_Id","password");
		}
	});

	try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromMail));
		message.setRecipients(Message.RecipientType.TO,
		InternetAddress.parse(tomail));

		BodyPart messageBodyPart = new MimeBodyPart(); 
		messageBodyPart.setContent(ExceptionUtils.getStackTrace(e), "text/plain; charset=" + ENCODING + ""); 
		Multipart multipart = new MimeMultipart(); 
		multipart.addBodyPart(messageBodyPart); 
		MimeBodyPart attachmentBodyPart = new MimeBodyPart(); 
		DataSource source = new FileDataSource(attachment);
		attachmentBodyPart.setDataHandler(new DataHandler(source)); 
		attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName())); 
		multipart.addBodyPart(attachmentBodyPart); 

		message.setSubject(numbertest);
		message.setContent(multipart); 
		Transport.send(message);

System.out.println("Send screenshot");

} 
	catch (MessagingException c) {
	throw new RuntimeException(c);
}

}
}

