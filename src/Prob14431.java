import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prob14431 {

    static class Edge implements Comparable<Edge> {

        int to, weight;
        Edge(int t, int w) { to = t; weight = w; }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    static class PrimeTester {
        boolean[] tester;
        PrimeTester(int max) {
            tester = new boolean[max];
            tester[0] = true;
            tester[1] = true;
            for(int i = 2 ; i * i <= tester.length ; i++) {
                if(!tester[i]) {
                    for(int j = i*i ; j < tester.length ; j += i) tester[j] = true;
                }
            }
        }

        boolean isPrime(int n) {
            return !tester[n];
        }
    }


    static List<int[]> pointList;
    static List<Edge>[] edgeList;
    static PrimeTester primeTester;
    public static void main(String[] args) throws Exception {

        primeTester = new PrimeTester(8501);

        pointList = new ArrayList<>();

        pointList.add(new int[]{read(), read()});
        pointList.add(new int[]{read(), read()});
        int N = read();
        while(N-->0) pointList.add(new int[]{read(), read()});

        edgeList = new List[pointList.size()];
        makeEdge();

        int dist = getDist();
        System.out.print(dist == 0 ? -1 : dist);
    }

    static int getDist() {
        int[] distMap = new int[pointList.size()];
        boolean[] visited = new boolean[pointList.size()];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        while(!pq.isEmpty()) {
            Edge poll = pq.poll();
            if(poll.to == 1) break;

            if(visited[poll.to]) continue;
            visited[poll.to] = true;

            if(edgeList[poll.to] == null) continue;
            for(Edge e : edgeList[poll.to]) {
                e.weight = poll.weight + ( distMap[e.to] == 0 ? e.weight : Math.min(distMap[e.to], e.weight) );
                if(distMap[e.to] == 0 || distMap[e.to] > e.weight) {
                    distMap[e.to] = e.weight;
                    pq.add(e);
                }
            }
        }

        return distMap[1];
    }

    static void makeEdge() {
        for(int key = 0 ; key < pointList.size() ; key++) {
            for(int key2 = key+1 ; key2 < pointList.size() ; key2++) {
                int dist = getDist(pointList.get(key), pointList.get(key2));
                if(!primeTester.isPrime(dist)) continue;

                addEdge(key, key2, dist);
                addEdge(key2, key, dist);
            }
        }
    }

    static void addEdge(int key, int t, int d) {
        if(edgeList[key] == null) edgeList[key] = new ArrayList<>();
        edgeList[key].add(new Edge(t, d));
    }

    static int getDist(int[] p1, int[] p2) {
        return (int) Math.floor(Math.sqrt(
                Math.pow(Math.abs(p1[0] - p2[0]), 2)
              + Math.pow(Math.abs(p1[1] - p2[1]), 2)
        ));
    }

    static int read() throws Exception {
        int n = 0, b, m = 1;
        while ((b = System.in.read()) > 32) {
            if(b == '-') m *= -1;
            else n = n * 10 + (b - '0');
        }
        return n * m;
    }
}
