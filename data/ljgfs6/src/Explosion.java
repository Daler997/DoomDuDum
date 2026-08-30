import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explosion {
    double b = 0;

    double x = s.granata.bam[0];
    double y = s.granata.bam[1];

    boolean bam = false;

    BufferedImage image = ImageIO.read(new File("data/bam/1.png"));

    BufferedImage image0 = ImageIO.read(new File("data/bam/20.png"));
    BufferedImage image1 = ImageIO.read(new File("data/bam/1.png"));
    BufferedImage image2 = ImageIO.read(new File("data/bam/2.png"));
    BufferedImage image3 = ImageIO.read(new File("data/bam/3.png"));
    BufferedImage image4 = ImageIO.read(new File("data/bam/4.png"));
    BufferedImage image5 = ImageIO.read(new File("data/bam/5.png"));
    BufferedImage image6 = ImageIO.read(new File("data/bam/6.png"));
    BufferedImage image7 = ImageIO.read(new File("data/bam/7.png"));
    BufferedImage image8 = ImageIO.read(new File("data/bam/8.png"));
    BufferedImage image9 = ImageIO.read(new File("data/bam/9.png"));
    BufferedImage image10 = ImageIO.read(new File("data/bam/10.png"));
    BufferedImage image11 = ImageIO.read(new File("data/bam/11.png"));
    BufferedImage image12 = ImageIO.read(new File("data/bam/12.png"));
    BufferedImage image13 = ImageIO.read(new File("data/bam/13.png"));
    BufferedImage image14 = ImageIO.read(new File("data/bam/14.png"));
    BufferedImage image15 = ImageIO.read(new File("data/bam/15.png"));
    BufferedImage image16 = ImageIO.read(new File("data/bam/16.png"));
    BufferedImage image17 = ImageIO.read(new File("data/bam/17.png"));
    BufferedImage image18 = ImageIO.read(new File("data/bam/18.png"));
    BufferedImage image19 = ImageIO.read(new File("data/bam/19.png"));
    BufferedImage image20 = ImageIO.read(new File("data/bam/20.png"));
    BufferedImage image21 = ImageIO.read(new File("data/bam/21.png"));
    BufferedImage image22 = ImageIO.read(new File("data/bam/22.png"));
    BufferedImage image23 = ImageIO.read(new File("data/bam/23.png"));
    BufferedImage image24 = ImageIO.read(new File("data/bam/24.png"));
    BufferedImage image25 = ImageIO.read(new File("data/bam/25.png"));

    public Explosion() throws IOException {
    }


    void paint(Graphics g){

        if (x != 0) {

            if (b == 0) image = image0;
            else if (b == 1) image = image1;
            else if (b == 2) image = image2;
            else if (b == 3) image = image3;
            else if (b == 4) image = image4;
            else if (b == 5) image = image5;
            else if (b == 6) image = image6;
            else if (b == 7) image = image7;
            else if (b == 8) image = image8;
            else if (b == 9) image = image9;
            else if (b == 10) image = image10;
            else if (b == 11) image = image11;
            else if (b == 12) image = image12;
            else if (b == 13) image = image13;
            else if (b == 14) image = image14;
            else if (b == 15) image = image15;
            else if (b == 16) image = image16;
            else if (b == 17) image = image17;
            else if (b == 18) image = image18;
            else if (b == 19) image = image19;
            else if (b == 20) image = image20;
            else if (b == 21) image = image21;
            else if (b == 22) image = image22;
            else if (b == 23) image = image23;
            else if (b == 24) image = image24;
            else if (b == 25) image = image25;

            else x = 0;

            b += 1;


            double angle = 0;

            if (x != s.player.pos[0]) angle = -Math.toDegrees(Math.atan((y - s.player.pos[1]) / (x - s.player.pos[0])));
            if (x == s.player.pos[0]) angle = 90;

            if (x - s.player.pos[0] < 0) angle += 180;

            angle -= (s.player.angle - (double) s.FOV / 2) - 360;
            angle %= 360;

            angle = (int) angle / ((double) s.FOV / s.numRays);

            double l = Math.sqrt((x - s.player.pos[0]) * (x - s.player.pos[0]) + (y - s.player.pos[1]) * (y - s.player.pos[1]));

            int projection = (int) (s.projHeight / l / Math.cos(Math.toRadians((double) s.FOV / s.numRays * angle - (double) s.FOV / 2)));

            g.drawImage(image, (int) (s.width - 1.0 * s.width / s.numRays * angle - projection / 2), s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle)), projection, projection, null);
        }
    }

}
