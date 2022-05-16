package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Player;
import application.modele.TileMap;
import application.vue.CharacterVue;
import application.vue.MapVue;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class Controller  implements Initializable{

	private MapVue maps;
	private CharacterVue playerVue;
	private Player player;
	private TileMap mapTile;
	
	
	@FXML
	private TilePane mapTilePane;
	@FXML
	private Pane mapPane;
	@FXML
	private BorderPane game;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mapTile = new TileMap(60,34);
		maps = new MapVue(mapTilePane, mapTile);
		player = new Player(0, 800, mapTile,8);
		playerVue = new CharacterVue(mapPane,player);
		game.setOnKeyPressed(new PlayerMovement(player));
		ChangeListener<Number> gravity = new CharacterObs(player);
		player.getXProperty().addListener(gravity);
		
		
	}
	

	
	
}
