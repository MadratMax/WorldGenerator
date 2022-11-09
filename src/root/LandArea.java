package root;

import java.util.ArrayList;
import java.util.UUID;

public class LandArea {

    private int width;
    private int height;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private String id;
    private ArrayList<Sprite> trees;
    private ArrayList<Sprite> lakes;
    private ArrayList<Sprite> commonObjects;
    private int index;
    private ArrayList<Sprite> stumpSprites;

    public LandArea(int width, int height, int index) {
        this.width = width;
        this.height = height;
        this.index = index;
        this.id = UUID.randomUUID().toString();
        trees = new ArrayList<Sprite>();
        stumpSprites = new ArrayList<Sprite>();
        lakes = new ArrayList<Sprite>();
        commonObjects = new ArrayList<Sprite>();
    }

    public int getIndex() {
        return index;
    }

    public int Width() {
        return width;
    }

    public int Height() {
        return height;
    }

    public int StartX() {
        return startX;
    }

    public int StartY() {
        return startY;
    }

    public int EndX() {
        endX = startX + width;
        return endX;
    }

    public int EndY() {
        endY = startY + height;
        return endY;
    }

    public void SetWidth(int newValue) {
        width = newValue;
    }

    public void SetHeight(int newValue) {
        height = newValue;
    }

    public void SetStartX(int newValue) {
        startX = newValue;
        endX = startX + width;
    }

    public void SetStartY(int newValue) {
        startY = newValue;
        endY = startY + height;
    }

    public String Id() {
        return id;
    }

    public void setTrees(ArrayList<Sprite> treeSprites) {
        trees = treeSprites;
    }

    public void setLakes(ArrayList<Sprite> lakeSprites) {
        lakes = lakeSprites;
    }

    public void setCommonObjects(ArrayList<Sprite> commonObjectSprites) {
        commonObjects = commonObjectSprites;
    }

    public ArrayList<Sprite> trees() {
        return trees;
    }

    public ArrayList<Sprite> stumps() {
        return stumpSprites;
    }

    public ArrayList<Sprite> lakes() {
        return lakes;
    }

    public ArrayList<Sprite> commonObjects() {
        return commonObjects;
    }

    public void setStumps(ArrayList<Sprite> stumps) {
        stumpSprites = stumps;
    }
}
