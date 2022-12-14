package root;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Model {

    Graphics2D getModel();
    BufferedImage getImage();
    Color getBackgroundColor();
    void addSprite(Sprite sprite, int x, int y);
    void removeSprite(Sprite sprite);
    SpriteObjectsMap getSpritesMap();
    int getWidth();
    int getHeight();
}
