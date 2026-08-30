import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gun {

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
    BufferedImage image5 = ImageIO.read(new File("data/shot/5.png"));
    BufferedImage image6 = ImageIO.read(new File("data/shot/6.png"));
    BufferedImage image7 = ImageIO.read(new File("data/shot/7.png"));
    BufferedImage image8 = ImageIO.read(new File("data/shot/8.png"));
    BufferedImage image9 = ImageIO.read(new File("data/shot/9.png"));
    BufferedImage image10 = ImageIO.read(new File("data/shot/10.png"));
    BufferedImage image11 = ImageIO.read(new File("data/shot/11.png"));
    BufferedImage image12 = ImageIO.read(new File("data/shot/12.png"));
    BufferedImage image13 = ImageIO.read(new File("data/shot/13.png"));
    BufferedImage image14 = ImageIO.read(new File("data/shot/14.png"));
    BufferedImage image15 = ImageIO.read(new File("data/shot/15.png"));
    BufferedImage image16 = ImageIO.read(new File("data/shot/16.png"));
    BufferedImage image17 = ImageIO.read(new File("data/shot/17.png"));
    BufferedImage image18 = ImageIO.read(new File("data/shot/18.png"));
    BufferedImage image19 = ImageIO.read(new File("data/shot/19.png"));


    public Gun() throws IOException {

    }

    void draw(Graphics g){

        if (shot){
            if (b == 0) image = image0;
            else if (b == 1) {
                image = image1;
                s.bullets.add(new Bullet());
                //s.player.vertAngle += 0.01;
            }
            else if (b == 2){
                image = image2;
                s.player.vertAngle += 0.01;
            }
            else if (b == 3){
                image = image3;
                s.player.vertAngle += 0.01;
            }
            else if (b == 4){
                image = image4;
                s.player.vertAngle += 0.01;
            }
            else if (b == 5){
                image = image5;
                s.player.vertAngle += 0.01;
            }
            else if (b == 6){
                image = image6;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 7) {
                image = image7;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 8) {
                image = image8;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 9){
                image = image9;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 10){
                image = image10;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 11){
                image = image11;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 12){
                image = image12;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 13){
                image = image13;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 14){
                image = image14;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 15){
                image = image15;
                s.player.vertAngle -= 0.003;
            }
            else if (b == 16) image = image16;
            else if (b == 17) image = image17;
            else if (b == 18) image = image18;
            else if (b == 19) image = image19;
            else{
                b = 0;
                shot = false;
            }



            b += 1;
        }else z = false;
        g.drawImage(image, s.width / 3 - (int) (50 * Math.sin(1.0 * a / 20)),
                (int) (s.height * (2.0 / 3 - 1.3 / 10)) - (int) (10 * Math.sin(1.0 * a / 10)),
                s.width / 3,
                s.height / 3,
                null);


        g.fillRect(700, 100, bulletCount * 30, 30);
    }


}
