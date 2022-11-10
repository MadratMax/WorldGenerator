package root;

import java.util.ArrayList;
import java.util.Random;

public class Designer {

    private ILandModel landModel;
    private Scanner scanner;
    private Multiplicator multiplicator;

    public Designer(ILandModel landModel, Scanner scanner, Multiplicator multiplicator) {
        this.landModel = landModel;
        this.scanner = scanner;
        this.multiplicator = multiplicator;
    }

    public LandArea getPoorForestArea(boolean searchEmptyArea) {
        LandArea landArea = scanner.scan().getMinTreesLandArea(searchEmptyArea);
        return landArea;
    }

    public LandArea populatePoorForest(ArrayList<Sprite> treeSprites, int force, boolean searchEmptyArea) {
        LandArea landArea = scanner.scan().getMinTreesLandArea(searchEmptyArea);
        Logger.printLog("going to populate poor forest in land area " + landArea.getIndex());
        return makeNewTrees(landArea, treeSprites, force);
    }

    public LandArea populateForestInArea(ArrayList<Sprite> treeSprites, int force, int areaIndex) {
        LandArea landArea = scanner.getLandArea(areaIndex);
        Logger.printLog("going to populate poor forest in land area " + landArea.getIndex());
        return makeNewTrees(landArea, treeSprites, force);
    }

    public LandArea populateForestRandom(ArrayList<Sprite> treeSprites, int force) {
        LandArea landArea = scanner.getLandArea(new Random().nextInt(100));
        Logger.printLog("going to populate poor forest in land area " + landArea.getIndex());
        return makeNewTrees(landArea, treeSprites, force);
    }

    private LandArea makeNewTrees(LandArea landArea, ArrayList<Sprite> treeSprites, int force) {
        Logger.printLog("current forrest contains " + treeSprites.size() + " types of trees");
        int totalDrawn = multiplicator.drawMultipleObjects(treeSprites, landArea.StartX(), landArea.StartY(), landArea.EndX(), landArea.EndY(), force);
        Logger.printLog("new trees added ("+ totalDrawn + ")");
        return landArea;
    }

    public LandArea cutATree(LandArea landArea, Sprite treeSprite) {
        multiplicator.drawStump(treeSprite);
        Logger.printLog("a tree was cut (x: " + treeSprite.getX() + " y: " + treeSprite.getY());

        return landArea;
    }

    public int cutATree(LandArea landArea, int x, int y) {
        for (Sprite sprite : landArea.trees()) {
            if (sprite.getX() == x && sprite.getY() == y) {
                multiplicator.drawStump(sprite);
                Logger.printLog("a tree was cut (x: " + sprite.getX() + " y: " + sprite.getY());
                ImageMaker.showWorldWithWoodcutter(landModel, multiplicator, false);
                return 1;
            }
        }

        return 0;
    }

    public int cutTrees(LandArea landArea) {
        int counter = 0;
        for (Sprite sprite : landArea.trees()) {
            if (sprite.getEntityModel().equals(EntityModel.TREE)) {
                multiplicator.drawStump(sprite);
                //Logger.printLog("a tree was cut (x: " + sprite.getX() + " y: " + sprite.getY());
                counter++;
            }

        }


        return counter;
    }

    public void addLake(LandArea landArea) {

    }
}
