package root;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteObjectsMap {

    private ArrayList<Sprite> spritesMap;

    public SpriteObjectsMap() {
        this.spritesMap = new ArrayList<Sprite>();
    }

    public void addSprite(BufferedImage image, int x, int y) {
        Sprite sprite = new Sprite(EntityModel.TREE, image);
        sprite.addCoordinates(x, y);
        spritesMap.add(sprite);
    }

    public void removeSprite(Sprite spriteToRemove) {
        List<Sprite> toRemove = new ArrayList<Sprite>();

        for (Sprite sprite: spritesMap) {
            if (sprite.equals(spriteToRemove)) {
                toRemove.add(sprite);
            }
        }

        spritesMap.removeAll(toRemove);
    }

    public ArrayList<Sprite> getSpritesMap() {
        return spritesMap;
    }

    public boolean getSpriteEntity(EntityModel entityModel, int x, int y) {
        for (Sprite sprite: spritesMap) {
            if (sprite.getEntityModel().equals(entityModel)) {
                if (sprite.getX() == x && sprite.getY() == y) {
                    return true;
                }
            }
        }
        return false;
    }

    public Sprite getSpriteByCoordinates(int x, int y) {
        for (Sprite sprite: spritesMap) {
            if (sprite.getX() == x && sprite.getY() == y) {
                return sprite;
            }
        }
        return null;
    }

    public Sprite getSpriteByCoordinates(EntityModel entityModel, int x, int y) {
        for (Sprite sprite: spritesMap) {
            if (sprite.getEntityModel().equals(entityModel) && sprite.getX() == x && sprite.getY() == y) {
                return sprite;
            }
        }
        return null;
    }
}
