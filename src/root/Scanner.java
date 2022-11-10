package root;

import java.util.ArrayList;
import java.util.Arrays;

import static root.Logger.printProgress;

public class Scanner {

    private int pieces = 100;
    private LandArea [] landBlocks;
    private ILandModel landModel;
    private SpriteObjectsMap spriteObjectsMap;
    private boolean scanTrees;
    private boolean scanLakes;
    private boolean scanRivers;
    private boolean scanWater;
    private boolean scanComObj;
    ArrayList<Sprite> treeSprites = null;
    ArrayList<Sprite> lakeSprites = null;
    ArrayList<Sprite> riverSprites = null;
    ArrayList<Sprite> comObjSprites = null;
    private LandArea lastScannedArea;
    private ArrayList<Sprite> stumpSprites = null;
    private int woodcutterLandAreaIndex;
    private boolean scanCharacter;
    private boolean woodcutterFound;

    public Scanner(ILandModel landModel, LandArea [] landBlocks) {
        this.landModel = landModel;
        this.spriteObjectsMap = landModel.getSpritesMap();
        this.landBlocks = landBlocks;
    }

    public void getData() {
        if (lastScannedArea == null) {
            Logger.printLog("no data found");
            return;
        }
        Logger.printLog("last scanned area: " + lastScannedArea.getIndex());
        Logger.printLog("trees: " + lastScannedArea.trees().size());
        Logger.printLog("stumps: " + lastScannedArea.stumps().size());
        Logger.printLog("lakes: " + lastScannedArea.lakes().size());
        //Logger.printLog("rivers: " + lastScannedArea.());
        Logger.printLog("com objects: " + lastScannedArea.commonObjects().size());
    }

    public boolean woodcutterFound() {
        return woodcutterFound;
    }

    public int woodcutterLandIndex() {
        return woodcutterLandAreaIndex;
    }

    private LandArea[] splitAreaToBlocks(LandArea landArea) {
        int widthStep = Math.abs(landArea.Width()/(pieces/10));
        int heightStep = Math.abs(landArea.Height()/(pieces/10));

        LandArea [] landAreaBlockArray = new LandArea[pieces];

        if (widthStep <= 0 || heightStep <= 0) {
            return landAreaBlockArray;
        }

        if (heightStep < 0) {
            return landAreaBlockArray;
        }
        int lastStartX = landArea.StartX();
        int lastStartY = landArea.StartY();

        boolean lastRow = false;

        for (int i = 0; i < pieces; i++) {
            landAreaBlockArray[i] = new LandArea(widthStep, heightStep, i);
            landAreaBlockArray[i].SetStartX(lastStartX);
            landAreaBlockArray[i].SetStartY(lastStartY);

            if (lastStartX + landAreaBlockArray[i].Width() >= landArea.Width()) {
                landAreaBlockArray[i].SetWidth(landArea.Width() - lastStartX);

                if (lastRow) {
                    Logger.printLog(pieces + " blocks have been created. block size: w: " + widthStep + " h: " + heightStep);
                    return landAreaBlockArray;
                }

                lastStartX = 0;
                lastStartY = lastStartY + heightStep + 1;
            } else {
                lastStartX = lastStartX + widthStep + 1;
            }
            if (lastStartY + landAreaBlockArray[i].Height() >= landArea.Width()) {
                heightStep = landArea.Height() - lastStartY;
                landAreaBlockArray[i].SetHeight(heightStep);
                lastRow = true;
            }
        }

        Logger.printLog(pieces + " blocks have been created. block size: w: " + widthStep + " h: " + heightStep);
        return landAreaBlockArray;
    }

    public LandArea getMinLakesLandArea() {
        int min = Integer.MAX_VALUE;
        LandArea minLakesArea = null;

        for (LandArea area: landBlocks) {
            if (area.lakes() != null && area.lakes().size() < min) {
                min = area.lakes().size();
                minLakesArea = area;
            }
        }

        Logger.printLog("found an area with min lakes. lakes: " + minLakesArea.trees().size() +  ". LandArea: " + minLakesArea.getIndex());
        return minLakesArea;
    }

    public LandArea getMinTreesLandArea(boolean searchEmptyArea) {
        int min = Integer.MAX_VALUE;
        LandArea minTreesArea = null;

        for (LandArea area: landBlocks) {
            if (area != null && area.trees() != null && area.trees().size() < min) {
                if (!searchEmptyArea && area.trees().size() == 0) {
                    continue;
                } else {
                    min = area.trees().size();
                    minTreesArea = area;
                }
            }
        }

        Logger.printLog("found an area with min trees. Trees: " + minTreesArea.trees().size() +  ". LandArea: " + minTreesArea.getIndex());
        return minTreesArea;
    }

    public LandArea getMinComObjLandArea() {
        int min = Integer.MAX_VALUE;
        LandArea minComObjArea = null;

        for (LandArea area: landBlocks) {
            if (area.commonObjects() != null && area.commonObjects().size() < min) {
                min = area.commonObjects().size();
                minComObjArea = area;
            }
        }

        Logger.printLog("found an area with min common objects. common obj: " + minComObjArea.commonObjects().size() +  ". LandArea: " + minComObjArea.getIndex());
        return minComObjArea;
    }

    public LandArea getMaxLakesLandArea() {
        int max = 0;
        LandArea maxLakesArea = null;

        for (LandArea area: landBlocks) {
            if (area.lakes() != null && area.lakes().size() > max) {
                max = area.lakes().size();
                maxLakesArea = area;
            }
        }

        Logger.printLog("found an area with max lakes. lakes: " + maxLakesArea.lakes().size() +  ". LandArea: " + maxLakesArea.getIndex());
        return maxLakesArea;
    }

    public LandArea getMaxTreesLandArea() {
        int max = 0;
        LandArea maxTreesArea = null;

        for (LandArea area: landBlocks) {
            if (area.trees() != null && area.trees().size() > max) {
                max = area.trees().size();
                maxTreesArea = area;
            }
        }

        Logger.printLog("found an area with max trees. trees: " + maxTreesArea.trees().size() +  ". LandArea: " + maxTreesArea.getIndex());
        return maxTreesArea;
    }

    public LandArea getMaxComObjLandArea() {
        int max = 0;
        LandArea maxComObjArea = null;

        for (LandArea area: landBlocks) {
            if (area.commonObjects() != null && area.commonObjects().size() > max) {
                max = area.commonObjects().size();
                maxComObjArea = area;
            }
        }

        Logger.printLog("found an area with max common objects. common obj: " + maxComObjArea.trees().size() +  ". LandArea: " + maxComObjArea.getIndex());
        return maxComObjArea;
    }

    public LandArea getLandArea(int areaIndex) {
        return landBlocks[areaIndex];
    }

    public Scanner setScanObject(EntityModel entityModel) {
        if (entityModel.equals(EntityModel.TREE)) {
            scanTrees = true;
        }
        if (entityModel.equals(EntityModel.LAKE)) {
            scanLakes = true;
        }
        if (entityModel.equals(EntityModel.RIVER)) {
            scanRivers = true;
        }
        if (entityModel.equals(EntityModel.BIG_WATER)) {
            scanWater = true;
        }
        if (entityModel.equals(EntityModel.COMMON_OBJ)) {
            scanComObj = true;
        }
        if (entityModel.equals(EntityModel.CHARACTER)) {
            scanCharacter = true;
        }

        return this;
    }

    public Scanner scanWoodcutter() {

        woodcutterFound = false;
        woodcutterLandAreaIndex = -1;

        for (LandArea area: landBlocks) {
            if (area == null) {
                return this;
            }
            try {
                long startTime = System.currentTimeMillis();
                printProgress("scanning area " + area.getIndex(), startTime, (long) landBlocks.length, Arrays.asList(landBlocks).indexOf(area)+1);
            } catch (IllegalArgumentException e) {
            }
            Thread t1 = new Thread(defineWoodcutterArea(Arrays.asList(landBlocks).indexOf(area)));
            t1.start();

            if (woodcutterFound) {
                //Logger.printLog("woodcutter found here: area-" + area.getIndex());
                woodcutterLandAreaIndex = area.getIndex();
                return this;
            }
        }

        //Thread t1 = new Thread(scanFullLand());
        //t1.start();
        return this;
    }

    public Scanner scan() {
        //Arrays.stream(landAreas).parallel().forEach(landArea -> scanArea(landArea));

        //scan(landAreas);

        woodcutterFound = false;
        woodcutterLandAreaIndex = -1;

        for (LandArea area: landBlocks) {
            if (area == null) {
                return this;
            }
            try {
                long startTime = System.currentTimeMillis();
                printProgress("scanning area " + area.getIndex(), startTime, (long) landBlocks.length, Arrays.asList(landBlocks).indexOf(area)+1);
            } catch (IllegalArgumentException e) {
            }
            //Thread t1 = new Thread(scanArea(Arrays.asList(landAreas).indexOf(area)));
            Thread t1 = new Thread(scanArea(Arrays.asList(landBlocks).indexOf(area)));
            //Arrays.stream(splitAreaToBlocks(area)).parallel().forEach(block -> scanArea(block.getIndex()));
            t1.start();

            if (scanCharacter && woodcutterFound) {
                Logger.printLog("woodcutter found here: area-" + area.getIndex());
                return this;
            }
        }

        //Thread t1 = new Thread(scanFullLand());
        //t1.start();
        return this;
    }

    public Scanner scan(LandArea[] landAreasArray) {
        //Arrays.stream(landAreas).parallel().forEach(landArea -> scanArea(landArea));

        woodcutterFound = false;
        woodcutterLandAreaIndex = -1;

        for (LandArea area:landAreasArray) {
            if (area == null) {
                return this;
            }

            LandArea [] blocks = splitAreaToBlocks(area);
            for (LandArea block : blocks) {
                try {
                    long startTime = System.currentTimeMillis();
                    printProgress("scanning area " + area.getIndex(), startTime, (long) landBlocks.length, Arrays.asList(landBlocks).indexOf(area)+1);
                } catch (IllegalArgumentException e) {
                }
                //Thread t1 = new Thread(scanArea(Arrays.asList(landAreas).indexOf(area)));
                Thread t1 = new Thread(scanArea(Arrays.asList(blocks).indexOf(block)));
                //Arrays.stream(splitAreaToBlocks(area)).parallel().forEach(block -> scanArea(block.getIndex()));
                t1.start();
                if (scanCharacter && woodcutterFound) {
                    Logger.printLog("woodcutter found here: area-" + block.getIndex());
                    return this;
                }
            }
        }

        //Thread t1 = new Thread(scanFullLand());
        //t1.start();
        return this;
    }

    public Scanner scan(int areaIndex) {
        LandArea landArea = landBlocks[areaIndex];

        if (landArea == null) {
            Logger.printLog("failed to get land area with index of " + areaIndex);
            return this;
        }
        try {
            //long startTime = System.currentTimeMillis();
            //printProgress("scanning", startTime, (long) landBlocks.length, Arrays.asList(landBlocks).indexOf(landArea)+1);
        } catch (IllegalArgumentException e) {
        }
        Thread t1 = new Thread(scanArea(Arrays.asList(landBlocks).indexOf(landArea)));
        t1.start();

        return this;
    }

    private String scanFast() {
        //Arrays.stream(landAreas).forEach(landArea -> new Thread(scanArea(landArea)).start());

        return "";
    }

    public String scanFullLand() {
        if (landModel == null) {
            return "";
        }
        for (int x = 0; x < landModel.getWidth(); x++) {
            for (int y = 0; y < landModel.getHeight(); y++) {
                int color = landModel.getImage().getRGB(x, y);
                int alpha = (color >> 24) & 0xFF;
                if (alpha != 0) {
                    Thread t1 = new Thread(defineSpritesToArea(x, y));
                    t1.start();
                }
            }
        }

        treeSprites = null;
        stumpSprites = null;
        lakeSprites = null;
        riverSprites = null;
        comObjSprites = null;

        return "true";
    }

    public String scanArea(LandArea landArea) {
        if (landArea == null) {
            return "";
        }
        for (int x = landArea.StartX(); x < landArea.EndX(); x++) {
            for (int y = landArea.StartY(); y < landArea.EndY(); y++) {
                int color = landModel.getImage().getRGB(x, y);
                int alpha = (color >> 24) & 0xFF;
                if (alpha != 0) {
                    Thread t1 = new Thread(defineSprites(landArea, x, y));
                    t1.start();
                }
            }
        }

        treeSprites = null;
        stumpSprites = null;
        lakeSprites = null;
        riverSprites = null;
        comObjSprites = null;

        return "true";
    }

    public String scanArea(int areaIndex) {
        LandArea landArea = landBlocks[areaIndex];

        if (landArea == null) {
            return "";
        }
        //int counter = 0;

        //for (LandArea landAreaBlock : splitAreaToBlocks(landArea)) {

        for (int x = landArea.StartX(); x <= landArea.EndX(); x++) {
            for (int y = landArea.StartY(); y <= landArea.EndY(); y++) {
                try {
                    //long startTime = System.currentTimeMillis();
                    //printProgress("scanning area " + areaIndex, startTime, (long) landArea.EndX()-1, x);
                } catch (IllegalArgumentException e) {
                }
                int color = landModel.getImage().getRGB(landArea.StartX(), landArea.StartY());
                int alpha = (color >> 24) & 0xFF;
                if (alpha != 0) {
                    defineSprites(landArea, x, y);
                }
            }
        }
            //counter++;
            //scanArea(splitAreaToBlocks(landArea)[landArea.getIndex()+1]);
        //}

        treeSprites = null;
        stumpSprites = null;
        lakeSprites = null;
        riverSprites = null;
        comObjSprites = null;

        return "true";
    }

    private String defineSpritesToArea(int x, int y) {
        Sprite found = spriteObjectsMap.getSpriteByCoordinates(x, y);
        if (found != null) {
            if (found.getEntityModel().equals(EntityModel.TREE)) {
                if (treeSprites == null) {
                    treeSprites = new ArrayList<Sprite>();
                }
                treeSprites.add(found);
            } else if (found.getEntityModel().equals(EntityModel.STUMP)) {
                if (stumpSprites == null) {
                    stumpSprites = new ArrayList<Sprite>();
                }
                stumpSprites.add(found);
            } else if (found.getEntityModel().equals(EntityModel.LAKE)) {
                if (lakeSprites == null) {
                    lakeSprites = new ArrayList<Sprite>();
                }
                lakeSprites.add(found);
            } else if (found.getEntityModel().equals(EntityModel.RIVER)) {
                if (riverSprites == null) {
                    riverSprites = new ArrayList<Sprite>();
                }
                riverSprites.add(found);
            } else if (found.getEntityModel().equals(EntityModel.COMMON_OBJ)) {
                if (comObjSprites == null) {
                    comObjSprites = new ArrayList<Sprite>();
                }
                comObjSprites.add(found);
            }
        }

        for (LandArea landArea: landBlocks) {
            if ((x >= landArea.StartX() && x <= landArea.EndX()) &&
                    (y >= landArea.StartY() && y <= landArea.EndY())) {

                landArea.setTrees(treeSprites);
                landArea.setStumps(stumpSprites);
                landArea.setLakes(lakeSprites);
                landArea.setCommonObjects(comObjSprites);
            }
        }

        return "";
    }

    private String defineSprites(LandArea landArea, int x, int y) {
        Sprite found = spriteObjectsMap.getSpriteByCoordinates(x, y);
        if (landModel.getSprite(EntityModel.CHARACTER) != null &&
                landModel.getSprite(EntityModel.CHARACTER).getX() == x &&
                landModel.getSprite(EntityModel.CHARACTER).getY() == y) {
            woodcutterFound = true;
            woodcutterLandAreaIndex = landArea.getIndex();
            landModel.getSprite(EntityModel.CHARACTER).setLocation(landArea.getIndex());
        }
        if (found != null) {
            if (found.getEntityModel().equals(EntityModel.TREE)) {
                if (treeSprites == null) {
                    treeSprites = new ArrayList<Sprite>();
                }
                treeSprites.add(found);
                landArea.setTrees(treeSprites);
            } else if (found.getEntityModel().equals(EntityModel.STUMP)) {
                if (stumpSprites == null) {
                    stumpSprites = new ArrayList<Sprite>();
                }
                stumpSprites.add(found);
                landArea.setStumps(stumpSprites);
            } else if (found.getEntityModel().equals(EntityModel.LAKE)) {
                if (lakeSprites == null) {
                    lakeSprites = new ArrayList<Sprite>();
                }
                lakeSprites.add(found);
                landArea.setLakes(lakeSprites);
            } else if (found.getEntityModel().equals(EntityModel.RIVER)) {
                if (riverSprites == null) {
                    riverSprites = new ArrayList<Sprite>();
                }
                riverSprites.add(found);
            } else if (found.getEntityModel().equals(EntityModel.COMMON_OBJ)) {
                if (comObjSprites == null) {
                    comObjSprites = new ArrayList<Sprite>();
                }
                comObjSprites.add(found);
                landArea.setCommonObjects(comObjSprites);
            }

            lastScannedArea = landArea;
        }

        return "";
    }

    private String defineWoodcutterArea(int areaIndex) {
        LandArea block = landBlocks[areaIndex];

        Sprite woodcutter =landModel.getSprite(EntityModel.CHARACTER);
        if (woodcutter != null &&
            (woodcutter.getX() >= block.StartX() && woodcutter.getX() < block.EndX()) &&
                (woodcutter.getY() >= block.StartY() && woodcutter.getY() < block.EndY())) {
            woodcutterFound = true;
            woodcutterLandAreaIndex = block.getIndex();
            landModel.getSprite(EntityModel.CHARACTER).setLocation(block.getIndex());
        }

        return "";
    }

    // Test method: count trees in the certain area
    public int countTrees(int area) {
        int backGrColor = landModel.getBackgroundColor().getRGB();
        int counter = 0;

        for (int x = landBlocks[area].StartX(); x < landBlocks[area].EndX(); x++) {
            for (int y = landBlocks[area].StartY(); y < landBlocks[area].EndY(); y++) {
                //System.out.println("searching in: (x: " + x + " y: " + y + ")");
                int color = landModel.getImage().getRGB(x, y);
                int alpha = (color >> 24) & 0xFF;
                if (alpha != 0) {

                    // Try to define a sprite
                    if (spriteObjectsMap.getSpriteEntity(EntityModel.TREE, x, y)) {
                        Sprite found = spriteObjectsMap.getSpriteByCoordinates(x, y);
                        //System.out.println("tree found. (x: " + found.getX() + " y: " + found.getY() + ")");
                        counter++;
                    }
                }
            }
        }

        return counter;
    }
}
