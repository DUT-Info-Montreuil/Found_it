package application.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

public class Inventory {

    private ObservableList<Resources> inventory;

    public Inventory(){
        this.inventory = FXCollections.observableArrayList();
    }

    public void removeInInventory(Resources r){
        inventory.remove(r);
    }

    public void addInInventory(int id){
        if(!alreadyInInventory(id)){
            inventory.add(new Resources(id,this));
        }
        else{
            getResourcesId(id).addQuantity();
        }
    }

    public boolean alreadyInInventory(int id){
        int i = 0;
        while(i < inventory.size() && inventory.get(i).getId() != id ){
            i++;
        }
        return i < inventory.size();
    }

    public Resources getResourcesId(int id){
        int i = 0;
        while(i < inventory.size() && inventory.get(i).getId() != id){
            i++;
        }
        return inventory.get(i);

    }





    
}
