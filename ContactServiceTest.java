import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for ContactService class
 * Tests all service functionality including add, delete, and update operations
 */
public class ContactServiceTest {
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }

    // Add contact tests
    @Test
    @DisplayName("Adding valid contact should succeed")
    public void testAddContactValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.addContact(contact);

        assertEquals(1, contactService.getContactCount());
        assertTrue(contactService.contactExists("123"));
        assertEquals(contact, contactService.getContact("123"));
    }

    @Test
    @DisplayName("Adding contact with parameters should succeed")
    public void testAddContactWithParameters() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertEquals(1, contactService.getContactCount());
        assertTrue(contactService.contactExists("123"));

        Contact retrieved = contactService.getContact("123");
        assertEquals("John", retrieved.getFirstName());
        assertEquals("Doe", retrieved.getLastName());
        assertEquals("5551234567", retrieved.getPhone());
        assertEquals("123 Main St", retrieved.getAddress());
    }

    @Test
    @DisplayName("Adding null contact should throw exception")
    public void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.addContact(null));
    }

    @Test
    @DisplayName("Adding contact with duplicate ID should throw exception")
    public void testAddDuplicateContactId() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.addContact("123", "Jane", "Smith", "9876543210", "456 Oak Ave"));
    }

    @Test
    @DisplayName("Adding multiple unique contacts should succeed")
    public void testAddMultipleContacts() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.addContact("456", "Jane", "Smith", "9876543210", "456 Oak Ave");
        contactService.addContact("789", "Bob", "Johnson", "5559876543", "789 Pine Rd");

        assertEquals(3, contactService.getContactCount());
        assertTrue(contactService.contactExists("123"));
        assertTrue(contactService.contactExists("456"));
        assertTrue(contactService.contactExists("789"));
    }

    // Delete contact tests
    @Test
    @DisplayName("Deleting existing contact should succeed")
    public void testDeleteContactValid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        assertEquals(1, contactService.getContactCount());

        contactService.deleteContact("123");
        assertEquals(0, contactService.getContactCount());
        assertFalse(contactService.contactExists("123"));
    }

    @Test
    @DisplayName("Deleting non-existent contact should throw exception")
    public void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.deleteContact("999"));
    }

    @Test
    @DisplayName("Deleting with null contact ID should throw exception")
    public void testDeleteNullContactId() {
        assertThrows(IllegalArgumentException.class, () -> contactService.deleteContact(null));
    }

    @Test
    @DisplayName("Deleting one contact from multiple should work correctly")
    public void testDeleteFromMultipleContacts() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.addContact("456", "Jane", "Smith", "9876543210", "456 Oak Ave");

        contactService.deleteContact("123");

        assertEquals(1, contactService.getContactCount());
        assertFalse(contactService.contactExists("123"));
        assertTrue(contactService.contactExists("456"));
    }

    // Update first name tests
    @Test
    @DisplayName("Updating first name should succeed")
    public void testUpdateFirstNameValid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.updateFirstName("123", "Jane");

        Contact contact = contactService.getContact("123");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    @DisplayName("Updating first name of non-existent contact should throw exception")
    public void testUpdateFirstNameNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateFirstName("999", "Jane"));
    }

    @Test
    @DisplayName("Updating first name with invalid value should throw exception")
    public void testUpdateFirstNameInvalid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updateFirstName("123", "VeryLongName"));
    }

    @Test
    @DisplayName("Updating first name with null should throw exception")
    public void testUpdateFirstNameNull() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updateFirstName("123", null));
    }

    // Update last name tests
    @Test
    @DisplayName("Updating last name should succeed")
    public void testUpdateLastNameValid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.updateLastName("123", "Smith");

        Contact contact = contactService.getContact("123");
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    @DisplayName("Updating last name of non-existent contact should throw exception")
    public void testUpdateLastNameNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateLastName("999", "Smith"));
    }

    @Test
    @DisplayName("Updating last name with null value should throw exception")
    public void testUpdateLastNameNull() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updateLastName("123", null));
    }

    @Test
    @DisplayName("Updating last name with invalid length should throw exception")
    public void testUpdateLastNameInvalid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updateLastName("123", "VeryLongLastName"));
    }

    // Update phone tests
    @Test
    @DisplayName("Updating phone number should succeed")
    public void testUpdatePhoneValid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.updatePhone("123", "9876543210");

        Contact contact = contactService.getContact("123");
        assertEquals("9876543210", contact.getPhone());
    }

    @Test
    @DisplayName("Updating phone of non-existent contact should throw exception")
    public void testUpdatePhoneNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("999", "9876543210"));
    }

    @Test
    @DisplayName("Updating phone with invalid format should throw exception")
    public void testUpdatePhoneInvalid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("123", "123-456-7890"));
    }

    @Test
    @DisplayName("Updating phone with null should throw exception")
    public void testUpdatePhoneNull() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("123", null));
    }

    @Test
    @DisplayName("Updating phone with wrong length should throw exception")
    public void testUpdatePhoneWrongLength() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        // Too short
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("123", "123456789"));

        // Too long
        assertThrows(IllegalArgumentException.class, () -> contactService.updatePhone("123", "12345678901"));
    }

    // Update address tests
    @Test
    @DisplayName("Updating address should succeed")
    public void testUpdateAddressValid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.updateAddress("123", "456 Oak Avenue");

        Contact contact = contactService.getContact("123");
        assertEquals("456 Oak Avenue", contact.getAddress());
    }

    @Test
    @DisplayName("Updating address of non-existent contact should throw exception")
    public void testUpdateAddressNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateAddress("999", "456 Oak Ave"));
    }

    @Test
    @DisplayName("Updating address with too long value should throw exception")
    public void testUpdateAddressTooLong() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updateAddress("123", "123 Very Very Very Long Street Name That Exceeds The Thirty Character Limit"));
    }

    @Test
    @DisplayName("Updating address with null should throw exception")
    public void testUpdateAddressNull() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> contactService.updateAddress("123", null));
    }

    // Get contact tests
    @Test
    @DisplayName("Getting existing contact should return correct contact")
    public void testGetContactValid() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        Contact contact = contactService.getContact("123");

        assertNotNull(contact);
        assertEquals("123", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("5551234567", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    @DisplayName("Getting non-existent contact should throw exception")
    public void testGetNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> contactService.getContact("999"));
    }

    @Test
    @DisplayName("Getting contact with null ID should throw exception")
    public void testGetContactNullId() {
        assertThrows(IllegalArgumentException.class, () -> contactService.getContact(null));
    }

    // Contact exists tests
    @Test
    @DisplayName("Contact exists should return true for existing contact")
    public void testContactExistsTrue() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        assertTrue(contactService.contactExists("123"));
    }

    @Test
    @DisplayName("Contact exists should return false for non-existent contact")
    public void testContactExistsFalse() {
        assertFalse(contactService.contactExists("999"));
    }

    @Test
    @DisplayName("Contact exists should return false for null ID")
    public void testContactExistsNull() {
        assertFalse(contactService.contactExists(null));
    }

    // Contact count tests
    @Test
    @DisplayName("Initial contact count should be zero")
    public void testInitialContactCount() {
        assertEquals(0, contactService.getContactCount());
    }

    @Test
    @DisplayName("Contact count should increase when adding contacts")
    public void testContactCountIncrease() {
        assertEquals(0, contactService.getContactCount());

        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        assertEquals(1, contactService.getContactCount());

        contactService.addContact("456", "Jane", "Smith", "9876543210", "456 Oak Ave");
        assertEquals(2, contactService.getContactCount());
    }

    @Test
    @DisplayName("Contact count should decrease when deleting contacts")
    public void testContactCountDecrease() {
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.addContact("456", "Jane", "Smith", "9876543210", "456 Oak Ave");
        assertEquals(2, contactService.getContactCount());

        contactService.deleteContact("123");
        assertEquals(1, contactService.getContactCount());

        contactService.deleteContact("456");
        assertEquals(0, contactService.getContactCount());
    }

    // Integration tests - testing multiple operations together
    @Test
    @DisplayName("Multiple operations on same contact should work correctly")
    public void testMultipleOperationsOnContact() {
        // Add contact
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");

        // Verify initial state
        Contact contact = contactService.getContact("123");
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("5551234567", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());

        // Update all fields
        contactService.updateFirstName("123", "Jane");
        contactService.updateLastName("123", "Smith");
        contactService.updatePhone("123", "9876543210");
        contactService.updateAddress("123", "456 Oak Avenue");

        // Verify updates
        contact = contactService.getContact("123");
        assertEquals("Jane", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("9876543210", contact.getPhone());
        assertEquals("456 Oak Avenue", contact.getAddress());

        // Contact ID should remain unchanged
        assertEquals("123", contact.getContactId());
    }

    @Test
    @DisplayName("Service should handle maximum length values correctly")
    public void testMaximumLengthValues() {
        // Test with maximum length values
        String maxId = "1234567890";        // 10 characters
        String maxFirstName = "1234567890";  // 10 characters
        String maxLastName = "1234567890";   // 10 characters
        String validPhone = "1234567890";    // 10 digits
        String maxAddress = "123456789012345678901234567890"; // 30 characters

        contactService.addContact(maxId, maxFirstName, maxLastName, validPhone, maxAddress);

        Contact contact = contactService.getContact(maxId);
        assertEquals(maxId, contact.getContactId());
        assertEquals(maxFirstName, contact.getFirstName());
        assertEquals(maxLastName, contact.getLastName());
        assertEquals(validPhone, contact.getPhone());
        assertEquals(maxAddress, contact.getAddress());
    }

    @Test
    @DisplayName("Service should handle minimum length values correctly")
    public void testMinimumLengthValues() {
        // Test with minimum length values (1 character each, except phone which must be 10)
        contactService.addContact("1", "A", "B", "1234567890", "C");

        Contact contact = contactService.getContact("1");
        assertEquals("1", contact.getContactId());
        assertEquals("A", contact.getFirstName());
        assertEquals("B", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("C", contact.getAddress());
    }

    @Test
    @DisplayName("Service should maintain contact integrity after operations")
    public void testContactIntegrityAfterOperations() {
        // Add multiple contacts
        contactService.addContact("123", "John", "Doe", "5551234567", "123 Main St");
        contactService.addContact("456", "Jane", "Smith", "9876543210", "456 Oak Ave");
        contactService.addContact("789", "Bob", "Johnson", "5559876543", "789 Pine Rd");

        // Update one contact
        contactService.updateFirstName("456", "Janet");
        contactService.updateAddress("456", "789 New Address");

        // Delete one contact
        contactService.deleteContact("789");

        // Verify remaining contacts are intact
        assertEquals(2, contactService.getContactCount());

        Contact john = contactService.getContact("123");
        assertEquals("John", john.getFirstName());
        assertEquals("Doe", john.getLastName());

        Contact janet = contactService.getContact("456");
        assertEquals("Janet", janet.getFirstName());
        assertEquals("Smith", janet.getLastName());
        assertEquals("789 New Address", janet.getAddress());

        // Verify deleted contact doesn't exist
        assertFalse(contactService.contactExists("789"));
    }
}