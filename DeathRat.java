import javafx.scene.image.Image;

/**
 * This Class represents a DeathRat in the game.
 * @author samgriffin and Ryan Wake
 */
 
public class DeathRat extends Rat {
    private int numCollisions = 0;
    static final private int ADULT_SPEED = 2;
    private int ticksSinceCreation;



    public DeathRat() {
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

    public void checkCollisions(){
        for(Rat rat: Level.getRatList()){
            if (rat.getX() == x && rat.getY() == y){
                Level.getRatList().remove(rat);
            }
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
