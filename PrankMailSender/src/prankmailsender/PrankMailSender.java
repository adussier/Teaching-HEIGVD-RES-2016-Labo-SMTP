package prankmailsender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import prankmailsender.config.ConfigurationProvider;
import prankmailsender.config.IConfigurationProvider;
import prankmailsender.model.mail.IMail;
import prankmailsender.model.mail.Mail;
import prankmailsender.model.mail.Person;
import prankmailsender.model.prank.Prank;
import prankmailsender.model.prank.PrankGenerator;
import prankmailsender.smtp.ISmtpClient;
import prankmailsender.smtp.SmtpClient;

/**
 * The main program
 * @author Amel Dussier
 */
public class PrankMailSender {

    public static void main(String[] args) {
        
        try {
            // create configuration provider
            IConfigurationProvider config = new ConfigurationProvider();
            
            // create SMTP client
            ISmtpClient client = new SmtpClient(config.getSmtpServerAddress(), config.getSmtpServerPort());
            
            // create pranks
            PrankGenerator generator = new PrankGenerator(config);
            
            // send prank mails
            for (Prank p : generator.getPranks()) {
                
                // generate mail
                String senderAddress = p.getSender().getMailAddress();
                List<String> recipientAddresses = new ArrayList();
                for (Person victim : p.getVictims())
                    recipientAddresses.add(victim.getMailAddress());
                IMail mail = new Mail(senderAddress, recipientAddresses, p.getMailContent());
                
                // send
                client.sendMail(mail);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PrankMailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
