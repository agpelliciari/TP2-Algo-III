package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.questions.choice.corrections.CorrectionFactory;
import tp2.clases.model.questions.choice.corrections.types.Correct;
import tp2.clases.model.questions.choice.corrections.types.Correction;
import tp2.clases.model.questions.choice.corrections.types.Incorrect;

import static org.junit.jupiter.api.Assertions.*;

// Prubas de la fábrica de correcciones
public class CorrectionFactoryTest {

    @Test
    // Correcta instanciación de una
    public void test01AssignCorrectCorrection() {
        // Arrange
        Correction correction;

        //Act
        correction = CorrectionFactory.assignCorrection("Correcta");

        // Assert
        assertInstanceOf(Correct.class, correction);
    }

    @Test
    public void test02AssignIncorrectCorrection() {
        // Arrange
        Correction correction;

        // Act
        correction = CorrectionFactory.assignCorrection("Incorrecta");

        // Assert
        assertInstanceOf(Incorrect.class, correction);
    }

    @Test
    //
    public void test03AssignUnknownCorrection() {
        assertThrows(IllegalArgumentException.class, () -> {CorrectionFactory.assignCorrection("Desconocida");});
    }
}
