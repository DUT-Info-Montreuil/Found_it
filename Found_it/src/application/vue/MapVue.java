package application.vue;

import application.modele.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MapVue {

	private TilePane mapTilePane;
	private TileMap mapTileMap;
	
	private Image sky = new Image("application/vue/tilset/sky.png");
	private Image grass = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image river = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image dirt = new Image("application/vue/tilset/blocks/dirt.png");
	private Image bedRock = new Image("application/vue/tilset/blocks/coal_block.png");
	
	public MapVue(TilePane mapTilePane, TileMap mapTileMap) {
		this.mapTilePane = mapTilePane;
		this.mapTileMap = mapTileMap;
		mapTilePane.setPrefColumns(60);
		mapTilePane.setPrefRows(34);
		buildMap();
	}
	
	private void buildMap() {
		for (int j = 0 ; j < mapTileMap.getMap().size() ; j++)
			if (mapTileMap.getMap().get(j).equals(112))
				mapTilePane.getChildren().add(new ImageView(sky));
			else if (mapTileMap.getMap().get(j).equals(2))
				mapTilePane.getChildren().add(new ImageView(grass));
			else if (mapTileMap.getMap().get(j).equals(20))
				mapTilePane.getChildren().add(new ImageView(dirt));
			else if (mapTileMap.getMap().get(j).equals(165))
				mapTilePane.getChildren().add(new ImageView(river));
			else if (mapTileMap.getMap().get(j).equals(999))
				mapTilePane.getChildren().add(new ImageView(bedRock));
	}
	public void reloadTile(int index) {
		if (mapTileMap.getMap().get(index).equals(112))
			mapTilePane.getChildren().set(index, new ImageView(sky));
		else if (mapTileMap.getMap().get(index).equals(2))
			mapTilePane.getChildren().set(index, new ImageView(grass));
		else if (mapTileMap.getMap().get(index).equals(20))
			mapTilePane.getChildren().set(index, new ImageView(dirt));
		else if (mapTileMap.getMap().get(index).equals(165))
			mapTilePane.getChildren().set(index, new ImageView(river));
		else if (mapTileMap.getMap().get(index).equals(999))
			mapTilePane.getChildren().set(index, new ImageView(bedRock));
	}
	
	
	
}
