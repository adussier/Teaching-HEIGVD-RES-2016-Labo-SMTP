package prankmailsender.model.mail;

import java.util.List;

/**
 * Interface for a simple mail
 * @author Amel Dussier & Sarra Berich
 */
public interface IMail {
    
    /**
     * Get the senders mail address
     * @return The senders address
     */
    String getSender();
    
    /**
     * Get the list of recipient addresses for the mail
     * @return The list of recipient addresses
     */
    List<String> getRecipients();
    
    /**
     * Get the subject of the mail
     * @return The subject
     */
    String getSubject();
    
    /**
     * Get the body of the mail
     * @return The body
     */
    String getBody();
}
