import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ak {

    int a = 0;
    int b = 0;

    int bulletCount = 5;

    boolean z = false;

    boolean shot = false;

    BufferedImage image = ImageIO.read(new File("data/shot/0.png"));

    BufferedImage image0 = ImageIO.read(new File("data/shot/0.png"));
    BufferedImage image1 = ImageIO.read(new File("data/shot/1.png"));
    BufferedImage image2 = ImageIO.read(new File("data/shot/2.png"));
    BufferedImage image3 = ImageIO.read(new File("data/shot/3.png"));
    BufferedImage image4 = ImageIO.read(new File("data/shot/4.png"));



    public Ak() throws IOException {

    }
}
