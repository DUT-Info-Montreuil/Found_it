package application.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

public class Inventory {

    private ObservableList<Resources> inventory;
    private int widthInventory = 9;
    private int heightInventory = 4;

    public Inventory(){
        this.inventory = FXCollections.observableArrayList();
    }

    public ObservableList<Resources> getInventoryProperty() {
        return inventory;
    }

    public void removeInInventory(Resources r){
        inventory.remove(r);
    }

    public int getWidthInventory() {
        return widthInventory;
    }
    public int getHeightInventory() {
        return heightInventory;
    }

    public boolean inventoryIsFull() {
        return inventory.size() == heightInventory*widthInventory;
    }
    public void addInInventory(int id){
        if (!inventoryIsFull()) {
            if(!alreadyInInventory(id)){
                inventory.add(new Resources(id,this));
            }
            getResourcesId(id).addQuantity();
        }
    }

    public boolean alreadyInInventory(int id){
        int i = 0;
        while(i < inventory.size() && inventory.get(i).getIdBlock() != id ){
            i++;
        }
        return i < inventory.size();
    }

    public Resources getResourcesId(int id){
        int i = 0;
        while(i < inventory.size() && inventory.get(i).getIdBlock() != id){
            i++;
        }
        return inventory.get(i);

    }





    
}
