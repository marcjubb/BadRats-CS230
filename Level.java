import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

/**
 * This class is responsible for running the GUI for the rats game and storing the relevant values to be called.
 *
 * @param <e> the type parameter
 * @author Samuel Griffin, Marc Jubb, Ryan Wake, Gonzalo Mandrión Flores, Aaron Davies,
 */
public class Level<e> extends Application {


    // The dimensions of the window

    private static  int GRID_WIDTH = 12;
    private static  int GRID_HEIGHT = 7;
    private static final int GRID_CELL_WIDTH = 64;
    private static final int GRID_CELL_HEIGHT = 64;
    private static final int CANVAS_WIDTH = GRID_CELL_WIDTH * GRID_WIDTH;
    private static final int CANVAS_HEIGHT = GRID_CELL_HEIGHT * GRID_HEIGHT;
    private static final int WINDOW_WIDTH = 400 + CANVAS_WIDTH;
    private static final int WINDOW_HEIGHT = 200 + CANVAS_HEIGHT;


    // The dimensions of the canvas


    // The width and height (in pixels) of each cell that makes up the game.


    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Item> currentInventory = new ArrayList<>();

    // The width of the grid in number of cells.

    private Canvas canvas;

    private Canvas canvasCounters;

    // Loaded images
    private static Image grass;
    private static Image path;
    private static Image tunnel;
    private static Image bomb;
    private static Image maleSexChange;
    private static Image femaleSexChange;
    private static Image deathRat;
    private static Image gas;
    private static Image noEntry;
    private static Image poison;
    private static Image sterilisation;

    private static int tickCount;
    private static int score = 0;


    private Timeline tickTimeline;

    private Stage primaryStage;
    private boolean levelCompleted = false;
    private int currentLevel;
    private boolean gameLost = false;
    private static int numOfMaleRats;
    private static int numOfFemaleRats;


    private static int maxPopulation = 4;

    BorderPane root;


    /**
     * The Player.
     */
    PlayerProfile player = new PlayerProfile("bob", 0);
    private String saveGame;
private Text nbOfRats = new Text("Number of Rats Alive: "+ Level.getNumOfFemaleRats() + Level.getNumOfMaleRats());
    //Arrays that store the objects on the game board.
    private static ArrayList<Rat> ratList = new ArrayList<>();
    private static ArrayList<Item> itemList = new ArrayList<>();

    //The quantity of rats by sex.


    //this is a hardcoded level layout only here for testing purposes
    private static Character[][] levelLayout = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'P', 'P', 'P', 'P', 'T', 'T', 'T', 'P', 'P', 'P', 'G'},
            {'G', 'P', 'G', 'G', 'P', 'G', 'G', 'G', 'P', 'G', 'P', 'G'},
            {'G', 'P', 'P', 'P', 'T', 'P', 'P', 'P', 'P', 'T', 'P', 'G'},
            {'G', 'P', 'G', 'G', 'T', 'G', 'P', 'G', 'P', 'G', 'P', 'G'},
            {'G', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}};


    /**
     * Gets num of male rats.
     *
     * @return the num of male rats
     */
//getters
    public static int getNumOfMaleRats() {
        return numOfMaleRats;
    }

    /**
     * Gets num of female rats.
     *
     * @return the num of female rats
     */
    public static int getNumOfFemaleRats() {
        return numOfFemaleRats;
    }

    /**
     * Gets grid height.
     *
     * @return the grid height
     */
    public static int getGridHeight() {
        return GRID_HEIGHT;
    }

    /**
     * Gets grid width.
     *
     * @return the grid width
     */
    public static int getGridWidth() {
        return GRID_WIDTH;
    }

    /**
     * Gets rat list.
     *
     * @return the rat list
     */
    public static ArrayList<Rat> getRatList() {
        return ratList;
    }

    /**
     * Gets item list.
     *
     * @return the item list
     */
    public static ArrayList<Item> getItemList() {
        return itemList;
    }


    /**
     * Get level layout character [ ] [ ].
     *
     * @return the character [ ] [ ]
     */

    public void getNumOfSex(){
        numOfMaleRats = 0;
        numOfFemaleRats = 0;
        for(Rat rat: Level.getRatList()){
            if((rat instanceof PlayableRat) && ((PlayableRat) rat).getSex() == PlayableRat.Sex.MALE ){
                numOfMaleRats++;
            }else if((rat instanceof PlayableRat) && ((PlayableRat) rat).getSex() == PlayableRat.Sex.FEMALE ){
                numOfFemaleRats++;
            }
        }
    }


    public static Character[][] getLevelLayout() {
        return levelLayout;
    }

    /**
     * Gets grid cell height.
     *
     * @return the grid cell height
     */
    public static int getGridCellHeight() {
        return GRID_CELL_HEIGHT;
    }

    /**
     * Gets rat list size.
     *
     * @return the rat list size
     */
    public static int getRatListSize() {
        return ratList.size();
    }

    /**
     * Gets playable rat list size.
     *
     * @return the playable rat list size
     */
    public static int getPlayableRatListSize() {

        return ratList.size();
    }

    /**
     * Compute num of sex.
     */
    public void computeNumOfSex(){
        for(Rat rat: Level.getRatList()){
            if((rat instanceof PlayableRat) && ((PlayableRat) rat).getSex() == PlayableRat.Sex.MALE ){
                System.out.println("hit");
                numOfMaleRats++;
            }else if((rat instanceof PlayableRat) && ((PlayableRat) rat).getSex() == PlayableRat.Sex.FEMALE ){
                numOfFemaleRats++;
            }
        }
    }

    /**
     * Creates a new baby object at the position of the mother when it is called and adds it to the rats arraylist.
     *
     * @param x The x-coordinate of the pregnant female rat.
     * @param y The y-coordinate of the pregnant female rat.
     */
    public static void giveBirth(int x, int y) {
        PlayableRat newBaby = new PlayableRat(x, y);
        newBaby.setImageDirection();
        ratList.add(newBaby);
    }

    /**
     * Draw the game on the canvas.
     */
    public void drawGame() {
        // Get the Graphic Context of the canvas. This is what we draw on.

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw row of dirt images
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.

        drawMap(gc, GRID_HEIGHT, GRID_WIDTH);

        for (Rat rat : ratList) {
            rat.draw(gc);
            /*gc.drawImage(rat.img, rat.getX() * GRID_CELL_WIDTH, rat.getY() * GRID_CELL_HEIGHT);*/
        }

        for (Item item : itemList) {
            item.draw(gc);
        }

    }

    public void drawCounters() {
        GraphicsContext gc = canvasCounters.getGraphicsContext2D();
        getNumOfSex();

        gc.clearRect(0, 0, canvasCounters.getWidth(), canvasCounters.getHeight());
        gc.fillText("Score: " + score, 80, 30);
        gc.fillText("Rats Remaining: " + getRatListSize(), 80, 50);
        gc.fillText("Males Remaining: " + numOfMaleRats, 80, 70);
        gc.fillText("Females Remaining: " + numOfFemaleRats, 80, 90);
    }



    /**
     * Canvas drag dropped occured.
     *
     * @param event the event
     */

    public void canvasDragDroppedOccured(DragEvent event) {
        int x = Math.floorDiv((int) event.getX(), 64);
        int y = Math.floorDiv((int) event.getY(), 64);
        // Draw an icon at the dropped location.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boolean itemAlreadyPlaced = false;
        for (Item item : itemList) {
            if (item.getX() == x && item.getY() == y){
                itemAlreadyPlaced = true;
            }
        }

        if (!itemAlreadyPlaced){
            if (levelLayout[y][x] == 'P') {
                if (Objects.equals(event.getDragboard().getString(), "Bomb")) {
                    itemList.add(itemList.size(), new Bomb(x, y));
                    gc.drawImage(bomb, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "MaleSexChange")) {
                    itemList.add(itemList.size(), new MaleSexChange(x, y));
                    gc.drawImage(maleSexChange, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "FemaleSexChange")) {
                    itemList.add(itemList.size(), new FemaleSexChange(x, y));
                    gc.drawImage(femaleSexChange, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "Gas")) {
                    itemList.add(itemList.size(), new Gas(x, y));
                    gc.drawImage(gas, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "Poison")) {
                    itemList.add(itemList.size(), new Poison(x, y));
                    gc.drawImage(poison, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "NoEntry")) {
                    itemList.add(itemList.size(), new NoEntrySign(x, y));
                    gc.drawImage(noEntry, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "Sterilisation")) {
                    itemList.add(itemList.size(), new Sterilisation(x, y));
                    gc.drawImage(sterilisation, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else {
                    itemList.add(itemList.size(), new DeathRatItem(x, y));
                    gc.drawImage(deathRat, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
            }
        }


    }


    private void addRandomItem() {
        Random r = new Random();
        int i = r.nextInt(items.size());
        currentInventory.add(items.get(i));
    }

    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI nodes.

        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it



        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        canvasCounters = new Canvas(CANVAS_WIDTH, 256);
        root.setBottom(canvasCounters);
        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);


        //Button to load the level
        Button btnLoadLevel = new Button("Load Level");
        //Button to save the layout running
        Button btnSaveLevel = new Button("Save Level");
        // Tick Timeline buttons
        Button startTickTimelineButton = new Button("Start Ticks");
        Button stopTickTimelineButton = new Button("StopTicks");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton, btnLoadLevel, btnSaveLevel);
        // Stop button is disabled by default
        stopTickTimelineButton.setDisable(true);




        // Setup the behaviour of the buttons.
        startTickTimelineButton.setOnAction(e -> {
            // Start the tick timeline and enable/disable buttons as appropriate.
            startTickTimelineButton.setDisable(true);
            tickTimeline.play();
            stopTickTimelineButton.setDisable(false);
        });

        stopTickTimelineButton.setOnAction(e -> {
            // Stop the tick timeline and enable/disable buttons as appropriate.
            stopTickTimelineButton.setDisable(true);
            tickTimeline.stop();
            startTickTimelineButton.setDisable(false);
        });

        btnLoadLevel.setOnAction(e -> {

            GraphicsContext gc = canvas.getGraphicsContext2D();
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                try {

                    // create a reader instance
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    //Collect size of the layout
                    Scanner sc = new Scanner(file);
                    String[] dataLevelFile = sc.nextLine().split(",");
                    int gridHeight = Integer.parseInt(dataLevelFile[1]);
                    int gridWidth = Integer.parseInt(dataLevelFile[0]);

                    //Collect the tiles variables
                    ArrayList<ArrayList<Character>> fileLevelLayout = new ArrayList<>();
                    String line;
                    br.readLine(); //skip first line
                    // read until end of file
                    while ((line = br.readLine()) != null) {

                        ArrayList<Character> chars = new ArrayList<Character>();
                        for (char c : line.toCharArray()) {
                            chars.add(c);
                        }
                        fileLevelLayout.add(chars);
                    }
                    levelLayout = fileLevelLayout.stream().map(u -> u.toArray(new Character[0])).toArray(Character[][]::new);

                    //Create a canvas after collecting the data from .txt
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //Clear canvas
                    gc.setFill(Color.GRAY); // Set the background to gray.
                    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                    drawMap(gc, gridHeight, gridWidth);
                    br.close();
                } catch (IOException ex) {
                    System.out.print("Error");
                }
            }
        });

        //Button to save the level layout in a file, just save the layout, should save more variables on it
        btnSaveLevel.setOnAction(e -> {
            Saver saveLevel = new Saver(); //Get save function from Saver class
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                String levelFormatted = Arrays.deepToString(levelLayout)
                        .replace(",", "")  //remove the commas
                        .replace("[", "")  //remove the right bracket
                        .replace("]", "\n") //remove the left bracket and lane break
                        .replace(" ", "");
                saveLevel.saveLevelFile(levelFormatted, file);
            }
        });

        // Setup a draggable image.
        ImageView dragBomb = new ImageView();
        ImageView dragMaleGender = new ImageView();
        ImageView dragFemaleGender = new ImageView();
        ImageView dragPoison = new ImageView();
        ImageView dragNoEntry = new ImageView();
        ImageView dragSterilisation = new ImageView();
        ImageView dragGas = new ImageView();
        ImageView dragDeathRat = new ImageView();

        dragMaleGender.setImage(maleSexChange);
        dragBomb.setImage(bomb);
        dragFemaleGender.setImage(femaleSexChange);
        dragPoison.setImage(poison);
        dragNoEntry.setImage(noEntry);
        dragSterilisation.setImage(sterilisation);
        dragGas.setImage(gas);
        dragDeathRat.setImage(deathRat);


        for (Item item : items) {
            if (item instanceof Bomb) {
                toolbar.getChildren().add(dragBomb);
            } else if (item instanceof MaleSexChange) {
                toolbar.getChildren().add(dragMaleGender);
            } else if (item instanceof FemaleSexChange) {
                toolbar.getChildren().add(dragFemaleGender);
            } else if (item instanceof Gas) {
                toolbar.getChildren().add(dragGas);
            } else if (item instanceof Poison) {
                toolbar.getChildren().add(dragPoison);
            } else if (item instanceof Sterilisation) {
                toolbar.getChildren().add(dragSterilisation);
            } else if (item instanceof DeathRatItem) {
                toolbar.getChildren().add(dragDeathRat);
            } else if (item instanceof NoEntrySign) {
                toolbar.getChildren().add(dragNoEntry);
            }
        }


//        toolbar.getChildren().add(dragBomb);
//        toolbar.getChildren().add(dragMaleGender);
//        toolbar.getChildren().add(dragFemaleGender);
//        toolbar.getChildren().add(dragGas);
//        toolbar.getChildren().add(dragPoison);
//        toolbar.getChildren().add(dragSterilisation);
//        toolbar.getChildren().add(dragDeathRat);
//        toolbar.getChildren().add(dragNoEntry);

        dragNoEntry.setOnDragDetected(event -> {

            Dragboard db = dragNoEntry.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("NoEntry");

            db.setContent(content);


            event.consume();
        });
        dragDeathRat.setOnDragDetected(event -> {

            Dragboard db = dragDeathRat.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("DeathRat");

            db.setContent(content);


            event.consume();
        });
        dragSterilisation.setOnDragDetected(event -> {

            Dragboard db = dragSterilisation.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("Sterilisation");

            db.setContent(content);


            event.consume();
        });
        dragPoison.setOnDragDetected(event -> {

            Dragboard db = dragPoison.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("Poison");

            db.setContent(content);


            event.consume();
        });
        dragGas.setOnDragDetected(event -> {

            Dragboard db = dragGas.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("Gas");

            db.setContent(content);


            event.consume();
        });
        dragFemaleGender.setOnDragDetected(event -> {

            Dragboard db = dragFemaleGender.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("FemaleSexChange");

            db.setContent(content);


            event.consume();
        });

        dragMaleGender.setOnDragDetected(event -> {

            Dragboard db = dragMaleGender.startDragAndDrop(TransferMode.ANY);


            ClipboardContent content = new ClipboardContent();
            content.putString("MaleSexChange");

            db.setContent(content);


            event.consume();
        });

        // This code setup what happens when the dragging starts on the image.
        // You probably don't need to change this (unless you wish to do more advanced things).
        dragBomb.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                // Mark the drag as started.
                // We do not use the transfer mode (this can be used to indicate different forms
                // of drags operations, for example, moving files or copying files).
                Dragboard db = dragBomb.startDragAndDrop(TransferMode.ANY);

                // We have to put some content in the clipboard of the drag event.
                // We do not use this, but we could use it to store extra data if we wished.
                ClipboardContent content = new ClipboardContent();
                content.putString("Bomb");
                db.setContent(content);

                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });

        // This code allows the canvas to receive a dragged object within its bounds.
        // You probably don't need to change this (unless you wish to do more advanced things).
        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // Mark the drag as acceptable if the source was the draggable image.
                // (for example, we don't want to allow the user to drag things or files into our application)
                if (event.getGestureSource() == dragBomb || event.getGestureSource() == dragMaleGender || event.getGestureSource() == dragFemaleGender
                        || event.getGestureSource() == dragDeathRat || event.getGestureSource() == dragPoison || event.getGestureSource() == dragNoEntry
                        || event.getGestureSource() == dragGas || event.getGestureSource() == dragSterilisation) {
                    // Mark the drag event as acceptable by the canvas.
                    event.acceptTransferModes(TransferMode.ANY);
                    // Consume the event. This means we mark it as dealt with.
                    event.consume();
                }
            }
        });

        // This code allows the canvas to react to a dragged object when it is finally dropped.
        // You probably don't need to change this (unless you wish to do more advanced things).
        canvas.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // We call this method which is where the bulk of the behaviour takes place.
                canvasDragDroppedOccured(event);

                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });
        return root;
    }

    private void drawMap(GraphicsContext gc, int gridHeight, int gridWidth) {
        for (int x = 0; x < gridHeight; x++) {
            for (int y = 0; y < gridWidth; y++) {
                if (levelLayout[x][y] == 'G') {
                    gc.drawImage(grass, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);

                } else if (levelLayout[x][y] == 'T') {
                    gc.drawImage(tunnel, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);

                } else {
                    gc.drawImage(path, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);
                }
            }
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bad Rats");

        tickCount = 0;
        for (int i = 0; i < 4; i++) {
            ratList.add(new PlayableRat());
        }
        computeNumOfSex();
        for (Rat rat : ratList) {
            rat.setImageDirection();
        }


        grass = new Image("/resources/Images/Tiles/Grass.png");
        path = new Image("/resources/Images/Tiles/Path.png");
        tunnel = new Image("/resources/Images/Tiles/Tunnel.png");
        deathRat = new Image("/resources/Images/Rat/DeathRatR.png");
        bomb = new Image("/resources/Images/Items/Bomb.png");
        femaleSexChange = new Image("/resources/Images/Items/FemaleSexChange.png");
        maleSexChange = new Image("/resources/Images/Items/MaleSexChange.png");
        gas = new Image("/resources/Images/Items/Gas.png");
        noEntry = new Image("/resources/Images/Items/NoEntry.png");
        poison = new Image("/resources/Images/Items/Poison.png");
        sterilisation = new Image("/resources/Images/Items/Sterilisation.png");

        items.add(new Bomb(5, 5));
        items.add(new MaleSexChange(0, 0));
        items.add(new FemaleSexChange(0, 0));
        items.add(new Gas(0, 0));
        items.add(new Poison(0, 0));
        items.add(new Sterilisation(0, 0));
        items.add(new DeathRatItem(0, 0));
        items.add(new NoEntrySign(0, 0));
        addRandomItem();

        Pane root = buildGUI();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(400), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        // We start the timeline upon a button press.

        //L oad menu
        //drawMenu();
        drawGame();
        primaryStage.setScene(scene);
        primaryStage.show();
        //drawGame moved to Play

    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    public void tick() {



        if(!levelCompleted && !gameLost) {
            if (tickCount % 10 == 0) {
                addRandomItem();
            }
//
            tickCount++;
            // Here we move the player right one cell and teleport
            // them back to the left side when they reach the right side.

            for (Rat rat : ratList) {
                rat.checkCollisions();
                if (tickCount % rat.getSpeed() == 0) {
                    rat.move();
                }
                rat.incrementTick();
            }

            int ratListLength = ratList.size();
            for (int i = 0; i <= ratListLength - 1; i++) {
                ratList.get(i).setImageDirection();
                if (ratList.get(i) instanceof PlayableRat && ((PlayableRat) ratList.get(i)).getIsPregnant()) {
                    ((PlayableRat) ratList.get(i)).incrementTickPregnant();
                    ((PlayableRat) ratList.get(i)).checkPregnancy();
                }
            }

            Iterator<Rat> iteratorRat = ratList.listIterator();
            while (iteratorRat.hasNext()) {
                Rat rat = iteratorRat.next();
                if (rat.isDestroyed()) {
                    iteratorRat.remove();
                    if (rat instanceof PlayableRat) {
                        if (((PlayableRat) rat).getIsPregnant()) {
                            score += 20;
                        } else {
                            score += 10;
                        }

                    }

                }
            }



                //checks if item should be destroyed
                //destroys rat
                ratList.removeIf(VisibleObject::isDestroyed);

            Iterator<Item> iteratorItem = itemList.listIterator();
            while (iteratorItem.hasNext()) {
                Item item = iteratorItem.next();
                item.update(); //updates the tickcount
                if (item.isDestroyed()) { //checks if item should be destroyed
                    iteratorItem.remove(); //destroys item
                }

            }
            // We then redraw the whole canvas.
            drawGame();
            drawCounters();
        }


        System.out.println("count" + Level.getNumOfMaleRats());
        nbOfRats = new Text("Number of Rats Alive: "+ Level.getNumOfMaleRats() + Level.getNumOfMaleRats());
        // We then redraw the whole canvas.
        drawGame();

    }
    private void levelEndScreen() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < getGridWidth(); i++) {
            for (int j = 0; j < getGridHeight(); j++) {
                if (levelCompleted) {
                    gc.drawImage(new Image("/resources/Images/WHITE.png"), (i * GRID_CELL_WIDTH), (j * GRID_CELL_HEIGHT));
                } else if (gameLost) {
                    gc.drawImage(new Image("/resources/Images/RED.png"), (i * GRID_CELL_WIDTH), (j * GRID_CELL_HEIGHT));
                }
            }
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font(25));
        if (levelCompleted) {
            gc.fillText("Congratulations you win!", Math.round(canvas.getWidth() / 2), Math.round(canvas.getHeight() / 2));
        } else if (gameLost) {
            gc.fillText("Congratulations you Suck!", Math.round(canvas.getWidth() / 2), Math.round(canvas.getHeight() / 2));
        }


    /**
     * Game status.
     */

    }

    public void gameStatus() {
        int numberOfDeathRatItems = 0;
        for (Item item : itemList) {
            if (item instanceof DeathRatItem) {
                numberOfDeathRatItems++;
            }
        }
        int totalNumOfRats = ratList.size() + numberOfDeathRatItems;
        if (totalNumOfRats <= 0) {
            levelCompleted = true;
            tickTimeline.stop();
            levelEndScreen();
            if (player.getMaxLevelCompleted() < this.currentLevel) {
                player.setMaxLevelCompleted(currentLevel);
            }
        } else if (totalNumOfRats > maxPopulation) {
            gameLost = true;
            levelEndScreen();
            tickTimeline.stop();

        }
    }


//    //Menu
//    public void drawMenu() {
//    }
//
//    private void Play() {
//        //Delete menu
//        drawGame();
//        primaryStage.setScene(scene);
//        //primaryStage.show();
//    }

    //private void LevelSelect()
    //private String Load()

    /**
     * Temp save.
     *
     * @throws IOException the io exception
     */
    public void tempSave() throws IOException {
        //Data persistence section, call in tick, dont get how were saving file.
        Saver saver = new Saver();
        String levelFormatted = Arrays.deepToString(levelLayout)
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "\n") //remove the left bracket and lane break
                .replace(" ", "");
        saver.saveLevelFile(levelFormatted, new File("/resources/LevelFiles/tempFile.txt"));

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
