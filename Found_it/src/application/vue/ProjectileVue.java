package application.vue;

import application.modele.Arrow;
import application.modele.Fireball;
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
        linkProjectile();
    }

    public void initImageProjectile() {
      if (p instanceof Arrow) {
        projectile = new ImageView(new Image("application/vue/tilset/arrow.png"));
      }
      if (p instanceof Fireball) {
        projectile = new ImageView(new Image("application/vue/tilset/Fireball.png"));
      }
      projectile.setRotate(rotationProjectile());
	  }
    private void addCharacterInMap() {
      projectile.setId(p.getId());
		  mapPane.getChildren().add(projectile);
	  }

    private void linkProjectile() {
      projectile.translateXProperty().bind(p.getXProperty());
      projectile.translateYProperty().bind(p.getYProperty());
      p.getDyProperty().addListener((obs,old,nouv)-> {
        projectile.setRotate(rotationProjectile());
      });

	  }
    private int rotationProjectile() {
      int dx, dy;
      if (p.getDX() < 0)
        dx = -90;
      else
        dx = 90;
      if (p.getDY() < 0)
        dy = (-45) * dx/90;
      else if (p.getDY() > 0)
        dy = 45 * dx/90;
      else
        dy = 0;
      return dx+dy;
    }


}
