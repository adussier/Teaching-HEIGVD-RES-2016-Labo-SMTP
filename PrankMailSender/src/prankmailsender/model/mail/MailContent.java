package prankmailsender.model.mail;

/**
 * The content of a mail
 * @author Amel Dussier
 */
public class MailContent {
    
    /**
     * The subject of the mail
     */
    private final String subject;
    
    /**
     * The body of the mail
     */
    private final String body;
    
    /**
     * Constructor
     * @param subject The subject of the mail
     * @param body The body of the mail
     */
    public MailContent(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
    
    /**
     * Gets the subject of the mail
     * @return The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets the body of the mail
     * @return The body
     */
    public String getBody() {
        return body;
    }
}
