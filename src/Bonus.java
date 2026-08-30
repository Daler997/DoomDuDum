import java.awt.*;
import java.awt.image.BufferedImage;

public class Bonus {

    double x;
    double y;

    int type = 0;

    Bonus(double x, double y){
        this.x = x;
        this.y = y;
    }

    void paint(Graphics g){

        if (x != 0) {

            double angle = 0;

            if (x != s.player.pos[0]) angle = -Math.toDegrees(Math.atan((y - s.player.pos[1]) / (x - s.player.pos[0])));
            if (x == s.player.pos[0]) angle = 90;

            if (x - s.player.pos[0] < 0) angle += 180;

            angle -= (s.player.angle - (double) s.FOV / 2) - 360;
            angle %= 360;

            angle = (int) angle / ((double) s.FOV / s.numRays);

            double l = Math.sqrt((x - s.player.pos[0]) * (x - s.player.pos[0]) + (y - s.player.pos[1]) * (y - s.player.pos[1]));

            int projection = (int) (s.projHeight / l / Math.cos(Math.toRadians((double) s.FOV / s.numRays * angle - (double) s.FOV / 2)));


            g.drawOval((int) (s.width - 1.0 * s.width / s.numRays * angle - projection / 2), s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle)), projection, projection / 2);
        }
    }

}
