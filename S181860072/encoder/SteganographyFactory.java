package S181860072.encoder;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.awt.image.BufferedImage;

public class SteganographyFactory {

    private static void compile(String classSource) {

        File sourceFile = new File(classSource);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());
    }

    public static void getSteganography(String classSource, String originImage) throws IOException {
        String className = classSource.substring(0, classSource.indexOf(".")).replace("/", ".");
        SteganographyFactory.compile(classSource);
        BufferedImage image = ImageIO.read(new File(originImage));
        SteganographyEncoder steganographyEncoder = new SteganographyEncoder(image);

        BufferedImage encodedImage = steganographyEncoder.encodeFile(new File(classSource.replace("java", "class")));
        ImageIO.write(encodedImage, "png", new File(className+".png"));

    }

    public static void main(String[] args) throws IOException {

        SteganographyFactory.getSteganography("S181860072/BubbleSorter.java","S181860072/resources/bubble.jpeg");
        //SteganographyFactory.getSteganography("S181860072/QuickSorter.java", "S181860072/resources/bubble.jpeg");
        //SteganographyFactory.getSteganography("S181860072/SelectSorter.java", "S181860072/resources/bubble.jpeg");
    }

}
