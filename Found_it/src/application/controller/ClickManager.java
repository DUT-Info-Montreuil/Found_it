package application.controller;

import application.modele.Player;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickManager implements EventHandler<MouseEvent> {

    private Player player;

    public ClickManager(Player p) {
        player = p;
    }

    @Override
    public void handle(MouseEvent e) {
        if (!player.isInInventory() && player.getPelle() != null)
            player.getPelle().dig((int)e.getX(), (int)e.getY());
    }

}
