package root;

public class LandSplitter {

    private ILandModel landModel;
    private int maxBlocks;
    private LandArea[] landBlocks;

    public LandSplitter(ILandModel landModel, int maxBlocks) {
        this.landModel = landModel;
        this.maxBlocks = maxBlocks;
        this.landBlocks = new LandArea [maxBlocks*2];
    }

    public LandArea[] split() {
        return splitAreaToBlocks(landModel);
    }

    private LandArea[] splitAreaToBlocks(ILandModel landModel) {
        int widthStep = Math.abs((landModel.getWidth() / 20));
        int heightStep = Math.abs((landModel.getHeight() / 20));

        //int widthStep = Math.abs(landModel.getWidth() / landModel.getHeight() * 10);
        //int heightStep = Math.abs(landModel.getWidth() / landModel.getHeight() * 10);


        int lastStartX = 0;
        int lastStartY = 0;

        boolean lastRow = false;
        int blockCounter = 0;

        for (int i = 0; i < maxBlocks*2; i++) {

            landBlocks[i] = new LandArea(widthStep, heightStep, i);
            landBlocks[i].SetStartX(lastStartX);
            landBlocks[i].SetStartY(lastStartY);

            blockCounter++;

            if (lastStartX + landBlocks[i].Width() > landModel.getWidth()) {
                landBlocks[i].SetWidth(landModel.getWidth() - lastStartX);

                if (lastRow) {
                    //Logger.printLog(blockCounter + " blocks have been created. block size: w: " + widthStep + " h: " + heightStep);
                    heightStep = landBlocks[0].Height();
                    break;
                }

                lastStartX = 0;
                lastStartY = lastStartY + heightStep + 1;

                if (lastStartY >= landModel.getHeight()) {
                    break;
                }

            } else {
                lastStartX = lastStartX + widthStep + 1;
            }
            if (lastStartY + landBlocks[i].Height() > landModel.getHeight()) {
                heightStep = landModel.getHeight() - lastStartY;
                landBlocks[i].SetHeight(heightStep);
                lastRow = true;
            }
        }

        Logger.printLog(blockCounter + " blocks have been created. block size: w: " + widthStep + " h: " + heightStep);
        maxBlocks = blockCounter-1;
        return landBlocks;
    }

    public int getBlockNumber() {
        return maxBlocks;
    }
}
