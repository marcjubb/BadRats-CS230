
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Gas extends Item {

    private int timeLeft;
    private boolean visibility;
    private int distanceUp;
    private int distanceDown;
    private int distanceRight;

    public Gas(int x, int y) {
        super(x, y, "Gas", "/resources/Images/Items/Gas.png");
        timeLeft = 10;
    }



}

//    public void update() {
//        timeLeft--;
//        if (timeLeft <= 0) {
//            destroySelf();
//        }
//    }
//
//    public boolean isVisible(){ //dont think it will work like this
//        //if gas is on the following tiles, set visibility
//        // Tile T = new Tile();
//        // switch(T)
//        //      case "P":   //path
//        //      visibility = true;
//        //      case "T":   //tunnel
//        //      visibility = false;
//        //      case "G":   //Grass
//        //      visibility = false;
//        //      default:    // if its not on anything
//        //      visibility = false;
//        // return visibility;
//        //TODO
//    }
//
//    public void checkTime(Rat Object){
//
//    }
//
//    public void draw(GraphicsContext gc) {
//       if(timeLeft < 0) {
//           //need to check here what tile gas is on also.
//            gc.drawImage(new Image("resources/Gas.png"), x, y);
//
//        }else{
//
//       }
//    }
//
//    public void updateDistance(){
//
//    }
//
//    public int getTimeLeft() {
//        return timeLeft;
/*   }*/

 /*   public String toString() {
        return super.toString() + ", " + timeLeft + "\n";
   }*/

