package application.modele;

import application.modele.algo.BFS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environment {
    
    private Player player;
    private ObservableList<Enemy> ennemies;
    private ObservableList<Projectile> listProjectile;
    private TileMap map;
    private BFS bfs;

    public Environment(TileMap mapTile) {
        ennemies = FXCollections.observableArrayList();
        listProjectile = FXCollections.observableArrayList();
        map = mapTile;
    }
    public void setPlayer(Player p) {
        player = p;
        bfs = new BFS(player, map, 4);
    }
    public ObservableList<Enemy> getListEnemiesProperty() {
        return ennemies;
    }
    public ObservableList<Projectile> getListProjectileProperty() {
        return listProjectile;
    }
    public void addEnemy(Enemy e) {
        ennemies.add(e);
        e.gravity();
        while(e.isFallingBoolean())
            e.gravity();
    }

    public void update() {
        player.update();
        bfs.launch();
        for (Enemy e : ennemies)
            e.update(player, bfs);
        for (int i = listProjectile.size() - 1 ; i >= 0 ; i--)
            listProjectile.get(i).update();
    }
    

    public void addProjectile(Projectile projectile) {
        listProjectile.add(projectile);
    }

    public void removedProjectile(Projectile projectile) {
        listProjectile.remove(projectile);
    }
 
}
