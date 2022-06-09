package application.controller;


import application.modele.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyManager implements EventHandler<KeyEvent> {

		private Player player;
	
		public KeyManager(Player p) {
			player = p;
		}
		
	
		@Override
		public void handle(KeyEvent e) {	
			switch(e.getCode()) {				
			case Q :
				player.setLeftPressed(e.getEventType() == KeyEvent.KEY_PRESSED);
			break;
					
			case D : 
				player.setRightPressed(e.getEventType() == KeyEvent.KEY_PRESSED);
			break;
				
			case SPACE : 
				player.setUpPressed(e.getEventType() == KeyEvent.KEY_PRESSED);
			break;

			case B : 
				if (e.getEventType() == KeyEvent.KEY_RELEASED) {
					player.openCloseInventory();
				}
			break;
			case Z : 
				if (e.getEventType() == KeyEvent.KEY_RELEASED)
					player.setVIT(2);
				else
					player.setVIT(3);
	
			break;
			default:		
			break;
					
				}	
			 }
				
	
}
