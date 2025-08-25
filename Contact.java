/**
 * Contact class represents a contact object with validation requirements
 * for a mobile application contact service.
 */
public class Contact {
    private final String contactId;  // Final to ensure immutability
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Constructor for Contact object
     * @param contactId Unique identifier, cannot be null, max 10 characters
     * @param firstName First name, cannot be null, max 10 characters
     * @param lastName Last name, cannot be null, max 10 characters
     * @param phone Phone number, cannot be null, must be exactly 10 digits
     * @param address Address, cannot be null, max 30 characters
     */
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // Validate and set contactId (immutable)
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID cannot be null and must be 10 characters or less");
        }
        this.contactId = contactId;

        // Set other fields using setters for validation
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    // Getter methods
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setter methods with validation (contactId is not included as it's immutable)
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null and must be 10 characters or less");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null and must be 10 characters or less");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number cannot be null and must be exactly 10 digits");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null and must be 30 characters or less");
        }
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId='" + contactId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return contactId.equals(contact.contactId);
    }

    @Override
    public int hashCode() {
        return contactId.hashCode();
    }
}