package root;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ImageMaker.clear();
        generateForest( 10, 5);
        //generateForest( 800, 100);
        //generateForest( 20, 10);
        //generateForest( 20, 5);
        //generateForest( 20, 1);
    }

    private static void generateForest(int dispersion, int density) {
        Color backGroundColor = new Color(229, 213, 166, 255);

        LandManager landManager = new LandManager();

        Generator landGenerator = landManager.createLandGenerator(backGroundColor,2000,1400);
        Model land = landGenerator.generate(new Sprite(null, null),0,0).getLand();

        landManager.scanner(land);

        Generator forestGenerator = landManager.createForestGenerator(land);
        Generator lakeGenerator = landManager.createLakeGenerator(land);

        ImageExtractor extractor = new ImageExtractor(backGroundColor);

        ArrayList<Sprite> lakes = extractor.importImages(EntityModel.LAKE).getSprites();
        ArrayList<Sprite> treeSprites = extractor.importImages(EntityModel.TREE).getSprites();

        lakeGenerator.generate(lakes, dispersion, 6);
        forestGenerator.generate(treeSprites, dispersion, density);
        //printStat();
        //landManager.designer(land).populatePoorForest(treeSprites, 300, true);
        //landManager.scanner(land).scan();

        //ImageMaker.save(land.getImage(), "forest: " + Counter.trees + "|dispersion-" + dispersion + "|density-" + density, false);

        // can be found by the specified block
        //ArrayList<Sprite> treesToReplace = scanner.findTrees(1);
        //Sprite newTree = painter.defaultMagentaTree();
        //landManager.multiplicator().redraw(treesToReplace, newTree);

        //int treesInArea = landManager.scanner(land).countTrees(3);
        //System.out.println("found trees: " + treesInArea);
        //landManager.multiplicator().drawFrame(scanner.getLandArea(1));
        landManager.scanner(land).scan();
        //LandArea landArea = landManager.getLandArea(20);

        ImageMaker.save(land.getImage(), "forest: " + Counter.trees + "|dispersion-" + dispersion + "|density-" + density, false);

        for (LandArea landArea : landManager.getLandBlocks()) {
            landManager.designer(land).cutTrees(landArea);
        }


        //landManager.multiplicator().drawFrame(landArea);
        //landManager.scanner(land).scan(20);
        //landManager.scanner(land).getData();

        ImageMaker.save(land.getImage(), "forest: " + Counter.trees + "|dispersion-" + dispersion + "|density-" + density, false);
        //ImageMaker.save(land, landManager.getLandArea(20), "area_0", false);

        printStat();
        landGenerator.dispose();


    }

    private static void printStat() {
        System.out.println("trees: " + Counter.trees);
    }
}
