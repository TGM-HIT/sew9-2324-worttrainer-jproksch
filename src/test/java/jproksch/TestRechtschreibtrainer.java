package jproksch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRechtschreibtrainer {

    private Rechtschreibtrainer trainer;

    @BeforeEach
    public void setUp() {
        trainer = new Rechtschreibtrainer();
    }

    @Test
    public void testAbpruefenWithCorrectInput() {
        // Arrange
        trainer.abpruefen("apple"); // Setze das aktive Wort-Bild-Paar (Annahme: es existiert in der Liste)

        // Act
        boolean result = trainer.abpruefen("apple"); // Rufe die Methode abpruefen mit der richtigen Eingabe auf

        // Assert
        assertTrue(result); // Erwarte, dass die Methode true zurückgibt, da die Eingabe korrekt ist
    }
}
