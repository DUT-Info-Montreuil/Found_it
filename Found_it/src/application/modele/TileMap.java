package application.modele;

import javafx.collections.ObservableList;

public class TileMap {
	
	private int width, height;
	private final int PIXEL = 32;
	private final int[] LISTIDBLOCKTRANSPARENT = {112}; 
	private ObservableList<Integer> map;
	

	public TileMap(int width, int height) {
		this.width = width;
		this.height = height;
		map = new MapLoader().translateToObservableList();
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
		map.set(getIndice(x, y), LISTIDBLOCKTRANSPARENT[0]);
	}
	
}
