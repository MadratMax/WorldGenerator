package root;

import java.awt.image.BufferedImage;

public interface ISprite {
    void addCoordinates(int x, int y);
    BufferedImage getImage();
    EntityModel getEntityModel();
    void setLocation(int areaIndex);
    int getLocation();
    int getX();
    int getY();
    int getType();
}
