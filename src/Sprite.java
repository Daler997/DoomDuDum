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

    boolean g = false;

    double aaa = 0;

//    int[] p = new int[2];
//    int[] pP = new int[2];

    Node p = new Node(0,0,0);
    Node pP = new Node(0,0,0);

    ArrayList<Node> path = new ArrayList<Node>();

    Sprite(double x, double y){
        this.x = x;
        this.y = y;

        path.add(p);
        path.add(pP);
    }

    Sprite(Sprite sprite){

        x = sprite.x;
        y = sprite.y;
        health = sprite.health;
        speed = sprite.speed;

        image = sprite.image;

        image1 = sprite.image1;
        image2 = sprite.image2;
        image3 = sprite.image3;
        image4 = sprite.image4;

        path.add(p);
        path.add(pP);
    }

    boolean die = false;

    void draw(Graphics g){
        g.setColor(Color.RED);
        if (!die) {
            g.fillOval((int) x + 2, (int) y + 25, 10, 10);


            for (int i = 0; i < path.size() - 1; ++i) {
                g.drawLine(path.get(i).x * RayCaster.mapp + 7 + RayCaster.mapp / 2, path.get(i).y * RayCaster.mapp + 30 + RayCaster.mapp / 2, path.get(i + 1).x * RayCaster.mapp + 7 + RayCaster.mapp / 2, path.get(i + 1).y * RayCaster.mapp + 30 + RayCaster.mapp / 2);
            }
        }
    }

    void move(Graphics g){

        p.x = (int)(x / RayCaster.mapp) ;
        p.y = (int)(y / RayCaster.mapp);
        pP.y = (int)(s.player.pos[1] / RayCaster.mapp);
        pP.x = (int)(s.player.pos[0] / RayCaster.mapp);




        if (!die) {

            if (path.size() >= 2) {
                if ((path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x) * (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x) + (path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0 - y) * (path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0 - y) <= speed * speed) {
                    x = path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0;
                    y = path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0;

                    findPath();
                }
                if (path.size() >= 2) {
                    if (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x > 0) {
                        x -= (speed * Math.cos(Math.PI + Math.atan((path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0 - y) / (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x))));
                        y -= (speed * Math.sin(Math.PI + Math.atan((path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0 - y) / (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x))));
                    } else if (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x < 0) {
                        x -= (speed * Math.cos(Math.atan((path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0 - y) / (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x))));
                        y -= (speed * Math.sin(Math.atan((path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0 - y) / (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x))));
                    } else {
                        y += (speed * Math.sin(Math.atan((path.get(path.size() - 2).y * RayCaster.mapp + RayCaster.mapp / 2.0 - y) / (path.get(path.size() - 2).x * RayCaster.mapp + RayCaster.mapp / 2.0 - x))));
                    }
                }
            } else {

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
        }




            //            if(path.get(path.size() - 2).x == path.get(path.size() - 1).x){
//                if(path.get(path.size() - 2).y == path.get(path.size() - 1).y + 1){
//                    y += speed;
//                }else{
//                    y -= speed;
//                }
//            }else{
//                if(path.get(path.size() - 2).x == path.get(path.size() - 1).x + 1){
//                    x += speed;
//                }else{
//                    x -= speed;
//                }
//            }


        if (aaa <= 1) image = image1;
        else if (aaa <= 2) image = image2;
        else if (aaa <= 3) image = image3;
        else if (aaa <= 4){
            image = image4;
            aaa = 0;
        }

        aaa += 0.2;



        if (health <= 0 && !die){
            if (s.player.mode == "ak"){
                s.player.g2b += 10;
            }if (s.player.mode == "gun"){
                s.player.g1b += 10;
            }if (s.player.mode == "granata"){
                s.player.g += 1;
            }
            die = true;
        }
        if ((s.explosion.x - x) * (s.explosion.x - x) + (s.explosion.y - y) * (s.explosion.y - y) < RayCaster.mapp * RayCaster.mapp + 100 && !die){
            health = 0;
        }

        ArrayList<Bullet> b = new ArrayList<Bullet>();
        for (Bullet bullet : s.bullets){
            if ((x - bullet.x) * (x - bullet.x) + (y - bullet.y) * (y - bullet.y) < RayCaster.mapp / 2 * RayCaster.mapp / 2 && Math.abs(bullet.h) <= RayCaster.mapp / 2 + 1 && !die){

                health -= bullet.damage;
                b.add(bullet);
            }
        }
        for (Bullet bullet : b){
            s.bullets.remove(bullet);
        }
    }

    void findPath(){

        ArrayList<Node> reachable = new ArrayList<Node>();
        reachable.add(p);

        ArrayList<Node> explored = new ArrayList<Node>();

        path = new ArrayList<Node>();


        boolean c = false;
        int k = 0;

        while (!c && !reachable.isEmpty()){
            ++k;
            Node node = chooseNode(reachable);

            reachable.remove(node);
            explored.add(node);

            reachable.addAll(adj(node, explored, reachable));

            for(Node n : reachable){

                if (n.x == pP.x && n.y == pP.y) {
                    c = true;

                    while (n.previous.x != p.x || n.previous.y != p.y){
                        path.add(n);
                        n = n.previous;
                    }
                    path.add(p);



                }
            }

        }

    }

    ArrayList<Node> adj(Node node, ArrayList<Node> explored, ArrayList<Node> reachable){
        ArrayList<Node> adj = new ArrayList<Node>();

        Node a;

        for (int i = node.x - 1; i <= node.x + 1; ++i){
            for (int j = node.y - 1; j <= node.y + 1; ++j) {
                if (Math.abs(i - node.x) != Math.abs(j - node.y)){


                    a = new Node(i, j, node.cost + 1, node);
                    adj.add(a);

                    for (Node node1 : explored){
                        if(node1.x == i && node1.y == j){
                            adj.remove(a);
                        }
                    }

                    for (Node node1 : reachable){
                        if(node1.x == i && node1.y == j){
                            adj.remove(a);
                        }
                    }

                }
            }
        }

        ArrayList<Node> r = new ArrayList<Node>();

        for(Node n : adj){
            double[] b = new double[2];
            b[0] = n.y * RayCaster.mapp;
            b[1] = n.x * RayCaster.mapp;

            if (RayCaster.in(s.map, b)){
                r.add(n);
            }
        }

        for(Node n : r){
            adj.remove(n);
        }

        return adj;
    }

    Node chooseNode(ArrayList<Node> reachable){
        Node node = new Node(0,0,0);
        int cost = 1000;

        for (Node node1 : reachable){
            if (node1.cost < cost) {
                node = node1;
                cost = node.cost;
            }
        }

        return node;
    }

}
