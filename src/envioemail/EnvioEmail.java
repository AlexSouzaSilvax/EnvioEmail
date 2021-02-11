package envioemail;

//import com.sun.xml.internal.ws.wsdl.writer.document.Message;
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import sun.rmi.transport.Transport;

public class EnvioEmail {

    private String mailSMTPserver;
    private String mailSMTPserverPort;
    private String mailSenha;

    public void enviaEmail(String from, String to, String subject, String message, String password, String qmEnvia) {

        Properties props = new Properties();

        //mailSMTPserver = "mx1.hostinger.com.br";
        //mailSMTPserver = "smtp.googlemail.com";
        mailSMTPserver = "smtp.gmail.com";
        //mailSMTPserverPort = "2525";
        //mailSMTPserverPort = "465";
        //mailSMTPserverPort = "587";
        //mailSMTPserverPort = "25";        
        mailSMTPserverPort = "";
        mailSenha = password;

        props.put("mail.transport.protocol", "smtp"); // define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPserver); // server SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); // ativa autenticacao
        props.put("mail.smtp.user", from); // usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
        //props.put("mail.smtp.ehlo", false);
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailSMTPserverPort); // porta
        props.put("mail.smtp.socketFactory.port", mailSMTPserverPort); // mesma porta para o socket
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        // Cria um autenticador que sera usado a seguir
        SimpleAuth auth = null;
        auth = new SimpleAuth(from, mailSenha);

        // Session - objeto que ira realizar a conex�o com o servidor
        /*
                 * Como h� necessidade de autentica��o � criada uma autenticacao que �
                 * responsavel por solicitar e retornar o usu�rio e senha para
                 * autentica��o
         */
        Session session = Session.getDefaultInstance(props, auth);
        //session.setDebug(true); // Habilita o LOG das a��es executadas durante o envio do email

        // Objeto que cont�m a mensagem
        Message msg = new MimeMessage(session);

        try {
            // Setando o destinat�rio
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Setando a origem do email
            msg.setFrom(new InternetAddress(qmEnvia));
            // Setando o assunto
            msg.setSubject(subject);
            // Setando o conte�do/corpo do email
            msg.setContent(message, "text/html;charset=UTF-8");
            // Setando anexo
            //FileDataSource fds = new FileDataSource("C:\\Users\\alex.silva\\Documents\\alex\\java\\new\\envioEmail.zip");
            FileDataSource fds = new FileDataSource("C:\\temp\\teste.txt");
            msg.setDataHandler(new DataHandler(fds));
            msg.setFileName(fds.getName());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = session.getTransport("smtp"); // define smtp para transporte
            /*
                         * 1 - define o servidor smtp 2 - seu nome de usuario do gmail 3 -
                         * sua senha do gmail
             */
            tr.connect(mailSMTPserver, from, mailSenha);
            msg.saveChanges(); // don't forget this
            // envio da mensagem
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            System.out.println("\nE-mail enviado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

// clase que retorna uma autenticacao para ser enviada e verificada pelo
// servidor smtp
class SimpleAuth extends Authenticator {

    public String username = null;
    public String password = null;

    public SimpleAuth(String user, String pwd) {
        username = user;
        password = pwd;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
