package tool;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author davisonsp
 */
public class SendMailAttachFile {

    final String CORREO_SICMECAL = "sicmecal@outlook.com";
    final String PASSWORD_SICMECAL = "tp2016ii";
    String servidorSMTP = "smtp.live.com";
    String puertoEnvio;
    String destinatario;
    String asunto;
    String cuerpo = null;
    public String archivoAdjunto;

    public SendMailAttachFile(String dest, String asun, String mens, String archivo) {
        this.destinatario = dest;
        this.asunto = asun;
        this.cuerpo = mens;
        this.archivoAdjunto = archivo;
    }

    public void send()
            throws MessagingException {
        Properties props = null;
        props = new Properties();
        props.put("mail.smtp.host", servidorSMTP);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", CORREO_SICMECAL);
        props.setProperty("mail.smtp.auth", "true");

        SecurityManager security = System.getSecurityManager();

        Session session = Session.getInstance(props, new GMailAuthenticator(CORREO_SICMECAL, PASSWORD_SICMECAL));

        BodyPart texto = new MimeBodyPart();
        texto.setText(this.cuerpo);

        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(new DataHandler(new FileDataSource(this.archivoAdjunto)));
        String fileName = archivoAdjunto.substring(archivoAdjunto.lastIndexOf("\\"));
        adjunto.setFileName(fileName);
        
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);
        multiParte.addBodyPart(adjunto);
        
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.CORREO_SICMECAL));
        
        Address receptor = new InternetAddress(this.destinatario);
        message.addRecipient(Message.RecipientType.TO, receptor);
        
        message.setSubject(this.asunto);
        
        message.setContent(multiParte);

        Transport t = session.getTransport("smtp");
        t.connect(CORREO_SICMECAL, PASSWORD_SICMECAL);
        t.sendMessage(message, message.getAllRecipients());

        t.close();
    }

    private class GMailAuthenticator extends Authenticator {
        String user;
        String pw;

        public GMailAuthenticator(String username, String password) {
            super();
            this.user = username;
            this.pw = password;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pw);
        }
    }
}
