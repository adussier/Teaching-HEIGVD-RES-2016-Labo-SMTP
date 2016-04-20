package prankmailsender.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of persons
 * @author Amel Dussier & Sarra Berich
 */
public class Group {

    /**
     * List with all members
     */
    private final List<Person> members = new ArrayList();
    
    /**
     * Adds a member to the group
     * @param person The person to add
     */
    public void addMember(Person person) {
        members.add(person);
    }
    
    /**
     * Gets all the members of the group
     * @return The members of the group
     */
    public List<Person> getMembers() {
        return new ArrayList(members);
    }
}
