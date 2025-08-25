import java.util.HashMap;
import java.util.Map;

/**
 * ContactService class manages a collection of Contact objects
 * Provides functionality to add, delete, and update contacts
 */
public class ContactService {
    private final Map<String, Contact> contacts;

    /**
     * Constructor initializes the contact storage
     */
    public ContactService() {
        this.contacts = new HashMap<>();
    }

    /**
     * Adds a new contact with unique ID
     * @param contact Contact object to add
     * @throws IllegalArgumentException if contact is null or contactId already exists
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }

        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact with ID " + contact.getContactId() + " already exists");
        }

        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Adds a new contact by creating Contact object with provided parameters
     * @param contactId Unique identifier for the contact
     * @param firstName First name of the contact
     * @param lastName Last name of the contact
     * @param phone Phone number of the contact
     * @param address Address of the contact
     * @throws IllegalArgumentException if contactId already exists or parameters are invalid
     */
    public void addContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact contact = new Contact(contactId, firstName, lastName, phone, address);
        addContact(contact);
    }

    /**
     * Deletes a contact by contact ID
     * @param contactId ID of the contact to delete
     * @throws IllegalArgumentException if contactId is null or contact doesn't exist
     */
    public void deleteContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact with ID " + contactId + " does not exist");
        }

        contacts.remove(contactId);
    }

    /**
     * Updates the first name of a contact
     * @param contactId ID of the contact to update
     * @param firstName New first name
     * @throws IllegalArgumentException if contactId is null, contact doesn't exist, or firstName is invalid
     */
    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getContact(contactId);
        contact.setFirstName(firstName);
    }

    /**
     * Updates the last name of a contact
     * @param contactId ID of the contact to update
     * @param lastName New last name
     * @throws IllegalArgumentException if contactId is null, contact doesn't exist, or lastName is invalid
     */
    public void updateLastName(String contactId, String lastName) {
        Contact contact = getContact(contactId);
        contact.setLastName(lastName);
    }

    /**
     * Updates the phone number of a contact
     * @param contactId ID of the contact to update
     * @param phone New phone number
     * @throws IllegalArgumentException if contactId is null, contact doesn't exist, or phone is invalid
     */
    public void updatePhone(String contactId, String phone) {
        Contact contact = getContact(contactId);
        contact.setPhone(phone);
    }

    /**
     * Updates the address of a contact
     * @param contactId ID of the contact to update
     * @param address New address
     * @throws IllegalArgumentException if contactId is null, contact doesn't exist, or address is invalid
     */
    public void updateAddress(String contactId, String address) {
        Contact contact = getContact(contactId);
        contact.setAddress(address);
    }

    /**
     * Retrieves a contact by ID
     * @param contactId ID of the contact to retrieve
     * @return Contact object
     * @throws IllegalArgumentException if contactId is null or contact doesn't exist
     */
    public Contact getContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact with ID " + contactId + " does not exist");
        }

        return contact;
    }

    /**
     * Returns the number of contacts in the service
     * @return Number of contacts
     */
    public int getContactCount() {
        return contacts.size();
    }

    /**
     * Checks if a contact exists with the given ID
     * @param contactId ID to check
     * @return true if contact exists, false otherwise
     */
    public boolean contactExists(String contactId) {
        return contactId != null && contacts.containsKey(contactId);
    }
}