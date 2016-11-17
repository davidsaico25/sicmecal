package tool;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author davisonsp
 */
public class SendMail {

    final String CORREO_SICMECAL = "sicmecal@outlook.com";
    final String PASSWORD_SICMECAL = "tp2016ii";
    String servidorSMTP = "smtp.live.com";
    String puertoEnvio;
    String destinatarios;
    String asunto = null;
    String cuerpo = null;

    public SendMail(String mailReceptor, String asunto, String cuerpo) {
        this.destinatarios = mailReceptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public void send() throws MessagingException {
        this.puertoEnvio = "587";
        Properties props = new Properties();
        props.put("mail.smtp.user", this.CORREO_SICMECAL);
        props.put("mail.smtp.host", this.servidorSMTP);
        props.put("mail.smtp.port", this.puertoEnvio);
        
        props.put("mail.smtp.starttls.enable", "true");
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", this.puertoEnvio);
        props.setProperty("mail.smtp.port", "587");
        
        SecurityManager security = System.getSecurityManager();

        Authenticator auth = new authenticatorSMTP();
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);
        msg.setText(this.cuerpo);
        msg.setSubject(this.asunto);
        msg.setFrom(new InternetAddress(this.CORREO_SICMECAL));
        
        Address destino = new InternetAddress(this.destinatarios);
        msg.addRecipient(Message.RecipientType.TO, destino);
        
        try {
            Transport t = session.getTransport("smtp");
            t.connect(this.CORREO_SICMECAL, this.PASSWORD_SICMECAL);
            t.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Correo Enviado exitosamente!");
            t.close();
        } catch (AuthenticationFailedException ex) {
            throw new MessagingException();
        }
    }

    private class authenticatorSMTP extends Authenticator {

        private authenticatorSMTP() {
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(SendMail.this.CORREO_SICMECAL, SendMail.this.PASSWORD_SICMECAL);
        }
    }
}
