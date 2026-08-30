public class Level {

    int[][] map;
    int mapWidth;

    Sprite[] sprites;
    Sprite[] normSprites;

    boolean passed = false;

    int[] pp = new int[2];

    Level(int[][] map, int mapWidth, Sprite[] sprites){
        this.map = map;
        this.mapWidth = mapWidth;
        this.sprites = sprites;
        this.normSprites = sprites;
    }

    Level(){
        pp[0] = 200;
        pp[1] = 100;
    }

}
