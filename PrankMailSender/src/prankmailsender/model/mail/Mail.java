package prankmailsender.model.mail;

import java.util.List;

/**
 *
 * @author Amel Dussier
 */
public class Mail implements IMail {

    private final String from;
    private final List<String> to;
    private final String subject;
    private final String body;
    
    public Mail(String from, List<String> to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
    
    @Override
    public String getFrom() {
        return from;
    }
    
    @Override
    public List<String> getTo() {
        return to;
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
