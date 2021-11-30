
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;

public class Level extends Application {


    // The dimensions of the window
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 500;

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 350;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    // The width of the grid in number of cells.
    private static final int GRID_WIDTH = 12;
    private static final int GRID_HEIGHT = 7;
    private Canvas canvas;

    // Loaded images
    private Image rat1;
    private Image grass;
    private Image path;
    private Image tunnel;
    private Image bomb;
    private Image maleSexChange;
    private Image femaleSexChange;
    private Image gas;
    private Image noEntry;
    private Image poison;
    private Image sterilisation;

    private Timeline tickTimeline;


    private Integer sizeLevel, levelWidth, levelHeight, maxPopulation, ratPopulationRate, secExpected, time;
    private boolean completed;
    //this is a hardcoded level layout only here for testing purposes
    private char[][] levelLayout = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}};
    Rat testRat =  new Rat();


    protected void generateLevel() {
    }

    private void loadLevel(String path) {
    }

    public char[][] getLevelLayout() {
        return levelLayout;
    }



    public Integer getLevelHeight() {
        return levelHeight;
    }

    public void setLevelHeight(Integer levelHeight) {
        this.levelHeight = levelHeight;
    }

    public Integer getLevelWidth() {
        return levelWidth;
    }

    public Integer getMaxPopulation() {
        return maxPopulation;
    }

    public Integer getRatPopulationRate() {
        return ratPopulationRate;
    }

    public Integer getSecExpected() {
        return secExpected;
    }

    public Integer getSizeLevel() {
        return sizeLevel;
    }

    public Integer getTime() {
        return time;
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


    /**
     * Draw the game on the canvas.
     */
    public void drawGame() {
        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the background to gray.
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw row of dirt images
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
        for (int y = 0; y < GRID_HEIGHT; y++){
            for(int x = 0; x < GRID_WIDTH; x++){
                if (levelLayout[y][x] == 'G') {
                    gc.drawImage(grass, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }else if (levelLayout[y][x] == 'T') {
                    gc.drawImage(tunnel, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }else {
                    gc.drawImage(path, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
            }
        }
        gc.drawImage(testRat.img, testRat.getX() * GRID_CELL_WIDTH, testRat.getY() * GRID_CELL_HEIGHT);
    }

    public void canvasDragDroppedOccured(DragEvent event) {
        int x = Math.floorDiv((int) event.getX(), 50);
        int y = Math.floorDiv((int) event.getY(), 50);
        // Draw an icon at the dropped location.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Draw the the image so the center is where we dropped.
        if (levelLayout[y][x] == 'P'){
            System.out.println(x * GRID_CELL_WIDTH);
            gc.drawImage(bomb, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
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

        // Tick Timeline buttons
        Button startTickTimelineButton = new Button("Start Ticks");
        Button stopTickTimelineButton = new Button("StopTicks");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton);
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


        // Setup a draggable image.
        ImageView draggableImage = new ImageView();
        draggableImage.setImage(bomb);
        toolbar.getChildren().add(draggableImage);

        // This code setup what happens when the dragging starts on the image.
        // You probably don't need to change this (unless you wish to do more advanced things).
        draggableImage.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                // Mark the drag as started.
                // We do not use the transfer mode (this can be used to indicate different forms
                // of drags operations, for example, moving files or copying files).
                Dragboard db = draggableImage.startDragAndDrop(TransferMode.ANY);

                // We have to put some content in the clipboard of the drag event.
                // We do not use this, but we could use it to store extra data if we wished.
                ClipboardContent content = new ClipboardContent();
                content.putString("Hello");
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
                if (event.getGestureSource() == draggableImage) {
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

        //Testing Tick's

        testRat.setX(0);
        testRat.setY(0);
        rat1 = new Image("resources/Images/Rat/Rat1.png");
        testRat.setImg(rat1);
        grass = new Image("/resources/Images/Tiles/Grass.png");
        path = new Image("/resources/Images/Tiles/Path.png");
        tunnel = new Image("/resources/Images/Tiles/Tunnel.png");
       /*
        bomb = new Image ("/resources/Images/Items/Bomb.png");
        maleSexChange= new Image("/resources/Images/Items/MaleSexChange.png");
        femaleSexChange =  new Image("/resources/Images/Items/FemaleSexChange.png");
        gas = new Image("/resources/Images/Items/Gas.png");
        noEntry = new Image("/resources/Images/Items/NoEntry.png");
        poison = new Image("/resources/Images/Items/Poison.png");
        sterilisation = new Image("/resources/Images/Items/Sterilisation.png");*/


        Pane root = buildGUI();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
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
        // Here we move the player right one cell and teleport
        // them back to the left side when they reach the right side.
       testRat.moveRight();
        if (testRat.getX() > 11) {
            testRat.setX(0);
        }
        // We then redraw the whole canvas.
        drawGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
