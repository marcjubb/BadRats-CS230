import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Iterator;


/* This Class represents the deathRat as an Item. The deathRatItem
 * will then become a deathRat after a set time when placed.
 * @author ryanwake, samgriffin */

public class DeathRatItem extends Item {

    private int ticksSinceCreation;
    private int currentKillCount;

    public DeathRatItem(int x, int y){
        super(x,y, "DeathRat", "/resources/Images/Rat/DeathRatR.png");
        ticksSinceCreation = 0;
        currentKillCount = 0;
    }

    public void update() {
        ticksSinceCreation++;
        checkCollision();
        if (ticksSinceCreation == 5) {
            destroySelf();
            DeathRat rat = new DeathRat(x, y, currentKillCount);
            rat.setImageDirection();
            Level.getRatList().add(rat);
        }

    }

    private void checkCollision(){
        Iterator<Rat> iterator = Level.getRatList().listIterator();
        while (iterator.hasNext()) {
            Rat rat = iterator.next();
            if (rat.getX() == x && rat.getY() == y) {
                rat.setDestroyed(true);
                currentKillCount++;
                if (currentKillCount >= 5){
                    destroySelf();
                }

            }
        }
    }
//    public void draw(GraphicsContext gc) {
//        while(ticksSinceCreation > 0){
//            gc.drawImage(new Image("/resources/Images/Rat/DeathRat.png"), x, y); //for some reason is making the window freeze when it is correct
//        }
//        //dont know what to do here cos were not deleting the item, it's changing to actual death rat
//    }

    public int getTicksSinceCreation() {
        return ticksSinceCreation;
    }


    @Override
    public String toString() {
        return super.toString() + ", " + ticksSinceCreation + "\n";
    }
}
