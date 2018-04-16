public class Main {

    public static void main(String[] args) {
        Graph g=new Graph(5);
        g.getCityNeighbours(0);
        System.out.println(g.getPossibleRoute(0));
        for(int i = 1; i < 5; i++) {
            System.out.println(g.shortestPath(0, i));
        }
        for(int i = 0; i < 5; i++) {
            g.getCityNeighbours(i);
            System.out.println(g.getPossibleRoute(i));
        }
        g.wglab();

    }
}

