package tp2.clases;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

// Modelo de como tienen que ir guardados los botones
public class MiBotonEventHandler implements EventHandler<ActionEvent> {
    private Button miBoton;

    public MiBotonEventHandler(Button miBoton) {
        this.miBoton = miBoton;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String textoClickeado = "¡Me han clickeado!";
        System.out.println(textoClickeado);
        this.miBoton.setText(textoClickeado);
    }
}
