import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ak {

    int a = 0;
    double b = 1;
    double c = 1;

    int bulletCount = 10;

    boolean z = false;

    boolean shot = false;
    boolean reload = false;

    BufferedImage image = ImageIO.read(new File("data/shot/0.png"));

    BufferedImage image1 = ImageIO.read(new File("data/gun2/1.png"));
    BufferedImage image2 = ImageIO.read(new File("data/gun2/2.png"));
    BufferedImage image3 = ImageIO.read(new File("data/gun2/3.png"));
    BufferedImage image4 = ImageIO.read(new File("data/gun2/4.png"));

    BufferedImage image5 = ImageIO.read(new File("data/gun2/5.png"));
    BufferedImage image6 = ImageIO.read(new File("data/gun2/6.png"));
    BufferedImage image7 = ImageIO.read(new File("data/gun2/7.png"));
    BufferedImage image8 = ImageIO.read(new File("data/gun2/8.png"));

    boolean aa = false;


    public Ak() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

    }

    void draw(Graphics g) throws LineUnavailableException, IOException {

        image = image1;
        if (shot && !reload) {
            if (b <= 1) {
                new Thread(() -> {
                    new MakeSound().playSound("data/bam.wav");
                }).start();
                image = image1;
            } else if (b <= 2) {
                image = image2;
                s.player.vertAngle += 0.01;
            } else if (b <= 3) {
                image = image3;
                s.player.vertAngle += 0.01;
            } else if (b <= 4) {
                image = image4;
                if(!aa){
                    Bullet khgddyu = (new Bullet());
                    khgddyu.damage = 10;
                    s.bullets.add(khgddyu);
                    bulletCount -= 1;
                }
                aa = true;
                s.player.vertAngle += 0.01;
            } else if(b <= 5) {
                b = 0;
                if (bulletCount <= 0) shot = false;
                aa = false;
            }

            b += 0.8;

        } else {
            z = false;
        }

        if(reload){

            if (c <= 1) {
                image = image5;
                
            } else if (c <= 2) {
                image = image6;
                s.player.vertAngle += 0.01;
            } else if (c <= 3) {
                image = image7;
                s.player.vertAngle += 0.01;
            } else if (c <= 4) {
                image = image8;
                s.player.vertAngle += 0.01;
            } else if(c <= 5) {
                c = 0;
                if (s.player.g2b + bulletCount >= 10){
                    s.player.g2b -= 10 - bulletCount;;
                    bulletCount = 10;
                }else{
                    bulletCount += s.player.g2b;
                    s.player.g2b = 0;
                }
                reload = false;
            }
            shot = false;
            c += 0.2;

        }
        g.drawImage(image, s.width / 3 - (int) (50 * Math.sin(1.0 * a / 20)),
                (int) (s.height * (2.0 / 3 - 1.3 / 10)) - (int) (10 * Math.sin(1.0 * a / 10)),
                s.width / 3,
                s.height / 3,
                null);


       // g.fillRect(700, 100, bulletCount * 30, 30);
    }
}
