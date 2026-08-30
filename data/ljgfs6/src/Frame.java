import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame implements MouseListener {

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

    BufferedImage sky = ImageIO.read(new File("data\\Широкий_Путин_идёт.jpg"));

    Frame() throws IOException {
        this.addKeyListener(s.player);
        this.addMouseListener(this);

        this.addMouseMotionListener(s.player);
        this.addMouseListener(s.player);

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

        double[][] lengh = z[0];

        //double a = 0;
        int sp = 0;

        for (int i = 0; i <= s.numRays + 1; ++i){

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

//                        double d = (l * Math.cos(Math.toRadians((double) s.FOV / s.numRays * j - (double) s.FOV / 2)));
//                        double ang = Math.atan((double) RayCaster.mapp / 2 / d) - s.player.vertAngle;
//
//                        double ang1 = -Math.atan((double) RayCaster.mapp / 2 / d) - s.player.vertAngle;
//
//                        int up = -(int) (s.dist * Math.tan(ang)) + s.height / 2;
//                        int down = -(int) (s.dist / Math.cos(ang1) * Math.sin(ang1)) + s.height / 2;
//
//                        g.drawImage(sc, (int) (s.width - a[j]), up, (int) w[j] + 1, up - down, null);

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
//
//                        double d = (l * Math.cos(Math.toRadians((double) s.FOV / s.numRays * j - (double) s.FOV / 2)));
//                        double ang = Math.atan((double) RayCaster.mapp / 2 / d) - s.player.vertAngle;
//
//                        double ang1 = -Math.atan((double) RayCaster.mapp / 2 / d) - s.player.vertAngle;
//
//                        int up = -(int) (s.dist * Math.tan(ang)) + s.height / 2;
//                        int down = -(int) (s.dist / Math.cos(ang1) * Math.sin(ang1)) + s.height / 2;
//
//
//                        g.drawImage(sc, (int) (s.width - a[j]), up, (int) w[j] + 1, up - down, null);

                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection, null);
                    }

                }else if (lengh[i][1] == 1){
                    g.setColor(Color.RED);
                    g.fillRect((int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection);

                    if (lengh[i][4] > 0) {
                        BufferedImage sc = sky.getSubimage((int) (sky.getWidth() - sky.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(sky.getWidth() * lengh[i][4] / mapp, 1), sky.getHeight());

                        g.drawImage(sc, (int) (s.width - a[j]), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, (int) w[j] + 1, projection, null);
                    }else if (lengh[i][4] < 0){
                        BufferedImage sc = sky.getSubimage((int) (sky.getWidth() * lengh[i][2] / mapp), 0, (int) Math.max(-(sky.getWidth() * lengh[i][4] / mapp), 1), sky.getHeight());

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
                }else if (lengh[i][1] == 2){
                    if (!s.sprites[0].die) {
                        g.drawImage(s.sprites[0].image, (int) (s.width - 1.0 * s.width / s.numRays * lengh[i][2] - projection / 2), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, projection, projection, null);

                        g.setColor(Color.PINK);
                        g.fillRect((int) (s.width - 1.0 * s.width / s.numRays * lengh[i][2] - projection / 2), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) - projection / 10 + (int) s.player.h * 100, s.sprites[0].health * projection / 100, projection / 10);
                    }
                }else if (lengh[i][1] == 3) {
                    if (!s.sprites[1].die) {
                        g.drawImage(s.sprites[1].image, (int) (s.width - 1.0 * s.width / s.numRays * lengh[i][2] - projection / 2), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) + (int) s.player.h * 100, projection, projection, null);

                        g.setColor(Color.PINK);
                        g.fillRect((int) (s.width - 1.0 * s.width / s.numRays * lengh[i][2] - projection / 2), s.height / 2 - projection / 2 + (int) (s.height * Math.tan(s.player.vertAngle)) - projection / 10 + (int) s.player.h * 100, s.sprites[1].health * projection / 100, projection / 10);
                    }
                    }
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

            g.fillRect(100, 100, 500, 300);

        }

        if (s.game) {
            z = RayCaster.rayCast();
            g.setColor(Color.DARK_GRAY);

            g.drawImage(sky, 0, 0, s.width, s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle)), null);
            g.fillRect(0, s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle)), s.width, s.height / 2 - (int) (s.height * Math.tan(s.player.vertAngle)));

            draw(g);
            drawRays(g);

            s.player.draw(g);

            drawMap(g);

            s.sprites[0].draw(g);
            s.sprites[1].draw(g);

            g.setColor(Color.BLACK);

            g.fillRect(s.width / 2 - 2, s.height / 2 -15, 4, 30);
            g.fillRect(s.width / 2 - 15, s.height / 2 -2, 30, 4);
            s.granata.paint(g);


            s.explosion.paint(g);

            for (Bullet bullet : s.bullets) bullet.draw(g);

            s.player.choose(g);

        }

        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (s.menu){
            if (100 < e.getX() && 600 > e.getX()){
                if (100 < e.getY() && 400 > e.getY()){
                    s.menu = false;
                    s.game = true;
                }
            }
        }else{
//            s.menu = true;
//            s.game = false;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
