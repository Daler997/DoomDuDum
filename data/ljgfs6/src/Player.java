import javax.imageio.ImageIO;
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

    Gun gun;

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


    String mode = "gun";
    boolean chooseMode = false;

    BufferedImage mode1;
    BufferedImage mode2;

    BufferedImage gran;
    BufferedImage gunn;

    Font font = new Font("Courier new", 1, 50);

    boolean flag = false;

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
            g.fillOval(300, 300, 400, 400);

            g.drawImage(gran, 550, 450, 130, 100, null);

            g.drawImage(gunn, 350, 450, 135, 120, null);

            PointerInfo info = MouseInfo.getPointerInfo();
            Point location = info.getLocation();
            int x = (int) location.getX();

            if (x > 500) {
                mode = "granata";

                g.drawImage(mode2, 500, 300, 200, 400, null);
                g.drawImage(gran, 550, 450, 130, 100, null);
            }
            else {
                mode = "gun";

                g.drawImage(mode1, 300, 300, 200, 400, null);
                g.drawImage(gunn, 350, 450, 135, 120, null);
            }
        }
    }

    void control(){

        if (stamina <= 0) run = false;

        System.out.println(stamina);

        if (run) speed = s.speed * 3;
        else speed = s.speed;

        if (run) stamina -= 0.3;
        else if (stamina < 100) stamina += 0.2;


        if (f || b || l || r){
            gun.a += 1;
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


        if (pos[0] > s.sprites[0].x - 10 && pos[0] < s.sprites[0].x + 10 && !s.sprites[0].die){
            if (pos[1] > s.sprites[0].y - 10 && pos[1] < s.sprites[0].y + 10){
                health -= 0.5;
            }
        }
        if (pos[0] > s.sprites[1].x - 10 && pos[0] < s.sprites[1].x + 10 && !s.sprites[1].die){
            if (pos[1] > s.sprites[1].y - 10 && pos[1] < s.sprites[1].y + 10){
                health -= 0.5;
            }
        }
        if (health <= 0){
            s.menu = true;
            s.game = false;

            health = 300;
            pos[0] = 150;
            pos[1] = 150;

            s.sprites[0].die = false;
            s.sprites[0].x = 150;
            s.sprites[0].y = 100;
        }


        if ((s.explosion.x - pos[0]) * (s.explosion.x - pos[0]) + (s.explosion.y - pos[1]) * (s.explosion.y - pos[1]) < RayCaster.mapp * RayCaster.mapp){
            if (!s.explosion.bam) health -= 100;
            s.explosion.bam = true;
        }

//        if (jump){
//            if (h >= 0){
//                h += dH;
//                dH -= 0.002;
//            }
//            else {
//                h = 0;
//                jump = false;
//                dH = 0.1;
//            }
//        }


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
            s.menu = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SHIFT){
            chooseMode = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && flag){
            run = true;
            flag = false;
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
            gun.bulletCount = 10;
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
    void draw(Graphics g){
        if (mode == "gun") gun.draw(g);

        g.setColor(Color.RED);
        g.fillRect(700, 150, (int) health, 30);

        g.setColor(Color.GRAY);
        g.fillRect(0, s.height * 8 / 10, s.width, s.height * 2 / 10);


        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawString("health", s.width / 10, (int) (s.height * 8.5 / 10));
        g.drawString(String.valueOf(health), s.width / 10, (int) (s.height * 8.5 / 10) + 50);

        g.drawString("stamina", s.width * 4 / 10, (int) (s.height * 8.5 / 10));
        g.drawString(String.valueOf((int) stamina), s.width * 4 / 10, (int) (s.height * 8.5 / 10) + 50);
    }

    void shot(){
        if (gun.bulletCount > 0) {
            if(!gun.shot) gun.bulletCount -= 1;
            gun.shot = true;
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
        if (mode == "gun" && e.getButton() == 1) {
            shot();
        }


        if (e.getButton() == 1 && mode == "granata") s.granata.trajectory = true;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1 && mode == "granata"){
            s.granata.dropped = true;
        }
        if (e.getButton() == 1 && mode == "granata") s.granata.trajectory = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
