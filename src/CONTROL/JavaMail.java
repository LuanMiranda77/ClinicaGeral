package CONTROL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.apache.commons.mail.EmailException;
import MODEL.Config;
import VIEW.TelaErroLog;
 
public class JavaMail{
	private Config config;
	
	public JavaMail() {
		config = CTConfig.getConfig();
	}
	
	public static boolean validaEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
	}
      public void enviarEmail(String destinatario, String assunto,String mensagem) throws EmailException{
    
          String username = config.getEmail();
          String password = config.getSenha_Email();
          String stmp = config.getServidor_Email();
          String porta = config.getPorta_Email();
           
           Properties props = new Properties();
         //  props.put("mail.transport.protocol", "smtp");
           props.put("mail.smtp.auth", "true");
           props.put ("mail.smtp.ssl.enable", "true");
           props.put("mail.smtp.host", stmp);
           props.put ("mail.smtp.port", porta);
          // props.put("mail.smtp.socketFactory.port", porta);
           
         //  props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
           
         //  props.put("mail.smtp.port", porta);
           
          
       
           Session session = Session.getDefaultInstance(props,
                       new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() 
                            {
                                  return new PasswordAuthentication(username, password);
                            }
                       });
       
           /** Ativa Debug para sessão */
           session.setDebug(true);
           

       
         
       
                 Message message = new MimeMessage(session);
                 try {
					message.setFrom(new InternetAddress(username));
				} catch (MessagingException e1) {
					new TelaErroLog(e1.getMessage(), "Erro interno endereço", "class JavaMail");
				} //Remetente
       
                 Address[] toUser = null;
				try {
					toUser = InternetAddress //Destinatário(s)
					            .parse(destinatario);
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
       
                 try {
					message.setRecipients(Message.RecipientType.TO, toUser);
				} catch (MessagingException e1) {
					new TelaErroLog(e1.getMessage(), "Erro recipents", "class JavaMail");
				}
                 try {
					message.setSubject(assunto);
				} catch (MessagingException e1) {
					new TelaErroLog(e1.getMessage(), "Erro suject", "class JavaMail");
				}//Assunto
                 try {
					message.setText(mensagem);
				} catch (MessagingException e1) {
					new TelaErroLog(e1.getMessage(), "Erro text", "class JavaMail");
				}
                 /**Método para enviar a mensagem criada*/
                 try {
                 Transport.send(message);
       
            }catch (MessagingException e) {
            	new TelaErroLog(e.getMessage(), "Erro tranpost", "class JavaMail");
           }
          }
		
}


