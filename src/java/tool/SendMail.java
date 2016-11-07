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

    String miCorreo;
    String miPassword;
    String servidorSMTP;
    String puertoEnvio;
    String[] destinatarios;
    String asunto = null;
    String cuerpo = null;
    int servidor;

    public SendMail(String[] mailReceptor, String asunto, String cuerpo, int servidor) {
        this.destinatarios = mailReceptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.servidor = servidor;
        configurarServidor();
    }

    public SendMail(String user, String pass, String[] mailReceptor, String asunto, String cuerpo, int servidor) {
        this(mailReceptor, asunto, cuerpo, servidor);
        this.miCorreo = user;
        this.miPassword = pass;
    }

    public final void configurarServidor() {
        if (this.servidor == 0) {//gmail
            this.servidorSMTP = "smtp.gmail.com";
            this.puertoEnvio = "587";
        }
        if (this.servidor == 1) {//outlook
            this.servidorSMTP = "smtp.live.com";
            this.puertoEnvio = "587";
        }
        if (this.servidor == 2) {//yahoo
            this.servidorSMTP = "smtp.mail.yahoo.com";
            this.puertoEnvio = "587";
        }
    }

    public void send()
            throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.user", this.miCorreo);
        props.put("mail.smtp.host", this.servidorSMTP);
        props.put("mail.smtp.port", this.puertoEnvio);
        //if (this.servidor != 2) {
        props.put("mail.smtp.starttls.enable", "true");
        //}
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", this.puertoEnvio);
        props.setProperty("mail.smtp.port", "587");
        // if (this.servidor == 0)
        //{
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", "false");
        //  }
        SecurityManager security = System.getSecurityManager();

        Authenticator auth = new authenticatorSMTP();
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);
        msg.setText(this.cuerpo);
        msg.setSubject(this.asunto);
        msg.setFrom(new InternetAddress(this.miCorreo));
        Address[] destinos = new Address[this.destinatarios.length];
        for (int i = 0; i < destinos.length; i++) {
            destinos[i] = new InternetAddress(this.destinatarios[i]);
        }
        msg.addRecipients(Message.RecipientType.TO, destinos);
        try {
            Transport t = session.getTransport("smtp");
            t.connect(this.miCorreo, this.miPassword);
            t.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Correo Enviado exitosamente!");
            t.close();
        } catch (AuthenticationFailedException ex) {
            throw new MessagingException();
        }
    }

    private class authenticatorSMTP
            extends Authenticator {

        private authenticatorSMTP() {
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(SendMail.this.miCorreo, SendMail.this.miPassword);
        }
    }
}
