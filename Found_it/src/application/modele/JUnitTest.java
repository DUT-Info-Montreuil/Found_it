package application.modele;
import java.io.File;

import org.junit.*;

import application.vue.MapVue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class JUnitTest {

    @Before
    public void initializeEnvironment(){
        TileMap mapTile = new TileMap();
        Environment e = new Environment(mapTile);
        Player player = new Player(0, 6300, mapTile,0,300,2,e);
        e.addEnemy(new Zombie (400, 400, mapTile, 10, 40,e));
    }

    @Test
    public void gettingAttack(){
        TileMap mapTile = new TileMap();
        Environment e = new Environment(mapTile);
        Player p = new Player(0, 6300, mapTile,0,300,2,e);

        e.addEnemy(new Zombie (400, 400, mapTile, 10, 40,e));
        e.getListEnemiesProperty().get(0).giveDamage(p);
        Assert.assertEquals(290, p.getHp());
    }

    @Test
    public void attackingEnemies(){
        TileMap mapTile = new TileMap();
        Environment e = new Environment(mapTile);
        Player p = new Player(0, 6300, mapTile,30,300,2,e);

        e.addEnemy(new Zombie (400, 400, mapTile, 10, 40,e));
        p.sendDamage(e.getListEnemiesProperty().get(0));
        Assert.assertTrue(e.getListEnemiesProperty().get(0).isAlive());
        Assert.assertEquals(10, e.getListEnemiesProperty().get(0).getHp());
    }

    //
    @Test
    public void blockDisepear(){
        TileMap mapTile = new TileMap();
        Environment e = new Environment(mapTile);
        Player p = new Player(0, 0, mapTile,30,300,2,e);
        p.setTools(new Pelle(mapTile, 1, p));
        mapTile.replace(0, 0, 8);
        p.useTool(0, 0);
        Assert.assertTrue(mapTile.wasTransparent(mapTile.getMap().get(0)));
    }

    @Test
    public void isNotFalling(){
        TileMap mapTile = new TileMap();
        Environment e = new Environment(mapTile);
        Player p = new Player(0, 40, mapTile,30,300,2,e);
        Assert.assertFalse(p.isFallingBoolean());
    }

}
