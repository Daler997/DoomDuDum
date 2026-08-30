import java.util.ArrayList;

public class s {

    static int level = 1;

    static Level level1;
    static Level level2;
    static Level level3;
    static Level level4;
    static Level level5;



    static int width = 1080;
    static int height = 720;

    static int mapSize = 300;
    static int mapWidth = 37;

    static int[][] map;

    static int numRays = 300;
    static int maxDepth = 40000;

    static double ratio = 1;

    static int FOV = 70;

    static double projHeight = ratio * mapSize / mapWidth / 2 / Math.tan(Math.toRadians((double) FOV / 2)) * width;

    static double dist = (double) width / 2 / Math.tan(Math.toRadians((double) FOV / 2));

    static double speed = 7.0 / mapWidth;
    static double angleSpeed = 1;

    static Player player = new Player();

    static boolean game = false;
    static boolean menu = true;
    static boolean chooseLevel = false;

    static Sprite[] sprites = new Sprite[2];

    static Granata granata = new Granata();
    static Explosion explosion;

    static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    static ArrayList<Bonus> bonuses = new ArrayList<Bonus>();
}
