import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prob1939 {

    static class Edge implements Comparable<Edge> {
        int to, weight;
        Edge(int t, int w) {
            this.to = t;
            this.weight = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.weight, weight);
        }
    }

    /**
     *
     * Dijkstra 를 뒤집어서 풀면 되겠다는 생각으로 접근.
     * Dijkstra가 A->B의 최단거리라면, 이 문제는 A->B의 최대무게임.
     *
     * 이를 구현하기 위해서는 일단, Dijkstra의 Dist Array를 이용하는 것이 아니라, Priority Queue에 들어가는 간선에 무게를 직접 입력해줘야함.
     * 즉, (여기까지 오면서 가장 적은 무게의 간선) + (다음 이동할 간선) 의 Min값을 간선에 저장하면서 진행.
     * 이렇게 하면, Priority Queue의 원리에 따라, 현재 이동한 간선들 중 최대 무게를 감당할 수 있는 다리(Bridge)를 Poll하게 된다.
     * 그러므로, 처음 목적지에 도달했을 때의 간선의 무게가 도달할 수 있는 최대 거리가 된다.
     *
     */

    public static void main(String[] args) throws Exception {

        int N = read(), M = read();
        List<Edge>[] map = new List[N+1];
        while(M-- > 0) {
            int A = read(), B = read(), C = read();
            if(map[A] == null) map[A] = new ArrayList<>();
            if(map[B] == null) map[B] = new ArrayList<>();
            map[A].add(new Edge(B, C));
            map[B].add(new Edge(A, C));
        }

        int Start = read(), Destination = read(), max = -1;
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(Start, Integer.MAX_VALUE));
        while(!pq.isEmpty()) {
            Edge poll = pq.poll();

            if(poll.to == Destination){
                max = poll.weight;
                break;
            }

            if(visited[poll.to]) continue;
            visited[poll.to] = true;

            if(map[poll.to] == null) continue;

            for(Edge move : map[poll.to]) {
                move.weight = Math.min(move.weight, poll.weight);
                pq.add(move);
            }
        }

        System.out.print(max);
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
