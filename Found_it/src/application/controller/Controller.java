package application.controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import application.modele.Enemy;
import application.modele.Environment;
import application.modele.Player;
import application.modele.Projectile;
import application.modele.Slime;
import application.modele.Squeleton;
import application.modele.TileMap;
import application.modele.Wizard;
import application.modele.Zombie;
import application.vue.CharacterVue;
import application.vue.InterfacePlayerVue;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller  implements Initializable{

	private MapVue maps;
	private Player player;
	private TileMap mapTile;
	private Timeline gameLoop;
	private KeyManager keyControl;
	private ClickManager clickControl;
	private Environment e;
	private MediaPlayer mediaPlayer;
	private CharacterVue characterVue;
	
	@FXML
	private TilePane mapTilePane;
	@FXML
	private Pane mapPane;
	@FXML
	private BorderPane game;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//music();
		mapTile = new TileMap(60,34);
		maps = new MapVue(mapTilePane, mapTile);
		e = new Environment(mapTile);
		player = new Player(0, 800, mapTile,0,300,2,e);
		characterVue = new CharacterVue(mapPane,player);
		new InterfacePlayerVue(player, mapPane);
		new InventoryVue(mapPane, player, mapTile);
		keyControl = new KeyManager(player);
		ListChangeListener<Integer> listen = new MapManager(maps);
		mapTile.getMap().addListener(listen);
		game.setOnKeyPressed(keyControl);
		game.setOnKeyReleased(keyControl);
		clickControl = new ClickManager(player);
		game.setOnMouseClicked(clickControl);
		ListChangeListener<Enemy> lObs = new ObsEnvironment(mapPane);
		e.getListEnemiesProperty().addListener(lObs);
		ListChangeListener<Projectile> pObs = new ObsProjectile(mapPane);
		e.getListProjectileProperty().addListener(pObs);
		e.addEnemy(new Zombie (400, 400, mapTile, 10, 40,e));
		e.addEnemy(new Slime (500, 150, mapTile, 10, 40,e));
		e.addEnemy(new Squeleton(500, 150, mapTile, 10, 40,e));
		e.addEnemy(new Wizard(600, 150, mapTile, 10, 75, e));
		initGameLoop();
		gameLoop.play();
		
	}
	public void initGameLoop() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.014),(ev ->{
			e.update();
		}));
		gameLoop.getKeyFrames().add(kf);
	}
	public void music() {
		String s = "/home/etudiants/info/cgrosjean/Documents/SAE/GITHUB/Found_it/Found_it/src/application/music.mp3";
		Media media = new Media(Paths.get(s).toUri().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	
	
}
