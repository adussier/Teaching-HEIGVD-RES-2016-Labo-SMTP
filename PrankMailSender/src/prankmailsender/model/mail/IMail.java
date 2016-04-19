/*
 */
package prankmailsender.model.mail;

import java.util.List;

/**
 *
 * @author Amel Dussier
 */
public interface IMail {
    
    String getFrom();
    
    List<String> getTo();
    
    String getSubject();
    
    String getBody();
}
