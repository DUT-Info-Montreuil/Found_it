package application.modele;

import java.io.File;

import javafx.collections.ObservableList;

public class TileMap {
	
	private int width, height;
	private final int PIXEL = 32;
	private final int[] LISTIDBLOCKTRANSPARENT = {TypeTuiles.sky.getCodeTuile()};
	private final int[] LISTIDBLOCKINDESTRUCTIBLE = {TypeTuiles.bedrock.getCodeTuile()}; 
	private ObservableList<Integer> map;
	private MapLoader loader;
	

	public TileMap() {
		loader = new MapLoader(this);
		map = loader.readFile(new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "/application/vue/mapsCSV/Map2.0.csv"));
	}
	
	public ObservableList<Integer> getMap() {
		return map;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int value) {
		width = value;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int value) {
		height = value;
	}
	
	public int getPIXELBLOCK() {
		return PIXEL;
	}
	public int getXCharacterInMap(int x) {
		return x/PIXEL;
	}
	
	public int getYCharacterInMap(int y) {
		return (y/PIXEL)*width;
	}
	
	public int getYIndiceHeight(int y) {
		return y / PIXEL;
	}
	
	public int getIndice(int x, int y) {
		return getXCharacterInMap(x) + getYCharacterInMap(y);
	}

	public int getCodeTuile(int x, int y) {
		return (int)map.get(getIndice(x, y));
	}

	public int getCodeTuileWithIndice(int x, int y) {
		return (int)map.get(x+y*width);
	}

	public boolean wasTransparent(int block) {
		int i = 0;
		while (i < LISTIDBLOCKTRANSPARENT.length && LISTIDBLOCKTRANSPARENT[i] != block)
			i++;
		return i < LISTIDBLOCKTRANSPARENT.length;
	}
	public void remove(int x, int y) {
		if(map.get(getIndice(x, y)) != LISTIDBLOCKINDESTRUCTIBLE[0])
			map.set(getIndice(x, y), LISTIDBLOCKTRANSPARENT[0]);
	}
	public boolean wasIndestructible(int block){
		int i = 0;
		while(i < LISTIDBLOCKINDESTRUCTIBLE.length && LISTIDBLOCKINDESTRUCTIBLE[i] != block){
			i++;
		}
		return i < LISTIDBLOCKINDESTRUCTIBLE.length;
	}
	public void replace(int x, int y, int block) {
		map.set(getIndice(x, y), block);
	}
	
}
