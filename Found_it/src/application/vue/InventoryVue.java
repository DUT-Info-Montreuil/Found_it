package application.vue;


import application.modele.Pelle;
import application.modele.Player;
import application.modele.TileMap;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class InventoryVue {
    
    private TilePane mainInventory;
    private TilePane secondInventory;
    private Pane pane;
    private TileMap map;
    private Player player;
    private ImageView bareHand;
    private int numberOfBlocks;
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

    //Blocks :
    private ImageView sky; 
	private ImageView grass; 
	private ImageView river; 
	private ImageView dirt; 

    //Boss drop :
    private ImageView specialSword;

    private final Image bareHandPicture = new Image("application/vue/logoInventory/fist.png");
    //private final Image woodPicture = new Image("application/vue/resources/wood.png");
    private final Image woodPickaxePicture = new Image("application/vue/equipments/woodPickAxe.png");
    private final Image woodAxePicture = new Image("application/vue/equipments/woodAxe.png");
    private final Image woodSwordPicture = new Image("application/vue/weapons/woodSword.png");

    private final Image skyPicture = new Image("application/vue/tilset/sky.png");
    private final Image dirtPicture = new Image("application/vue/tilset/dirt.png");
    private final Image riverPicture = new Image("application/vue/tilset/grassfond.png");
    private final Image grassPicture = new Image("application/vue/tilset/grass.png");

    
    //private final Image ironPicture = new Image ("application/vue/resources/iron.png");
    //private final Image ironPickaxePicture = new Image("application/vue/equipments/ironPickaxe.png");
    //private final Image ironAxePicture = new Image("application/vue/equipments/ironAxe.png");
    //private final Image ironSwordPicture = new Image("");


    public InventoryVue(Pane pane, Player p, TileMap map) {
        mainInventory = new TilePane();
        secondInventory = new TilePane();
        player = p;
        this.map = map;
        bareHand = new ImageView(bareHandPicture);
        woodPickaxe = new ImageView(woodPickaxePicture);
        woodAxe = new ImageView(woodAxePicture);
        woodSword = new ImageView(woodSwordPicture);
        sky = new ImageView(skyPicture);
        river = new ImageView(riverPicture);
        grass = new ImageView(grassPicture);
        dirt = new ImageView(dirtPicture);
        createMainInventory(mainInventory,pane);
        createSecondInventory(secondInventory,pane);
        linkInventory();
    }

    public void createMainInventory(TilePane inv,Pane pane){
        pane.getChildren().add(inv);
        inv.setTranslateX(775);
        inv.setTranslateY(407);
        inv.setPrefColumns(9);
        inv.setPrefRows(4);
        for(int i = 0; i < 20; i++){
            Button inventoryCase = new Button();
            inventoryCase.setId("B"+i);
            inventoryCase.setPrefWidth(64);
            inventoryCase.setPrefHeight(64);
            inventoryCase.setStyle("-fx-base: #e0d9d8;");
            inv.getChildren().add(inventoryCase);
        }
        inv.setOrientation(Orientation.VERTICAL);
        inv.setHgap(5);
        inv.setVgap(5);
    }


    public void createSecondInventory(TilePane inv,Pane pane) {
        pane.getChildren().add(inv);
        inv.setHgap(10);
        inv.getChildren().add(bareHand);
        inv.getChildren().add(woodPickaxe);
        inv.getChildren().add(woodAxe);
        inv.getChildren().add(woodSword);
    }

 


    public void linkInventory() {
        mainInventory.visibleProperty().bind(player.isInInventoryProperty());
        //secondInventory.visibleProperty();
        bareHand.setOnMouseClicked(event -> {
            player.setTools(null);
        });
        woodPickaxe.setOnMouseClicked(event -> {
            player.setTools(new Pelle(map, 1, player));
        });
        
    }

}
