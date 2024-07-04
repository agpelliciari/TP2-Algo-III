package tp2.clases.controllers.handlers;

import tp2.clases.model.player.Player;

public class MultiplierButtonHandler {

    String factorString;

    public MultiplierButtonHandler(String factorString) {
        this.factorString = factorString;
    }

    public void selectMultiplier(Player player, boolean selectedMultiplier) {
        if (selectedMultiplier)
                player.useMultiplier(Integer.parseInt(factorString));
    }
}
