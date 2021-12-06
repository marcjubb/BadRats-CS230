import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

/**
 * This class is responsible for running the GUI for the
 * rats game and storing the relevant values to be called.
 *
 * @author Samuel Griffin, Marc Jubb, Ryan Wake, Gonzalo Mandrión Flores, Aaron Davies, Alex Walker
 */
public class Level extends Application {


    // The dimensions of the window

    private static final int AUTO_SAVE_FREQUENCY = 20;
    private static final int GRID_CELL_WIDTH = 64;
    private static final int GRID_CELL_HEIGHT = 64;
    private static int gridWidth = 12;
    private static final int CANVAS_WIDTH = GRID_CELL_WIDTH * gridWidth;
    private static final int WINDOW_WIDTH = 400 + CANVAS_WIDTH;
    private static int gridHeight = 7;

    // The dimensions of the canvas
    // The width and height (in pixels) of each cell that makes up the game.
    private static final int CANVAS_HEIGHT = GRID_CELL_HEIGHT * gridHeight;
    private static final int WINDOW_HEIGHT = 200 + CANVAS_HEIGHT;
    private static Canvas canvas;

    // The width of the grid in number of cells.
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
    private static int currentLevel;
    private static int numOfMaleRats;
    private static int numOfFemaleRats;
    private static int frequencyOfNewItem = 5;
    private static int maxPopulation = 10;
    private static  Inventory inv = new Inventory();
    private static int ticksExpected = 100;
    private static BorderPane root;
    private static Stage loginStage = new Stage();
    private static Stage createUserStage = new Stage();
    private static Stage menuStage = new Stage();
    //Arrays that store the objects on the game board.
    private static ArrayList<Rat> ratList = new ArrayList<>();
    private static ArrayList<Item> itemList = new ArrayList<>();
    //this is a hardcoded level layout only here for testing purposes
    private static Character[][] levelLayout = {
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'P', 'P', 'P', 'P', 'T', 'T', 'T', 'P', 'P', 'P', 'G'},
            {'G', 'P', 'G', 'G', 'P', 'G', 'G', 'G', 'P', 'G', 'P', 'G'},
            {'G', 'P', 'P', 'P', 'T', 'P', 'P', 'P', 'P', 'T', 'P', 'G'},
            {'G', 'P', 'G', 'G', 'T', 'G', 'P', 'G', 'P', 'G', 'P', 'G'},
            {'G', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}};

    private Canvas canvasCounters;
    private Timeline tickTimeline;
    private boolean levelCompleted = false;
    private boolean gameLost = false;
    private static TreeMap<Integer, String> level1Scores = new TreeMap<>();
    private static TreeMap<Integer, String> level2Scores = new TreeMap<>();
    private static TreeMap<Integer, String> level3Scores = new TreeMap<>();
    private static TreeMap<Integer, String> level4Scores = new TreeMap<>();
    //The quantity of rats by sex.





    /**
     * Gets grid height.
     *
     * @return the grid height
     */
    public static int getGridHeight() {
        return gridHeight;
    }

    /**
     * Gets grid width.
     *
     * @return the grid width
     */
    public static int getGridWidth() {
        return gridWidth;
    }

    public static Set getHighScore(int level){
        if (level ==1){
            return level1Scores.entrySet();
        }else if(level ==2){
            return level2Scores.entrySet();
        }else if(level ==3){
            return level3Scores.entrySet();
        }else{
            return level4Scores.entrySet();
        }

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
     * Creates a new baby object at the position of the mother
     * when it is called and adds it to the rats arraylist.
     *
     * @param x The x-coordinate of the pregnant female rat.
     * @param y The y-coordinate of the pregnant female rat.
     */
    public static void giveBirth(int x, int y) {
        PlayableRat newBaby = new PlayableRat(x, y);
        newBaby.setImageDirection();
        ratList.add(newBaby);
    }

    public static void saveGame(boolean isAuto) {
        String filename;
        if (!isAuto) {
            filename = "resources/LevelFiles/ExistingFiles/level" + currentLevel + PlayerProfiles.getCurrentUserName() + ".txt";
        } else{
            filename = "resources/LevelFiles/ExistingFiles/AutoSaveFiles/levelAutoSave" + currentLevel+ PlayerProfiles.getCurrentUserName() + ".txt";
        }

        File outputFile = null;
        try {
            outputFile = new File(filename);
            if(outputFile.createNewFile()){
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening file");
            System.exit(0);
        }
        out.println(gridWidth + ", " + gridHeight);
        String levelLayoutAsString = Arrays.deepToString(levelLayout);

        levelLayoutAsString = levelLayoutAsString
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "\n") //remove the left bracket and lane break
                .replace(" ", "");

        out.println(levelLayoutAsString);
        out.println(ticksExpected);
        out.println(tickCount);
        for (Rat rat : ratList) {
            out.print(rat.toString() + ";");
        }
        out.println();
        for (Item item : itemList) {
            out.print(item.toString() + ";");
        }
        out.println();
        out.println(frequencyOfNewItem);
        out.println(maxPopulation);


        out.close();
    }

    /**
     * Loads an existing file that has been saved before.
     */
    public static void loadExisting() {
        Stage secondaryStage = new Stage();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondaryStage.setScene(scene);
        secondaryStage.show();
        FileChooser fileChooser = new FileChooser();
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File inputFile = fileChooser.showOpenDialog(new Stage());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scanner in = null;
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file");
            System.exit(0);
        }


        String line = (in.nextLine());
        String[] widthAndHeight = line.split(", ");
        gridWidth = Integer.parseInt(widthAndHeight[0]);
        gridHeight = Integer.parseInt(widthAndHeight[1]);
        ArrayList<ArrayList<Character>> fileLevelLayout = new ArrayList<>();
        for (int i = 0; i <= gridHeight - 1; i++) {
            ArrayList<Character> chars = new ArrayList<>();
            line = in.nextLine();
            for (char ch : line.toCharArray()) {
                chars.add(ch);
            }
            fileLevelLayout.add(chars);

        }

        levelLayout = fileLevelLayout.stream().map(u -> u.toArray(new Character[0])).toArray(Character[][]::new);
        System.out.println(Arrays.deepToString
                (fileLevelLayout.stream().map(u -> u.toArray(new Character[0])).toArray(Character[][]::new)));
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        ticksExpected = Integer.parseInt(in.nextLine());
        tickCount = Integer.parseInt(in.nextLine());
        System.out.println(ticksExpected);
        System.out.println(tickCount);
        line = in.nextLine();
        Scanner lineReader = new Scanner(line);
        lineReader.useDelimiter(";");
        while (lineReader.hasNext()) {
            String[] ratData = lineReader.next().split(", ");
            if (ratData[0].equals("PlayableRat")) {
                int x = Integer.parseInt(ratData[1]);
                int y = Integer.parseInt(ratData[2]);
                int ticksSinceCreation = Integer.parseInt(ratData[3]);
                Rat.Direction direction = Rat.Direction.valueOf(ratData[4]);
                PlayableRat.Sex sex = PlayableRat.Sex.valueOf((ratData[5]));
                boolean isAdult = Boolean.parseBoolean(ratData[6]);
                boolean isPregnant = Boolean.parseBoolean(ratData[7]);
                int pregnantTick = Integer.parseInt(ratData[8]);
                boolean isSterile = Boolean.parseBoolean(ratData[9]);
                ratList.add(new PlayableRat(x, y, isAdult, isPregnant, sex,
                        ticksSinceCreation, direction, pregnantTick, isSterile));
                Rat rat = new PlayableRat(x, y, isAdult, isPregnant, sex,
                        ticksSinceCreation, direction, pregnantTick, isSterile);
                System.out.println(rat);
            } else if (ratData[0].equals("DeathRat")) {
                int x = Integer.parseInt(ratData[1]);
                int y = Integer.parseInt(ratData[2]);
                int ticksSinceCreation = Integer.parseInt(ratData[3]);
                Rat.Direction direction = Rat.Direction.valueOf(ratData[4]);
                int currentKillCount = Integer.parseInt(ratData[5]);
                DeathRat rat = new DeathRat(x, y, ticksSinceCreation, currentKillCount, direction);

            }
        }
        System.out.println("End of rats");
        line = in.nextLine();
        lineReader = new Scanner(line);
        lineReader.useDelimiter(";");

        /* Variables for data item */
        final int ITEM_DATA_X = 1;
        final int ITEM_DATA_Y = 2;
        final int TIMER_DATA = 3;

        while (lineReader.hasNext()) {
            String[] itemData = lineReader.next().split(", ");
            switch (itemData[0]) {
                case "Bomb": {

                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    int timer = Integer.parseInt(itemData[TIMER_DATA]);
                    itemList.add(new Bomb(x, y, timer));

                    break;
                }
                case "Sterilisation": {
                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    int timer = Integer.parseInt(itemData[TIMER_DATA]);

                    itemList.add(new Sterilisation(x, y, timer));
                    System.out.println((new Sterilisation(x, y, timer)));
                    break;
                }
                case "DeathRatItem": {

                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    int tickSinceCreation = Integer.parseInt(itemData[3]);
                    int currentKillCount = Integer.parseInt(itemData[4]);
                    System.out.println(new DeathRatItem(x, y, tickSinceCreation, currentKillCount));
                    break;
                }
                case "Poison": {
                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    itemList.add(new Poison(x, y));
                    System.out.println(new Poison(x, y));
                    break;
                }
                case "FemaleSexChange": {
                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    itemList.add(new FemaleSexChange(x, y));
                    System.out.println(new FemaleSexChange(x, y));
                    break;
                }
                case "MaleSexChange": {
                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    itemList.add(new MaleSexChange(x, y));

                    break;
                }
                case "Gas": {
                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    int timer = Integer.parseInt(itemData[3]);
                    boolean isDissipating = Boolean.parseBoolean(itemData[4]);
                    itemList.add(new Gas(x, y, timer, isDissipating));
                    System.out.println(new Gas(x, y, timer, isDissipating).toString());
                    break;
                }
                case "NoEntrySign": {
                    int x = Integer.parseInt(itemData[ITEM_DATA_X]);
                    int y = Integer.parseInt(itemData[ITEM_DATA_Y]);
                    int durability = Integer.parseInt(itemData[3]);
                    itemList.add(new NoEntrySign(x, y, durability));
                    System.out.println(new NoEntrySign(x, y, durability));
                    break;
                }
            }
        }
        frequencyOfNewItem = Integer.parseInt(in.nextLine());
        maxPopulation = Integer.parseInt(in.nextLine());
        System.out.println(frequencyOfNewItem);
        System.out.println(maxPopulation);

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //Clear canvas
        gc.setFill(Color.GRAY); // Set the background to gray.
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawMap(gc, gridHeight, gridWidth);
        in.close();

    }

    /**
     * Load new.
     *
     * @param currentLevel the current level
     */
    public static void loadNew(int currentLevel) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String filename = "resources/LevelFiles/map0"+currentLevel+ ".txt";

        File inputFile = new File(filename);
        Scanner in = null;
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + filename);
            System.exit(0);
        }
        String line = (in.nextLine());
        String[] widthAndHeight = line.split(", ");
        gridWidth = Integer.parseInt(widthAndHeight[0]);
        gridHeight = Integer.parseInt(widthAndHeight[1]);
        System.out.println(gridWidth);
        System.out.println(gridHeight);
        ArrayList<ArrayList<Character>> fileLevelLayout = new ArrayList<>();

        for (int i = 0; i <= gridHeight - 1; i++) {
            ArrayList<Character> chars = new ArrayList<>();
            line = in.nextLine();
            for (char ch : line.toCharArray()) {
                chars.add(ch);
            }
            fileLevelLayout.add(chars);

        }
        levelLayout = fileLevelLayout.stream().map(u -> u.toArray(new Character[0])).toArray(Character[][]::new);
        System.out.println(Arrays.deepToString(levelLayout));

        ticksExpected = Integer.parseInt(in.nextLine());
        System.out.println(ticksExpected);

        line = in.nextLine();
        Scanner lineReader = new Scanner(line);
        lineReader.useDelimiter(";");

        while (lineReader.hasNext()) {
            String[] ratData = lineReader.next().split(", ");
            int x = Integer.parseInt(ratData[0]);
            int y = Integer.parseInt(ratData[1]);
            ratList.add(new PlayableRat(x, y));
            System.out.println(new PlayableRat(x, y).toString());
        }
        frequencyOfNewItem = Integer.parseInt(in.nextLine());
        System.out.println(frequencyOfNewItem);
        maxPopulation = Integer.parseInt(in.nextLine());
        System.out.println(maxPopulation);

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //Clear canvas
        gc.setFill(Color.GRAY); // Set the background to gray.
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawMap(gc, gridHeight, gridWidth);
        in.close();


    }



    private static void drawMap(GraphicsContext gc, int gridHeight, int gridWidth) {
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

    /**
     * Load level 1.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void loadLevel1() throws FileNotFoundException {
        currentLevel = 1;
        Stage secondaryStage = new Stage();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondaryStage.setScene(scene);
        secondaryStage.show();

        loadNew(1);



    }

    /**
     * Load level 2.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void loadLevel2() throws FileNotFoundException {
        currentLevel = 2;
        Stage secondaryStage = new Stage();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondaryStage.setScene(scene);
        secondaryStage.show();
        loadNew(2);
    }

    /**
     * Load level 3.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void loadLevel3() throws FileNotFoundException {
        currentLevel = 3;
        Stage secondaryStage = new Stage();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondaryStage.setScene(scene);
        secondaryStage.show();
        loadNew(3);
    }




    /**
     * Load level 4.
     *
     * @throws FileNotFoundException the file not found exception
     */

    public static void loadLevel4() throws FileNotFoundException {
        currentLevel = 4;
        Stage secondaryStage = new Stage();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondaryStage.setScene(scene);
        secondaryStage.show();
        loadNew(4);
    }

    /**
     * Create account window.
     *
     * @throws IOException the io exception
     */
    public static void createAccountWindow() throws IOException {
        Parent rooter = FXMLLoader.load(Objects.requireNonNull(Level.class.getResource("CreateAccount.fxml")));
        Scene createUser = new Scene(rooter);
        createUserStage.setScene(createUser);
        createUserStage.show();
    }

    /**
     * Close login.
     */
    public static void closeLogin() {
        loginStage.hide();
    }

    /**
     * Close create user.
     */
    public static void closeCreateUser() {
        createUserStage.hide();
    }

    /**
     * Draw menu.
     *
     * @throws IOException the io exception
     */
    public static void drawMenu() throws IOException {
        Parent rooter = FXMLLoader.load(Objects.requireNonNull(Level.class.getResource("Menu.fxml")));
        Scene menu = new Scene(rooter);
        menuStage.setScene(menu);
        menuStage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public TreeMap<Integer, String> getLevel1Scores() {
        return level1Scores;
    }



    public TreeMap<Integer, String> getLevel2Scores() {
        return level2Scores;
    }

    public TreeMap<Integer, String> getLevel3Scores() {
        return level3Scores;
    }



    public TreeMap<Integer, String> getLevel4Scores() {
        return level4Scores;
    }


    /**
     * Get level layout character [ ] [ ].
     */
    public void getNumOfSex() {
        numOfMaleRats = 0;
        numOfFemaleRats = 0;
        ratWorker();
    }

    private void ratWorker() {
        for (Rat rat : Level.getRatList()) {
            if ((rat instanceof PlayableRat) && ((PlayableRat) rat).getSex()
                    == PlayableRat.Sex.MALE) {
                numOfMaleRats++;
            } else if ((rat instanceof PlayableRat) && ((PlayableRat) rat).getSex()
                    == PlayableRat.Sex.FEMALE) {
                numOfFemaleRats++;
            }
        }
    }

    /**
     * Sets current level.
     *
     * @param currentLevel the current level
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Compute num of sex.
     */
    public void computeNumOfSex() {
        ratWorker();
    }

    /**
     * Draw the game on the canvas.
     */
    public void drawGame() {
        // Get the Graphic Context of the canvas. This is what we draw on.

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw row of dirt images
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.

        drawMap(gc, gridHeight, gridWidth);

        for (Rat rat : ratList) {
            rat.draw(gc);
        }

        for (Item item : itemList) {
            item.draw(gc);
        }

    }

    /**
     * Draw counters.
     */
    public void drawCounters() {
        GraphicsContext gc = canvasCounters.getGraphicsContext2D();
        getNumOfSex();

        gc.clearRect(0, 0, canvasCounters.getWidth(), canvasCounters.getHeight());
        gc.fillText("Max number of rats: " + maxPopulation, 80, 10);
        gc.fillText("Score: " + score, 80, 30);
        gc.fillText("Rats Remaining: " + getRatListSize(), 80, 50);
        gc.fillText("Males Remaining: " + numOfMaleRats, 80, 70);
        gc.fillText("Females Remaining: " + numOfFemaleRats, 80, 90);
    }

    /**
     * Draw inv.
     */
    public void drawInv() {

        GraphicsContext gc = canvasCounters.getGraphicsContext2D();
        gc.fillText("Number of Bomb Items: " + inv.getNumOfBombItems(),
                80, 120);
        gc.fillText("Number of Male Sex Change Items: " + inv.getNumOfMaleSexChangeItems(),
                80, 140);
        gc.fillText("Number of Female Sex Change Items: " + inv.getNumOfFemaleSexChangeItems(),
                80, 160);
        gc.fillText("Number of Gas Items: " + inv.getNumOfGasItems(),
                80, 180);
        gc.fillText("Number of Poison Items: " + inv.getNumOfPoisonItems(),
                80, 200);
        gc.fillText("Number of Sterilise Items: " + inv.getNumOfSterilisationItems(),
                80, 220);
        gc.fillText("Number of Death Rat Items: " + inv.getNumOfDeathRatItems(),
                80, 240);
        gc.fillText("Number of No Entry Sign Items: " + inv.getNumOfNoEntrySignItems(),
                80, 260);


    }

    /**
     * Load new.
     */
    public void loadNew() {
        String filename = "level1New.txt";
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
            if (item.getX() == x && item.getY() == y && !item.destroyed) {
                itemAlreadyPlaced = true;
                break;
            }
        }

        if (!itemAlreadyPlaced) {
            if (levelLayout[y][x] == 'P') {
                if (Objects.equals(event.getDragboard().getString(), "Bomb") && inv.getNumOfBombItems() > 0) {
                    itemList.add(itemList.size(), new Bomb(x, y));
                    inv.decrementBomb();
                    gc.drawImage(bomb, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "MaleSexChange")
                        && inv.getNumOfMaleSexChangeItems() > 0) {
                    itemList.add(itemList.size(), new MaleSexChange(x, y));
                    inv.decrementMaleSexChange();
                    gc.drawImage(maleSexChange, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "FemaleSexChange")
                        && inv.getNumOfFemaleSexChangeItems() > 0) {
                    itemList.add(itemList.size(), new FemaleSexChange(x, y));
                    inv.decrementFemaleSexChange();
                    gc.drawImage(femaleSexChange, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "Gas")
                        && inv.getNumOfGasItems() > 0) {
                    itemList.add(itemList.size(), new Gas(x, y));
                    inv.decrementGas();
                    gc.drawImage(gas, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "Poison")
                        && inv.getNumOfPoisonItems() > 0) {
                    itemList.add(itemList.size(), new Poison(x, y));
                    inv.decrementPoison();
                    gc.drawImage(poison, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "NoEntry")
                        && inv.getNumOfNoEntrySignItems() > 0) {
                    itemList.add(itemList.size(), new NoEntrySign(x, y));
                    inv.decrementNoEntrySign();
                    gc.drawImage(noEntry, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "Sterilisation")
                        && inv.getNumOfSterilisationItems() > 0) {
                    itemList.add(itemList.size(), new Sterilisation(x, y));
                    inv.decrementSterilisation();
                    gc.drawImage(sterilisation, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                } else if (Objects.equals(event.getDragboard().getString(), "DeathRat")
                        && inv.getNumOfDeathRatItems() > 0) {
                    itemList.add(itemList.size(), new DeathRatItem(x, y));
                    inv.decrementDeathRat();
                    gc.drawImage(deathRat, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                }
            }
        }
    }

    private void buildGUI() {

        // Create top-level panel that will hold all GUI nodes.
        root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        canvasCounters = new Canvas(300, 270);
        root.setLeft(canvasCounters);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);


        //Button to save the layout running
        Button btnSaveLevel = new Button("Save Level");
        // Tick Timeline buttons
        Button startTickTimelineButton = new Button("Start Ticks");
        Button stopTickTimelineButton = new Button("StopTicks");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton, btnSaveLevel);
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

        btnSaveLevel.setOnAction(e -> {
            saveGame(false);
        });


        //Save the variables board into a .txt file
//        btnSaveLevel.setOnAction(e -> {
//            Saver saveLevel = new Saver(); //Get save function from Saver class
//            FileChooser fileChooser = new FileChooser();
//
//            //Set extension filter for text files
//            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//            fileChooser.getExtensionFilters().add(extFilter);
//
//            String levelFormatted = Arrays.deepToString(levelLayout)
//                    .replace(",", "")  //remove the commas
//                    .replace("[", "")  //remove the right bracket
//                    .replace("]", "\n") //remove the left bracket and lane break
//                    .replace(" ", "");
//            String babyRatsFormatted = ratList.toString()
//                    .replace("[PlayableRat, ", "")
//                    .replace("PlayableRat,", "")
//                    .replace("]", "\n");
//            String itemsFormatted = itemList.toString()
//                    .replace("[", "")
//                    .replace(", ", " ")
//                    .replace("]", "");
//
//            //needs to include items data correctly formatted
//            saveLevel.saveLevelFile(gridHeight + "," + gridWidth, tickCount, maxPopulation, babyRatsFormatted, itemsFormatted, levelFormatted);
//            System.out.println("Saved");
//            //System.out.println(ratList.toString());
//            //System.out.println(itemsFormatted);
//            //System.out.println(itemList.toString());
//        });


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
                if (event.getGestureSource() == dragBomb || event.getGestureSource() == dragMaleGender
                        || event.getGestureSource() == dragFemaleGender
                        || event.getGestureSource() == dragDeathRat
                        || event.getGestureSource() == dragPoison
                        || event.getGestureSource() == dragNoEntry
                        || event.getGestureSource() == dragGas
                        || event.getGestureSource() == dragSterilisation) {
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
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("Bad Rats");

        tickCount = 0;
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

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(400), event -> {
            try {
                tick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);

        // We start the timeline upon a button press.
        drawLogin();
        buildGUI();
        drawGame();
        drawCounters();
        drawInv();
    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     *
     * @throws IOException the io exception
     */
    public void tick() throws IOException {
        gameStatus();

        if (!levelCompleted && !gameLost) {
            if (tickCount % AUTO_SAVE_FREQUENCY == 0){
                saveGame(true);
            }


            if ((tickCount % frequencyOfNewItem) == 0) {
                inv.addItem();
            }
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
            drawInv();
        }
    }

    private void levelEndScreen() throws IOException {
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
            switch (currentLevel) {
                case 1:
                    level1Scores.put(score,PlayerProfiles.getCurrentUserName());
                    updateHighScores(level1Scores);

                    break;
                case 2:
                    level2Scores.put(score,PlayerProfiles.getCurrentUserName());
                    updateHighScores(level2Scores);
                    break;
                case 3:
                    level3Scores.put(score,PlayerProfiles.getCurrentUserName());
                    updateHighScores(level3Scores);
                    break;
                case 4:
                    level4Scores.put(score,PlayerProfiles.getCurrentUserName());
                    updateHighScores(level4Scores);
                    break;

            }

            PlayerProfiles.load();
            System.out.println(PlayerProfiles.getCurrentHighestLevel());
            System.out.println(currentLevel);
            if (PlayerProfiles.getCurrentHighestLevel() == currentLevel && PlayerProfiles.getCurrentHighestLevel() != 4) {
                PlayerProfiles.setCurrentHighestLevel(currentLevel + 1);
                PlayerProfiles.getProfiles().set(PlayerProfiles.getCurrentUserIndex() - 1,
                        new PlayerProfile(PlayerProfiles.getCurrentUserName(), PlayerProfiles.getCurrentHighestLevel()));
                PlayerProfiles.save(false);
            }

            gc.fillText("Congratulations you win!",
                    Math.round(canvas.getWidth() / 2), Math.round(canvas.getHeight() / 2));
        } else if (gameLost) {
            gc.fillText("Congratulations you Suck!",
                    Math.round(canvas.getWidth() / 2), Math.round(canvas.getHeight() / 2));
        }


        /**
         * Game status.
         */

    }


    public void updateHighScores(TreeMap<Integer, String> levelScores) throws IOException {

        File leaderBoardPath = new File("resources/HighScoresLevel" + currentLevel + ".txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(leaderBoardPath, true);
            for (int i = 0; i < levelScores.size(); i++) {

                if ((i + 1) > levelScores.size()) {
                    System.out.println(levelScores.firstKey() + " "
                            + PlayerProfiles.getCurrentUserName());
                    writer.write(levelScores.firstKey() + " "
                            + PlayerProfiles.getCurrentUserName());
                } else {
                    writer.write(levelScores.firstKey() + " "
                            + PlayerProfiles.getCurrentUserName() + "\n");
                }

            }
        } catch (Exception e) {
            System.out.println("Couldn't save profile at " + leaderBoardPath);
        }
        assert writer != null;
        writer.close();
    }



    public static void loadLevel1HighScores() throws FileNotFoundException {
        File leaderBoardPath =  new File("resources/HighScoresLevel1.txt");
        Scanner in = null;
        in = new Scanner(leaderBoardPath);
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner lineReader = new Scanner(currentLine);
            int score = lineReader.nextInt();
            String name = lineReader.next();
            level1Scores.put(score, name);
        }
    }
    public static void loadLevel2HighScores() throws FileNotFoundException {
        File leaderBoardPath = new File("resources/HighScoresLevel2.txt");
        Scanner in = null;
        in = new Scanner(leaderBoardPath);
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner lineReader = new Scanner(currentLine);
            int inp = lineReader.nextInt();
            String name = lineReader.next();
            level2Scores.put(inp, name);
        }
    }
    public static void loadLevel3HighScores() throws FileNotFoundException {
        File leaderBoardPath = new File("resources/HighScoresLevel3.txt");
        Scanner in;
        in = new Scanner(leaderBoardPath);
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner lineReader = new Scanner(currentLine);
            int inp = lineReader.nextInt();
            String name = lineReader.next();
            level3Scores.put(inp, name);
        }
    }
    public static void loadLevel4HighScores() throws FileNotFoundException {
        File leaderBoardPath = new File("resources/HighScoresLevel4.txt");
        Scanner in;
        in = new Scanner(leaderBoardPath);
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Scanner lineReader = new Scanner(currentLine);
            int inp = lineReader.nextInt();
            String name = lineReader.next();
            level4Scores.put(inp, name);
        }
    }

        public void gameStatus () throws IOException {
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
                score += ticksExpected - tickCount;
                levelEndScreen();

            } else if (totalNumOfRats > maxPopulation) {
                gameLost = true;
                levelEndScreen();
                tickTimeline.stop();
            }
        }

        public void drawLogin () throws IOException {
            Parent loginRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene login = new Scene(loginRoot);

            loginStage.setScene(login);
            loginStage.show();
        }


    }
