package prankmailsender.model.mail;

import java.util.List;

/**
 * Concrete mail
 * @author Amel Dussier
 */
public class Mail implements IMail {

    /**
     * The senders address
     */
    private final String sender;
    
    /**
     * The list of recipients
     */
    private final List<String> recipients;
    
    /**
     * The subject
     */
    private final String subject;
    
    /**
     * The body
     */
    private final String body;
    
    /**
     * Constructor
     * @param sender The senders mail address
     * @param recipients The recipients mail addresses
     * @param content The mail content
     */
    public Mail(String sender, List<String> recipients, MailContent content) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = content.getSubject();
        this.body = content.getBody();
    }
    
    @Override
    public String getSender() {
        return sender;
    }
    
    @Override
    public List<String> getRecipients() {
        return recipients;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getBody() {
        return body;
    }
}
