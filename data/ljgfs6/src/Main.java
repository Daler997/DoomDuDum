import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, AWTException {

        s.player.mode2 = ImageIO.read(new File("data\\mode\\semicircle1.png"));
        s.player.mode1 = ImageIO.read(new File("data\\mode\\semicircle2.png"));

        s.player.gran = ImageIO.read(new File("data\\mode\\granata.png"));
        s.player.gunn = ImageIO.read(new File("data\\mode\\gun.png"));

        s.explosion = new Explosion();

        s.sprites[0] = new Sprite(100, 100);
        s.sprites[1] = new Sprite(200, 200);


        s.sprites[0].image1 = ImageIO.read(new File("data\\vrag\\1.png"));
        s.sprites[0].image2 = ImageIO.read(new File("data\\vrag\\2.png"));
        s.sprites[0].image3 = ImageIO.read(new File("data\\vrag\\3.png"));
        s.sprites[0].image4 = ImageIO.read(new File("data\\vrag\\4.png"));

        s.sprites[0].image = ImageIO.read(new File("data\\vrag\\1.png"));


        s.sprites[1].image1 = ImageIO.read(new File("data\\vrag\\1.png"));
        s.sprites[1].image2 = ImageIO.read(new File("data\\vrag\\2.png"));
        s.sprites[1].image3 = ImageIO.read(new File("data\\vrag\\3.png"));
        s.sprites[1].image4 = ImageIO.read(new File("data\\vrag\\4.png"));

        s.sprites[1].image = ImageIO.read(new File("data\\vrag\\1.png"));


        Gun gun = new Gun();
        s.player.gun = gun;

        Map.createMap();
        s.map = Map.map;

        Frame frame = new Frame();

        frame.setSize(s.width, s.height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Robot r = new Robot();


        int[][] map = Map.map;


        while (true){

            while (s.menu){
                frame.repaint();
            }

            while (s.game) {
                if (!s.player.chooseMode) {
                     PointerInfo info = MouseInfo.getPointerInfo();
                     Point location = info.getLocation();
                     int x = (int) location.getX();
                     int y = (int) location.getY();

                     if (Math.abs(x - 500) < 80) {
                         s.player.angle -= (x - 500) / 5.0;
                         s.player.vertAngle -= (y - 100) / 500.0;
                     }
                     if (s.player.vertAngle > Math.PI / 2 - 0.2) s.player.vertAngle = Math.PI / 2 - 0.2;
                     if (s.player.vertAngle < - Math.PI / 2 + 0.2) s.player.vertAngle = -Math.PI / 2 + 0.2;

                     r.mouseMove(500, 100);
                }

                s.width = frame.getWidth();
                s.height = frame.getHeight();

                s.projHeight = s.ratio * s.mapSize / s.mapWidth / 2 / Math.tan(Math.toRadians((double) s.FOV / 2)) * s.width;

                s.player.control();
                s.granata.drop();
                frame.repaint();

                for (int i = 0; i < s.numRays; ++i){
                    frame.w[i] = 1.0 * s.width / 2 /  Math.tan(Math.toRadians(1.0 * s.FOV / 2)) * (Math.tan(Math.toRadians(-1.0 * s.FOV / 2 + 1.0 * s.FOV / s.numRays * i)) - Math.tan(Math.toRadians(-1.0 * s.FOV / 2 + 1.0 * s.FOV / s.numRays * (i - 1))));
                    if (i != 0) frame.a[i] = frame.a[i-1] + frame.w[i];
                }


                if (s.granata.bam[0] != 0){
                    s.explosion = new Explosion();
                }

                ArrayList<Bullet> b = new ArrayList<Bullet>();
                for (Bullet bullet : s.bullets) {
                    double[] a = new double[2];
                    a[1] = bullet.x - bullet.x % RayCaster.mapp;
                    a[0] = bullet.y - bullet.y % RayCaster.mapp;

                    if (RayCaster.in(s.map, a)) {

                        boolean c = false;

                        int n = 0;


                        for (int i = 0; i < s.mapWidth * s.mapWidth; ++i){
                            if ((int) a[1] == s.map[i][0] && (int) a[0] == s.map[i][1]){
                                c = true;
                                n = i;
                                break;
                            }
                        }
                        if (bullet.h < RayCaster.mapp / 2){
                            b.add(bullet);
                            s.map[n][2] -= 1;

                            if (s.map[n][2] == 0) s.map[n] = new int[3];
                        }
                    }
                }
                for (Bullet bullet : b){
                    s.bullets.remove(bullet);
                }
                Thread.sleep(5);
            }
        }

    }
}