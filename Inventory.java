import java.util.Random;
//Might be useful

/**
 * This class represents the items the player has in their inventory.
 * @author Samuel Griffin, Ryan Wake
 */
public class Inventory {
    private static final int NUMBER_OF_ITEMS =  8;
    private static final int MAX_OF_ITEM = 4;
    private int numOfDeathRatItems = 0;
    private int numOfGasItems = 0;
    private int numOfPoisonItems = 0;
    private int numOfFemaleSexChangeItems = 0;
    private int numOfMaleSexChangeItems = 0;
    private int numOfBombItems = 0;
    private int numOfSterilisationItems = 0;
    private int numOfNoEntrySignItems = 0;


    /**
     * Decreases the number of bomb items the user has by 1.
     */
    public void decrementBomb() {
        numOfBombItems--;
    }

    /**
     * Decreases the number of gas items the user has by 1.
     */
    public void decrementGas() {
        numOfGasItems--;
    }

    /**
     * Decreases the number of poison items the user has by 1.
     */
    public void decrementPoison() {
        numOfPoisonItems--;
    }

    /**
     * Decreases the number of female sex change objects by 1.
     */
    public void decrementFemaleSexChange() {
        numOfFemaleSexChangeItems--;
    }

    /**
     * Decreases the number of male sex change items the user has by 1.
     */
    public void decrementMaleSexChange() {
        numOfMaleSexChangeItems--;
    }

    /**
     * Decreases the number of sterilisation items the user has by 1.
     */
    public void decrementSterilisation() {
        numOfSterilisationItems--;
    }

    /**
     * Decreases the number of bomb items the user has by 1.
     */
    public void decrementDeathRat() {
        numOfDeathRatItems--;
    }

    /**
     * Decreases the number of no entry sign items the user has by 1.
     */
    public void decrementNoEntrySign() {
        numOfNoEntrySignItems--;
    }


    /**
     * Gets the number of bombs the user has in their inventory.
     * @return The number of bombs.
     */
    public int getNumOfBombItems() {
        return numOfBombItems;
    }

    /**
     * Gets the number of death rats the user has in their inventory.
     * @return The number of death rats.
     */
    public int getNumOfDeathRatItems() {
        return numOfDeathRatItems;
    }

    /**
     * Gets the number of gas items the user has in their inventory.
     * @return The number of gas items.
     */
    public int getNumOfGasItems() {
        return numOfGasItems;
    }

    /**
     * Gets the number of poison items the user has in their inventory
     * @return The number of poison items.
     */
    public int getNumOfPoisonItems() {
        return numOfPoisonItems;
    }


    /**
     * Get the number of female sex change items the user has in their inventory.
     * @return The number of female sex change items
     */
    public int getNumOfFemaleSexChangeItems() {
        return numOfFemaleSexChangeItems;
    }

    /**
     * Get the number of sterilisation items the user has in their inventory.
     * @return The number of Sterilisation items.
     */
    public int getNumOfSterilisationItems() {
        return numOfSterilisationItems;
    }

    /**
     * Get the number of male sex change items the user has in their inventory.
     * @return The number of male sex change items.
     */
    public int getNumOfMaleSexChangeItems() {
        return numOfMaleSexChangeItems;
    }

    /**
     * Get the number of no entry signs the user has in their inventory.
     * @return The number of no entry signs.
     */
    public int getNumOfNoEntrySignItems() {
        return numOfNoEntrySignItems;
    }


    /**
     * Increases the number of one type of item randomly as long as the user does not already have 4 of that item.
     */
    public void addItem() {
        boolean itemAdded = false;
        if (numOfBombItems == MAX_OF_ITEM && numOfMaleSexChangeItems == MAX_OF_ITEM && numOfFemaleSexChangeItems == MAX_OF_ITEM
                && numOfGasItems == MAX_OF_ITEM && numOfPoisonItems == MAX_OF_ITEM && numOfSterilisationItems == MAX_OF_ITEM &&
                numOfDeathRatItems == MAX_OF_ITEM && numOfNoEntrySignItems == MAX_OF_ITEM) {
            itemAdded = true;
        }
        do {
            int rnd = new Random().nextInt(NUMBER_OF_ITEMS);
            if (rnd == 0 && numOfBombItems < MAX_OF_ITEM) {
                numOfBombItems++;
                itemAdded = true;
            } else if (rnd == 1 && numOfMaleSexChangeItems < MAX_OF_ITEM) {
                numOfMaleSexChangeItems++;
                itemAdded = true;
            } else if (rnd == 2 && numOfFemaleSexChangeItems < MAX_OF_ITEM) {
                numOfFemaleSexChangeItems++;
                itemAdded = true;
            } else if (rnd == 3 && numOfGasItems < MAX_OF_ITEM) {
                numOfGasItems++;
                itemAdded = true;
            } else if (rnd == 4 && numOfPoisonItems < MAX_OF_ITEM) {
                numOfPoisonItems++;
                itemAdded = true;
            } else if (rnd == 5 && numOfSterilisationItems < MAX_OF_ITEM) {
                numOfSterilisationItems++;
                itemAdded = true;
            } else if (rnd == 6 && numOfDeathRatItems < MAX_OF_ITEM) {
                numOfDeathRatItems++;
                itemAdded = true;
            } else if (rnd == 7 && numOfNoEntrySignItems < MAX_OF_ITEM) {
                numOfNoEntrySignItems++;
                itemAdded = true;
            }
        } while (!itemAdded);

    }

}
