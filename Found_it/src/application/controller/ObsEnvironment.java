package application.controller;

import application.modele.Enemy;
import application.vue.CharacterVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObsEnvironment implements ListChangeListener<Enemy> {


    private Pane mapPane;

    public ObsEnvironment(Pane mapPane) {
        this.mapPane = mapPane;
    }

    @Override
    public void onChanged(Change<?extends Enemy> c) {
        while (c.next()) {
            for (Enemy e : c.getAddedSubList())
                new CharacterVue(mapPane,e);   
        }
    }
    

}
