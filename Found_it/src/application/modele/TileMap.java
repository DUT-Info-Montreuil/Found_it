package application.modele;

import java.io.File;

import javafx.collections.ObservableList;

public class TileMap {
	
	private int width, height;
	private final int PIXEL = 32;
	private final int[] LISTIDBLOCKTRANSPARENT = {111};
	private final int[] LISTIDBLOCKINDESTRUCTIBLE = {999}; 
	private ObservableList<Integer> map;
	

	public TileMap(int width, int height) {
		this.width = width;
		this.height = height;
		// map = new MapLoader().translateToObservableList();
		map = new MapLoader().readFile(new File("/home/etudiants/info/cgrosjean/Documents/SAE/GITHUB/Found_it/Found_it/src/application/vue/mapsjson/MapTestTest.csv"));
	}
	
	public ObservableList<Integer> getMap() {
		return map;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
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
	
}
