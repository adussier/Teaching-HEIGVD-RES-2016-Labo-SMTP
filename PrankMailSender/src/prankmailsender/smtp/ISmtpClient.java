package prankmailsender.smtp;

import java.io.IOException;
import prankmailsender.model.mail.IMail;

/**
 * Interface for an SMTP client
 * @author Amel Dussier & Sarra Berich
 */
public interface ISmtpClient {
    
    /**
     * Sends a mail using the SMTP protocol
     * @param mail The mail to send
     * @throws IOException If something bad happens between the client and the server
     */
    void sendMail(IMail mail) throws IOException;
}
