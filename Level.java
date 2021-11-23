
import java.util.HashMap;

public class Level {
    private Integer sizeLevel, levelWidth, levelHeight, maxPopulation, ratPopulationRate, secExpected, time;
    private HashMap<Integer, Integer> ratsY, ratsX, itemsX, itemsY = new HashMap<Integer, Integer>();

    public Level(){

 }
 public HashMap<Integer, Integer> getItemsX() {
     return itemsX;
 }

    public HashMap<Integer, Integer> getItemsY() {
        return itemsY;
    }

    public HashMap<Integer, Integer> getRatsX() {
        return ratsX;
    }

    public HashMap<Integer, Integer> getRatsY() {
        return ratsY;
    }

    public Integer getLevelHeight() {
        return levelHeight;
    }

    public void setLevelHeight(Integer levelHeight) {
        this.levelHeight = levelHeight;
    }

    public Integer getLevelWidth() {
        return levelWidth;
    }

    public Integer getMaxPopulation() {
        return maxPopulation;
    }

    public Integer getRatPopulationRate() {
        return ratPopulationRate;
    }

    public Integer getSecExpected() {
        return secExpected;
    }

    public Integer getSizeLevel() {
        return sizeLevel;
    }

    public Integer getTime() {
        return time;
    }

    public void setItemsX(HashMap<Integer, Integer> itemsX) {
        this.itemsX = itemsX;
    }

    public void setItemsY(HashMap<Integer, Integer> itemsY) {
        this.itemsY = itemsY;
    }

    public void setLevelWidth(Integer levelWidth) {
        this.levelWidth = levelWidth;
    }

    public void setMaxPopulation(Integer maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public void setRatPopulationRate(Integer ratPopulationRate) {
        this.ratPopulationRate = ratPopulationRate;
    }

    public void setRatsX(HashMap<Integer, Integer> ratsX) {
        this.ratsX = ratsX;
    }

    public void setRatsY(HashMap<Integer, Integer> ratsY) {
        this.ratsY = ratsY;
    }

    public void setSecExpected(Integer secExpected) {
        this.secExpected = secExpected;
    }

    public void setSizeLevel(Integer sizeLevel) {
        this.sizeLevel = sizeLevel;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
