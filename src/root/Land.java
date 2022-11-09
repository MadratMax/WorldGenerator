package root;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Land implements Model {

    private final BufferedImage image;
    private Graphics2D land2D;
    EntityModel entityModel;
    private final int width;
    private final int height;
    private Color backGroundColor;
    private SpriteObjectsMap spriteObjectsMap;

    public Land(Graphics2D land2D, BufferedImage image, Color backGroundColor, int width, int height) {
        this.land2D = land2D;
        this.height = height;
        this.width = width;
        this.entityModel = EntityModel.LAND;
        this.image = image;
        this.backGroundColor = backGroundColor;
        this.spriteObjectsMap = new SpriteObjectsMap();
    }

    public Graphics2D getModel() {
        return land2D;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void addSprite(Sprite sprite, int x, int y) {
        spriteObjectsMap.addSprite(sprite.getImage(), x, y);
    }

    public void removeSprite(Sprite sprite) {
        spriteObjectsMap.removeSprite(sprite);
    }

    public SpriteObjectsMap getSpritesMap() {
        return spriteObjectsMap;
    }

    public Color getBackgroundColor() {
        return backGroundColor;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
