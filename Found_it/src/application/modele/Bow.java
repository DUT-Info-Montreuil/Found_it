package application.modele;


public class Bow {
    
    private MainCharacter user;
    private TileMap mapTile;
    private Environment environment;

    public Bow(MainCharacter userCharacter, TileMap map, Environment env) {
        user = userCharacter;
        mapTile = map;
        environment = env;
    }

    public void shoot(MainCharacter target) {
        environment.addProjectile(new Arrow(user, target, mapTile));
    }

}
