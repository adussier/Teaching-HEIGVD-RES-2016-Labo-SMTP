package prankmailsender.model.prank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import prankmailsender.config.IConfigurationProvider;
import prankmailsender.model.mail.MailContent;
import prankmailsender.model.mail.Group;
import prankmailsender.model.mail.Person;

/**
 * The prank generator
 * @author Amel Dussier
 */
public class PrankGenerator {
    
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    
    /**
     * The list of generated pranks
     */
    private final List<Prank> pranks = new ArrayList();
    
    /**
     * Constructor
     * @param configurationProvider 
     */
    public PrankGenerator(IConfigurationProvider configurationProvider) {
        
        // get the configured mail contents
        List<MailContent> mailContents = configurationProvider.getMailContents();
        
        // get the configured victims
        List<Person> victims = configurationProvider.getVictims();
        
        // get the number of groups to create
        int numberOfGroups = configurationProvider.getNumberOfGroups();
        
        // get the minimum number of members per group
        int minimumGroupMembers = configurationProvider.getMinimumGroupMembers();
        
        // check and adapt number of groups
        if (victims.size() / numberOfGroups < minimumGroupMembers) {
            numberOfGroups = victims.size() / minimumGroupMembers;
            LOG.warning("Not enough mail addresses to satisfy number of groups parameter. Only " + numberOfGroups + " groups will be generated");
        }
        
        // generate groups
        List<Group> groups = generateGroups(victims, numberOfGroups);
        
        // generate pranks
        for (Group group : groups) {
            
            // get and shuffle the list of victims
            List<Person> members = group.getMembers();
            Collections.shuffle(members);
            
            // get pranked sender from victims list
            Person sender = members.remove(0);
            
            // get a random mail content
            MailContent mailContent = mailContents.get(new Random().nextInt(mailContents.size()));
            
            // add prank to the list
            pranks.add(new Prank(sender, members, mailContent));
        }
    }
    
    /**
     * Generates a list of victim groups
     * @param victims The list of available victims
     * @param numberOfGroups The number of groups to create
     * @return The created groups
     */
    private List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
        
        List<Group> groups = new ArrayList();
        
        // create a shuffled copy of the mail addresses
        List<Person> availableVictims = new ArrayList(victims);
        Collections.shuffle(availableVictims);
        
        // create groups
        for (int i = 0; i < numberOfGroups; i++)
            groups.add(new Group());
        
        // distribute available mail addresses on the group list
        int groupIndex = 0;
        while (availableVictims.size() > 0) {
            groups.get(groupIndex).addMember(availableVictims.remove(0));
            groupIndex = ++groupIndex % numberOfGroups;
        }
        
        return groups;
    }
    
    /**
     * Gets the list of generated pranks
     * @return The list of pranks
     */
    public List<Prank> getPranks() {
        return pranks;
    }
}
