
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private Image grass;
    private Image path;
    private Image tunnel;


    private Integer sizeLevel, levelWidth, levelHeight, maxPopulation, ratPopulationRate, secExpected, time;
    private boolean completed;
    private HashMap<Integer, Integer> ratsY, ratsX, itemsX, itemsY = new HashMap<Integer, Integer>();
    //this is a hardcoded level layout only here for testing purposes
    private char[][] levelLayout = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'P', 'P', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'T', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}};


//    public Level(int width, int height) {
//        this.levelWidth = width;
//        this.levelHeight = height;
//        generateLevel();
//    }

//    public Level(String path) {
//        loadLevel(path);
//    }

    protected void generateLevel() {
    }

    private void loadLevel(String path) {
    }

    public char[][] getLevelLayout() {
        return levelLayout;
    }

    public HashMap<Integer, Integer> getItemsX() {
        return itemsX;
    }

    public HashMap<Integer, Integer> getItemsY() {
        return itemsY;
    }

    public HashMap<Integer, Integer> getRatsX() {
        return ratsX;
    }

    public HashMap<Integer, Integer> getRatsY() {
        return ratsY;
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

    public void setItemsX(HashMap<Integer, Integer> itemsX) {
        this.itemsX = itemsX;
    }

    public void setItemsY(HashMap<Integer, Integer> itemsY) {
        this.itemsY = itemsY;
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

    public void setRatsX(HashMap<Integer, Integer> ratsX) {
        this.ratsX = ratsX;
    }

    public void setRatsY(HashMap<Integer, Integer> ratsY) {
        this.ratsY = ratsY;
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

    public void canvasDragDroppedOccured(DragEvent event) {
        double x = event.getX();
        double y = event.getY();

        // Print a string showing the location.
        String s = String.format("You dropped at (%f, %f) relative to the canvas.", x, y);
        System.out.println(s);

        // Draw an icon at the dropped location.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Draw the the image so the top-left corner is where we dropped.
        //gc.drawImage(iconImage, x, y);
        // Draw the the image so the center is where we dropped.
        //gc.drawImage(iconImage, x - iconImage.getWidth() / 2.0, y - iconImage.getHeight() / 2.0);
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
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (levelLayout[y][x] == 'G') {
                    gc.drawImage(grass, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (levelLayout[y][x] == 'T') {
                    gc.drawImage(tunnel, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else {
                    gc.drawImage(path, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
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

        // Setup a draggable image.
        ImageView draggableImage = new ImageView();
        draggableImage.setImage(new Image("/resources/Bomb.png"));
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
        grass = new Image("/resources/Images/Tiles/Grass.png");
        path = new Image("/resources/Images/Tiles/Path.png");
        tunnel = new Image("/resources/Images/Tiles/Tunnel.png");

        Pane root = buildGUI();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        drawGame();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
