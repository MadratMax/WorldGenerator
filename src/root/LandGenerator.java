package root;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LandGenerator implements Generator {

    private final int width;
    private final int height;
    private Color backGroundColor;
    private Model landModel;

    public LandGenerator(Color groundColor, int width, int height) {
        this.width = width;
        this.height = height;
        this.backGroundColor = groundColor;
    }

    private BufferedImage bi;
    private Graphics2D land;

    public Model getLand() {
        return landModel;
    }

    public void dispose() {
        this.land.dispose();
        bi = null;
        Counter.dispose();
    }

    public Generator generate(Sprite sprite, int count, int density) {
        bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bi.createGraphics();

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        backGroundColor = new Color(229, 213, 166, 255);

        GradientPaint gp = new GradientPaint(0, 0,
                backGroundColor, 0, height/2, backGroundColor, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(backGroundColor);

        land = g2d;
        landModel = new Land(land, bi, backGroundColor, width, height);
        return this;
    }

    public Generator generate(ArrayList<Sprite> sprites, int count, int density) {
        return generate(sprites.get(0), count, density);
    }
}
