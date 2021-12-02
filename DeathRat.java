import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This Class represents a DeathRat in the game.
 * @author samgriffin and Ryan Wake
 */
 
public class DeathRat extends Rat {
    private int numCollisions = 0;
    static final private int ADULT_SPEED = 2;
    //private int ticksSinceCreation;



    public DeathRat() {
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
    }

    public DeathRat(int x, int y) {
        super.x = x;
        super.y = y;
        super.speed = ADULT_SPEED;
        super.ticksSinceCreation = 0;
        super.direction = generateDirection();
    }


    public void checkCollisions(){
        /*int ratListLength = Level.getRatList().size();
        ArrayList<Rat> ratsToRemove = new ArrayList<>();
        for (int i = 0; i <= ratListLength-1; i++) {
            if (Level.getRatList().get(i).getX() == this.x && Level.getRatList().get(i).getY() == this.y){
                System.out.println("a!");
                ratsToRemove.add(Level.getRatList().get(i));
            }
        }*/
        System.out.println("COLLISION!");

        for(Rat rat: Level.getRatList()){
            if (rat.getClass() != this.getClass() && rat.getX() == x && rat.getY() == y){
                System.out.println("Mac - 'sam grif!'");
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
    checkCollisions();
    }
 
}
