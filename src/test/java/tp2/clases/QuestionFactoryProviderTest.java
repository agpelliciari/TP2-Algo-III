package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.questions.factories.*;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFactoryProviderTest {

    @Test
    // El proveedor de fabricas devuelve todas las ocurrencias de verdadero o falso
    public void test01TheProviderReturnsATrueOrFalseFactory() {
        // Arrange
        QuestionFactory factoryOne = QuestionFactoryProvider.getFactory("Verdadero Falso");
        QuestionFactory factoryTwo = QuestionFactoryProvider.getFactory("Verdadero Falso Simple");
        QuestionFactory factoryThree = QuestionFactoryProvider.getFactory("Verdadero Falso Penalidad");

        // Assert
        assertInstanceOf(TrueOrFalseFactory.class, factoryOne);
        assertInstanceOf(TrueOrFalseFactory.class, factoryTwo);
        assertInstanceOf(TrueOrFalseFactory.class, factoryThree);
    }

    @Test
    // El proveedor de fabricas devuelve todas las ocurrencias de multiple choice
    public void test02TheProviderReturnsAMultipleChoiceFactory() {
        // Arrange
        QuestionFactory factoryOne = QuestionFactoryProvider.getFactory("Multiple Choice Simple");
        QuestionFactory factoryTwo = QuestionFactoryProvider.getFactory("Multiple Choice Puntaje Parcial");
        QuestionFactory factoryThree = QuestionFactoryProvider.getFactory("Multiple Choice Penalidad");

        // Assert
        assertInstanceOf(MultipleChoiceFactory.class, factoryOne);
        assertInstanceOf(MultipleChoiceFactory.class, factoryTwo);
        assertInstanceOf(MultipleChoiceFactory.class, factoryThree);
    }

    @Test
    // El proveedor de fabricas devuelve todas las ocurrencias de ordered choice
    public void test03TheProviderReturnsAOrderedChoiceFactory() {
        // Arrange
        QuestionFactory factoryOne = QuestionFactoryProvider.getFactory("Ordered choice");
        QuestionFactory factoryTwo = QuestionFactoryProvider.getFactory("Ordered Choice");

        // Assert
        assertInstanceOf(OrderedChoiceFactory.class, factoryOne);
        assertInstanceOf(OrderedChoiceFactory.class, factoryTwo);
    }

    @Test
    // El proveedor de fabricas devuelve una pregunta de group choice
    public void test04TheProviderReturnsAGroupChoiceFactory() {
        // Arrange
        QuestionFactory factory = QuestionFactoryProvider.getFactory("Group Choice");

        // Assert
        assertInstanceOf(GroupChoiceFactory.class, factory);
    }

    @Test
    // El proveedor de fabricas lanza una excepcion si recibe una cadena invalida
    public void test05TheProviderThrowsAnExceptionIfTheTypeIsInvalid() {
        // Assert
        assertThrows(IllegalStateException.class, () -> {QuestionFactoryProvider.getFactory("Unknown question type");});
    }
}
