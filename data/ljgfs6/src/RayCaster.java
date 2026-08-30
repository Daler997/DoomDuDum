public class RayCaster {

    static int numRays = s.numRays;
    static int FOV = s.FOV;
    static int[][] map = s.map;
    static int maxDepth = s.maxDepth;
    static int mapWidth = s.mapWidth;
    static double rayAngle = 1.0 * FOV / numRays;
    static int mapp = s.mapSize / mapWidth;

    static int n = 0;
    

    static double[][][] rayCast(){

        double[][][] ll = new double[2][numRays][2];


        double[][] lengh = new double[numRays + 2][3];

        double[][] show = new double[numRays][2];

        double right = (s.player.angle - (double) FOV / 2);

        double x = s.player.pos[0];
        double y = s.player.pos[1];

        double ppx = mapp - x % mapp;
        double ppy = y % mapp;




        for (int j = 0; j < numRays; ++j){

            double[] le = new double[6];
            le[1] = 0;
            le[3] = j;
            double[] le1 = new double[6];
            le1[1] = 1;
            le1[3] = j;

            double r = j * rayAngle + right;

            double cos;

            if(Math.cos(Math.toRadians(r)) != 0){
                cos = Math.cos(Math.toRadians(r));
            }else{
                cos = 0.000001;
            }

            double sin;

            if(Math.sin(Math.toRadians(r)) != 0){
                sin = Math.sin(Math.toRadians(r));
            }else{
                sin = 0.000001;
            }

            double tan = sin / cos;

            double xm = x + maxDepth * cos;
            double ym = y - maxDepth * sin;

            double[] coord = new double[2];
            double[] Coord = new double[2];

            double l = 0;
            double l1 = 0;

            for (int i = 0; i < mapWidth + 10; ++i){

                if (sin > 0){
                    coord[0] = x + (ppy + i * mapp) / tan;
                    coord[1] = y - ppy - i * mapp;
                }else{
                    coord[0] = x + (ppy - (i + 1) * mapp) / tan;
                    coord[1] = y - ppy + (i + 1) * mapp;
                }

                double[] a = new double[2];

                a[0] = coord[1];
                a[1] = coord[0] - coord[0] % mapp;

                double[] b = new double[2];

                b[0] = coord[1] - mapp;
                b[1] = coord[0] - coord[0] % mapp;

                l = Math.sqrt((coord[0] - x) * (coord[0] - x) + (coord[1] - y) * (coord[1] - y));

                if (l > maxDepth){
                    coord[0] = xm;
                    coord[1] = ym;

                    l = maxDepth;
                    le[0] = l;

                    break;
                }

                if (in(map, a) || in(map, b)){
                    le[0] = l;
                    le[5] = n;
                    break;
                }else{
                    l = maxDepth * maxDepth + 1;
                }

            }

            for (int i = 0; i < mapWidth + 10; ++i){

                if (cos < 0){
                    Coord[0] = x + ppx - (i + 1) * mapp;
                    Coord[1] = y + (-ppx + (i + 1) * mapp) * tan;
                }else{
                    Coord[0] = x + ppx + i * mapp;
                    Coord[1] = y - (ppx + i * mapp) * tan;
                }


                double[] a = new double[2];

                a[0] = Coord[1] - Coord[1] % mapp;
                a[1] = Coord[0];

                double[] b = new double[2];

                b[0] = Coord[1] - Coord[1] % mapp;
                b[1] = Coord[0] - mapp;

                l1 = Math.sqrt((Coord[0] - x) * (Coord[0] - x) + (Coord[1] - y) * (Coord[1] - y));

                if (l1 > maxDepth){
                    Coord[0] = xm;
                    Coord[1] = ym;

                    l1 = maxDepth;
                    le1[0] = l1;

                    break;
                }

                if (in(map, a) || in(map, b)){
                    le1[0] = l1;
                    le1[5] = n;
                    break;
                }else{
                    l1 = maxDepth * maxDepth;
                }

            }

            if (l < l1){

                le[2] = mapp - coord[0] % mapp;

                lengh[j] = le;
                show[j] = coord;

            }else if (l > l1){

                le1[2] = mapp - Coord[1] % mapp;

                lengh[j] = le1;
                show[j] = Coord;
            }else{
                lengh[j] = le1;
                lengh[j][1] = lengh[Math.abs(j - 1)][1];
                show[j] = coord;
            }

        }

        for (int i = 0; i < 2; ++i){
            double angle = 0;

            if (x != s.sprites[i].x) angle = -Math.toDegrees(Math.atan((y - s.sprites[i].y) / (x -  s.sprites[i].x)));
            if (x == s.sprites[i].x) angle = 90;

            if (x - s.sprites[i].x > 0) angle += 180;

            angle -= right - 360;
            angle %= 360;

            angle = (int) angle / rayAngle;

            double[] a = new double[4];

            a[1] = 2 + i;
            a[2] = angle;
            a[0] = Math.sqrt((x -  s.sprites[i].x) * (x -  s.sprites[i].x) + (y - s.sprites[i].y) * (y - s.sprites[i].y));


            lengh[numRays + i] = a;
            s.sprites[i].move();

        }

        for (int i = 0; i < numRays - 1; ++i){
            lengh[i][4] = -lengh[i + 1][2] + lengh[i][2];
        }

        ll[0] = sort(lengh);
        ll[1] = show;



        return ll;
    }

    static boolean in(int[][] a, double[] b){
        boolean c = false;


        for (int i = 0; i < mapWidth * mapWidth; ++i){
            if ((int) b[1] == a[i][0] && (int) b[0] == a[i][1]){
                c = true;
                n = a[i][2];
                break;
            }
        }

        return c;
    }

    static double[][] sort(double[][] s){
        for (int i = 0; i < s.length - 1; ++i){
            for (int j = 0; j < s.length - i - 1; ++j){
                if (s[j + 1][0] > s[j][0]){
                    double[] swap = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = swap;
                }
            }
        }

        return s;
    }

}
