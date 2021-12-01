
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This Class represents the Items.Sterilisation item in the game.
 * @author ryanwake
 */
public class Sterilisation extends Item {

    private int timeLeft;
    private int distanceUp;
    private int distanceDown;
    private int distanceLeft;
    private int distanceRight;

    public Sterilisation(int x, int y){
        super(x, y, "sterilisation");
        img = new Image("/resources/Images/Items/Sterilisation.png");
        timeLeft = 3;
    }

//    public void update() {
//        timeLeft--;
//        if (timeLeft <= 0) {
//            destroySelf();
//        }
//    }
    public void draw(GraphicsContext gc) {
      if (timeLeft > 0){
        gc.drawImage(new Image("resources/Sterilisation.png"), x, y);
      }else{
          //remove image.
      }

//    public void updateDistance(){
//          //TODO
//        }
    }
    public int getTimeLeft(){
        return timeLeft;
    }
    @Override
    public String toString() {
        return super.toString() + ", " + timeLeft + "\n";
    }
}
