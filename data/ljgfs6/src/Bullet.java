import java.awt.*;

public class Bullet {

    double x = s.player.pos[0];
    double y = s.player.pos[1];
    double ang = Math.toRadians(s.player.angle);
    double vAng = s.player.vertAngle;
    double h = 0;

    int damage = 30;

    void draw(Graphics g){

        g.setColor(Color.BLACK);
        //g.fillOval((int) x + 3, (int) y + 25, 10, 10);

        double angle = 0;

        if (x != s.player.pos[0]) angle = -Math.toDegrees(Math.atan((y - s.player.pos[1]) / (x -  s.player.pos[0])));
        if (x == s.player.pos[0]) angle = 90;

        if (x - s.player.pos[0] < 0) angle += 180;

        angle -= (s.player.angle - (double) s.FOV / 2) - 360;
        angle %= 360;

        angle = angle / ((double) s.FOV / s.numRays);

        double l = Math.sqrt((x - s.player.pos[0]) * (x - s.player.pos[0]) + (y - s.player.pos[1]) * (y - s.player.pos[1]));

        int projection = (int) (s.projHeight / l / Math.cos(Math.toRadians((double) s.FOV / s.numRays * angle - (double) s.FOV / 2)));

        if (l > 3) g.fillOval((int) (s.width - 1.0 * s.width / s.numRays * angle - projection / 100.0), s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle - vAng)), projection / 50, projection / 50);


        x += 5 * Math.cos(vAng) * Math.cos(ang);
        y -= 5 * Math.cos(vAng) * Math.sin(ang);
        h += 5 * Math.sin(vAng);

    }

}
