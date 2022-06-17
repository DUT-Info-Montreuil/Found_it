package application.vue;


import application.controller.Camera;
import application.modele.Pelle;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.weapons.Sword;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class InventoryVue {
    
    private TilePane inventory;
    private Camera cam;
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


    public InventoryVue(Pane pane, Player p, TileMap map, Camera cam) {
        inventory = new TilePane();
        player = p;
        this.map = map;
        this.cam = cam;
        inventoryIcon = new ImageView(inventoryPicture);
        woodPickaxe = new ImageView(woodPickaxePicture);
        woodAxe = new ImageView(woodAxePicture);
        woodSword = new ImageView(woodSwordPicture);
        addInventory(pane);
        linkInventory();
        Button but = new Button();
    }

    public void addInventory(Pane pane) {
        pane.getChildren().add(inventory);
        inventory.getChildren().add(inventoryIcon);
        inventory.getChildren().add(woodPickaxe);
        inventory.getChildren().add(woodAxe);
        inventory.getChildren().add(woodSword);
        inventory.setHgap(10);
    }

    public void linkInventory() {
        inventory.visibleProperty().bind(player.isInInventoryProperty());
        inventory.translateXProperty().bind(cam.translateXProperty());
        inventory.translateYProperty().bind(cam.translateYProperty());
        inventoryIcon.setOnMouseClicked(event -> {
            player.setTools(null);
        });
        woodPickaxe.setOnMouseClicked(event -> {
            player.setTools(new Pelle(map, 32, player));
        });
        woodSword.setOnMouseClicked(event -> {
            player.setTools(new Sword(map, 10, 32, player));
        });
        
    }

}
