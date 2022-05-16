package application.controller;

import application.modele.MainCharacter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class CharacterObs implements ChangeListener<Number> {

	private MainCharacter character;
	
	public CharacterObs(MainCharacter c) {
		character = c;
	}
	
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		character.gravity();

	}

}
