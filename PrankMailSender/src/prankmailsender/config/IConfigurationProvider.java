package prankmailsender.config;

/**
 *
 * @author Amel Dussier
 */
public interface IConfigurationProvider {
    
    String getSmtpServerAddress();
    
    String getSmtpServerPort();
    
    int getNumberOfGroups();
}
