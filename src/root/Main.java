package root;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ImageMaker.clear();
        generateForest( 10, 60);
        //generateForest( 800, 100);
        //generateForest( 20, 10);
        //generateForest( 20, 5);
        //generateForest( 20, 1);
    }

    private static void generateForest(int dispersion, int density) {
        Color backGroundColor = new Color(229, 213, 166, 255);

        LandManager landManager = new LandManager();
        ImageExtractor extractor = new ImageExtractor(backGroundColor);


        // lets create a woodcutter
        Sprite woodcutterSprite = extractor.importImages(EntityModel.CHARACTER).getSprite();


        Generator landGenerator = landManager.createLandGenerator(backGroundColor,600,480);
        ILandModel land = landGenerator.generate(woodcutterSprite,0,0).getLand();
        Woodcutter woodcutter = new Woodcutter(landManager, land, woodcutterSprite);

        landManager.scanner(land);

        Generator forestGenerator = landManager.createForestGenerator(land);
        Generator lakeGenerator = landManager.createLakeGenerator(land);

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


        // here we tell the scanner to enable woodcutter search
        //landManager.scanner(land).setScanObject(EntityModel.CHARACTER);
        // start scan
        //landManager.scanner(land).scan();


        UI ui = new UI(land);
        ImageMaker.setUI(ui);
        Thread wcStart = new Thread(startWoodcutter(land, landManager, woodcutter));
        wcStart.start();


        printStat();
        //LandArea landArea = landManager.getLandArea(20);

        //ImageMaker.save(land.getImage(), "forest: " + Counter.trees + "|dispersion-" + dispersion + "|density-" + density, false);


        // lets cut trees in each land area
        //int counter = 0;
        //for (LandArea landArea : landManager.getLandBlocks()) {
        //    counter = counter + landManager.designer(land).cutTrees(landArea);
        //}

        //Logger.printLog("trees cut: " + counter);

        landManager.scanner(land).scanWoodcutter();
        //if (landManager.scanner(land).woodcutterFound()) {
        //    ImageMaker.showArea(land, landManager.getLandArea(landManager.scanner(land).woodcutterLandIndex()), landManager.multiplicator());
        //    landManager.multiplicator().drawFrame(landManager.scanner(land).getLandArea(landManager.scanner(land).woodcutterLandIndex()));
        //}


        //ui.display(land.getImage());
        //landManager.multiplicator().drawFrame(landArea);
        //landManager.scanner(land).scan(20);
        //landManager.scanner(land).getData();

        //ImageMaker.save(land.getImage(), "forest: " + Counter.trees + "|dispersion-" + dispersion + "|density-" + density, false);
        //ImageMaker.save(land, landManager.getLandArea(20), "area_0", false);

        printStat();
        landGenerator.dispose();





    }

    private static void printStat() {
        System.out.println("\ntrees: " + Counter.trees);
    }

    private static String startWoodcutter(ILandModel landModel, LandManager landManager, ICharacter character) {
        while (true) {
            character.move();
            //int currentPossition = landManager.scanner(landModel).woodcutterLandIndex();
            //if (currentPossition >= 0) {
                //LandArea area = landManager.scanner(landModel).getLandArea(currentPossition);
                //ImageMaker.showAreaWithWoodcutter(landModel, area , landManager.multiplicator());
                ImageMaker.showWorldWithWoodcutter(landModel, landManager.multiplicator());
            //}

            /*try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
