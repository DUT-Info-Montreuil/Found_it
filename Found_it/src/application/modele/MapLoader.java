package application.modele;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MapLoader {

	private TileMap mapTile;

	public MapLoader(TileMap map) {
		mapTile = map;
	}

	public ObservableList<Integer> readFile(File file) {
		ObservableList<Integer> list = FXCollections.observableArrayList();
		String line;
		FileReader fr;
		BufferedReader br;
		int width = 0 , height = 0;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			line = br.readLine();
			width = line.split(",").length;
			while (line != null) {
				for (String num : line.split(",")) {
					list.add(Integer.parseInt(num));
				}
				height++;
				line = br.readLine();
			}
		}catch(Exception e){System.out.println("pas marche");}
		mapTile.setWidth(width);
		mapTile.setHeight(height);
		return list;
	}

}
