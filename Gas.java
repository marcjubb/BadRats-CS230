//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//
///**
// * This Class represents the Gas item in the game.
// * @author ryanwake
// */
//public class Gas extends Item {
//
//    private int timeLeft;
//    private boolean visibility;
//    private int distanceUp;
//    private int distanceDown;
//    private int distanceLeft;
//    private int distanceRight;
//
//    public  Gas(int x, int y){
//        super(x,y);
//    }
//
//    public Gas(String itemName, String imagePath,int x, int y){
//        super(itemName, imagePath,x,y);
//        this.setItemName("Gas");
//        this.setImagePath("resources/Gas.png");
//        timeLeft = 10;
//    }
//
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
//        //if rat collides for certain amount of ticks, kill it
//        //dont know if this should be done here.
//        //TODO
//    }
//
//    public void draw(GraphicsContext gc) {
//       if(timeLeft < 0) {
//           //need to check here what tile gas is on also.
//            gc.drawImage(new Image("resources/Gas.png"), x, y);
//
//        }else{
//           //remove image from screen.
//           //TODO
//       }
//    }
//
//    public void updateDistance(){
//        //TODO
//    }
//
//    public int getTimeLeft() {
//        return timeLeft;
//    }
//
//    public String toString() {
//        return super.toString() + ", " + timeLeft + "\n";
//    }
//
//
//}
