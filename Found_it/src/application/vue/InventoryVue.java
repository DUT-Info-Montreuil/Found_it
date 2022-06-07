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
    private ImageView inventoryIcon;
    //Wood :
    private ImageView wood;
    private ImageView woodPickaxe;
    private ImageView woodAxe;
    private ImageView woodSword;
    //Iron :
    private ImageView ironPickaxe;
    private ImageView ironAxe;
    private ImageView ironSword;
    private ImageView iron;

    //Boss drop :
    private ImageView specialSword;

    private final Image inventoryPicture = new Image("application/vue/logoInventory/InventoryIcon.png");
    private final Image woodPickaxePicture = new Image("application/vue/equipments/woodPickAxe.png");
    private final Image woodAxePicture = new Image("application/vue/equipments/woodAxe.png");
    private final Image woodSwordPicture = new Image("application/vue/weapons/woodSword.png");


    public InventoryVue(Pane pane, Player p, TileMap map) {
        inventory = new TilePane();
        player = p;
        this.map = map;
        inventoryIcon = new ImageView(inventoryPicture);
        woodPickaxe = new ImageView(woodPickaxePicture);
        woodAxe = new ImageView(woodAxePicture);
        woodSword = new ImageView(woodSwordPicture);
        addInventory(pane);
        linkInventory();
    }

    public void addInventory(Pane pane) {
        pane.getChildren().add(inventory);
        inventory.getChildren().add(inventoryIcon);
        inventory.setHgap(10);
        inventory.getChildren().add(woodPickaxe);
        inventory.setHgap(10);
        inventory.getChildren().add(woodAxe);
        inventory.setHgap(10);
        inventory.getChildren().add(woodSword);
    }

    public void linkInventory() {
        inventory.visibleProperty().bind(player.isInInventoryProperty());
        inventoryIcon.setOnMouseClicked(event -> {
            player.setTools(null);
        });
        woodPickaxe.setOnMouseClicked(event -> {
            player.setTools(new Pelle(map, 32, player));
        });
        woodSword.setOnMouseClicked(event -> {
            player.setTools(new Sword(map, 40, 32, player));
        });
        
    }

}
