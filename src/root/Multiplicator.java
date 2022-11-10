package root;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Multiplicator {

    private final Randomizator random;
    private final ILandModel landModel;
    private final Color backGroundColor;
    private Graphics2D world2D;
    private Sprite lastSprite;
    private int lastStartX;
    private int lastStartY;
    private int lastCount;

    public Multiplicator(ILandModel model) {
        this.landModel = model;
        this.world2D = model.getModel();
        random = new Randomizator();
        backGroundColor = model.getBackgroundColor();
    }

    public void redraw(Sprite sprite, Sprite newSprite) {
        int x = sprite.getX();
        int y = sprite.getY();
        landModel.removeSprite(sprite);
        world2D.drawImage(newSprite.getImage(), x, y, null);
        landModel.addSprite(newSprite, x, y);
        if (newSprite.getEntityModel().equals(EntityModel.TREE)) {
            Counter.trees++;
        }
        if (newSprite.getEntityModel().equals(EntityModel.LAKE)) {
            Counter.lakes++;
        }
        if (newSprite.getEntityModel().equals(EntityModel.RIVER)) {
            Counter.rivers++;
        }
    }

    public void redraw(ArrayList<Sprite> sprites, Sprite newSprite) {
        for (Sprite sprite: sprites) {
            int x = sprite.getX();
            int y = sprite.getY();
            landModel.removeSprite(sprite);
            world2D.drawImage(newSprite.getImage(), x, y, null);
            landModel.addSprite(newSprite, x, y);
            if (newSprite.getEntityModel().equals(EntityModel.TREE)) {
                Counter.trees++;
            }
            if (newSprite.getEntityModel().equals(EntityModel.LAKE)) {
                Counter.lakes++;
            }
            if (newSprite.getEntityModel().equals(EntityModel.RIVER)) {
                Counter.rivers++;
            }
        }
    }

    public int drawMultipleObjects(Sprite sprite, int startX, int startY, int count) {
        lastSprite = sprite;
        lastStartX = startX;
        lastStartY = startY;
        lastCount = count;
        int total = 0;

        for (int i = 0 ;i < count; i++) {
            int newX = 0;
            int newY = 0;
            if (random.getRandomOperator().equals(MathOperator.PLUS)) {
                newX = random.getRandomFrom(random.getRandomFrom(count));
                newX = Math.abs(newX);
            }
            else {
                newX = random.getRandomFrom(random.getRandomFrom(count));
                newX = Math.abs(newX);
            }
            if (random.getRandomOperator().equals(MathOperator.PLUS)) {
                newY = random.getRandomFrom(count + random.getRandomFrom(count));
                newY = Math.abs(newY);
            }
            else {
                newY = random.getRandomFrom(count - random.getRandomFrom(count));
                newY = Math.abs(newY);
            }

            int newY2 = 0;
            if (random.getRandomOperator().equals(MathOperator.PLUS))
                newY2 = startY+newY;
            else
                newY2 = startY-newY;

            if (random.getRandomOperator().equals(MathOperator.PLUS)) {
                draw(sprite, startX + newX, newY2);
            }
            else {
                draw(sprite, startX - newX, newY2);
            }

            total = i+1;
        }

        return total;
    }

    public int drawMultipleObjects(ArrayList<Sprite> sprites, int startX, int startY, int count) {
        lastSprite = null;
        lastStartX = startX;
        lastStartY = startY;
        lastCount = count;
        int total = 0;

        for (int i = 0 ;i < count; i++) {
            lastSprite = sprites.get(random.getRandomFrom(sprites.size()));
            int newX = 0;
            int newY = 0;
            if (random.getRandomOperator().equals(MathOperator.PLUS)) {
                newX = random.getRandomFrom(random.getRandomFrom(count));
                newX = Math.abs(newX);
            }
            else {
                int temp = random.getRandomFrom(count);
                newX = random.getRandomFrom(temp);
                newX = Math.abs(newX);
            }
            if (random.getRandomOperator().equals(MathOperator.PLUS)) {
                newY = random.getRandomFrom(count + random.getRandomFrom(count));
                newY = Math.abs(newY);
            }
            else {
                newY = random.getRandomFrom(count - random.getRandomFrom(count));
                newY = Math.abs(newY);
            }

            int newY2 = 0;
            if (random.getRandomOperator().equals(MathOperator.PLUS))
                newY2 = startY+newY;
            else
                newY2 = startY-newY;

            if (random.getRandomOperator().equals(MathOperator.PLUS)) {
                draw(lastSprite, startX + newX, newY2);
            }
            else {
                draw(lastSprite, startX - newX, newY2);
            }

            total = i+1;
        }

        return total;
    }

    public int drawMultipleObjects(ArrayList<Sprite> sprites, int startX, int startY, int endX, int endY, int amount) {
        lastSprite = null;
        lastStartX = startX;
        lastStartY = startY;
        int lastEndX = endX;
        int lastEndY = endY;
        int total = 0;

        for (int i = 0 ; i < amount; i++) {
            if (sprites.size() == 0) {
                return 0;
            }
            lastSprite = sprites.get(random.getRandomFrom(sprites.size()));
            int randomX = random.getRandomFromRange(startX, endX);
            int randomY = random.getRandomFromRange(startY, endY);
            draw(lastSprite, randomX, randomY);
            total = i+1;
        }

        return total;
    }

    public void multiplyLast(int times) {
        for (int i = 0; i < times; i++) {
            drawMultipleObjects(lastSprite, lastStartX, lastStartY, lastCount);
        }
    }

    private void draw(Sprite sprite, int x, int y) {
        if (x >= landModel.getWidth())
            x = random.getRandomFrom(landModel.getWidth());
        if (y >= landModel.getHeight())
            y = random.getRandomFrom(landModel.getHeight());
        //x = Math.abs(x);
        //y = Math.abs(y);

        if (x < 0 || y < 0) {
            return;
        }

        world2D.drawImage(sprite.getImage(), x, y, null);
        landModel.addSprite(sprite, x, y);
        if (sprite.getEntityModel().equals(EntityModel.TREE)) {
            Counter.trees++;
            //Logger.printLog("tree added.  x: " + x + " y: " + y);
        }
        if (sprite.getEntityModel().equals(EntityModel.LAKE)) {
            Counter.lakes++;
        }
        if (sprite.getEntityModel().equals(EntityModel.RIVER)) {
            Counter.rivers++;
        }
    }

    public void drawStump(Sprite sprite) {

        if (sprite.getImage() == null) {
            return;
        }

        int x = sprite.getImage().getMinX();
        int y = sprite.getImage().getMinY();
        int w = sprite.getImage().getWidth();
        int h = sprite.getImage().getHeight()-3;

        BufferedImage stump = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = stump.createGraphics();

        g2d.setColor(backGroundColor);
        g2d.fillRect(x, y, w, h);
        g2d.setColor(backGroundColor);
        world2D.drawImage(stump, sprite.getX(), sprite.getY(), null);
        Sprite stumpSprite = new Sprite(EntityModel.STUMP, stump);
        landModel.removeSprite(sprite);
        landModel.addSprite(stumpSprite, sprite.getX(), sprite.getY());
        if (sprite.getEntityModel().equals(EntityModel.TREE)) {
            Counter.trees--;
        }
    }

    public void drawFrame(int startX, int startY, int width, int height) {
        world2D.setColor(Color.black);
        world2D.drawLine(startX, startY, width, height);
    }

    public void drawFrame(LandArea landArea) {
        int x1 = landArea.StartX();
        int y1 = landArea.StartY();
        int x2 = landArea.EndX();
        int y2 = landArea.StartY();

        world2D.setColor(Color.black);
        world2D.drawLine(x1, y1, x2, y2);

        x1 = landArea.StartX();
        y1 = landArea.EndY();
        x2 = landArea.EndX();
        y2 = landArea.EndY();

        world2D.setColor(Color.black);
        world2D.drawLine(x1, y1, x2, y2);

        x1 = landArea.StartX();
        y1 = landArea.StartY();
        x2 = landArea.StartX();
        y2 = landArea.EndY();

        world2D.setColor(Color.black);
        world2D.drawLine(x1, y1, x2, y2);

        x1 = landArea.EndX();
        y1 = landArea.StartY();
        x2 = landArea.EndX();
        y2 = landArea.EndY();

        world2D.setColor(Color.black);
        world2D.drawLine(x1, y1, x2, y2);
    }

    public BufferedImage drawArea(ILandModel landModel, LandArea landArea, Sprite sprite) {

        if (sprite.getImage() == null) {
            return null;
        }

        BufferedImage tempImg0 = deepCopy(landModel.getImage());
        Graphics2D tempGraphics = tempImg0.createGraphics();

        BufferedImage tempImg = tempImg0.getSubimage(landArea.StartX(), landArea.StartY(), landArea.Width(), landArea.Height());

        tempGraphics.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null);

        return tempImg;
    }

    public BufferedImage drawWorldWithWoodcutter(ILandModel landModel, Sprite sprite) {

        if (sprite.getImage() == null) {
            return null;
        }

        BufferedImage tempImg0 = deepCopy(landModel.getImage());
        Graphics2D tempGraphics = tempImg0.createGraphics();

        tempGraphics.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null);

        return tempImg0;
    }

    private Sprite flip(Sprite sprite) {
        BufferedImage bi = new BufferedImage(sprite.getImage().getWidth(),sprite.getImage().getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = sprite.getImage().getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < sprite.getImage().getHeight();yy++){
                sprite.getImage().setRGB(sprite.getImage().getWidth()-xx, yy, sprite.getImage().getRGB(xx, yy));
            }
        }
        Sprite newSp = new Sprite(sprite.getEntityModel(), bi);
        return newSp;
    }

    private BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
