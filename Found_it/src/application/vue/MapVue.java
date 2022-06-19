package application.vue;

import application.modele.TileMap;
import application.modele.TypeTuiles;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MapVue {

	private TilePane mapTilePane;
	private TileMap mapTileMap;
	
	private Image skyPicture = new Image("application/vue/tilset/sky.png");
	private Image grassPicture = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image riverPicture = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image dirtPicture = new Image("application/vue/tilset/blocks/dirt.png");
	private Image bedRockPicture = new Image("application/vue/tilset/blocks/coal_block.png");
	
	public MapVue(TilePane mapTilePane, TileMap mapTileMap) {
		this.mapTilePane = mapTilePane;
		this.mapTileMap = mapTileMap;
		mapTilePane.setPrefColumns(mapTileMap.getWidth());
		mapTilePane.setPrefRows(mapTileMap.getHeight());
		buildMap();
	}
	
	private void buildMap() {
		for (int j = 0 ; j < mapTileMap.getMap().size() ; j++){
			if (mapTileMap.getMap().get(j).equals(TypeTuiles.sky.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(skyPicture));
			else if (mapTileMap.getMap().get(j).equals(TypeTuiles.grass.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(grassPicture));
			else if (mapTileMap.getMap().get(j).equals(TypeTuiles.dirt.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(dirtPicture));
			else if (mapTileMap.getMap().get(j).equals(TypeTuiles.river.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(riverPicture));
			else if (mapTileMap.getMap().get(j).equals(TypeTuiles.bedrock.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(bedRockPicture));
		
		}
	}
	
	public void reloadTile(int index) {
		
		if (mapTileMap.getMap().get(index).equals(TypeTuiles.sky.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(skyPicture));
		else if (mapTileMap.getMap().get(index).equals(TypeTuiles.grass.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(grassPicture));
		else if (mapTileMap.getMap().get(index).equals(TypeTuiles.dirt.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(dirtPicture));
		else if (mapTileMap.getMap().get(index).equals(TypeTuiles.river.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(riverPicture));
		else if (mapTileMap.getMap().get(index).equals(TypeTuiles.bedrock.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(bedRockPicture));
		
	}
			
}
