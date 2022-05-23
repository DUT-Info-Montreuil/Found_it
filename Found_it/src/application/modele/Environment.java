package application.modele;

import application.modele.algo.BFS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environment {
    
    private Player player;
    private ObservableList<Enemy> ennemies;
    private TileMap map;
    private BFS bfs;

    public Environment(TileMap mapTile , Player p) {
        player = p;
        ennemies = FXCollections.observableArrayList();
        map = mapTile;
        bfs = new BFS(player, map, 4);
    }
    public ObservableList<Enemy> getListEnemiesProperty() {
        return ennemies;
    }
    public void addEnemy(Enemy e) {
        ennemies.add(e);
        e.gravity();
        while(e.isFallingBoolean())
            e.gravity();
    }

    public void update() {
        bfs.launch();
        for (Enemy e : ennemies) {
            if (e.isAttacking() || bfs.isNear(e, e.getRangeForAttack()))
                e.goAttacking();
            if (e.isAttacking())
                e.attack(bfs);
            else 
                e.harmless();
            if (e.isJumpingBoolean())
                e.jump();
            if (e.leftPressedBoolean())
                e.moveLeft();
            if (e.rightPressedBoolean())
                e.moveRight();
            e.gravity();
        }
    }
    public void updatePlayer() {
        if (player.getLeftPressedBoolean())
			player.moveLeft();
		if (player.getRightPressedBoolean())
			player.moveRight();
		if (player.getUpPressedBoolean() || player.isJumpingBoolean())
			player.jump();
        player.gravity();
    }

}