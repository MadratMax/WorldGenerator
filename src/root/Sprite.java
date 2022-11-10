package root;

import java.awt.image.BufferedImage;

public class Sprite implements ISprite {

    private EntityModel entityModel;
    private BufferedImage sprite;
    private int type;
    private int x;
    private int y;
    private int areaIndex = -1;

    public Sprite(EntityModel model, int type, BufferedImage image) {
        this.entityModel = model;
        this.type = type;
        this.sprite = image;
    }

    public Sprite(EntityModel model, BufferedImage image) {
        this.entityModel = model;
        this.type = 0;
        this.sprite = image;
    }

    public void addCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(int areaIndex) {
        this.areaIndex = areaIndex;
    }

    public int getLocation() {
        return areaIndex;
    }

    public BufferedImage getImage() {
        return sprite;
    }

    public EntityModel getEntityModel() {
        return entityModel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }
}
