package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
    private String inputFilePath;
    private String outputFilePath;
    private String newFileName;
    private BufferedImage image = null;


    public Bitmap(String inputFilePath, String outputFilePath, String newFileName) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.newFileName = newFileName;
    }

    public boolean readFile() {
        try {
            File file = new File(this.inputFilePath);
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean saveFile() {
        try {
            File outputFile = new File(this.outputFilePath + this.newFileName);
            ImageIO.write(this.image, "bpm", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int greenify() {
        Color brightGreen = new Color(124,252,0);
        int GreenRBG = brightGreen.getRGB();
        int height = this.image.getHeight();
        int width = this.image.getWidth();

        for (int h = 1; h < height; h++) {
            for (int w = 1; w < width; w++) {
                this.image.setRGB(h,w,GreenRBG);
            }
        }
        return GreenRBG;
    }

    public int imageFlipHorizontal() {
        int lastRGBValue = 0;
        int height = this.image.getHeight();
        int width = this.image.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.image.setRGB((width -1) -x, y, this.image.getRGB(x,y));
            }
        }
        return lastRGBValue;
    }

    public int imageFlipVertical() {
        int lastRGBValue = 0;
        int height = this.image.getHeight();
        int width = this.image.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.image.setRGB(x, (height -1) -y, this.image.getRGB(x, y));
                lastRGBValue = this.image.getRGB(x, height -1 -y);
            }
        }
        return lastRGBValue;
    }


}

//got help from demo code, code review, and as always Nich.