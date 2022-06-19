package application.vue;


import application.modele.ConstructionPlan;
import application.modele.Inventory;
import application.modele.Pelle;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.TypeTuiles;
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


    public InventoryVue(Pane pane, Player p, TileMap map, Inventory inv) {
        mainInventory = new TilePane();
        secondInventory = new TilePane();
        player = p;
        this.map = map;
        this.pane = pane;
        this.inv = inv;
        bareHand = new ImageView(bareHandPicture);
        woodPickaxe = new ImageView(woodPickaxePicture);
        woodSword = new ImageView(woodSwordPicture);
        sky = new ImageView(skyPicture);
        river = new ImageView(riverPicture);
        grass = new ImageView(grassPicture);
        dirt = new ImageView(dirtPicture);
        createMainInventory();
        createSecondInventory(secondInventory,pane);
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


    public void createSecondInventory(TilePane inv,Pane pane) {
        pane.getChildren().add(inv);
        inv.setHgap(10);
        inv.getChildren().add(bareHand);
        inv.getChildren().add(woodPickaxe);
        inv.getChildren().add(woodSword);
    }

 


    public void linkInventory() {
        mainInventory.visibleProperty().bind(player.isInInventoryProperty());
        bareHand.setOnMouseClicked(event -> {
            player.setTools(null);
        });
        woodPickaxe.setOnMouseClicked(event -> {
            player.setTools(new Pelle(map, 1, player));
        });
        
    }

}
