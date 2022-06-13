package application.modele;


public class Magic {
    
    private MainCharacter user;
    private TileMap mapTile;
    private Environment environment;

    public Magic(MainCharacter userCharacter, TileMap map, Environment env) {
        user = userCharacter;
        mapTile = map;
        environment = env;
    }

    public void shoot(MainCharacter target) {
        environment.addProjectile(new Fireball(user, target, mapTile,environment));
    }

}