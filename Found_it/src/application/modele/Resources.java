package application.modele;

import java.util.Observable;

import javax.lang.model.element.QualifiedNameable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class Resources {

    private int idblock;
    private IntegerProperty quantity;
    private Inventory inv;

    public Resources(int idblock, Inventory inv){
        this.idblock = idblock;
        this.inv = inv;
        this.quantity = new SimpleIntegerProperty(); 
    }

    public int getIdBlock(){
        return this.idblock;
    }

    public int getQuantityValue(){
        return this.quantity.getValue();
    }

    public IntegerProperty getQuantityProperty(){
        return quantity;
    }

    public void addResources(Resources r){
        addQuantity();
    }


    public void removeResources(Resources r){
        inv.removeInInventory(r);
    }

    public void addQuantity(){
        quantity.set(getQuantityValue()+ 1);
    }

    public void removeQuantity(){
        quantity.set(getQuantityValue()- 1);
        if (getQuantityValue() <= 0)
            removeResources(this);

    }


    
}
