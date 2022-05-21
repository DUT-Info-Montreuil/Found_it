package application.controller;

import application.vue.MapVue;
import javafx.collections.ListChangeListener;

public class MapManager implements ListChangeListener<Integer>{

    private MapVue viewMap;

    public MapManager(MapVue view) {
        viewMap = view;
    }

    @Override
    public void onChanged(Change<? extends Integer> c) {
        while (c.next()) {
            viewMap.reloadTile(c.getFrom());
        }
    }

}
