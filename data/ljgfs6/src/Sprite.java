import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

    double x = 150;
    double y = 70;
    int health = 100;

    double speed = 10.0 / s.mapWidth;

    BufferedImage image;

    BufferedImage image1;
    BufferedImage image2;
    BufferedImage image3;
    BufferedImage image4;

    double aaa = 0;



    Sprite(double x, double y){
        this.x = x;
        this.y = y;
    }

    boolean die = false;

    void draw(Graphics g){
        g.setColor(Color.RED);
        if (!die) g.fillOval((int) x + 2, (int) y + 25, 10, 10);
    }

    void move(){


        if (aaa <= 1) image = image1;
        else if (aaa <= 2) image = image2;
        else if (aaa <= 3) image = image3;
        else if (aaa <= 4){
            image = image4;
            aaa = 0;
        }

        aaa += 0.2;


        if (!die) {
            if (s.player.pos[0] - x > 0) {
                x -= (speed * Math.cos(Math.PI + Math.atan((s.player.pos[1] - y) / (s.player.pos[0] - x))));
                y -= (speed * Math.sin(Math.PI + Math.atan((s.player.pos[1] - y) / (s.player.pos[0] - x))));
            } else if (s.player.pos[0] - x < 0) {
                x -= (speed * Math.cos(Math.atan((s.player.pos[1] - y) / (s.player.pos[0] - x))));
                y -= (speed * Math.sin(Math.atan((s.player.pos[1] - y) / (s.player.pos[0] - x))));
            } else {
                y += (speed * Math.sin(Math.atan((s.player.pos[1] - y) / (s.player.pos[0] - x))));
            }
        }
        if (health <= 0){
            die = true;
        }
        if ((s.explosion.x - x) * (s.explosion.x - x) + (s.explosion.y - y) * (s.explosion.y - y) < RayCaster.mapp * RayCaster.mapp + 100){
            die = true;
        }

        ArrayList<Bullet> b = new ArrayList<Bullet>();
        for (Bullet bullet : s.bullets){
            if ((x - bullet.x) * (x - bullet.x) + (y - bullet.y) * (y - bullet.y) < RayCaster.mapp / 2 * RayCaster.mapp / 2 && Math.abs(bullet.h) <= RayCaster.mapp / 2 + 1){
                health -= bullet.damage;
                b.add(bullet);
            }
        }
        for (Bullet bullet : b){
            s.bullets.remove(bullet);
        }
    }

}
