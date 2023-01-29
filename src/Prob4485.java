import java.util.PriorityQueue;

public class Prob4485 {

    static class Node implements Comparable<Node> {
        int loc, dist;
        Node(int a, int b) {
            loc = a; dist = b;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(dist, o.dist);
        }
    }

    static final byte[][] DIRECTION = new byte[][]{ {1, 0}, {0, 1}, {-1, 0}, {0, -1} };

    public static void main(String[] args) throws Exception {
        int N, prob = 0;
        StringBuilder out = new StringBuilder();

        while((N=read()) > 0) {
            int[] map = new int[N*N];
            int key = 0;
            while(key < map.length){
                map[key++] = System.in.read() - '0';
                System.in.read();
            }
            Node[] nodes = new Node[N*N];

            PriorityQueue<Node> prioQ = new PriorityQueue<>();
            prioQ.add(new Node(0, map[0]));
            while(!prioQ.isEmpty()) {

                Node poll = prioQ.poll();

                if (poll.loc == N * N - 1) break;

                for (byte[] dir : DIRECTION) {

                    int y = (poll.loc/N) + dir[0];
                    int x = (poll.loc%N) + dir[1];
                    if (isOutRange(x, N) || isOutRange(y, N)) continue;

                    int search = y*N+x;
                    if(nodes[search] == null || nodes[search].dist > (poll.dist + map[search])) {
                        nodes[search] = new Node(search, poll.dist + map[search]);
                        prioQ.add(nodes[search]);
                    }
                }
            }

            out.append("Problem ").append(++prob).append(": ").append(nodes[N*N-1].dist).append('\n');
        }
        System.out.print(out);
    }

    static boolean isOutRange(int xy, int N) {
        return xy < 0 || xy >= N;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
