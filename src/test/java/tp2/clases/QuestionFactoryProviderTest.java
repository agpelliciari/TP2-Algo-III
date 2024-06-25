package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFactoryProviderTest {

    @Test
    public void test01TheProviderReturnsATrueOrFalseFactory() {
        //Arrange
        QuestionFactory factoryOne = QuestionFactoryProvider.getFactory("Verdadero Falso");
        QuestionFactory factoryTwo = QuestionFactoryProvider.getFactory("Verdadero Falso Simple");
        QuestionFactory factoryThree = QuestionFactoryProvider.getFactory("Verdadero Falso Penalidad");

        //Assert
        assertInstanceOf(TrueOrFalseFactory.class, factoryOne);
        assertInstanceOf(TrueOrFalseFactory.class, factoryTwo);
        assertInstanceOf(TrueOrFalseFactory.class, factoryThree);
    }

    @Test
    public void test02TheProviderReturnsAMultipleChoiceFactory() {
        //Arrange
        QuestionFactory factoryOne = QuestionFactoryProvider.getFactory("Multiple Choice Simple");
        QuestionFactory factoryTwo = QuestionFactoryProvider.getFactory("Multiple Choice Puntaje Parcial");
        QuestionFactory factoryThree = QuestionFactoryProvider.getFactory("Multiple Choice Penalidad");

        //Assert
        assertInstanceOf(MultipleChoiceFactory.class, factoryOne);
        assertInstanceOf(MultipleChoiceFactory.class, factoryTwo);
        assertInstanceOf(MultipleChoiceFactory.class, factoryThree);
    }

    @Test
    public void test03TheProviderReturnsAOrderedChoiceFactory() {
        //Arrange
        QuestionFactory factoryOne = QuestionFactoryProvider.getFactory("Ordered choice");
        QuestionFactory factoryTwo = QuestionFactoryProvider.getFactory("Ordered Choice");

        //Assert
        assertInstanceOf(OrderedChoiceFactory.class, factoryOne);
        assertInstanceOf(OrderedChoiceFactory.class, factoryTwo);
    }

    @Test
    public void test04TheProviderReturnsAGroupChoiceFactory() {
        //Arrange
        QuestionFactory factory = QuestionFactoryProvider.getFactory("Group Choice");

        //Assert
        assertInstanceOf(GroupChoiceFactory.class, factory);
    }

    @Test
    public void test05TheProviderThrowsAnExceptionIfTheTypeIsInvalid() {
        //Assert
        assertThrows(IllegalStateException.class, () -> {QuestionFactoryProvider.getFactory("Unknown Factory");;});
    }
}
