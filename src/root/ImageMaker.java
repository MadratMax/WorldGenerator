package root;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageMaker {

    public static void save(BufferedImage image, boolean removeOld) {
        try {
            String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            if (ImageIO.write(image, "png", new File("./out_" + fileSuffix + ".png"))) {
                System.out.println("-- saved");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void clear() {
        Path landPath = Path.of("./Land");
        File f = new File(landPath.toString());
        deleteDir(f);
    }

    public static void save(BufferedImage image, String tag, boolean removeOld) {
        try {
            Path landPath = Path.of("./Land");
            String fileName = landPath.toString() + "/out_" + tag + "_" + ".png";
            if (removeOld) {
                File f = new File(landPath.toString());
                deleteDir(f);
            }
            if (!Files.exists(landPath)) {
                Files.createDirectory(landPath);
            }
            if (ImageIO.write(image, "png", new File(fileName))) {
                Logger.printLog("-- saved: " + fileName);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void save(ILandModel landModel, LandArea area, String tag, boolean removeOld) {
        try {
            BufferedImage areaImg = landModel.getImage().getSubimage(area.StartX(), area.StartY(), area.Width(), area.Height());
            Path landPath = Path.of("./Land");
            String fileName = landPath.toString() + "/out_" + tag + "_" + ".png";
            if (removeOld) {
                File f = new File(landPath.toString());
                deleteDir(f);
            }
            if (!Files.exists(landPath)) {
                Files.createDirectory(landPath);
            }
            if (ImageIO.write(areaImg, "png", new File(fileName))) {
                Logger.printLog("-- saved: " + fileName);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void showArea(ILandModel landModel, LandArea landArea, Multiplicator multiplicator) {
        Sprite character = landModel.getSprite(EntityModel.CHARACTER);
        boolean characterIsHere = character != null &&
                character.getLocation() == landArea.getIndex();

        if (characterIsHere) {
            BufferedImage img = multiplicator.drawArea(landModel, landArea, character);
            String tag = "woodcutter_area-" + landArea.getIndex();

            try {
                Path landPath = Path.of("./Land");
                String fileName = landPath.toString() + "/out_" + tag + "_" + ".png";

                if (!Files.exists(landPath)) {
                    Files.createDirectory(landPath);
                }
                if (ImageIO.write(img, "png", new File(fileName))) {
                    Logger.printLog("-- saved: " + fileName);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void showWorldWithWoodcutter(ILandModel landModel, Multiplicator multiplicator, boolean removeOld) {
        Sprite character = landModel.getSprite(EntityModel.CHARACTER);
        BufferedImage img = multiplicator.drawWorldWithWoodcutter(landModel, character);
        String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String tag = "world-" + fileSuffix;

        try {
            Path landPath = Path.of("./Land");
            String fileName = landPath.toString() + "/out_" + tag + "_" + ".png";

            if (removeOld) {
                File f = new File(landPath.toString());
                deleteDir(f);
            }

            if (!Files.exists(landPath)) {
                Files.createDirectory(landPath);
            }

            if (ImageIO.write(img, "png", new File(fileName))) {
                Logger.printLog("-- saved: " + fileName);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!f.isDirectory())
                    f.delete();
            }
        }
    }
}
