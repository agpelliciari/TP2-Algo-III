package tp2.clases.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tp2.clases.Player;
import tp2.clases.Power;
import java.util.ArrayList;

public class PowersUsedScreen extends VBox {
    private ArrayList<Player> players;
    public PowersUsedScreen (ArrayList<Player> players) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(20);
        this.players = players;

        Label title = new Label("Poderes usados en durante la partida");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        getChildren().add(title);

        displayPlayersWhoUsedPowers();
    }

    private void displayPlayersWhoUsedPowers() {
        for (Player player: players) {
            ArrayList<Power> powers = player.getPowers();
            storeUsedPowers(powers, player);
        }
    }

    private void storeUsedPowers(ArrayList<Power> powers, Player player) {
        for (Power power: powers) {
            String powerUsed = getNamePower(power);
            Label powerUsedLabel = new Label();
            if (!powerUsed.isEmpty()) {
                powerUsedLabel.setText(player.getName() + " usó " + powerUsed);
                powerUsedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                getChildren().add(powerUsedLabel);
            }
        }
    }

    private String getNamePower(Power power) {
        if (power.isActive()) { return power.getName(); }
        return "";
    }
}
