import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This Class represents the Gas item in the game.
 * @author ryanwake, Marc Jubb.
 */
public class Gas extends Item {
    private final int GAS_DURATION = 9;
    private final int DISSIPATING_FACTOR = 4;
    private final int RAT_IN_GAS = 3;
    private final int TIMER_DIVIDING_FACTOR = 3;
    private final int RADIUS_CALCULATOR = 2;
    private final int GAS_OUTSIDE_RADIUS = 4;
    private int timer;
    private boolean isDissipating;
    private int radius ;

    /**
     * Create Gas at a specified coordinate.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Gas(int x, int y) {
        super (x, y, "Gas", "/resources/Images/Items/Gas.png");
        timer = GAS_DURATION;
        isDissipating = false;
    }

    /**
     * Create Gas at a specified coordinate with certain properties.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param timer The time since placed.
     * @param isDissipating Whether or not the gas is shrinking.
     */
    public Gas(int x, int y, int timer, boolean isDissipating) {
        super(x, y, "Gas", "/resources/Images/Items/Gas.png");
        this.timer = timer;
        this.isDissipating = isDissipating;
    }

    /**
     * Calculates the area of effect of Gas and the effect on rats.
     */
   public void gasArea() {
       if (isDissipating) {
           for (int x = getX() + (radius - DISSIPATING_FACTOR); x  < getX() +
                   (DISSIPATING_FACTOR - radius); x++) {
               for (int y = getY() + (radius - DISSIPATING_FACTOR); y < getY()
                       + (DISSIPATING_FACTOR - radius); y++) {
                   ratInGas(x, y);
               }
           }
       } else {
           for (int x = getX() - radius; x < getX() + radius + 1; x++) {
               for (int y = getY() - radius; y < getY() + radius + 1; y++) {
                   ratInGas(x, y);
               }
           }
       }
    }

    /**
     * Kills rat if they are in gas for certain amount of ticks
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    private void ratInGas(int x, int y) {
        for (Rat rat : Level.getRatList()) {
            if (rat.getX() == x && rat.getY() == y && rat.getTicksInGas()
                    < RAT_IN_GAS) {
                rat.incrementTicksInGas();
            } else if ((rat.getX() == x && rat.getY() == y && rat.getTicksInGas()
                    >= RAT_IN_GAS)) {
                rat.setDestroyed(true);
            }
        }
    }
    /**
     * Gas will dissipate after specified number of ticks.
     */
    public void update() {
        timer--;
        if (timer <= 0) {
            isDissipating = true;
        }
    }

    /**
     * Draw the Gas on the canvas.
     * @param gc The GraphicsContext in which we will draw on.
     */
    public void draw(GraphicsContext gc) {
        radius = RADIUS_CALCULATOR - (timer / TIMER_DIVIDING_FACTOR); //Calculate a sort of radius to draw gas.
        if (isDissipating) {
            for (int x = getX() + (radius - DISSIPATING_FACTOR); x  < getX() +
                    (DISSIPATING_FACTOR - radius); x++) {   //Draw the Gas in reverse making a dissipating effect.
                for (int y = getY() + (radius - DISSIPATING_FACTOR); y < getY() +
                        (DISSIPATING_FACTOR - radius); y++) {
                    if (isInBounds(x, y)) { //Ensures it's not drawn out of bounds.
                        if (Level.getLevelLayout()[y][x] != 'G' && Level.getLevelLayout()[y][x] != 'T') {
                            gc.drawImage(new Image("/resources/Images/Items/Gas.png"),
                                    x * Level.getGridCellHeight(), y * Level.getGridCellHeight());
                        }
                    }
                }
                System.out.println("done");
            }
           if (radius == GAS_OUTSIDE_RADIUS) {  //Destroys gas if outside radius.
               this.setDestroyed(true);
           }
        } else {
            for (int x = getX() - radius; x < getX() + radius + 1; x++) { //Draw Gas based on radius.
                for (int y = getY() - radius; y < getY() + radius + 1; y++) {
                    if (isInBounds(x, y)) {
                        if (Level.getLevelLayout()[y][x] != 'G' && Level.getLevelLayout()[y][x] != 'T') {
                            gc.drawImage(new Image("/resources/Images/Items/Gas.png"),
                                    x * Level.getGridCellHeight(), y * Level.getGridCellHeight());
                        }
                    }

                }
            }
        }
        gasArea();
    }

    /**
     * Checks if the coordinates are in the bounds.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return True if coordinates are in the bounds else false.
     */
    private Boolean isInBounds(int x, int y) {
        return y < Level.getGridHeight() - 1 && x < Level.getGridWidth() - 1 &&
                y > 0 && x > 0;
    }

    /**
     * Get the data of the gas.
     * @return the gas's data.
     */
    @Override
    public String toString(){
        return super.toString() + ", " + timer + ", " + isDissipating;
    }
}




