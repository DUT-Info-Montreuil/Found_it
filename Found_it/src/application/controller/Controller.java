package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Enemy;
import application.modele.Environment;
import application.modele.Player;
import application.modele.Slime;
import application.modele.TileMap;
import application.modele.Zombie;
import application.vue.CharacterVue;
import application.vue.InventoryVue;
import application.vue.MapVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controller  implements Initializable{

	private MapVue maps;
	private Player player;
	private TileMap mapTile;
	private Timeline gameLoop;
	private KeyManager keyControl;
	private ClickManager clickControl;
	private Environment e;
	
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
		player = new Player(0, 800, mapTile,0,100,2);
		new CharacterVue(mapPane,player);
		new InventoryVue(mapPane, player, mapTile);
		keyControl = new KeyManager(player);
		ListChangeListener<Integer> listen = new MapManager(maps);
		mapTile.getMap().addListener(listen);
		game.setOnKeyPressed(keyControl);
		game.setOnKeyReleased(keyControl);
		clickControl = new ClickManager(player);
		game.setOnMouseClicked(clickControl);
		e = new Environment(mapTile,player);
		ListChangeListener<Enemy> lObs = new ObsEnvironment(mapPane);
		e.getListEnemiesProperty().addListener(lObs);
		e.addEnemy(new Zombie (400, 400, mapTile, 10, 30));
		e.addEnemy(new Slime (500, 150, mapTile, 10, 30));
		System.out.println(game.getHeight());
		initGameLoop();
		gameLoop.play();
		
	}
	public void initGameLoop() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.014),(ev ->{
			if (!player.isInInventory())
				e.updatePlayer();
			e.update();
		}));
		gameLoop.getKeyFrames().add(kf);
	}
	

	
	
}
