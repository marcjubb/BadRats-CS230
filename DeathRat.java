import javafx.scene.image.Image;

/**
 * This Class represents a DeathRat in the game.
 * @author samgriffin and Alex Walker
 */
 
public class DeathRat extends Rat {
    private int numCollisions = 0;
    static final private int ADULT_SPEED = 2;
    private int ticksSinceCreation;
    //Constructor- tick, search, if found then kill, if time elapsed then die
    //Search
    //Kill
    public DeathRat() {     //not a clue what im doing here
        DeathRatItem dRatItem = new DeathRatItem(getX(),getY());
        super.x = dRatItem.getX();
        super.y = dRatItem.getY();
        super.speed = ADULT_SPEED;
        ticksSinceCreation = 0;
        super.direction = generateDirection();
    }

    public DeathRat(int x, int y) {
        super.x = x;
        super.y = y;
        super.speed = ADULT_SPEED;
        ticksSinceCreation = 0;
        super.direction = generateDirection();
    }

    public boolean checkCollisions(){
        PlayableRat pRat = new PlayableRat();
        DeathRat dRat = new DeathRat();
        if((pRat.getX() == dRat.getX()) && (pRat.getY() == dRat.getY())){
            return true;
        }else {
            return false;
        }
    }

    public void setImageDirection() {
        switch (getDirection()) {
            case "NORTH":
                this.setImg(new Image("resources/Images/Rat/DeathRatDown.png"));
                break;
            case "SOUTH":
                this.setImg(new Image("resources/Images/Rat/DeathRatUp.png"));
                break;
            case "EAST":
                this.setImg(new Image("resources/Images/Rat/Rat10.png"));
                break;
            case "WEST":
                this.setImg(new Image("resources/Images/Rat/Rat11.png"));
                break;
        }
    }
 
}
