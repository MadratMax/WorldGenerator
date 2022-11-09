package root;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageExtractor {

    private Sprite sprite;
    private Color backgroundColor;
    private ArrayList<Sprite> sprites;

    public ImageExtractor(Color backColor) {
        this.backgroundColor = backColor;
    }

    public ImageExtractor importImage(EntityModel entityModel) {
        String spriteFilePath = null;
        switch (entityModel) {
            case LAKE -> spriteFilePath = "./data/img/lakes/lake-1.png";
            case TREE -> spriteFilePath = "./data/img/trees/tree-1.png";
        }

        File spriteFile = new File(spriteFilePath);

        if (spriteFile.exists()) {
            sprite = processSprite(spriteFile, entityModel);
        } else {
            System.out.println("no sprites found in " + spriteFilePath);
        }

        return this;
    }

    public ImageExtractor importImages(EntityModel entityModel) {
        String spritesPath = null;
        switch (entityModel) {
            case LAKE -> spritesPath = "./data/img/lakes/";
            case TREE -> spritesPath = "./data/img/trees/";
        }

        File spriteFileDir = new File(spritesPath);
        sprites = new ArrayList<>();

        for (final File fileEntry : spriteFileDir.listFiles()) {
            if (!fileEntry.isDirectory()) {
                sprites.add(processSprite(fileEntry, entityModel));
            }
        }

        return this;
    }

    private Sprite processSprite(File file, EntityModel entityModel) {
        try {
            BufferedImage spriteImg = ImageIO.read(file);
            return new Sprite(entityModel, spriteImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Sprite getSprite() {
        //normalize(sprite.getImage());
        return sprite;
    }

    public ArrayList<Sprite> getSprites() {
        //normalize(sprite.getImage());
        return sprites;
    }

    private void normalize(BufferedImage spriteImg) {
        Graphics2D g2d = spriteImg.createGraphics();
        g2d.setColor(backgroundColor);
    }
}
