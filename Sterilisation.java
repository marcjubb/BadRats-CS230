
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This Class represents the Items.Sterilisation item in the game.
 * @author ryanwake
 */
public class Sterilisation extends Item {

    private int timer;
    private final int SIZE_OF_EFFECT =2;


    public Sterilisation(int x, int y){
        super(x, y, "Sterilisation", "/resources/Images/Items/Sterilisation.png");
        timer = 5;
    }

    public void sterilizeArea(){
        for (int x = getX()-1; x < getX() +2; x++) {
            for (int y = getY()-1; y < getY()+2; y++) {
                for(Rat rat : Level.getRatList()){
                    if (rat.getX() == x&&rat.getY() == y && rat instanceof PlayableRat){
                        ((PlayableRat) rat).setSterile(true);
                    }
                }
            }
        }
    }

        public void update() {
            timer--;
            if (timer <= 0) {
                destroySelf();
            }
        }
    //    public void update() {
//        timeLeft--;
//        if (timeLeft <= 0) {
//            destroySelf();
//        }
//

    public void draw(GraphicsContext gc) {


            for (int x = getX()-1; x < getX() +2; x++) {
                for (int y = getY()-1; y < getY()+2; y++) {
                    if (Level.getLevelLayout()[y][x] != 'G'){
                        gc.drawImage(new Image("/resources/Images/Items/Sterilisation.png"), x*Level.getGridCellHeight(), y*Level.getGridCellHeight());
                    }
                }
            }
            sterilizeArea();



    }

    @Override
    public String toString() {
        return super.toString() + ", " + timer + "\n";
    }
}

