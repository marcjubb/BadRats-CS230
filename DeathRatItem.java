import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/* This Class represents the deathRat as an Item. The deathRatItem
 * will then become a deathRat after a set time when placed.
 * @author ryanwake */

public class DeathRatItem extends Item {

    private int ticksSinceCreation;

    public DeathRatItem(int x, int y){
        super(x,y, "DeathRat");
        this.setImg(new Image("/resources/Images/Rat/Rat10.png"));

    }

    public DeathRatItem(String itemName,int x, int y){
        super(x,y,"Death Rat");
        this.setItemName("DeathRatItem");
        this.setImg(new Image("/resources/Images/Rat/Rat10.png"));
        ticksSinceCreation = 0;


    }

    public void update() {
        ticksSinceCreation++;
        if (ticksSinceCreation == 5) {
            destroySelf();
            DeathRat rat = new DeathRat(x, y);
            rat.setImageDirection();
            Level.getRatList().add(rat);
            //create playerable DeathRat object at same coords?
        }
    }
    public void draw(GraphicsContext gc) {
        while(ticksSinceCreation > 0){
            gc.drawImage(new Image("resources/DeathRat.png"), x, y);
        }
        //dont know what to do here cos were not deleting the item, it's changing to actual death rat
    }

    public int getTicksSinceCreation() {
        return ticksSinceCreation;
    }


    @Override
    public String toString() {
        return super.toString() + ", " + ticksSinceCreation + "\n";
    }
}
