package prankmailsender.config;

import prankmailsender.model.mail.MailContent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import prankmailsender.model.mail.Person;

/**
 * Concrete configuration provider
 * @author Amel Dussier
 */
public class ConfigurationProvider implements IConfigurationProvider {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(ConfigurationProvider.class.getName());
    
    /**
     * Encoding used in configuration files
     */
    private static final String ENCODING = "UTF-8";
    
    /**
     * The configuration properties
     */
    private final Properties configurationProperties = new Properties();
    
    /**
     * The list of configured victims
     */
    private final List<Person> victims = new ArrayList();
    
    /**
     * The list of configured mail contents
     */
    private final List<MailContent> mailContents = new ArrayList();
    
    /**
     * Constructor
     * @throws IOException If a problem occurs reading the configuration files
     */
    public ConfigurationProvider() throws IOException {
        
        // load configuration properties        
        InputStream stream = new FileInputStream("./config/config.properties");
        configurationProperties.load(stream);
        
        // load victims
        loadVictims("./config/victims.utf8");
        
        // load mail contents
        loadMessageContents("./config/messages.utf8");
    }
    
    /**
     * Loads all configured victims
     * @param filePath The path to the configuration file
     * @throws IOException If a problem occurs reading the configuration file
     */
    private void loadVictims(String filePath) throws IOException {
        
        // create buffered reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), ENCODING));
        
        // read line by line
        String line = reader.readLine();
        while (line != null) {
            
            // get informations from line
            String[] victimInformations = line.split(";", 3);
            
            // add new victim to the list
            victims.add(new Person(victimInformations[0], victimInformations[1], victimInformations[2]));
            
            // read next line
            line = reader.readLine();
        }
    }
    
    /**
     * Loads all configured mail contents
     * @param filePath The path to the configuration file
     * @throws IOException If a problem occurs reading the configuration file
     */
    private void loadMessageContents(String filePath) throws IOException {
        
        // create buffered reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), ENCODING));
        
        boolean inBody = false;
        String subject = null;
        StringBuilder body = new StringBuilder();
        
        // read line by line
        String line = reader.readLine();
        while (line != null) {
            
            if (line.equals("---")) { // separator between subject and body
                inBody = true;
            }
            else if (line.equals("###")) { // separator between mail contents
                inBody = false;
                
                // add mail content to list
                mailContents.add(new MailContent(subject, body.toString()));
                subject = null;
                body = new StringBuilder();
            }
            else if (inBody) {
                // add line to body
                body.append(line);
                body.append("\r\n");
            }
            else { // subject
                inBody = false;
                subject = line;
            }
            
            // read next line
            line = reader.readLine();
        }
    }
    
    @Override
    public String getSmtpServerAddress() {
        return configurationProperties.getProperty("smtpServerAddress");
    }

    @Override
    public int getSmtpServerPort() {
        return Integer.parseInt(configurationProperties.getProperty("smtpServerPort"));
    }

    @Override
    public int getNumberOfGroups() {
        return Integer.parseInt(configurationProperties.getProperty("numberOfGroups"));
    }

    @Override
    public int getMinimumGroupMembers() {
        return Integer.parseInt(configurationProperties.getProperty("minimumGroupMembers"));
    }

    @Override
    public List<MailContent> getMailContents() {
        return mailContents;
    }

    @Override
    public List<Person> getVictims() {
        return victims;
    }
}
