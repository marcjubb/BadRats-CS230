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
    protected static Image Gas;
    protected static Image FemaleSexChange;
    protected static Image MaleSexChange;
    protected static Image Poison;
    protected static Image NoEntry;
    protected static Image Sterilisation;

    static{

        Bomb4 = new Image("resources/Bomb4.png");
        Bomb3 = new Image("resources/Bomb3.png");
        Bomb2 = new Image("resources/Bomb2.png");
        Bomb1 = new Image("resources/Bomb1.png");
        Gas = new Image("resources/Gas.png");
        FemaleSexChange = new Image("resources/FemaleSexChange.png");
        MaleSexChange = new Image("resources/MaleSexChange.png");
        Poison = new Image("resources/Poison.png");
        NoEntry = new Image("resources/NoEntry.png");
        Sterilisation = new Image("resources/Sterilisation.png");
    }
    public static Image Bomb4(){
        return Bomb4;
    }
    public static Image Bomb3(){
        return Bomb3;
    }
    public static Image Bomb2(){
        return Bomb2;
    }
    public static Image Bomb1(){
        return Bomb1;
    }
    public static Image Gas(){
        return Gas;
    }
    public static Image getFemaleSexChangeImage(){
        return FemaleSexChange;
    }
    public static Image getMaleSexChangeImage() {
        return MaleSexChange;
    }
    public static Image Poison(){
        return Poison;
    }
    public static Image NoEntry(){
        return NoEntry;
    }
    public static Image Sterilisation(){
        return Sterilisation;
    }


}
