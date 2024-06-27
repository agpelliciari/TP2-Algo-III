package tp2.clases.questions.factories;

import tp2.clases.questions.modes.ClassicMode;
import tp2.clases.questions.modes.PartialMode;
import tp2.clases.questions.modes.PenaltyMode;

public class QuestionFactoryProvider {

    public static QuestionFactory getFactory(String type) {
        return switch (type) {
            case "Verdadero Falso Simple", "Verdadero Falso" -> new TrueOrFalseFactory(new ClassicMode());
            case "Verdadero Falso Penalidad" -> new TrueOrFalseFactory(new PenaltyMode());
            case "Multiple Choice Simple" -> new MultipleChoiceFactory(new ClassicMode());
            case "Multiple Choice Puntaje Parcial" -> new MultipleChoiceFactory(new PartialMode());
            case "Multiple Choice Penalidad" -> new MultipleChoiceFactory(new PenaltyMode());
            case "Ordered choice", "Ordered Choice" -> new OrderedChoiceFactory();
            case "Group Choice" -> new GroupChoiceFactory();
            default -> throw new IllegalStateException();
        };
    }
}
