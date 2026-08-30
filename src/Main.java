import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, AWTException, UnsupportedAudioFileException, LineUnavailableException {

        Level level1 = new Level();
        Level level2 = new Level();
        Level level3 = new Level();
        Level level4 = new Level();
        Level level5 = new Level();

        s.level1 = level1;
        s.level2 = level2;
        s.level3 = level3;
        s.level4 = level4;
        s.level5 = level5;

        level1.mapWidth = 37;
        level2.mapWidth = 37;
        level3.mapWidth = 37;
        level4.mapWidth = 37;
        level5.mapWidth = 37;


        Map.createMap();
        s.level1.map = Map.mapLevel1;
        s.level2.map = Map.mapLevel2;
        s.level3.map = Map.mapLevel3;
        s.level4.map = Map.mapLevel4;
        s.level5.map = Map.mapLevel5;


        s.player.mode3 = ImageIO.read(new File("data\\mode\\3.png"));
        s.player.mode2 = ImageIO.read(new File("data\\mode\\2.png"));
        s.player.mode1 = ImageIO.read(new File("data\\mode\\1.png"));

        s.player.gran = ImageIO.read(new File("data\\mode\\granata.png"));
        s.player.gunn = ImageIO.read(new File("data\\mode\\gun.png"));
        s.player.akk = ImageIO.read(new File("data\\gun2\\1.png"));

        s.explosion = new Explosion();

        s.sprites[0] = new Sprite(RayCaster.mapp * 3.0 / 2, RayCaster.mapp * 3.0 / 2);
        s.sprites[1] = new Sprite(200, 190);


        s.level1.normSprites = s.sprites;
        s.level2.normSprites = new Sprite[3];
        s.level3.normSprites = new Sprite[7];
        s.level4.normSprites = new Sprite[12];
        s.level5.normSprites = new Sprite[15];

        s.level2.normSprites[0] = new Sprite(10, 30);
        s.level2.normSprites[1] = new Sprite(50, 180);
        s.level2.normSprites[2] = new Sprite(200, 70);



            s.level3.normSprites[0] = new Sprite(250, 280);
            s.level3.normSprites[1] = new Sprite(90, 150);
            s.level3.normSprites[2] = new Sprite(120, 50);
            s.level3.normSprites[3] = new Sprite(220, 50);
            s.level3.normSprites[4] = new Sprite(80, 5 * 20);
            s.level3.normSprites[5] = new Sprite(50, 200);
            s.level3.normSprites[6] = new Sprite(50, 100);


            s.level4.normSprites[0] = new Sprite(50, 210);
            s.level4.normSprites[1] = new Sprite(50, 280);
            s.level4.normSprites[2] = new Sprite(150, 210);
            s.level4.normSprites[3] = new Sprite(280, 20);
            s.level4.normSprites[4] = new Sprite(30, 30);
            s.level4.normSprites[5] = new Sprite(70, 90);
            s.level4.normSprites[6] = new Sprite(280, 280);
            s.level4.normSprites[7] = new Sprite(110, 110);
            s.level4.normSprites[8] = new Sprite(250, 250);
            s.level4.normSprites[9] = new Sprite(180, 50);
            s.level4.normSprites[10] = new Sprite(90, 50);
            s.level4.normSprites[11] = new Sprite(120, 50);


            s.level5.normSprites[0] = new Sprite(220, 180);
            s.level5.normSprites[1] = new Sprite(50, 70);
            s.level5.normSprites[2] = new Sprite(200, 160);
            s.level5.normSprites[3] = new Sprite(90, 200);
            s.level5.normSprites[4] = new Sprite(30, 70);
            s.level5.normSprites[5] = new Sprite(40, 60);
            s.level5.normSprites[6] = new Sprite(220, 120);
            s.level5.normSprites[7] = new Sprite(90, 90);
            s.level5.normSprites[8] = new Sprite(60, 70);
            s.level5.normSprites[9] = new Sprite(80, 50);
            s.level5.normSprites[10] = new Sprite(120, 120);
            s.level5.normSprites[11] = new Sprite(200, 200);
            s.level5.normSprites[12] = new Sprite(150, 250);
            s.level5.normSprites[13] = new Sprite(250, 150);
            s.level5.normSprites[14] = new Sprite(200, 280);



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

        for (int i = 0; i < 3; ++i) {
            s.level2.normSprites[i].image1 = ImageIO.read(new File("data\\vrag\\111.png"));
            s.level2.normSprites[i].image2 = ImageIO.read(new File("data\\vrag\\222.png"));
            s.level2.normSprites[i].image3 = ImageIO.read(new File("data\\vrag\\333.png"));
            s.level2.normSprites[i].image4 = ImageIO.read(new File("data\\vrag\\444.png"));

            s.level2.normSprites[i].image = ImageIO.read(new File("data\\vrag\\111.png"));
        } for (int i = 0; i < 7; ++i) {
            s.level3.normSprites[i].image1 = ImageIO.read(new File("data\\vrag\\1.png"));
            s.level3.normSprites[i].image2 = ImageIO.read(new File("data\\vrag\\2.png"));
            s.level3.normSprites[i].image3 = ImageIO.read(new File("data\\vrag\\3.png"));
            s.level3.normSprites[i].image4 = ImageIO.read(new File("data\\vrag\\4.png"));

            s.level3.normSprites[i].image = ImageIO.read(new File("data\\vrag\\1.png"));
        } for (int i = 0; i < 12; ++i) {
            s.level4.normSprites[i].image1 = ImageIO.read(new File("data\\vrag\\111.png"));
            s.level4.normSprites[i].image2 = ImageIO.read(new File("data\\vrag\\222.png"));
            s.level4.normSprites[i].image3 = ImageIO.read(new File("data\\vrag\\333.png"));
            s.level4.normSprites[i].image4 = ImageIO.read(new File("data\\vrag\\444.png"));

            s.level4.normSprites[i].image = ImageIO.read(new File("data\\vrag\\111.png"));
        } for (int i = 0; i < 15; ++i) {
            s.level5.normSprites[i].image1 = ImageIO.read(new File("data\\vrag\\1.png"));
            s.level5.normSprites[i].image2 = ImageIO.read(new File("data\\vrag\\2.png"));
            s.level5.normSprites[i].image3 = ImageIO.read(new File("data\\vrag\\3.png"));
            s.level5.normSprites[i].image4 = ImageIO.read(new File("data\\vrag\\4.png"));

            s.level5.normSprites[i].image = ImageIO.read(new File("data\\vrag\\1.png"));
        }



        Gun gun = new Gun();
        s.player.gun = gun;

        Ak ak = new Ak();
        s.player.ak = ak;

        if (s.level == 1){
            s.map = s.level1.map;
            s.mapWidth = s.level1.mapWidth;
            s.sprites = s.level1.normSprites;
        }else if (s.level == 2){
            s.map = s.level2.map;
            s.mapWidth = s.level2.mapWidth;
            s.sprites = s.level2.normSprites;
        }


        Frame frame = new Frame();

        frame.setSize(s.width, s.height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Robot r = new Robot();



        while (true){

            while (s.menu || s.chooseLevel){
                s.width = frame.getWidth();
                s.height = frame.getHeight();
                frame.repaint();

                s.level1.sprites = Arrays.copyOf(s.level1.normSprites, s.level1.normSprites.length);
                s.level2.sprites = Arrays.copyOf(s.level2.normSprites, s.level2.normSprites.length);
                s.level3.sprites = Arrays.copyOf(s.level3.normSprites, s.level3.normSprites.length);
                s.level4.sprites = Arrays.copyOf(s.level4.normSprites, s.level4.normSprites.length);
                s.level5.sprites = Arrays.copyOf(s.level5.normSprites, s.level5.normSprites.length);


                int i = 0;
                for(Sprite sprite : s.level1.normSprites){
                    s.level1.sprites[i] = new Sprite(sprite);
                    ++i;
                }

                i = 0;
                for(Sprite sprite : s.level2.normSprites){
                    s.level2.sprites[i] = new Sprite(sprite);
                    ++i;
                }

                i = 0;
                for(Sprite sprite : s.level3.normSprites){
                    s.level3.sprites[i] = new Sprite(sprite);
                    ++i;
                }

                i = 0;
                for(Sprite sprite : s.level4.normSprites){
                    s.level4.sprites[i] = new Sprite(sprite);
                    ++i;
                }

                i = 0;
                for(Sprite sprite : s.level5.normSprites){
                    s.level5.sprites[i] = new Sprite(sprite);
                    ++i;
                }
                s.player.g = 5;
                s.player.g1b = 30;
                s.player.g2b = 50;
                s.player.gun.bulletCount = 10;
                s.player.ak.bulletCount = 10;
                s.player.game = false;
                s.player.angle = 90;
                s.player.health = 300;

                Map.createMap();
                s.level1.map = Map.mapLevel1;
                s.level2.map = Map.mapLevel2;
                s.level3.map = Map.mapLevel3;
                s.level4.map = Map.mapLevel4;
                s.level5.map = Map.mapLevel5;

            }
            Thread.sleep(1);
            while (s.game) {

                if (!s.player.chooseMode) {
                     PointerInfo info = MouseInfo.getPointerInfo();
                     Point location = info.getLocation();
                     int x = (int) location.getX();
                     int y = (int) location.getY();

                     if (Math.abs(x - s.width / 2) < 80) {
                         s.player.angle -= (x - s.width / 2) / 5.0;
                         s.player.vertAngle -= (y - (s.height / 2 - 100)) / 500.0;
                     }
                     if (s.player.vertAngle > Math.PI / 2 - 0.2) s.player.vertAngle = Math.PI / 2 - 0.2;
                     if (s.player.vertAngle < - Math.PI / 2 + 0.2) s.player.vertAngle = -Math.PI / 2 + 0.2;

                     r.mouseMove(s.width / 2, (s.height / 2 - 100));
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
                        int q = 0;


                        for (int i = 0; i < s.mapWidth * s.mapWidth; ++i){
                            if (((int) a[1] == s.map[i][0] && (int) a[0] == s.map[i][1]) && !(a[0] == 0 || a[1] == 0) && !(a[0] == 36 * RayCaster.mapp || a[1] == 36 * RayCaster.mapp)){

                                System.out.println(a[1]);
                                c = true;
                                n = i;
                                ++q;
                                break;
                            }
                        }
                        if (bullet.h < -RayCaster.mapp / 2){
                            b.add(bullet);
                        }
                        if (bullet.h < (double) RayCaster.mapp / 2 && bullet.h > (double) -RayCaster.mapp / 2){
                            b.add(bullet);
                            s.map[n][2] -= q;

                            if (s.map[n][2] == 0) s.map[n] = new int[3];
                        }
                    }
                }
                for (Bullet bullet : b){
                    s.bullets.remove(bullet);
                }


//                if (!c){
//                    s.level += 1;
//
//                    s.player.pos[0] = 150;
//                    s.player.pos[1] = 150;
//
//                    s.menu = true;
//                    s.game = false;
//
//                }

                if (s.level == 1){
                    s.map = s.level1.map;
                    s.mapWidth = s.level1.mapWidth;
                    s.sprites = s.level1.sprites;
                }else if (s.level == 2){
                    s.map = s.level2.map;
                    s.mapWidth = s.level2.mapWidth;
                    s.sprites = s.level2.sprites;
                }else if (s.level == 3){
                    s.map = s.level3.map;
                    s.mapWidth = s.level3.mapWidth;
                    s.sprites = s.level3.sprites;
                }else if (s.level == 4){
                    s.map = s.level4.map;
                    s.mapWidth = s.level4.mapWidth;
                    s.sprites = s.level4.sprites;
                }else if (s.level == 5){
                    s.map = s.level5.map;
                    s.mapWidth = s.level5.mapWidth;
                    s.sprites = s.level5.sprites;
                }

                boolean c = false;
                for(int i = 0; i < s.sprites.length; ++i){
                    if (!s.sprites[i].die){
                        c = true;
                        break;
                    }
                }
                if(!c){
                    if(s.level == 1) s.level1.passed = true;
                    if(s.level == 2) s.level2.passed = true;
                    if(s.level == 3) s.level3.passed = true;
                    if(s.level == 4) s.level4.passed = true;
                    if(s.level == 5) s.level5.passed = true;
                }
                s.player.game = true;


                Thread.sleep(5);
            }
        }

    }
}