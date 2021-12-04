
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Gas extends Item {
    private int timer;
    private boolean isDissipating;
    int radius ;
    public Gas(int x, int y) {
        super(x, y, "Gas", "/resources/Images/Items/Gas.png");
        timer = 9;
        isDissipating = false;
    }

   public void gasArea() {
       if (isDissipating) {
           for (int x = getX() + (radius-4);x  < getX() + (4-radius) ; x++) {
               for (int y = getY() + (radius-4); y < getY() + (4-radius) ; y++) {
                   ratInGas(x, y);
               }
           }
       }else {
           for (int x = getX() - radius; x < getX() + radius + 1; x++) {
               for (int y = getY() - radius; y < getY() + radius + 1; y++) {
                   ratInGas(x, y);
               }
           }
       }
    }

    private void ratInGas(int x, int y) {
        for (Rat rat : Level.getRatList()) {
            if (rat.getX() == x && rat.getY() == y && rat.getTicksInGas() < 3) {
                rat.incrementTicksInGas();
            } else if ((rat.getX() == x && rat.getY() == y && rat.getTicksInGas() >= 3)) {
                rat.setDestroyed(true);
            }
        }
    }

    public void update() {
        timer--;
        if (timer <= 0) {
            isDissipating = true;
        }
    }

public void draw(GraphicsContext gc) {
    radius = 2- (timer/3);
    if (isDissipating) {
        for (int x = getX() + (radius-4);x  < getX() + (4-radius) ; x++) {
            for (int y = getY() + (radius-4); y < getY() + (4-radius) ; y++) {
                if (isInBounds(x, y)) {
                    if (Level.getLevelLayout()[y][x] != 'G' && Level.getLevelLayout()[y][x] != 'T') {
                        gc.drawImage(new Image("/resources/Images/Items/Gas.png"), x * Level.getGridCellHeight(), y * Level.getGridCellHeight());
                    }
                }
            }
        }
    }else{
        for (int x = getX() - radius; x < getX() + radius + 1; x++) {
            for (int y = getY() - radius; y < getY() + radius + 1; y++) {
                if (isInBounds(x, y)) {
                    if (Level.getLevelLayout()[y][x] != 'G' && Level.getLevelLayout()[y][x] != 'T') {
                        gc.drawImage(new Image("/resources/Images/Items/Gas.png"), x * Level.getGridCellHeight(), y * Level.getGridCellHeight());
                    }
                }

            }
        }
    }
        gasArea();
 }
 private Boolean isInBounds(int x,int y){
     return y < Level.getGridHeight() - 1 && x < Level.getGridWidth() - 1 &&
             y > 0 && x > 0;
 }
}




