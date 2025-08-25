import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for Contact class
 * Tests all validation requirements and functionality
 */
public class ContactTest {

    @Test
    @DisplayName("Contact creation with valid parameters should succeed")
    public void testContactCreationValid() {
        Contact contact = new Contact("1234567890", "John", "Doe", "5551234567", "123 Main St");

        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("5551234567", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    // Contact ID validation tests
    @Test
    @DisplayName("Contact creation with null contact ID should throw exception")
    public void testContactCreationWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "John", "Doe", "5551234567", "123 Main St"));
    }

    @Test
    @DisplayName("Contact creation with contact ID longer than 10 characters should throw exception")
    public void testContactCreationWithLongId() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("12345678901", "John", "Doe", "5551234567", "123 Main St"));
    }

    @Test
    @DisplayName("Contact creation with exactly 10 character contact ID should succeed")
    public void testContactCreationWithMaxLengthId() {
        assertDoesNotThrow(() -> {
            new Contact("1234567890", "John", "Doe", "5551234567", "123 Main St");
        });
    }

    // First name validation tests
    @Test
    @DisplayName("Contact creation with null first name should throw exception")
    public void testContactCreationWithNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", null, "Doe", "5551234567", "123 Main St"));
    }

    @Test
    @DisplayName("Contact creation with first name longer than 10 characters should throw exception")
    public void testContactCreationWithLongFirstName() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "Christopher", "Doe", "5551234567", "123 Main St"));
    }

    @Test
    @DisplayName("Setting valid first name should succeed")
    public void testSetFirstNameValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    @DisplayName("Setting null first name should throw exception")
    public void testSetFirstNameNull() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
    }

    // Last name validation tests
    @Test
    @DisplayName("Contact creation with null last name should throw exception")
    public void testContactCreationWithNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", null, "5551234567", "123 Main St"));
    }

    @Test
    @DisplayName("Contact creation with last name longer than 10 characters should throw exception")
    public void testContactCreationWithLongLastName() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", "VeryLongName", "5551234567", "123 Main St"));
    }

    @Test
    @DisplayName("Setting valid last name should succeed")
    public void testSetLastNameValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    // Phone validation tests
    @Test
    @DisplayName("Contact creation with null phone should throw exception")
    public void testContactCreationWithNullPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", "Doe", null, "123 Main St"));
    }

    @Test
    @DisplayName("Contact creation with phone shorter than 10 digits should throw exception")
    public void testContactCreationWithShortPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", "Doe", "555123456", "123 Main St"));
    }

    @Test
    @DisplayName("Contact creation with phone longer than 10 digits should throw exception")
    public void testContactCreationWithLongPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", "Doe", "55512345678", "123 Main St"));
    }

    @Test
    @DisplayName("Contact creation with non-digit phone should throw exception")
    public void testContactCreationWithNonDigitPhone() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", "Doe", "555-123-456", "123 Main St"));
    }

    @Test
    @DisplayName("Setting valid phone should succeed")
    public void testSetPhoneValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setPhone("9876543210");
        assertEquals("9876543210", contact.getPhone());
    }

    // Address validation tests
    @Test
    @DisplayName("Contact creation with null address should throw exception")
    public void testContactCreationWithNullAddress() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", "Doe", "5551234567", null));
    }

    @Test
    @DisplayName("Contact creation with address longer than 30 characters should throw exception")
    public void testContactCreationWithLongAddress() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", "Doe", "5551234567", "123 Very Very Very Long Street Name"));
    }

    @Test
    @DisplayName("Contact creation with exactly 30 character address should succeed")
    public void testContactCreationWithMaxLengthAddress() {
        String maxAddress = "123456789012345678901234567890"; // Exactly 30 characters
        assertDoesNotThrow(() -> {
            new Contact("123", "John", "Doe", "5551234567", maxAddress);
        });
    }

    @Test
    @DisplayName("Setting valid address should succeed")
    public void testSetAddressValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setAddress("456 Oak Ave");
        assertEquals("456 Oak Ave", contact.getAddress());
    }

    // Contact ID immutability test
    @Test
    @DisplayName("Contact ID should be immutable after creation")
    public void testContactIdImmutable() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        // Contact ID should remain the same throughout the object's lifetime
        assertEquals("123", contact.getContactId());

        // Modify other fields to ensure ID doesn't change
        contact.setFirstName("Jane");
        contact.setLastName("Smith");
        contact.setPhone("9876543210");
        contact.setAddress("456 Oak Ave");

        assertEquals("123", contact.getContactId()); // ID should still be the same
    }

    // Equality and hash code tests
    @Test
    @DisplayName("Contacts with same ID should be equal")
    public void testContactEquality() {
        Contact contact1 = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        Contact contact2 = new Contact("123", "Jane", "Smith", "9876543210", "456 Oak Ave");

        assertEquals(contact1, contact2);
        assertEquals(contact1.hashCode(), contact2.hashCode());
    }

    @Test
    @DisplayName("Contacts with different IDs should not be equal")
    public void testContactInequality() {
        Contact contact1 = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        Contact contact2 = new Contact("456", "John", "Doe", "5551234567", "123 Main St");

        assertNotEquals(contact1, contact2);
    }

    @Test
    @DisplayName("toString method should return formatted string")
    public void testToString() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        String result = contact.toString();

        assertTrue(result.contains("123"));
        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("5551234567"));
        assertTrue(result.contains("123 Main St"));
    }
}