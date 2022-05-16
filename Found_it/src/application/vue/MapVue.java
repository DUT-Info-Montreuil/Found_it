package application.vue;

import application.modele.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MapVue {

	private TilePane mapTilePane;
	private TileMap mapTileMap;
	
	private Image sky = new Image("application/vue/tilset/sky.png");
	private Image grass = new Image("application/vue/tilset/grass.png");
	private Image river = new Image("application/vue/tilset/river.png");
	private Image dirt = new Image("application/vue/tilset/dirt.png");
	
	public MapVue(TilePane mapTilePane, TileMap mapTileMap) {
		this.mapTilePane = mapTilePane;
		this.mapTileMap = mapTileMap;
		mapTilePane.setPrefColumns(60);
		mapTilePane.setPrefRows(34);
		buildMap();
	}
	
	private void buildMap() {
		for (int j = 0 ; j < mapTileMap.getMap().length ; j++) {
			if (mapTileMap.getMap()[j] == 112)
				mapTilePane.getChildren().add(new ImageView(sky));
			else if (mapTileMap.getMap()[j] == 2)
				mapTilePane.getChildren().add(new ImageView(grass));
			else if (mapTileMap.getMap()[j] == 20)
				mapTilePane.getChildren().add(new ImageView(dirt));
			else if (mapTileMap.getMap()[j] == 165)
				mapTilePane.getChildren().add(new ImageView(river));
		}
	}
	
	
	
}
