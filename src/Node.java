public class Node {
    int x;
    int y;
    int cost;
    Node previous;

    Node(int x, int y, int cost, Node previous){
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.previous = previous;
    }

    Node(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

}
