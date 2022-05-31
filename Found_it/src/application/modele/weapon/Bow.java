package application.modele.weapon;

import application.modele.Arrow;
import application.modele.MainCharacter;
import application.modele.TileMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bow {
    
    private MainCharacter user;
    private TileMap mapTile;
    private ObservableList listArrow;

    public Bow(MainCharacter userCharacter, TileMap map) {

        user = userCharacter;
        mapTile = map;
        listArrow = FXCollections.observableArrayList();


    }

    public void shoot(MainCharacter target) {
        new Arrow(user, target, mapTile);
    }

}
