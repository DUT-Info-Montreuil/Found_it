package application.controller;

import application.modele.Arrow;
import application.modele.Projectile;
import application.vue.ProjectileVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObsProjectile implements ListChangeListener<Projectile>{

    private Pane mapPane;

    public ObsProjectile(Pane mapPane) {
        this.mapPane = mapPane;
    }

    @Override
    public void onChanged(Change<?extends Projectile> c) {
        while (c.next()) {
            for (Projectile p : c.getAddedSubList())
                new ProjectileVue(p,mapPane);
            for (Projectile p : c.getRemoved())
                mapPane.getChildren().remove(mapPane.lookup("#" + p.getId()));

        }
    }
}
