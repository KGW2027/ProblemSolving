public class Prob17352 {

    static int[] union;

    public static void main(String[] args) throws Exception {
        int N = read();
        union = new int[N+1];
        while(--N > 1) {
            int p1 = getParent(read()), p2 = getParent(read());
            if(p1 == p2) continue;
            union[p2] = p1;
        }

        int b1 = -1, b2 = -1;
        for(int idx = 1 ; idx < union.length ; idx++) {
            if(union[idx] > 0) continue;
            if(b1 == -1) b1 = idx;
            else {
                b2 = idx;
                break;
            }
        }
        System.out.printf("%d %d", b1, b2);
    }

    static int getParent(int node) {
        return union[node] == 0 ? node : (union[node] = getParent(union[node]));
    }

    static int read() throws Exception {
        int n=0, b;
        while(( b = System.in.read() ) > 32) n=n*10+(b-'0');
        return n;
    }
}
