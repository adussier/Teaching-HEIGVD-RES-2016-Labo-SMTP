package prankmailsender.model.prank;

import java.util.List;
import prankmailsender.model.mail.MailContent;
import prankmailsender.model.mail.Person;

/**
 * A mail prank
 * @author Amel Dussier & Sarra Berich
 */
public class Prank {
    
    /**
     * The pranked sender
     */
    private final Person sender;
    
    /**
     * The victims of the prank
     */
    private final List<Person> victims;
    
    /**
     * The content of the prank mail
     */
    private final MailContent mailContent;
    
    /**
     * Constructor
     * @param sender The pranked sender
     * @param victims The victims of the prank
     * @param mailContent The content of the prank mail
     */
    public Prank(Person sender, List<Person> victims, MailContent mailContent) {
        this.sender = sender;
        this.victims = victims;
        this.mailContent = mailContent;
    }
    
    /**
     * Gets the pranked sender
     * @return The sender
     */
    public Person getSender() {
        return sender;
    }
    
    /**
     * Gets the victims of the prank
     * @return The victims
     */
    public List<Person> getVictims() {
        return victims;
    }
    
    /**
     * Gets the content of the prank mail
     * @return The content of the mail
     */
    public MailContent getMailContent() {
        return mailContent;
    }
}
