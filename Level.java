import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;


public class Level<e> extends Application {


    // The dimensions of the window
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int GRID_WIDTH = 12;
    private static final int GRID_HEIGHT = 7;
    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 64 * GRID_WIDTH;
    private static final int CANVAS_HEIGHT = 64 * GRID_HEIGHT;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 64;
    private static final int GRID_CELL_HEIGHT = 64;

    // The width of the grid in number of cells.

    private Canvas canvas;

    // Loaded images
    private static Image grass;
    private static Image path;
    private static Image tunnel;
    private static Image bomb;
    private static Image maleSexChange;
    private static Image femaleSexChange;
    private static Image deathrat;
    private static Image gas;
    private static Image noEntry;
    private static Image poison;
    private static Image sterilisation;
    private static int tickCount;


    private Timeline tickTimeline;

    private Stage primaryStage;
    private boolean pauseGame = false;
    private boolean levelCompleted = false;

    private static int sizeLevel, levelWidth, levelHeight, maxPopulation, ratPopulationRate, secExpected, time;
    private boolean completed;


    PlayerProfile player;
    private String saveGame;

    private static ArrayList<Rat> ratList = new ArrayList<Rat>();
    private static ArrayList<Item> itemList = new ArrayList<Item>();
    //this is a hardcoded level layout only here for testing purposes
    private static char[][] levelLayout = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'P', 'T', 'T', 'G'},
            {'G', 'P', 'G', 'G', 'T', 'G', 'P', 'G', 'T', 'G', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'P', 'P', 'T', 'T', 'G'},
            {'G', 'P', 'G', 'G', 'T', 'G', 'P', 'G', 'T', 'G', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}};


    public static int getGridHeight() {
        return GRID_HEIGHT;
    }

    public static int getGridWidth() {
        return GRID_WIDTH;
    }

    public static ArrayList<Rat> getRatList() {
        return ratList;
    }

    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    protected void generateLevel() {
    }

    private void loadLevel(String path) {
    }

    public static char[][] getLevelLayout() {
        return levelLayout;
    }


    public static int getLevelHeight() {
        return levelHeight;
    }

    public static int getGridCellHeight() {
        return GRID_CELL_HEIGHT;
    }

    public void setLevelHeight(int levelHeight) {
        this.levelHeight = levelHeight;
    }

    public static int getLevelWidth() {
        return levelWidth;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public int getRatPopulationRate() {
        return ratPopulationRate;
    }

    public int getSecExpected() {
        return secExpected;
    }

    public int getSizeLevel() {
        return sizeLevel;
    }

    public int getTime() {
        return time;
    }

    public int getGridCellWidth() {
        return GRID_CELL_WIDTH;
    }

    public void setLevelWidth(Integer levelWidth) {
        this.levelWidth = levelWidth;
    }

    public void setMaxPopulation(Integer maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public void setRatPopulationRate(Integer ratPopulationRate) {
        this.ratPopulationRate = ratPopulationRate;
    }

    public void setSecExpected(Integer secExpected) {
        this.secExpected = secExpected;
    }

    public void setSizeLevel(Integer sizeLevel) {
        this.sizeLevel = sizeLevel;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public boolean isCompleted() {
        return this.completed;
    }

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

        if (!pauseGame) {
            GraphicsContext gc = canvas.getGraphicsContext2D();

            // Clear canvas
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Set the background to gray.
            gc.setFill(Color.GRAY);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            // Draw row of dirt images
            // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
            for (int x = 0; x < GRID_HEIGHT; x++) {
                for (int y = 0; y < GRID_WIDTH; y++) {
                    if (levelLayout[x][y] == 'G') {
                        gc.drawImage(grass, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);
                    } else if (levelLayout[x][y] == 'T') {
                        gc.drawImage(tunnel, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);
                    } else {
                        gc.drawImage(path, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);
                    }
                }
            }

            for (Rat rat : ratList) {
                rat.draw(gc);
                /*gc.drawImage(rat.img, rat.getX() * GRID_CELL_WIDTH, rat.getY() * GRID_CELL_HEIGHT);*/
            }

            for (Item item : itemList) {
                item.draw(gc);
            }

        } else {
            pauseGame();
        }

    }


    public void canvasDragDroppedOccured(DragEvent event) {
        int x = Math.floorDiv((int) event.getX(), 64);
        int y = Math.floorDiv((int) event.getY(), 64);
        // Draw an icon at the dropped location.
        GraphicsContext gc = canvas.getGraphicsContext2D();

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
                gc.drawImage(deathrat, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);


            }
        }

    }

    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();
        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it.
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
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

            // Clear canvas
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Set the background to gray.
            gc.setFill(Color.GRAY);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                try {
                    // create a reader instance
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    //Scanner sc = new Scanner(file);

                    ArrayList<ArrayList<Character>> levelLayout = new ArrayList<>();
                    String line;

                    br.readLine(); //skip first line, uncomment if you load map with height/width
                    // read until end of file
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        ArrayList<Character> chars = new ArrayList<Character>();
                        for (char c : line.toCharArray()) {
                            chars.add(c);
                        }
                        levelLayout.add(chars);
                    }

                    //System.out.print(levelLayout);
                    System.out.print(levelLayout.get(0));

                    for (int x = 0; x < GRID_HEIGHT; x++) {
                        for (int y = 0; y < GRID_WIDTH; y++) {

                            if (levelLayout.get(x).get(y) == 'G') {
                                gc.drawImage(grass, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);

                            } else if (levelLayout.get(x).get(y) == 'T') {
                                gc.drawImage(tunnel, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);

                            } else {
                                gc.drawImage(path, y * GRID_CELL_WIDTH, x * GRID_CELL_HEIGHT);
                            }
                        }
                    }
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
        dragDeathRat.setImage(deathrat);


        toolbar.getChildren().add(dragBomb);
        toolbar.getChildren().add(dragMaleGender);
        toolbar.getChildren().add(dragFemaleGender);
        toolbar.getChildren().add(dragGas);
        toolbar.getChildren().add(dragPoison);
        toolbar.getChildren().add(dragSterilisation);
        toolbar.getChildren().add(dragDeathRat);
        toolbar.getChildren().add(dragNoEntry);

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


    @Override
    public void start(Stage primaryStage) throws Exception {
        tickCount = 0;
        for (int i = 0; i < 4; i++) {
            ratList.add(new PlayableRat());
        }

        for (Rat rat : ratList) {
            rat.setImageDirection();
        }
        /*Bomb test = new Bomb(1,1);
      itemList.add(test);*/

        /*  itemList.add(new MaleSexChange(1,2));*/

/*        ratRight = new Image("resources/Images/Rat/MRatRight.png");
        ratLeft = new Image("resources/Images/Rat/MRatLeft.png");
        ratUp = new Image("resources/Images/Rat/RatUp.png");
        ratDown = new Image("resources/Images/Rat/RatDown.png");*/


        grass = new Image("/resources/Images/Tiles/Grass.png");
        path = new Image("/resources/Images/Tiles/Path.png");
        tunnel = new Image("/resources/Images/Tiles/Tunnel.png");
        deathrat = new Image("/resources/Images/Rat/DeathRatR.png");
        bomb = new Image("/resources/Images/Items/Bomb.png");
        femaleSexChange = new Image("/resources/Images/Items/FemaleSexChange.png");
        maleSexChange = new Image("/resources/Images/Items/MaleSexChange.png");
        gas = new Image("/resources/Images/Items/Gas.png");
        noEntry = new Image("/resources/Images/Items/NoEntry.png");
        poison = new Image("/resources/Images/Items/Poison.png");
        sterilisation = new Image("/resources/Images/Items/Sterilisation.png");


        Pane root = buildGUI();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> processKeyEvent(event));
        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(250), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        // We start the timeline upon a button press.

        // Display the scene on the stage
        drawGame();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    public void tick() {
//        if (levelCompleted){
//            System.out.println("Game Won"); //not sure what we want to do when the game is won.
//        } else if (tickCount >= secExpected){
//            System.out.println("Game Lost");
//        }
        tickCount++;
        // Here we move the player right one cell and teleport
        // them back to the left side when they reach the right side.

        for (Rat rat: ratList) {
            if (tickCount % rat.getSpeed() == 0){
                rat.move();
            }
            rat.incrementTick();
        }
        int ratListLength = ratList.size();
        for (int i = 0; i <= ratListLength-1; i++) {
            ratList.get(i).setImageDirection();
            if (ratList.get(i) instanceof PlayableRat && ((PlayableRat) ratList.get(i)).getIsPregnant()){
                ((PlayableRat) ratList.get(i)).incrementTickPregnant();
                ((PlayableRat) ratList.get(i)).checkPregnancy();
            }
        }

        Iterator<Rat> iteratorRat = ratList.listIterator();
        while (iteratorRat.hasNext()) {
            Rat rat = iteratorRat.next();
            //the commented out bits was just something I was trying, Sam
//            if (tickCount % rat.getSpeed() == 0) {
//                rat.move();
//            }
//            rat.setImageDirection();
//            rat.incrementTick();
//            if (rat instanceof PlayableRat && ((PlayableRat) rat).getIsPregnant()) {
//                ((PlayableRat) rat).incrementTickPregnant();
//                ((PlayableRat) rat).checkPregnancy();
//            }

            rat.checkCollisions();
            if (rat.isDestroyed()) {
                iteratorRat.remove();
            }
        }

        Iterator<Rat> iteratorRat2 = ratList.listIterator();
        while (iteratorRat2.hasNext()) {
            Rat rat = iteratorRat2.next();
            if (rat.isDestroyed()) { //checks if item should be destroyed
                iteratorRat2.remove(); //destroys rat
            }
        }

        Iterator<Item> iteratorItem = itemList.listIterator();
        while (iteratorItem.hasNext()) {
            Item item = iteratorItem.next();
            item.update(); //updates the tickcount
            if (item.isDestroyed()) { //checks if item should be destroyed
                iteratorItem.remove(); //destroys item
            }
        }

//        for (Item item : itemList){
//            item.update(); //updates the tickcount
//            if (item.isDestroyed()) { //checks if item should be destroyed
//                itemList.remove(item); //destroys item
//            }
//        }


        // We then redraw the whole canvas.
        drawGame();
    }

    public void pauseGame() {
        Stage pauseStage = new Stage();

        BorderPane root = new BorderPane();
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_WIDTH);
        root.setCenter(canvas);

        HBox toolbar = new HBox();
        toolbar.setSpacing(100);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);


        Button continueBtn = new Button("Continue");
        toolbar.getChildren().add(continueBtn);

        Button saveGameBtn = new Button("Save");
        toolbar.getChildren().add(saveGameBtn);

        Button exitBtn = new Button("Exit");
        toolbar.getChildren().add(exitBtn);

        tickTimeline.stop();

        continueBtn.setOnAction(e -> {
            pauseGame = false;
            pauseStage.close();
            tickTimeline.play();

        });

        saveGameBtn.setOnAction(e -> {
            try {
                saveToFile();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        exitBtn.setOnAction(e -> {
            pauseStage.close();
            primaryStage.close();
        });
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        pauseStage.setScene(scene);
        pauseStage.show();

    }

    public void processKeyEvent(KeyEvent event) {

        // Do nothing for all other keys.
        if (event.getCode() == KeyCode.P) {
            pauseGame = true;
            System.out.println("work");
        }
        event.consume();
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter(saveGame);
            //writer.write(Tiles.toString());
            writer.write(player.toString());
            for (Rat rat : ratList) {
                writer.write(ratList.toString());
            }
            for (Item item : itemList) {
                writer.write(itemList.toString());
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
