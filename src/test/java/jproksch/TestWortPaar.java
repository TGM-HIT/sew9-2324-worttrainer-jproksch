package jproksch;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestWortPaar {

    @Test
    public void testValidWortPaarCreation(){
        // Arrange
        String name = "Apple";
        String url = "https://example.com/apple.jpg";

        // Act
        WortPaar wortPaar = new WortPaar(name, url);

        // Assert
        assertEquals(name, wortPaar.getName());
        assertEquals(url, wortPaar.getUrl().toString());
    }

    @Test
    public void testInvalidWortPaarCreationWithEmptyName() {
        // Arrange
        String name = "";
        String url = "https://example.com/apple.jpg";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new WortPaar(name, url));
    }

    @Test
    public void testInvalidWortPaarCreationWithInvalidURL() {
        // Arrange
        String name = "Apple";
        String url = "invalid-url"; // Eine ungültige URL

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new WortPaar(name, url));
    }
}
