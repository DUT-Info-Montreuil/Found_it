package application.controller;

import application.modele.Resources;
import application.vue.InventoryVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObsInventory implements ListChangeListener<Resources> {


    private InventoryVue inventoryVue;

    public ObsInventory(InventoryVue inventoryVue) {
        this.inventoryVue = inventoryVue;
    }

    @Override
    public void onChanged(Change<?extends Resources> c) {
        while (c.next()) {
            for (Resources r : c.getAddedSubList()){
                inventoryVue.addInInventoryVue( r.getIdBlock());
            } 
            for (Resources r : c.getRemoved()){
                inventoryVue.removeInInventoryVue(r.getIdBlock());;
            }   
        }
    }

}
