package application.vue;



import application.modele.MainCharacter;
import application.modele.Player;
import application.modele.Slime;
import application.modele.Squeleton;
import application.modele.Wizard;
import application.modele.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CharacterVue {

	private Pane mapPane;
	private MainCharacter c;
	private ImageView character;
	private Timeline loopImage;
	private int[] nbImage;


	public CharacterVue(Pane mapPane, MainCharacter p) {
		this.mapPane = mapPane;
		c = p;
		initImageCharacter();
		addCharacterInMap();
		linkCharacter();
		loopImage();
	}
	
	public void initImageCharacter() {
		nbImage = new int[5];
		if (c instanceof Player) {
			character = new ImageView(new Image("application/vue/character/SwordsManAnim.png"));
			nbImage[0]=10; nbImage[1]=6; nbImage[2]=2; nbImage[3]=2; nbImage[4]=9;
		}
		else if (c instanceof Zombie) {
			character = new ImageView(new Image("application/vue/ennemies/ElDiabloAnimation.png"));
			nbImage[0]=6; nbImage[1]=5; nbImage[2]=1; nbImage[3]=1; nbImage[4]=4;
		}
		else if (c instanceof Slime) {
			character = new ImageView(new Image("application/vue/ennemies/SlimesAnimation.png"));
			nbImage[0]=9; nbImage[1]=9; nbImage[2]=9; nbImage[3]=9; nbImage[4]=9;
		}
		else if (c instanceof Squeleton) {
			character = new ImageView(new Image("application/vue/ennemies/HuntressAnimation.png"));
			nbImage[0]=10; nbImage[1]=8; nbImage[2]=2; nbImage[3]=2; nbImage[4]=10;
		}
		else if(c instanceof Wizard){
			character = new ImageView(new Image("application/vue/ennemies/WizardAnimation.png"));
			nbImage[0]=8; nbImage[1]=8; nbImage[2]=8; nbImage[3]=8; nbImage[4]=5;
		}
		character.setId(c.getId());
		character.setViewport(new Rectangle2D(0, 0, 32, 32));
	}

	private void addCharacterInMap() {
		mapPane.getChildren().add(character);
	}
	
	public ImageView getCharacter() {
		return character;
	}
	
	public int[] getNbImage() {
		return nbImage;
	}
	
	private void linkCharacter() {
		character.translateXProperty().bind(c.getXProperty());
		character.translateYProperty().bind(c.getYProperty());
		c.getDirectionnalXProperty().addListener((obs,old,nouv)-> {character.setScaleX(c.getDirectionnalX());});
	}

	public void nextImage(int ligne) {
		if (character.getViewport().getMinY() == ligne * 32) {
			if (isLastImage(ligne))
				character.setViewport(new Rectangle2D(0, character.getViewport().getMinY(), 32, 32));
			else
				character.setViewport(new Rectangle2D(character.getViewport().getMaxX(), character.getViewport().getMinY(), 32, 32));
		}
		else {
			character.setViewport(new Rectangle2D(0, ligne * 32, 32, 32));
		}
	}
	private boolean isLastImage(int ligne) {
		return character.getViewport().getMaxX() >= 32 * nbImage[ligne];
	}

	private void initLoopImage() {
		loopImage = new Timeline();
		loopImage.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.12),(ev ->{
			if (!c.isAlive()) {
				nextImage(4);
				if (isLastImage(4)) {
					mapPane.getChildren().remove(character);
					loopImage.stop();
				}
			}
			else if (c.isJumpingBoolean())
				nextImage(2);
			else if (c.isFallingBoolean())
				nextImage(3);
			else if ((c.getRightPressed() || c.getLeftPressed()) && !(c.getLeftPressed() && c.getRightPressed()))
				nextImage(1);
			else
				nextImage(0);
		}));
		loopImage.getKeyFrames().add(kf);
	}
	private void loopImage() {
		initLoopImage();
		loopImage.play();
	}

}
