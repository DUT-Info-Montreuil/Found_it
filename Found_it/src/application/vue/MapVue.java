package application.vue;

import application.modele.TileMap;
import application.modele.TypeTuiles;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MapVue {

	private TilePane mapTilePane;
	private TileMap mapTileMap;
	
	private Image sky = new Image("application/vue/tilset/sky.png");
	private Image grass = new Image("application/vue/tilset/grass.png");
	private Image river = new Image("application/vue/tilset/grassfond.png");
	private Image dirt = new Image("application/vue/tilset/dirt.png");
	
	public MapVue(TilePane mapTilePane, TileMap mapTileMap) {
		this.mapTilePane = mapTilePane;
		this.mapTileMap = mapTileMap;
		mapTilePane.setPrefColumns(60);
		mapTilePane.setPrefRows(34);
		buildMap();
	}
	
	private void buildMap() {
		for (int j = 0 ; j < mapTileMap.getMap().size() ; j++)
			if (mapTileMap.getMap().get(j).equals(TypeTuiles.sky.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(sky));
			else if (mapTileMap.getMap().get(j).equals(TypeTuiles.grass.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(grass));
			else if (mapTileMap.getMap().get(j).equals(TypeTuiles.dirt.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(dirt));
	}
	public void reloadTile(int index) {
		if (mapTileMap.getMap().get(index).equals(TypeTuiles.sky.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(sky));
		else if (mapTileMap.getMap().get(index).equals(TypeTuiles.grass.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(grass));
		else if (mapTileMap.getMap().get(index).equals(TypeTuiles.dirt.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(dirt));
	}
	
	
	
}
