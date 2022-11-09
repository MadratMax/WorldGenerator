package root;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Painter {

    private final Color backgroundColor;
    private Sprite sprite;

    public Painter(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Sprite createTree(int index) {
        switch (index) {
            case 0: {
                return customTree("");
            }
            case 1: {
                return defaultTree();
            }
            case 2: {
                return defaultTree2();
            }
            case 3: {
                return defaultTree3();
            }
        }

        return defaultTree();
    }

    private Sprite customTree(String spritePath) {
        sprite = null;
        try {
            File f = new File(spritePath);
            BufferedImage image = ImageIO.read(f);
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            sprite = new Sprite(EntityModel.TREE, 0, bufferedImage);
            return sprite;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return sprite;
    }

    private Sprite defaultTree() {
        int width = 11;
        int height = 10;

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);

        // create a circle with black
        g2d.setColor(Color.green);
        g2d.fillOval(0, 0, width, height/2);
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(4, height/2 + 1, width/5,height/2);

        // create a string with yellow
        //g2d.setColor(Color.yellow);
        //g2d.drawString("Java Code Geeks", 50, 120);

        sprite = new Sprite(EntityModel.TREE, 1, bufferedImage);
        return sprite;
    }

    private Sprite defaultTree2() {
        int width = 11;
        int height = 10;

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.GREEN);
        int radius = 3;
        g2d.fillOval((width / 2) - radius, (height / 2) - (radius * 2), radius * 2, radius * 2);
        g2d.fillOval((width / 2) - radius, (height / 2) - radius, radius * 2, radius * 2);
        g2d.fillOval((width / 2) - (radius * 2), (height / 2) - radius, radius * 2, radius * 2);
        g2d.fillOval((width / 2), (height / 2) - radius, radius * 2, radius * 2);
        g2d.setColor(new Color(139, 69, 19));
        width = 1;
        height = 4;
        g2d.fillRect(4, height + 4, width,height);

        sprite = new Sprite(EntityModel.TREE, 2, bufferedImage);
        return sprite;
    }

    public Sprite defaultTree3() {
        int width = 11;
        int height = 10;

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);

        // create a circle with black
        g2d.setColor(new Color(238, 127, 13, 255));
        g2d.fillOval(0, 0, width, height/2);
        g2d.setColor(new Color(139, 71, 23));
        g2d.fillRect(4, height/2 + 1, width/5,height/2);

        // create a string with yellow
        //g2d.setColor(Color.yellow);
        //g2d.drawString("Java Code Geeks", 50, 120);

        sprite = new Sprite(EntityModel.TREE, 3, bufferedImage);
        return sprite;
    }
}
