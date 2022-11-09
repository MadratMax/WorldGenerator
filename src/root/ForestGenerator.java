package root;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ForestGenerator implements Generator {

    private final Model land;
    private final Multiplicator multiplicator;
    private Sprite tree;
    private final Randomizator random;

    public ForestGenerator(Multiplicator multiplicator, Model land) {
        this.land = land;
        this.multiplicator = multiplicator;
        random = new Randomizator();
    }

    public Generator generate(Sprite sprite, int dispersion, int density) {
        tree = sprite;
        int startX = 0;
        int startY = 0;
        if (random.getRandomFrom(2) == 1)
            startX = (land.getWidth()/2 + random.getRandomFrom(land.getWidth()/2));
        else
            startX = (land.getWidth()/2 - random.getRandomFrom(land.getWidth()/2));
        if (random.getRandomFrom(2) == 1)
            startY = (land.getHeight()/2 + random.getRandomFrom(land.getWidth()/2));
        else
            startY = (land.getHeight()/2 - random.getRandomFrom(land.getWidth()/2));

        int tempDispersion = startX + startY + (density*100);
        int tempX = startX;
        int tempY = startY;
        for (int i = 0; i < density; i ++) {
            multiplicator.drawMultipleObjects(tree, tempX, tempY, dispersion);
            tempX = random.getRandomFrom(tempDispersion);
            tempY = random.getRandomFrom(tempDispersion);
            dispersion = random.getRandomFrom(tempDispersion);
        }

        return this;
    }

    public Generator generate(ArrayList<Sprite> sprites, int dispersion, int density) {
        int startX = 0;
        int startY = 0;
        if (random.getRandomFrom(2) == 1)
            startX = (land.getWidth()/2 + random.getRandomFrom(land.getWidth()/2));
        else
            startX = (land.getWidth()/2 - random.getRandomFrom(land.getWidth()/2));
        if (random.getRandomFrom(2) == 1)
            startY = (land.getHeight()/2 + random.getRandomFrom(land.getWidth()/2));
        else
            startY = (land.getHeight()/2 - random.getRandomFrom(land.getWidth()/2));

        int tempDispersion = startX + startY + (density*100);
        int tempX = startX;
        int tempY = startY;
        for (int i = 0; i < density; i ++) {
            multiplicator.drawMultipleObjects(sprites, tempX, tempY, dispersion);
            tempX = random.getRandomFrom(tempDispersion);
            tempY = random.getRandomFrom(tempDispersion);
            dispersion = random.getRandomFrom(tempDispersion);
        }

        return this;
    }

    public Model getLand() {
        return land;
    }

    public void dispose() {
        Counter.dispose();
    }
}
