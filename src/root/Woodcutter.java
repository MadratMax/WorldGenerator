package root;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Woodcutter implements ICharacter {

    private final Sprite sprite;
    private final Randomizator random;
    private LandManager landManager;
    private final ILandModel landModel;
    private Sprite woodcutterSprite;
    private int currentX;
    private int currentY;
    private Direction direction;
    private EntityModel entityModel;
    private BufferedImage spriteImg;
    private int type;
    private int areaIndex = -1;
    private Direction lastDirection;
    private int treesCut;

    public Woodcutter(LandManager landManager, ILandModel landModel, Sprite sprite) {
        this.landManager = landManager;
        this.landModel = landModel;
        this.woodcutterSprite = sprite;
        this.spriteImg = sprite.getImage();
        this.type = sprite.getType();
        this.entityModel = sprite.getEntityModel();
        this.sprite = sprite;
        this.currentX = sprite.getX();
        this.currentY = sprite.getY();
        this.random = new Randomizator();
    }

    public int getX() {
        return currentX;
    }

    public int getY() {
        return currentY;
    }

    public void setLocation(int areaIndex) {
        this.areaIndex = areaIndex;
    }

    public int getLocation() {
        return areaIndex;
    }

    public void move() {
        lookAround();
        int steps = decideDistance();
        steps = Math.abs(steps);
        switch (decideDirection()) {
            case N -> goNorth(steps);
            case NE -> {
                goNorth(steps/2);
                goEast(steps/2);
            }
            case E -> goEast(steps);
            case ES -> {
                goEast(steps/2);
                goSouth(steps/2);
            }
            case S -> goSouth(steps);
            case SW -> {
                goSouth(steps/2);
                goWest(steps/2);
            }
            case W -> goWest(steps);
            case WN -> {
                goWest(steps/2);
                goNorth(steps/2);
            }
        }
        // if there is a tree around -> go to the tree (tree.x == wc.x +- 10)
        // if wc x = tree.x && wc.y = tree.y + tree.h -> cut a tree
        Logger.woodcutterProgress(currentX, currentY, treesCut);
    }

    public Direction decideDirection() {
        int dirIndex = random.getRandomFromRange(1, 9);
        Direction direction = Direction.N;
        switch (dirIndex) {
            case 1: {
                direction = Direction.N;
            } break;
            case 2: {
                direction =  Direction.NE;
            } break;
            case 3: {
                direction =  Direction.E;
            } break;
            case 4: {
                direction =  Direction.ES;
            } break;
            case 5: {
                direction =  Direction.S;
            } break;
            case 6: {
                direction =  Direction.SW;
            } break;
            case 7: {
                direction =  Direction.W;
            } break;
            case 8: {
                direction =  Direction.WN;
            } break;
        }

        lastDirection = direction;
        return direction;
    }

    public int whereAmI() {
        return landManager.scanner(landModel).scanWoodcutter().woodcutterLandIndex();
    }

    public int decideDistance() {
        int distance = random.getRandomFrom(20);
        return distance;
    }

    public Direction lastDirection() {
        return lastDirection;
    }

    public void cutTree(Sprite treeSprite) {
        int currentAreaIndex = whereAmI();
        if (currentAreaIndex < 0) {
            return;
        }

        treesCut = treesCut + landManager.designer(landModel).cutATree(landManager.scanner(landModel).getLandArea(currentAreaIndex), treeSprite.getX(), treeSprite.getY());
    }

    public void lookAround() {
        int currentAreaIndex = whereAmI();
        if (currentAreaIndex < 0) {
            return;
        }
        LandArea currentArea = landManager.scanner(landModel).getLandArea(currentAreaIndex);
        landManager.scanner(landModel).scan(currentAreaIndex);
        for(int i = 0; i < 10; i++) {
            ArrayList<Sprite> trees = currentArea.trees();
            if (trees == null || trees.size() == 0) {
                return;
            }
            for (Sprite tree : trees) {
                if (tree.getImage() == null){
                    return;
                }
                if (currentX == tree.getX() && currentY == tree.getY() + tree.getImage().getHeight()) {
                    cutTree(tree);
                    return;
                }
                if (currentX + i == tree.getX()) {
                    goEast(1);
                }
                if (currentY + i == tree.getY()) {
                    goSouth(1);
                }
                if (currentX - i == tree.getX()) {
                    goWest(1);
                }
                if (currentY - i == tree.getY()) {
                    goNorth(1);
                }
            }
        }
    }

    private void goNorth(int step) {
        for (int i = 0; i <= step; i++) {
            if (currentY - 1 < 0) {
            } else {
                currentY--;
            }

            setCurrentPosition();
        }
    }

    private void goEast(int step) {
        for (int i = 0; i <= step; i++) {
            if (currentX + 1 > landModel.getHeight()) {

            } else {
                currentX++;
            }

            setCurrentPosition();
        }
    }

    private void goSouth(int step) {
        for (int i = 0; i <= step; i++) {
            if (currentY + 1 > landModel.getWidth()) {

            } else {
                currentY++;
            }

            setCurrentPosition();
        }
    }

    private void goWest(int step) {
        for (int i = 0; i <= step; i++) {
            if (currentX - 1 < 0) {

            } else {
                currentX--;
            }

            setCurrentPosition();
        }
    }

    public void setCurrentPosition() {
        sprite.addCoordinates(currentX, currentY);
    }

    public void avoidObstacle() {

    }
}
