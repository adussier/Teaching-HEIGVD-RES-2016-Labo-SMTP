package prankmailsender.smtp;

import java.io.IOException;
import prankmailsender.model.mail.IMail;

/**
 *
 * @author Amel Dussier
 */
public interface ISmtpClient {
    
    void sendMail(IMail mail) throws IOException;
}
