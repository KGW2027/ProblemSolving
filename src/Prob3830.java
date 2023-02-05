public class Prob3830 {

    static int[][] parent_cost;

    public static void main(String[] args) throws Exception {
        StringBuilder out = new StringBuilder();

        int N, M;
        while((N = read()) > 0 && (M = read()) > 0) {
            parent_cost = new int[N+1][2];


            while(M-- > 0) {
                int cmd = readCommand(), a = read(), b = read();
                int ap = compress(a), bp = compress(b);
                if(cmd == '!') {
                    int w = read();

                    if(ap == bp) continue; // 무게간 상대성을 깨트리는 링크는 존재하지 않는다. ( 또는 사이클을 이루는 경우 변화 없음 )

                    parent_cost[bp][0] = a;
                    parent_cost[bp][1] = w - parent_cost[b][1];

                } else {
                    out.append( (ap!=bp) ? "UNKNOWN" : (parent_cost[b][1] - parent_cost[a][1])).append('\n');
                }
            }
        }
        System.out.print(out);
    }

    static int compress(int node) {
        if(parent_cost[node][0] > 0) {
            int topParent = compress(parent_cost[node][0]);
            parent_cost[node][1] += parent_cost[parent_cost[node][0]][1];
            parent_cost[node][0] = topParent;
            return topParent;
        }
        return node;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n ;
    }

    static int readCommand() throws Exception {
        int cmd = System.in.read();
        System.in.read();
        return cmd;
    }

}
