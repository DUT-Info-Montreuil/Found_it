package application.vue;


import application.modele.Pelle;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.weapons.Sword;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class InventoryVue {
    
    private TilePane inventory;
    private TileMap map;
    private Player player;
    private ImageView naked;
    private ImageView pickaxe;
    private ImageView sword;

    private final Image nakedPicture = new Image("application/vue/logo/fist.png");
    private final Image pickaxePicture = new Image("application/vue/tilset/pickaxe.png");
    private final Image swordPicture = new Image("");

    public InventoryVue(Pane pane, Player p, TileMap map) {
        inventory = new TilePane();
        player = p;
        this.map = map;
        naked = new ImageView(nakedPicture);
        pickaxe = new ImageView(pickaxePicture);
        addInventory(pane);
        linkInventory();
    }

    public void addInventory(Pane pane) {
        pane.getChildren().add(inventory);
        inventory.getChildren().add(naked);
        inventory.getChildren().add(pickaxe);
        inventory.setHgap(5);
    }

    public void linkInventory() {
        inventory.visibleProperty().bind(player.isInInventoryProperty());
        naked.setOnMouseClicked(event -> {
            player.setTools(null);
        });
        pickaxe.setOnMouseClicked(event -> {
            player.setTools(new Pelle(map, 1, player));
        });
        sword.setOnMouseClicked(event ->{
            player.setTools(new Sword(map, env, 1, player));
        })
        
    }

}
