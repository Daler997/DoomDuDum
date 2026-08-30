import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Granata {

    double x = s.player.pos[0];
    double y = s.player.pos[1];
    double h = 0;

    double ang = s.player.angle;
    double vAng = s.player.vertAngle;

    double a = 0.0015;

    double sp0 = 0.02;
    double sp = 0.02;

    double dSp = 0;

    double[] bam = new double[2];

    boolean dropped = false;
    boolean trajectory = false;


    int o = 0;

    void drop(){
        if (!dropped){
            x = s.player.pos[0] - 3 * Math.cos(Math.toRadians(-140 - s.player.angle));
            y = s.player.pos[1] - 3 * Math.sin(Math.toRadians(-140 - s.player.angle));

            sp = sp0;

            h = 2 * s.player.vertAngle;
            ang = Math.toRadians(s.player.angle) + 0.0;
            vAng = s.player.vertAngle;

            bam[0] = 0;
            bam[1] = 0;

            sp0 += dSp;

            sp0 = 0.3 * Math.sin(vAng);

        }else{
            if (h > -RayCaster.mapp / 2){
                double PP = x;
                double QQ = y;


                x += 0.3 * Math.cos(vAng) * Math.cos(ang);
                y -= 0.3 * Math.cos(vAng) * Math.sin(ang);

                if (collideX(PP, y, h)){
                    y += 0.3 * Math.cos(vAng) * Math.sin(ang);
                    ang = -ang;
                }
                if (collideY(QQ, x, h)){
                    x -= 0.3 * Math.cos(vAng) * Math.cos(ang);
                    ang = Math.PI - ang;
                }

                //if (collide (x, y, h, PP, QQ)) sp = -sp;
                h += sp;
                sp -= a;

            }else {
                dropped = false;

                bam[0] = x;
                bam[1] = y;
            }

        }
    }


    void paint (Graphics g){

        g.fillOval((int) x + 3, (int) y + 25, 10, 10);

        double angle = 0;

        if (x != s.player.pos[0]) angle = -Math.toDegrees(Math.atan((y - s.player.pos[1]) / (x -  s.player.pos[0])));
        if (x == s.player.pos[0]) angle = 90;

        if (x - s.player.pos[0] < 0) angle += 180;

        angle -= (s.player.angle - (double) s.FOV / 2) - 360;
        angle %= 360;

        angle = angle / ((double) s.FOV / s.numRays);

        double l = Math.sqrt((x - s.player.pos[0]) * (x - s.player.pos[0]) + (y - s.player.pos[1]) * (y - s.player.pos[1]));

        int projection = (int) (s.projHeight / l / Math.cos(Math.toRadians((double) s.FOV / s.numRays * angle - (double) s.FOV / 2)));

        if (dropped) g.fillOval((int) (s.width - 1.0 * s.width / s.numRays * angle - projection / 20.0), s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle - Math.atan(h / l))) - projection / 20, projection / 10, projection / 10);
        showTrajectory(g);

//
//        sp += 0.001 * Math.sin(Math.toRadians(o));
//        o += 1;
    }

    void showTrajectory(Graphics g){
        if (trajectory){
            double xx = s.player.pos[0] - 3 * Math.cos(Math.toRadians(-140 - s.player.angle));
            double yy = s.player.pos[1] - 3 * Math.sin(Math.toRadians(-140 - s.player.angle));
            double hh = 2 * s.player.vertAngle;
            double aa = 0.0015;
            double spp = sp0;
            double angg = Math.toRadians(s.player.angle) + 0.0;

            int[][] show = new int[10000][2];

            int i = 0;

            while (hh > -RayCaster.mapp / 2 & i < 1000){
                show[i] = coord(xx, yy, hh);
                    double PP = xx;
                    double QQ = yy;

//                    xx += 0.3 * Math.cos(angg);
//                    yy -= 0.3 * Math.sin(angg);
                xx += 0.3 * Math.cos(vAng) * Math.cos(angg);
                yy -= 0.3 * Math.cos(vAng) * Math.sin(angg);
                    if (collideX(PP, yy, hh)) {
                        yy += 0.3 * Math.cos(vAng) * Math.sin(ang);
                        angg = -angg;
                    }
                    if (collideY(QQ, xx, hh)) {
                        xx -= 0.3 * Math.cos(vAng) * Math.cos(ang);
                        angg = Math.PI - angg;
                    }

                    //if (collide(xx, yy, hh, PP, QQ)) spp = -spp;
                    hh += spp;
                    spp -= aa;
                    i += 1;
            }
            int[] dot1 = show[0];
            for (int[] dot2 : show){
                if (!Arrays.equals(dot2, new int[2]) && !Arrays.equals(dot1, new int[2]) && Math.abs(dot2[0] - dot1[0]) < 100){
                    g.drawLine(dot1[0], dot1[1], dot2[0], dot2[1]);
                }
                dot1 = dot2;
            }

        }
    }

    int[] coord(double x, double y, double h){
        double angle = 0;

        if (x != s.player.pos[0]) angle = -Math.toDegrees(Math.atan((y - s.player.pos[1]) / (x -  s.player.pos[0])));
        if (x == s.player.pos[0]) angle = 90;

        if (x - s.player.pos[0] < 0) angle += 180;

        angle -= s.player.angle - (double) s.FOV / 2 - 360;
        angle %= 360;
        angle =  angle / ((double) s.FOV / s.numRays);

        double l = Math.sqrt((x -  s.player.pos[0]) * (x -  s.player.pos[0]) + (y - s.player.pos[1]) * (y - s.player.pos[1]));

        int projection = (int) (s.projHeight / l / Math.cos(Math.toRadians((double) s.FOV / s.numRays * angle - (double) s.FOV / 2)));
        int[] j = new int[2];
        j[0] = (int) (s.width - 1.0 * s.width / s.numRays * angle);
        j[1]  = s.height / 2 + (int) (s.height * Math.tan(s.player.vertAngle - Math.atan(h / l)));

        return j;
    }


    Boolean collideX(double PP, double y, double h){
        boolean c = false;

        for (int[] i : s.map){
            if(i[0] + RayCaster.mapp + 1 > PP && PP > i[0] - 1){
                if (i[1] + RayCaster.mapp + 1 > y && y > i[1] - 1){
                    if(h <= RayCaster.mapp / 2) c = true;
                }
            }
        }

        return c;
    }



    boolean collideY(double QQ, double x, double h){
        boolean c = false;

        for (int[] i : s.map){
            if(i[1] + RayCaster.mapp + 1 > QQ && QQ > i[1] - 1){
                if (i[0] + RayCaster.mapp + 1 > x && x > i[0] - 1){
                    if (h <= RayCaster.mapp / 2) c = true;
                }
            }
        }

        return c;
    }

    boolean collide(double x, double y, double h, double PP, double QQ){
        boolean c = false;
        if (h < 1){
            double[] a = new double[2];
            a[1] = x - x % RayCaster.mapp;
            a[0] = y - y % RayCaster.mapp;
            if (RayCaster.in(s.map, a)){
                if (!collideX(PP, y, h) && !collideY(QQ, x, h)) c = true;
            }
        }
        return c;
    }
}
