package application.modele;

import java.util.ArrayList;

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
        bfs = new BFS(player, map, 4,15);
    }
    public ObservableList<Enemy> getListEnemiesProperty() {
        return ennemies;
    }
    public ObservableList<Projectile> getListProjectileProperty() {
        return listProjectile;
    }
    public void removePlayer() {
        player = null;
    }
    public void addEnemy(Enemy e) {
        ennemies.add(e);
        e.gravity();
        while(e.isFallingBoolean())
            e.gravity();
    }
    public void removeEnnemie(Enemy e) {
        ennemies.remove(e);
    }

    public void recalculateBFS() {
        bfs.launch();
    }

    public boolean IsOnBFSzero(int x, int y) {
        return bfs.isOnZero(x, y);
    }


    public void update() {
        player.update();
        for (int j = ennemies.size() - 1 ; j >= 0 ; j--)
            ennemies.get(j).update(player, bfs);
        for (int i = listProjectile.size() - 1 ; i >= 0 ; i--)
            listProjectile.get(i).update();
    }
    

    public void addProjectile(Projectile projectile) {
        listProjectile.add(projectile);
    }

    public void removedProjectile(Projectile projectile) {
        listProjectile.remove(projectile);
    }

    public ArrayList<Enemy> isNearPlayer(int range) {
        ArrayList<Enemy> listEnnemiesProximity = new ArrayList<Enemy>();
        for (int i = 0 ; i < ennemies.size() ; i++)
            if ((Math.abs(player.getX() - ennemies.get(i).getX()) <= range || 
            Math.abs(player.getXRight() - ennemies.get(i).getX()) <= range) && 
            (Math.abs(player.getY() - ennemies.get(i).getY()) <= range || 
            Math.abs(player.getYBOT() - ennemies.get(i).getY()) <= range))
                listEnnemiesProximity.add(ennemies.get(i));
        return listEnnemiesProximity;
    }
 
}
