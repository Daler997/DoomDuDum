import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player implements KeyListener, MouseMotionListener, MouseListener {

    double[] pos = new double[2];
    double angle = 90;

    double vertAngle = 0;

    double speed = s.speed * 3;
    double angleSpeed = s.angleSpeed;

    double stamina = 100;
    boolean run = false;

    int bbaamm = 0;

    Gun gun;
    Ak ak;

    int g = 5;
    int g1b = 30;
    int g2b = 50;

    int mouse = 0;
    int mouse0 = 0;

    double health = 300;

    boolean f = false;
    boolean b = false;
    boolean l = false;
    boolean r = false;
    boolean q = false;
    boolean e = false;

    double h = 0;
    boolean jump = false;
    double dH = 0.3;

    int bullet = 0;


    String mode = "ak";
    boolean chooseMode = false;

    BufferedImage mode1;
    BufferedImage mode2;
    BufferedImage mode3;


    BufferedImage gran;
    BufferedImage gunn;
    BufferedImage akk;


    Font font = new Font("Courier new", 1, 50);

    boolean flag = false;
    boolean game = false;

    Player() {
        pos[0] = (double) s.mapSize / 2 + 15;
        pos[1] = (double) s.mapSize / 2;
    }



    Boolean collideX(double PP){
        boolean c = false;

        for (int[] i : s.map){
            if(i[0] + RayCaster.mapp + 1 > PP && PP > i[0] - 1){
                if (i[1] + RayCaster.mapp + 1 > pos[1] && pos[1] > i[1] - 1){
                    c = true;
                }
            }
        }

        return c;
    }
    boolean collideY(double QQ){
        boolean c = false;

        for (int[] i : s.map){
            if(i[1] + RayCaster.mapp + 1 > QQ && QQ > i[1] - 1){
                if (i[0] + RayCaster.mapp + 1 > pos[0] && pos[0] > i[0] - 1){
                    c = true;
                }
            }
        }

        return c;
    }

    void choose(Graphics g){
        if (chooseMode){
            g.setColor(Color.GRAY);
            g.fillOval(s.width / 2 - s.width / 6, s.height / 2 - s.width / 6 - 100, s.width / 3, s.width / 3);

            g.drawImage(gran, s.width / 2 - s.width / 7 + s.width / 5, s.height / 2 - s.width / 8 - s.height / 10, 130, 100, null);

            g.drawImage(gunn, s.width / 2 - s.width / 7, s.height / 2 - s.width / 8 - s.height / 10, 135, 120, null);

            double angle = getAngle();
           // System.out.println(angle);

            if (angle < Math.PI / 2 && angle > -Math.PI / 6) {
                mode = "granata";

                //g.drawImage(mode2, 500, 300, 200, 400, null);

                g.drawImage(mode1,s.width / 2 - s.width / 6, s.height / 2 - s.width / 6 - 100, s.width / 3, s.width / 3, null);
               // g.drawImage(gran, s.width / 2 - s.width / 7 + s.width / 5, s.height / 2 - s.width / 8 - s.height / 10, 130, 100, null);
            }
            else if (angle > Math.PI / 2 && angle < 7 * Math.PI / 6){
                mode = "gun";

                g.drawImage(mode2,s.width / 2 - s.width / 6, s.height / 2 - s.width / 6 - 100, s.width / 3, s.width / 3, null);
               // g.drawImage(gunn, s.width / 2 - s.width / 7, s.height / 2 - s.width / 8 - s.height / 10, 135, 120, null);
            }
            else{
                mode = "ak";

                g.drawImage(mode3,s.width / 2 - s.width / 6, s.height / 2 - s.width / 6 - 100, s.width / 3, s.width / 3, null);
               // g.drawImage(akk, s.width / 2 - s.width / 20, s.height / 2 - s.width / 8 + s.height / 5, 135, 120, null);
            }
        }
    }

    private static double getAngle() {
        PointerInfo info = MouseInfo.getPointerInfo();
        Point location = info.getLocation();
        int x = (int) location.getX();
        int y = (int) location.getY();
        double angle = 0;

        System.out.println(x);

        if (s.width / 2 < x){
            angle = Math.atan((double) ((s.height / 2 - 100) - y) / (x - s.width / 2));
        }else if(x < s.width / 2){
            angle = Math.atan((double) ((s.height / 2 - 100) - y) / (x - s.width / 2)) + Math.PI;
        }else {
            if (y < (s.height / 2 - 100)) {
                angle = Math.PI / 2;
            }else{
                angle = 3 * Math.PI / 2;
            }
        }
        return angle;
    }

    void control(){

        if (stamina <= 0) run = false;


        if (run) speed = s.speed * 3;
        else speed = s.speed;

        if (run) stamina -= 0.3;
        else if (stamina < 100) stamina += 0.2;


        if (f || b || l || r){
            gun.a += 1;
            ak.a += 1;
            if (gun.a % 70 == 0){
            new Thread(() -> {
                new MakeSound().playSound("data/SHAG.wav");
            }).start();
            }
        }

        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));

        double PP = pos[0];
        double QQ = pos[1];

        if (f){

            double[] p = new double[2];
            double[] p1 = new double[2];

            p[1] = (int) pos[0] - (int) pos[0] % RayCaster.mapp;
            p[0] = (int) (pos[1] - speed * sin) - (int) (pos[1] - speed * sin) % RayCaster.mapp;

            p1[1] = (int) (pos[0] + speed * cos) - (int) (pos[0] + speed * cos) % RayCaster.mapp;
            p1[0] = (int) pos[1] - (int) pos[1] % RayCaster.mapp;


            pos[1] -= speed * sin;
            pos[0] += speed * cos;

            if (collideX(PP)){
                pos[1] += speed * sin;
            }
            if (collideY(QQ)){
                pos[0] -= speed * cos;
            }
        }
        if (b){

            double[] p = new double[2];
            double[] p1 = new double[2];

            p[1] = (int) pos[0] - (int) pos[0] % RayCaster.mapp;
            p[0] = (int) (pos[1] + speed * sin) - (int) (pos[1] + speed * sin) % RayCaster.mapp;

            p1[1] = (int) (pos[0] - speed * cos) - (int) (pos[0] - speed * cos) % RayCaster.mapp;
            p1[0] = (int) pos[1] - (int) pos[1] % RayCaster.mapp;

            pos[1] += speed * sin;
            pos[0] -= speed * cos;

            if (collideX(PP)){
                pos[1] -= speed * sin;
            }
            if (collideY(QQ)){
                pos[0] += speed * cos;
            }
        }
        if (l){

            double[] p = new double[2];
            double[] p1 = new double[2];

            p[1] = (int) pos[0] - (int) pos[0] % RayCaster.mapp;
            p[0] = (int) (pos[1] - speed * cos) - (int) (pos[1] - speed * cos) % RayCaster.mapp;

            p1[1] = (int) (pos[0] - speed * sin) - (int) (pos[0] - speed * sin) % RayCaster.mapp;
            p1[0] = (int) pos[1] - (int) pos[1] % RayCaster.mapp;

            pos[1] -= speed * cos;
            pos[0] -= speed * sin;

            if (collideX(PP)){
                pos[1] += speed * cos;
            }
            if (collideY(QQ)){
                pos[0] += speed * sin;
            }
        }
        if (r){

            double[] p = new double[2];
            double[] p1 = new double[2];

            p[1] = (int) pos[0] - (int) pos[0] % RayCaster.mapp;
            p[0] = (int) (pos[1] + speed * cos) - (int) (pos[1] + speed * cos) % RayCaster.mapp;

            p1[1] = (int) (pos[0] + speed * sin) - (int) (pos[0] + speed * sin) % RayCaster.mapp;
            p1[0] = (int) pos[1] - (int) pos[1] % RayCaster.mapp;

            pos[1] += speed * cos;
            pos[0] += speed * sin;

            if (collideX(PP)){
                pos[1] -= speed * cos;
            }
            if (collideY(QQ)){
                pos[0] -= speed * sin;
            }
        }

        if (q){
            angle += angleSpeed;
        }
        if (e){
            angle -= angleSpeed;
        }

        for (int i = 0; i < s.sprites.length; ++i) {
            if (pos[0] > s.sprites[i].x - 10 && pos[0] < s.sprites[i].x + 10 && !s.sprites[i].die) {
                if (pos[1] > s.sprites[i].y - 10 && pos[1] < s.sprites[i].y + 10) {
                    health -= 0.5;
                    if (bbaamm % 70 == 0){
                        new Thread(() -> {
                            new MakeSound().playSound("data/udaar.wav");
                        }).start();
                    }
                    bbaamm += 1;
                }
            }
        }

        if (health <= 0){
            s.menu = true;
            s.game = false;

            health = 300;
            pos[0] = 150;
            pos[1] = 150;

        }


        if ((s.explosion.x - pos[0]) * (s.explosion.x - pos[0]) + (s.explosion.y - pos[1]) * (s.explosion.y - pos[1]) < RayCaster.mapp * RayCaster.mapp){
            if (!s.explosion.bam) health -= 100;
            s.explosion.bam = true;
        }



        angle = (angle + 360) % 360;

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyChar() == 'w'){
            f = true;
        }
        if (e.getKeyChar() == 'a'){
            l = true;
        }
        if (e.getKeyChar() == 's'){
            b = true;
        }
        if (e.getKeyChar() == 'd'){
            r = true;
        }
        if (e.getKeyChar() == 'q'){
            q = true;
        }
        if (e.getKeyChar() == 'e'){
            this.e = true;
        }


    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            s.game = false;
            s.chooseLevel = false;
            s.menu = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SHIFT){
            chooseMode = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && flag){
            run = true;
            flag = false;
        }


        if (e.getKeyCode() == KeyEvent.VK_F1){
            System.out.println(1);
            s.level1.passed = true;
            s.level2.passed = true;
            s.level3.passed = true;
            s.level4.passed = true;
            s.level5.passed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {



        if (e.getKeyChar() == 'w'){
            f = false;
        }
        if (e.getKeyChar() == 'a'){
            l = false;
        }
        if (e.getKeyChar() == 's'){
            b = false;
        }
        if (e.getKeyChar() == 'd'){
            r = false;
        }
        if (e.getKeyChar() == 'q'){
            q = false;
        }
        if (e.getKeyChar() == 'e'){
            this.e = false;
        }
        if (e.getKeyChar() == 'r'){
            if (mode == "gun"){
                if (g1b + gun.bulletCount >= 10){
                    g1b -= 10 - gun.bulletCount;;
                    gun.bulletCount = 10;
                }else{
                    gun.bulletCount += g1b;
                    g1b = 0;
                }
            }
            else if(mode == "ak") ak.reload = true;
        }

        if (e.getKeyChar() == 'z') s.granata.dSp = 0;
        if (e.getKeyChar() == 'x') s.granata.dSp = 0;

        if (e.getKeyCode() == KeyEvent.VK_SHIFT){
            chooseMode = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            run = false;
            flag = true;
        }

    }
    void draw(Graphics g) throws LineUnavailableException, IOException {
        if (mode == "gun") gun.draw(g);
        else if(mode == "ak") ak.draw(g);

//        g.setColor(Color.RED);
//        g.fillRect(700, 150, (int) health, 30);

        g.setColor(Color.GRAY);
        g.fillRect(0, s.height * 8 / 10, s.width, s.height * 2 / 10);


        g.setFont(font);
        g.setColor(Color.BLACK);


        g.drawString("health", s.width / 10, (int) (s.height * 8.5 / 10));
        g.drawString(String.valueOf(health), s.width / 10, (int) (s.height * 8.5 / 10) + 50);

        g.drawString("stamina", s.width * 4 / 10, (int) (s.height * 8.5 / 10));
        g.drawString(String.valueOf((int) stamina), s.width * 4 / 10, (int) (s.height * 8.5 / 10) + 50);

        if (mode == "granata") {
            g.drawString("granates", s.width * 7 / 10, (int) (s.height * 8.5 / 10));
            g.drawString(String.valueOf(this.g), s.width * 7 / 10, (int) (s.height * 8.5 / 10) + 50);
        }else if (mode == "ak"){
            g.drawString("gun2 bullets", s.width * 7 / 10, (int) (s.height * 8.5 / 10));
            String string = ak.bulletCount + "/" + String.valueOf(this.g2b);
            g.drawString(string, s.width * 7 / 10, (int) (s.height * 8.5 / 10) + 50);
        }else{
            g.drawString("gun1 bullets", s.width * 7 / 10, (int) (s.height * 8.5 / 10));
            String string = gun.bulletCount + "/" + String.valueOf(this.g1b);
            g.drawString(string, s.width * 7 / 10, (int) (s.height * 8.5 / 10) + 50);
        }
    }

    void shot(){
        if(mode == "gun") {
            if (gun.bulletCount > 0) {
                if (!gun.shot) gun.bulletCount -= 1;
                gun.shot = true;
            }
        }if(mode == "ak"){
            if (ak.bulletCount > 0) {
                //if (!ak.shot) ak.bulletCount -= 1;
                ak.shot = true;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {




    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (game) {
            if (mode == "gun" && e.getButton() == 1) {
                shot();
            }

            if (game) {
                if (mode == "ak") {
                    shot();
                }
            }


            if (e.getButton() == 1 && mode == "granata" && g >= 0) s.granata.trajectory = true;

        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (game) {
            ak.shot = false;
            if (e.getButton() == 1 && mode == "granata") {
                if (!s.granata.dropped) {
                    g -= 1;
                    System.out.println(g);
                }

                if (g >= 0) s.granata.dropped = true;
                else g = 0;
            }
            if (e.getButton() == 1 && mode == "granata") s.granata.trajectory = false;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
