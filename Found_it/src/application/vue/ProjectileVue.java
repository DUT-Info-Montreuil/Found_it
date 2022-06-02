package application.vue;

import application.modele.Arrow;
import application.modele.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ProjectileVue {
    
    private Pane mapPane;
    private Projectile p;
    private ImageView projectile;

    public ProjectileVue(Projectile projectile, Pane map) {
        p = projectile;
        mapPane = map;
        initImageProjectile();
        addCharacterInMap();
        linkCharacter();
    }

    public void initImageProjectile() {
      if (p instanceof Arrow) {
        projectile = new ImageView(new Image("application/vue/tilset/arrow.png"));
      }
	  }
    private void addCharacterInMap() {
		  mapPane.getChildren().add(projectile);
	  }

    private void linkCharacter() {
      projectile.translateXProperty().bind(p.getXProperty());
      projectile.translateYProperty().bind(p.getYProperty());
	  }


}
