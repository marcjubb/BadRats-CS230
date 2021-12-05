import javafx.scene.image.Image;

/**
 * ~~~~rough start~~~~
 * Assigns each item with a image for level to draw said items with images
 *
 * @author Aaron Davies
 */

class ItemImage {
    protected static Image Bomb4;
    protected static Image Bomb3;
    protected static Image Bomb2;
    protected static Image Bomb1;
    protected static Image Bomb;
    protected static Image Gas;
    protected static Image FemaleSexChange;
    protected static Image MaleSexChange;
    protected static Image Poison;
    protected static Image NoEntry;
    protected static Image Sterilisation;
    protected static Image DeathRatItem;

    public ItemImage(){

        Bomb4 = new Image("resources/Bomb4.png");
        Bomb3 = new Image("resources/Bomb3.png");
        Bomb2 = new Image("resources/Bomb2.png");
        Bomb = new Image("resources/Images/Items/Bomb.png");
        Gas = new Image("resources/Images/Items/Gas.png");
        FemaleSexChange = new Image("resources/Images/Items/Gas.png");
        MaleSexChange = new Image("resources/Images/Items/MaleSexChange.png");
        Poison = new Image("resources/Images/Items/Poison.png");
        NoEntry = new Image("resources/Images/Items/NoEntry.png");
        Sterilisation = new Image("resources/Images/Items/Sterilisation.png");
        DeathRatItem = new Image("resources/Images/Rat/DeathRatR.png");
    }
    public static Image Bomb4() {
        return Bomb4;
    }
    public static Image Bomb3() {
        return Bomb3;
    }
    public static Image Bomb2() {
        return Bomb2;
    }
    public static Image Bomb1() {
        return Bomb1;
    }
    public static Image getBomb() {
        return Bomb;
    }
    public static Image getGas() {
        return Gas;
    }
    public static Image getFemaleSexChangeImage() {
        return FemaleSexChange;
    }
    public static Image getMaleSexChangeImage()  {
        return MaleSexChange;
    }
    public static Image getPoison() {
        return Poison;
    }
    public static Image getNoEntry() {
        return NoEntry;
    }
    public static Image getSterilisation() {
        return Sterilisation;
    }
    public static Image getDeathRatItem() {
        return DeathRatItem;
    }


}
