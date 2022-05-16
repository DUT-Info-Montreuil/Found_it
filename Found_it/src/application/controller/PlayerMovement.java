package application.controller;

import application.modele.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerMovement implements EventHandler<KeyEvent> {

		private Player player;
	
		public PlayerMovement(Player p) {
			super();
			player = p;
		}
		
	
		@Override
		public void handle(KeyEvent e) {
			System.out.println(e.getCode());	
			switch(e.getCode()) {				
					
			case Q : player.deplacementGauche();
			break;
					
			case D : player.deplacementDroite();
			break;
				
			case SPACE : player.jump();
			break;
			default:		
			break;
					
				}	
			 }
				
	
}
