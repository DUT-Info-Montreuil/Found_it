package application.vue;


import application.controller.Camera;
import application.modele.ConstructionPlan;
import application.modele.Inventory;
import application.modele.Pelle;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.TypeTuiles;
import application.modele.weapons.Sword;
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
    private Inventory inv;
    private Camera cam;
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


    private final Image bareHandPicture = new Image("application/vue/logoInventory/fist.png");
    private final Image woodPickaxePicture = new Image("application/vue/equipments/woodPickAxe.png");
    private final Image woodSwordPicture = new Image("application/vue/weapons/woodSword.png");

    private Image skyPicture = new Image("application/vue/tilset/sky.png");
	private Image grassPicture = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image riverPicture = new Image("application/vue/tilset/blocks/grass_side.png");
	private Image dirtPicture = new Image("application/vue/tilset/blocks/dirt.png");
	private Image bedRockPicture = new Image("application/vue/tilset/blocks/coal_block.png");

    
    //private final Image ironPicture = new Image ("application/vue/resources/iron.png");
    //private final Image ironPickaxePicture = new Image("application/vue/equipments/ironPickaxe.png");
    //private final Image ironAxePicture = new Image("application/vue/equipments/ironAxe.png");
    //private final Image ironSwordPicture = new Image("");


    public InventoryVue(Pane pane, Player p, TileMap map, Inventory inv, Camera cam) {
        mainInventory = new TilePane();
        secondInventory = new TilePane();
        player = p;
        this.map = map;
        this.pane = pane;
        this.inv = inv;
        this.cam = cam;
        bareHand = new ImageView(bareHandPicture);
        woodPickaxe = new ImageView(woodPickaxePicture);
        woodSword = new ImageView(woodSwordPicture);
        sky = new ImageView(skyPicture);
        river = new ImageView(riverPicture);
        grass = new ImageView(grassPicture);
        dirt = new ImageView(dirtPicture);
        createMainInventory();
        createSecondInventory();
        linkInventory();
    }

    public void createMainInventory(){
        pane.getChildren().add(mainInventory);
        mainInventory.setTranslateX(775);
        mainInventory.setTranslateY(407);
        mainInventory.setPrefColumns(inv.getWidthInventory());
        mainInventory.setPrefRows(inv.getHeightInventory());
        mainInventoryResources();
        mainInventory.setOrientation(Orientation.VERTICAL);
        mainInventory.setHgap(5);
        mainInventory.setVgap(5);
    }

    public void addInInventoryVue(int block){
        Button inventoryCase = new Button();
        setGraphicButton(inventoryCase, block);
        inventoryCase.setOnMouseClicked(event -> {
            player.setTools(new ConstructionPlan(map, 2, player, block));
        });
        inv.getResourcesId(block).getQuantityProperty().addListener((obs,old,nouv)-> {
            inventoryCase.setText(String.valueOf(inv.getResourcesId(block).getQuantityValue()));
        });
        inventoryCase.setId("B" + block);
        mainInventory.getChildren().set(getIndiceFirstForInventory(),inventoryCase);
    }

    public int getIndiceFirstForInventory() {
        int i = 0;
        while (mainInventory.getChildren().get(i).getId() != null)
            i++;
        return i;
    }

    public void removeInInventoryVue(int block) {
        Button inventoryCase = new Button();
        setGraphicButton(inventoryCase, 0);
        mainInventory.getChildren().remove(mainInventory.lookup("#B" + block));
        mainInventory.getChildren().add(inventoryCase);
    }

    public void setGraphicButton(Button but, int block) {
        but.setPrefWidth(64);
        but.setPrefHeight(64);
        but.setStyle("-fx-base: #e0d9d8;");
        but.setFocusTraversable(false);
        if (block == TypeTuiles.sky.getCodeTuile())
            but.setGraphic(sky);
        else if (block == TypeTuiles.dirt.getCodeTuile())
            but.setGraphic(dirt);
        else if (block == TypeTuiles.grass.getCodeTuile())
            but.setGraphic(grass);
    }


    public void mainInventoryResources(){
        for(int i = 0; i < inv.getWidthInventory()*inv.getHeightInventory(); i++){
            Button inventoryCase = new Button();
            setGraphicButton(inventoryCase, 0);
            mainInventory.getChildren().add(inventoryCase);
        }
    }


    public void createSecondInventory() {
        pane.getChildren().add(secondInventory);
        secondInventory.setHgap(10);
        secondInventory.getChildren().add(bareHand);
        secondInventory.getChildren().add(woodPickaxe);
        secondInventory.getChildren().add(woodSword);
    }

 


    public void linkInventory() {
        mainInventory.visibleProperty().bind(player.isInInventoryProperty());
        mainInventory.layoutXProperty().bind(cam.translateXProperty());
        mainInventory.layoutYProperty().bind(cam.translateYProperty());
        secondInventory.translateXProperty().bind(cam.translateXProperty());
        secondInventory.translateYProperty().bind(cam.translateYProperty());
        bareHand.setOnMouseClicked(event -> {
            player.setTools(null);
        });
        woodPickaxe.setOnMouseClicked(event -> {
            player.setTools(new Pelle(map, 1, player));
        });
        woodSword.setOnMouseClicked(event -> {
            player.setTools(new Sword(map, 10, 32, player));
        });
        
    }

}