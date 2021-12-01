//import javafx.scene.canvas.GraphicsContext;
//
///**
// * Blocks rat movement until it takes enough damage and is destroyed.
// * @author Michael Pokorski
// */
//
//public class NoEntrySign extends Item {
//    private int durability;
//
//    public NoEntrySign(int x, int y) {
//        this.x = x;
//        this.y = y;
//        durability = 5;
//    }
//
//    public void damage() {
//        durability--;
//        if (durability <= 0) {
//            destroySelf();
//        }
//    }
//
//    public void draw(GraphicsContext gc) {
//       if (durability == 5) {
//           gc.drawImage(new Image("/resources/Images/Items/NoEntry.png"), x, y);
//       }else if (durability == 4) {
//           gc.drawImage(new Image("/resources/Images/Items/NoEntry4.png"), x, y);
//       }else if (durability == 3) {
//           gc.drawImage(new Image("/resources/Images/Items/NoEntry3.png"), x, y);
//       }else if (durability == 2) {
//           gc.drawImage(new Image("/resources/Images/Items/NoEntry2.png"), x, y);
//       }else {
//           gc.drawImage(new Image("/resources/Images/Items/NoEntry1.png"), x, y);
//       }
//    }
//}
