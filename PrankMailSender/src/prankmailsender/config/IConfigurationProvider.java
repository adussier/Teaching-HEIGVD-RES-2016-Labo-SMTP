package prankmailsender.config;

import prankmailsender.model.mail.MailContent;
import java.util.List;
import prankmailsender.model.mail.Person;

/**
 * Interface for the configuration provider
 * @author Amel Dussier
 */
public interface IConfigurationProvider {
    
    /**
     * Get the SMTP server address
     * @return The server address
     */
    String getSmtpServerAddress();
    
    /**
     * Get the SMTP server port
     * @return The server port
     */
    int getSmtpServerPort();
    
    /**
     * Get the number of groups to create
     * @return The number of groups
     */
    int getNumberOfGroups();
    
    /**
     * Get the minimum number of members for each group
     * @return The number of members per group
     */
    int getMinimumGroupMembers();
    
    /**
     * Gets a list of mail contents
     * @return The mail contents
     */
    List<MailContent> getMailContents();
    
    /**
     * Gets a list with all mail addresses
     * @return The mail addresses
     */
    List<Person> getVictims();
}
