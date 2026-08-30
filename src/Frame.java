import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame implements MouseListener, MouseMotionListener{
    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getX() > 18.0 / 64 * s.width && e.getX() < 46.0 / 64 * s.width){
            if(e.getY() > 15.5 / 48 * s.height && e.getY() < 25.0 / 48 * s.height) menu = menu2;
            else menu = menu1;
        }else menu = menu1;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(e.getX() > 18.0 / 64 * s.width && e.getX() < 46.0 / 64 * s.width){
            if(e.getY() > 15.5 / 48 * s.height && e.getY() < 25.0 / 48 * s.height) menu = menu2;
            else menu = menu1;
        }else menu = menu1;

    }

    int[][] map = s.map;

    double[][][] z = RayCaster.rayCast();

    int mapp = s.mapSize / s.mapWidth;

    double[] w = new double[s.numRays];
    double[] a = new double[s.numRays];

    int i = 0;


    BufferedImage image1 = ImageIO.read(new File("data\\stena\\stena1.png"));
    BufferedImage image2 = ImageIO.read(new File("data\\stena\\stena2.png"));
    BufferedImage image3 = ImageIO.read(new File("data\\stena\\stena3.png"));
    BufferedImage image4 = ImageIO.read(new File("data\\stena\\stena4.png"));
    BufferedImage image = ImageIO.read(new File("data\\stena\\stena.jpg"));


    BufferedImage im = ImageIO.read(new File("data\\imp.png"));

    BufferedImage sky = ImageIO.read(new File("data\\sky.png"));

    BufferedImage sky1 = ImageIO.read(new File("data\\sky1.png"));
    BufferedImage sky2 = ImageIO.read(new File("data\\sky2.png"));

    BufferedImage menu =  ImageIO.read(new File("data\\Doom1.png"));
    BufferedImage cl =  ImageIO.read(new File("data\\cl.jpg"));

    Font font = new Font("Courier new", 1, 30);

    BufferedImage menu1 =  ImageIO.read(new File("data\\Doom1.png"));
    BufferedImage menu2 =  ImageIO.read(new File("data\\Doom2.png"));

    Frame() throws IOException {
        this.addKeyListener(s.player);
        this.addMouseListener(this);

        this.addMouseMotionListener(s.player);
        this.addMouseListener(s.player);
        this.addMouseMotionListener(this);

        for (int i = 0; i < s.numRays; ++i){
            w[i] = 1.0 * s.width / 2 /  Math.tan(Math.toRadians(1.0 * s.FOV / 2)) * (Math.tan(Math.toRadians(-1.0 * s.FOV / 2 + 1.0 * s.FOV / s.numRays * i)) - Math.tan(Math.toRadians(-1.0 * s.FOV / 2 + 1.0 * s.FOV / s.numRays * (i - 1))));
            if (i != 0) a[i] = a[i-1] + w[i];
        }
    }


    void drawMap(Graphics g){

        g.setColor(Color.BLACK);

        for (int[] i : map){
            g.fillRect(i[0] + 7, i[1] + 30, mapp, mapp);
        }

        g.fillOval((int) s.player.pos[0] + 2, (int) s.player.pos[1] + 25, 10, 10);
    }

    void drawRays(Graphics g){

        double[][] show = z[1];

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        g2.setStroke(new BasicStroke(1));

        for (int i = 0; i < s.numRays; ++i){
            g.setColor(Color.BLUE);
            if (i < s.numRays / 2 + 100 && i > s.numRays / 2 - 100) g.setColor(Color.GREEN);
            g2.drawLine((int) s.player.pos[0] + 7, (int) s.player.pos[1] + 30, (int) show[i][0] + 7, (int) show[i][1] + 30);
        }

    }

    void draw(Graphics g){

        boolean gg = false;
        boolean exp = false;

        int[] bon = new int[s.bonuses.size()];

        int[][][] q = s.granata.showTrajectory(g)[0];
        int[][][] d = s.granata.showTrajectory(g)[1];

        double[][] lengh = z[0];

        int sp = 0;

        for (int i = 0; i <= s.numRays + s.sprites.length - 1; ++i){

            double l;

            int j = (int) lengh[i][3];


            if (lengh[i][0] != 0){
                l = lengh[i][0];
            }else{
                l = 0.0001;
            }

            if (l < s.maxDepth - 0.1){
                int projection = (int) (s.projHeight / l / Math.cos(Math.toRadians((double) s.FOV / s.numRays * j - (double) s.FOV / 2)));
                if (lengh[i][1] == 0){
                    g.setColor(Color.RED);
                    g.fillRect((int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection);

                    double[] le = new  double[3];

                    for (int t = 0; t < s.numRays; ++t){
                        if (lengh[t][2] == j + 1) le = lengh[t];
                    }

                    if (l * l < (s.granata.x - s.player.pos[0]) * (s.granata.x - s.player.pos[0]) + (s.granata.y - s.player.pos[1]) * (s.granata.y - s.player.pos[1]) && !gg){
                        s.granata.paint(g);
                        gg = true;
                    }

                    if (l * l < (s.explosion.x - s.player.pos[0]) * (s.explosion.x - s.player.pos[0]) + (s.explosion.y - s.player.pos[1]) * (s.explosion.y - s.player.pos[1]) && !exp){
                        s.explosion.paint(g);
                        exp = true;
                    }

                    for (int t = 0; t < 10000; ++t){
                        if (l < d[t][0][0] && d[t][0][1] == 0){

                            g.setColor(Color.BLACK);
                             g.drawLine(q[t][0][0], q[t][0][1], q[t][1][0], q[t][1][1]);
                             d[t][0][1] = 1;
                        }
                    }

                    for (int t = 0; t < s.bonuses.size(); ++t){
                        Bonus bon1 = s.bonuses.get(t);
                        if (l * l < (bon1.x - s.player.pos[0]) * (bon1.x - s.player.pos[0]) + (bon1.y - s.player.pos[1]) * (bon1.y - s.player.pos[1]) && bon[t] == 0){
                            g.setColor(Color.BLACK);
                            bon1.paint(g);
                            bon[t] = 1;
                        }
                    }

                    if (lengh[i][4] > 0) {

                        BufferedImage sc = image4.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());

                        if (lengh[i][5] == 1) {
                            sc = image3.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }if (lengh[i][5] == 2) {
                            sc = image2.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }if (lengh[i][5] == 3) {
                            sc = image1.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }if (lengh[i][5] == 4) {
                            sc = image.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }


                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection, null);
                    }else if (lengh[i][4] < 0){
                        BufferedImage sc = image4.getSubimage((int) (image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(-(image.getWidth() * lengh[i][4] / mapp), 1), image.getHeight());

                        if (lengh[i][5] == 1) {
                            sc = image3.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }if (lengh[i][5] == 2) {
                            sc = image2.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }if (lengh[i][5] == 3) {
                            sc = image1.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }if (lengh[i][5] == 4) {
                            sc = image.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(image.getWidth() * lengh[i][4] / mapp, 1), image.getHeight());
                        }


                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection, null);
                    }

                }else if (lengh[i][1] == 1){
                    g.setColor(Color.RED);
                    g.fillRect((int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection);

                    if (lengh[i][4] > 0) {
                        BufferedImage sc = sky1.getSubimage((int) (sky1.getWidth() - sky.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(sky.getWidth() * lengh[i][4] / mapp, 1), sky.getHeight());

                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection, null);
                    }else if (lengh[i][4] < 0){
                        BufferedImage sc = sky1.getSubimage((int) (sky.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(-(sky.getWidth() * lengh[i][4] / mapp), 1), sky.getHeight());

                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection, null);
                    }

//                    if (lengh[i][2] - lengh[(i + 1079) % 1080][2] > 0) {
//                        BufferedImage sc = image.getSubimage((int) (image.getWidth() - image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max((image.getWidth() * (lengh[i][2] - lengh[(i + 1079) % 1080][2])) / mapp, 1), image.getHeight());
//
//                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2, (int) w[j] + 1, projection, null);
//                    }else if (lengh[i][2] - lengh[(i + 1079) % 1080][2] < 0){
//                        BufferedImage sc = image.getSubimage((int) (image.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(-(image.getWidth() * (lengh[i][2] - lengh[(i + 1079) % 1080][2])) / mapp, 1), image.getHeight());
//
//                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2, (int) w[j] + 1, projection, null);
//                    }
                }else{
                    if (!s.sprites[(int) lengh[i][1] - 2].die) {
                        g.drawImage(s.sprites[(int) lengh[i][1] - 2].image, (int) (s.width - 1.0 * s.width / s.numRays * lengh[i][2] - projection / 2), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, projection, projection, null);

                        g.setColor(Color.PINK);
                        g.fillRect((int) (s.width - 1.0 * s.width / s.numRays * lengh[i][2] - projection / 2), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) - projection / 10 + (int) s.player.h * 100, s.sprites[(int) lengh[i][1] - 2].health * projection / 100, projection / 10);
                    }
                }
            }

        }

        if (!gg) s.granata.paint(g);
        if (!exp) s.explosion.paint(g);

        for (int t = 0; t < 10000; ++t){
            if (d[t][0][1] == 0){
                g.drawLine(q[t][0][0], q[t][0][1], q[t][1][0], q[t][1][1]);
            }
        }

        for (int t = 0; t < s.bonuses.size(); ++t){
            Bonus bon1 = s.bonuses.get(t);
            if (bon[t] != 0){
                g.setColor(Color.BLACK);
                bon1.paint(g);
            }
        }

    }


    @Override
    public void paint(Graphics g) {

        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
        }
        g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        super.paint(g);

        if (s.menu){

            g.drawImage(menu, 0, 0, s.width, s.height, null);

        }

        if (s.chooseLevel){
            g.drawImage(cl, 0, 0, s.width, s.height, null);

            g.setColor(Color.GREEN);
            g.setFont(font);

            g.fillRect(100, 100, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("1", 110, 130);

            if(s.level1.passed) g.setColor(Color.GREEN);
            else g.setColor(Color.GRAY);
            g.fillRect(250, 100, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("2", 260, 130);

            if(s.level2.passed) g.setColor(Color.GREEN);
            else g.setColor(Color.GRAY);
            g.fillRect(400, 100, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("3", 410, 130);

            if(s.level3.passed) g.setColor(Color.GREEN);
            else g.setColor(Color.GRAY);
            g.fillRect(550, 100, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("4", 560, 130);

            if(s.level4.passed) g.setColor(Color.GREEN);
            else g.setColor(Color.GRAY);
            g.fillRect(700, 100, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("5", 710, 130);

        }

        if (s.game) {
            z = RayCaster.rayCast();
            g.setColor(Color.DARK_GRAY);

            double a = Math.random();

            if (a < 0.9) sky = sky1;
            else sky = sky2;

            g.drawImage(sky, 0, 0, s.width, s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle)), null);
            g.fillRect(0, s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle)), s.width, s.height / 2 - (int) (s.height * Math.tan(s.player.vertAngle)));

            draw(g);
            drawRays(g);


            drawMap(g);

            for (int i = 0; i < s.sprites.length; ++i) {
                s.sprites[i].draw(g);
                s.sprites[i].move(g);
            }
            g.setColor(Color.BLACK);

            g.fillRect(s.width / 2 - 2, s.height / 2 -15, 4, 30);
            g.fillRect(s.width / 2 - 15, s.height / 2 -2, 30, 4);


            for (Bullet bullet : s.bullets) bullet.draw(g);

            s.player.choose(g);
            try {
                s.player.draw(g);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            map = s.map;

        }

        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public void mouseClicked(MouseEvent e) {



    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (s.menu){
            if (18.0 / 64 * s.width < e.getX() && 46.0 / 64 * s.width > e.getX()){
                if (15.5 / 48 * s.height < e.getY() && 25.0 / 48 * s.height > e.getY()){
                    s.menu = false;
                    s.chooseLevel = true;
                    s.game = false;
                }
            }

            if (18.0 / 64 * s.width < e.getX() && 46.0 / 64 * s.width > e.getX()){
                if (25.5 / 48 * s.height < e.getY() && 37.2 / 48 * s.height > e.getY()){
                    System.exit(0);
                }
            }
        }

        if(s.chooseLevel){

            if(100 < e.getX() && 150 > e.getX()){
                if(100 < e.getY() && 150 > e.getY()){
                    s.level = 1;

                    s.menu = false;
                    s.chooseLevel = false;
                    s.game = true;

                    s.player.pos[0] = s.level1.pp[0];
                    s.player.pos[1] = s.level1.pp[1];
                }
            }

            if(s.level1.passed) {
                if (250 < e.getX() && 300 > e.getX()) {
                    if (100 < e.getY() && 150 > e.getY()) {
                        s.level = 2;

                        s.menu = false;
                        s.chooseLevel = false;
                        s.game = true;

                        s.player.pos[0] = s.level2.pp[0];
                        s.player.pos[1] = s.level2.pp[1];
                    }
                }
            }

            if(s.level2.passed) {
                if (400 < e.getX() && 450 > e.getX()) {
                    if (100 < e.getY() && 150 > e.getY()) {
                        s.level = 3;

                        s.menu = false;
                        s.chooseLevel = false;
                        s.game = true;

                        s.player.pos[0] = s.level3.pp[0];
                        s.player.pos[1] = s.level3.pp[1];
                    }
                }
            }

            if(s.level3.passed) {
                if (550 < e.getX() && 600 > e.getX()) {
                    if (100 < e.getY() && 150 > e.getY()) {
                        s.level = 4;

                        s.menu = false;
                        s.chooseLevel = false;
                        s.game = true;

                        s.player.pos[0] = s.level4.pp[0];
                        s.player.pos[1] = s.level4.pp[1];
                    }
                }
            }

            if(s.level4.passed) {
                if (700 < e.getX() && 750 > e.getX()) {
                    if (100 < e.getY() && 150 > e.getY()) {
                        s.level = 5;

                        s.menu = false;
                        s.chooseLevel = false;
                        s.game = true;

                        s.player.pos[0] = s.level5.pp[0];
                        s.player.pos[1] = s.level5.pp[1];
                    }
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
