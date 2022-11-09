package root;

import java.awt.*;

public class LandManager {

    Painter painter;
    private Multiplicator multiplicator;
    private Scanner scanner;
    private Designer designer;
    private int maxBlocks;
    private LandArea[] blocks;

    public LandManager() {

    }

    public Generator createForestGenerator(Model land) {
        if (land != null) {
            this.multiplicator = new Multiplicator(land);
            return new ForestGenerator(multiplicator, land);
        }

        System.out.println("failed to create forest gen");
        return null;
    }

    public Generator createLakeGenerator(Model land) {
        if (land != null) {
            this.multiplicator = new Multiplicator(land);
            return new LakeGenerator(multiplicator, land);
        }

        System.out.println("failed to create lake gen");
        return null;
    }

    public Generator createLandGenerator(Color backGroundColor, int width, int height) {
        if (backGroundColor != null) {
            return new LandGenerator(backGroundColor,width, height);
        }

        System.out.println("failed to create land gen");
        return null;
    }

    public Designer designer(Model landModel) {
        if (designer == null) {
            designer = new Designer(landModel, scanner(landModel), multiplicator);
        }

        return designer;
    }

    public Scanner scanner(Model landModel) {
        if (scanner == null) {
            maxBlocks = 200;
            LandSplitter splitter = new LandSplitter(landModel, maxBlocks);
            blocks = splitter.split();
            maxBlocks = splitter.getBlockNumber();
                    scanner = new Scanner(landModel, blocks);
        }

        return scanner;
    }

    public Multiplicator multiplicator() {
        return multiplicator;
    }

    public LandArea getLandArea(int index) {
        return scanner.getLandArea(index);
    }

    public LandArea [] getLandBlocks() {
        return blocks;
    }

    public int getMaxBlocks() {
        return maxBlocks;
    }
}
