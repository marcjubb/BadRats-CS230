
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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
    private static final int CANVAS_HEIGHT = 400;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    // The width of the grid in number of cells.
    private static final int GRID_WIDTH = 12;

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
        // We draw the row at y value 2.
        for (int x = 0; x < GRID_WIDTH; x++) {
            gc.drawImage(grass, x * GRID_CELL_WIDTH, 2 * GRID_CELL_HEIGHT);
        }

    }

    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it.
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
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
