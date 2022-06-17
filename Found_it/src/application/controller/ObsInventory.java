package application.controller;

import application.modele.Enemy;
import application.modele.Player;
import application.modele.Resources;
import application.vue.CharacterVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObsInventory implements ListChangeListener<Resources> {


    private Pane mapPane;
    private Player player;

    public ObsInventory(Pane mapPane, Player p) {
        this.mapPane = mapPane;
        this.player = p;
    }

    @Override
    public void onChanged(Change<?extends Resources> c) {
        while (c.next()) {
            for (Resources r : c.getAddedSubList()){
                
            } 
                
        }
    }

}