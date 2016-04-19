package prankmailsender.model.mail;

/**
 * A simple person
 * @author Amel Dussier
 */
public class Person {
    
    /**
     * The first name
     */
    private final String firstName;
    
    /**
     * The last name
     */
    private final String lastName;
    
    /**
     * The mail address
     */
    private final String mailAddress;
    
    /**
     * Constructor
     * @param firstName The persons first name
     * @param lastName The persons last name
     * @param mailAddress The persons mail address
     */
    public Person(String firstName, String lastName, String mailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAddress = mailAddress;
    }
    
    /**
     * Gets the first name of the person
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the person
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Gets the mail address of the person
     * @return The mail address
     */
    public String getMailAddress() {
        return mailAddress;
    }
}
