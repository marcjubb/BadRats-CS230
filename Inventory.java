import java.util.Iterator;
import java.util.Random;
//Might be useful
public class Inventory {

    private int numOfDeathRatItems;
    private int numOfGasItems;
    private int numOfPoisonItems;
    private int numOfFemaleSexChangeItems;
    private int numOfMaleSexChangeItems;
    private int numOfBombItems;
    private int numOfSterilisationItems;
    private int numOfNoEntrySignItems;

    private int numAvaliable =  7;
    private static int tickRateCounter = 0;

    private boolean isBombAvailable;
    private boolean isDeathRatAvailable;
    private boolean isGasAvailable;
    private boolean isPoisonAvailable;
    private boolean isFemaleSexAvailable;
    private boolean isMaleSexAvailable;
    private boolean isSterilisationAvailable;
    private boolean isNoEntrySignAvailable;

    public int getNumOfBombItems() {
        return numOfBombItems;
    }

    public int getNumOfDeathRatItems() {
        return numOfDeathRatItems;
    }
    public int getNumOfGasItems() {
        return numOfGasItems;
    }

    public int getNumOfPoisonItems() {
        return numOfPoisonItems;
    }

    public int getNumOfFemaleSexChangeItems() {
        return numOfFemaleSexChangeItems;
    }

    public int getNumOfSterilisationItems() {
        return numOfSterilisationItems;
    }

    public int getNumOfMaleSexChangeItems() {
        return numOfMaleSexChangeItems;
    }

    public int getNumOfNoEntrySignItems() {
        return numOfNoEntrySignItems;
    }

    public Inventory() {
        this.numOfBombItems = 0;
        this.numOfDeathRatItems = 0;
        this.numOfGasItems = 0;
        this.numOfFemaleSexChangeItems = 0;
        this.numOfMaleSexChangeItems = 0;
        this.numOfNoEntrySignItems = 0;
        this.numOfSterilisationItems = 0;
    }
    public void numberOfEachItem() {
        for(Item item : Level.getItemList()) {
            if (item instanceof Bomb) {
                numOfBombItems++;
                if (numOfBombItems >= 4) {
                    isBombAvailable = false;
                    numAvaliable--;
                }
            } else if(item instanceof DeathRatItem) {
                numOfDeathRatItems++;
                if (numOfDeathRatItems >= 4) {
                    isDeathRatAvailable = false;
                    numAvaliable--;
                }
            } else if(item instanceof FemaleSexChange) {
                numOfFemaleSexChangeItems++;
                if (numOfFemaleSexChangeItems >= 4) {
                    isFemaleSexAvailable = false;
                    numAvaliable--;
                }
            } else if(item instanceof Gas) {
                numOfGasItems++;
                if (numOfGasItems >= 4) {
                    isGasAvailable = false;
                    numAvaliable--;
                }
            } else if(item instanceof Poison) {
                numOfPoisonItems++;
                if (numOfPoisonItems >= 4) {
                    isPoisonAvailable = false;
                    numAvaliable--;

                }
            } else if(item instanceof MaleSexChange) {
                numOfMaleSexChangeItems++;
                if (numOfMaleSexChangeItems >= 4) {
                    isMaleSexAvailable = false;
                    numAvaliable--;
                }
            } else if((item instanceof Sterilisation)) {
                numOfSterilisationItems++;
                if (numOfSterilisationItems >= 4) {
                    isSterilisationAvailable = false;
                    numAvaliable--;
                }
            }
            else if(item instanceof NoEntrySign){
                numOfNoEntrySignItems++;
                if(numOfNoEntrySignItems >= 4){
                    isNoEntrySignAvailable = false;
                    numAvaliable--;
                }
            }
        }
    }
    public void addItem(int x, int y) {
        numberOfEachItem();
        int rnd = new Random().nextInt(numAvaliable);

        Iterator<Item> iteratorItem = Level.getItemList().listIterator();
        while (iteratorItem.hasNext()) {
            Item item = iteratorItem.next();
            if(isBombAvailable && rnd == 0){
                Level.getItemList().add(new Sterilisation(x, y));
            }
            else if(isDeathRatAvailable && rnd == 1){
                Level.getItemList().add(new DeathRatItem(x, y));
            }
            else if(isGasAvailable && rnd == 3){
                Level.getItemList().add(new Gas(x, y));
            }
            else if(isFemaleSexAvailable && rnd == 4){
                Level.getItemList().add(new DeathRatItem(x, y));
            }
            else if(isPoisonAvailable && rnd == 5){
                Level.getItemList().add(new Poison(x, y));
            }
            else if(isMaleSexAvailable && rnd == 6){
                Level.getItemList().add(new MaleSexChange(x, y));
            }
            else if(isSterilisationAvailable && rnd == 7){
                Level.getItemList().add(new Sterilisation(x, y));
            }


        }

    }

}
