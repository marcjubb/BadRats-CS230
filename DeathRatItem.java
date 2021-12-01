/*import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

*
 * This Class represents the deathRat as an Item. The deathRatItem
 * will then become a deathRat after a set time when placed.
 * @author ryanwake

public class DeathRatItem extends Item {

    private int timeLeft;

    public DeathRatItem(int x, int y){
        super(x,y);
    }

    public DeathRatItem(String itemName, String imagePath,int x, int y){
        super(itemName,imagePath,x,y);
        this.setItemName("DeathRatItem");
        this.setImagePath("resources/DeathRat.png");
        timeLeft = 2;


    }

    public void update() {
        timeLeft--;
        if (timeLeft <= 0) {
            destroySelf();
            DeathRat rat = new DeathRat(x, y);
            //create playerable DeathRat object at same coords?
        }
    }
    public void draw(GraphicsContext gc) {
        while(timeLeft > 0){
            gc.drawImage(new Image("resources/DeathRat.png"), x, y);
        }
        //dont know what to do here cos were not deleting the item, it's changing to actual death rat
    }

    public int getTimeLeft() {
        return timeLeft;
    }


    @Override
    public String toString() {
        return super.toString() + ", " + timeLeft + "\n";
    }
}*/
