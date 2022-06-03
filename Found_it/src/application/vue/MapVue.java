package application.vue;

import application.modele.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MapVue {

	private TilePane mapTilePane;
	private TileMap mapTileMap;

	//private TypeTuiles tuiles;
	
	private TypeTuiles sky = TypeTuiles.sky;
	private TypeTuiles grass = TypeTuiles.grass;
	private TypeTuiles river = TypeTuiles.river;
	private TypeTuiles dirt = TypeTuiles.dirt;
	private TypeTuiles bedrock = TypeTuiles.bedrock;
	
	private Image skyPicture = new Image("application/vue/tilset/sky.png");
	private Image grassPicture = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image riverPicture = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image dirtPicture = new Image("application/vue/tilset/blocks/dirt.png");
	private Image bedRockPicture = new Image("application/vue/tilset/blocks/coal_block.png");
	
	public MapVue(TilePane mapTilePane, TileMap mapTileMap) {
		this.mapTilePane = mapTilePane;
		this.mapTileMap = mapTileMap;
		mapTilePane.setPrefColumns(60);
		mapTilePane.setPrefRows(34);
		buildMap();
	}
	
	private void buildMap() {
		for (int j = 0 ; j < mapTileMap.getMap().size() ; j++){
			if (mapTileMap.getMap().get(j).equals(sky.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(skyPicture));
			else if (mapTileMap.getMap().get(j).equals(grass.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(grassPicture));
			else if (mapTileMap.getMap().get(j).equals(dirt.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(dirtPicture));
			else if (mapTileMap.getMap().get(j).equals(river.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(riverPicture));
			else if (mapTileMap.getMap().get(j).equals(bedrock.getCodeTuile()))
				mapTilePane.getChildren().add(new ImageView(bedRockPicture));
		
		}
	}
	
	public void reloadTile(int index) {
		
		if (mapTileMap.getMap().get(index).equals(sky.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(skyPicture));
		else if (mapTileMap.getMap().get(index).equals(grass.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(grassPicture));
		else if (mapTileMap.getMap().get(index).equals(dirt.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(dirtPicture));
		else if (mapTileMap.getMap().get(index).equals(river.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(riverPicture));
		else if (mapTileMap.getMap().get(index).equals(bedrock.getCodeTuile()))
			mapTilePane.getChildren().set(index, new ImageView(bedRockPicture));
		
	}

	public enum TypeTuiles {

		sky (112),
		grass(2),
		dirt(20),
		river(165),
		bedrock(999);
	
		private int codeTuiles;
	
		TypeTuiles(int codeTuiles){
			this.codeTuiles = codeTuiles;
		}
	
		public int getCodeTuile(){
			return this.codeTuiles;
		}
	
	
	
	}
	
	
	
	
}
