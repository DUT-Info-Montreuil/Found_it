package application.vue;

import application.modele.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InterfacePlayerVue {
    
    private Player player;
    private Pane mapPane;


    private final Image hpMaxPicture = new Image("C:\\Users\\Jonas\\IdeaProjects\\FOUND_IT\\Found_it\\Found_it\\src\\application\\vue\\healthBar\\healthBar100.png");
    private ImageView hp;
    private final Image hpSeventyFivePicture = new Image("C:\\Users\\Jonas\\IdeaProjects\\FOUND_IT\\Found_it\\Found_it\\src\\application\\vue\\healthBar\\healthBar75.png");
    private final Image hpFiftyPicture = new Image("C:\\Users\\Jonas\\IdeaProjects\\FOUND_IT\\Found_it\\Found_it\\src\\application\\vue\\healthBar\\healthBar50.png");
    private final Image hpTwentyFivePicture = new Image("C:\\Users\\Jonas\\IdeaProjects\\FOUND_IT\\Found_it\\Found_it\\src\\application\\vue\\healthBar\\healthBar25.png");
    private final Image hpTenPicture = new Image("C:\\Users\\Jonas\\IdeaProjects\\FOUND_IT\\Found_it\\Found_it\\src\\application\\vue\\healthBar\\healthBar10.png");
    private final Image hpZeroPicture = new Image("C:\\Users\\Jonas\\IdeaProjects\\FOUND_IT\\Found_it\\Found_it\\src\\application\\vue\\healthBar\\healthBar0.png");



    public InterfacePlayerVue(Player p, Pane pane) {
        player = p;
        mapPane = pane;
        hp = new ImageView(hpMaxPicture);
        addInterfaceHP();
    }

    public void addInterfaceHP() {
        mapPane.getChildren().add(hp);
        hp.setTranslateX(700);
        player.getHpProperty().addListener((obs,old,nouv)-> {
			if(player.getHp() <= 0){
                hp.setImage(hpZeroPicture);
            } else if (player.getHp() <= 10) {
                hp.setImage(hpTenPicture);
            } else if (player.getHp() <= 25) {
                hp.setImage(hpTwentyFivePicture);
            } else if (player.getHp() <= 50) {
                hp.setImage(hpFiftyPicture);
            } else if (player.getHp() <= 75) {
                hp.setImage(hpSeventyFivePicture);
            }

        });

    }

}
