//import javafx.scene.canvas.GraphicsContext;
//
///**
// * A bomb that explodes after a short period of time.
// * @author Michael Pokorski
// */
//
//public class Bomb extends Item {
//    private int timer;
//    private int distanceUp;
//    private int distanceDown;
//    private int distanceLeft;
//    private int distanceRight;
//
//    public Bomb(int x, int y) {
//        //this.x = x;
//        //this.y = y;
//        timer = 5;
//        updateDistance();
//    }
//
//    public void update() {
//        timer--;
//        if (timer <= -2) {
//            destroySelf();
//        }
//    }
//
//    public void draw(GraphicsContext gc) {
//        if (timer > 0) {
//            //Draw image at position
//        }else {
//            //Draw rectangles for blast area
//        }
//    }
//
//    public boolean collisionAt(int x, int y) {
//        if (timer > 0) {
//            return false;
//        }else {
//            //Check if position is in blast area
//            return false;
//        }
//    }
//
//    private void updateDistance() {
//        //for (int i = 0; i < roomWidth - x; i++) {
//        //  if (tileAt(x + i, y) == path) distanceRight = i;
//        //  else break;
//        //}
//        //duplicate code for each direction
//    }
//    public String toString() {
//        return super.toString() + ", " + timeLeft + "\n";
//    }
//}
