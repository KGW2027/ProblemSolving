public class Prob20040 {

    static int[] union;
    static boolean[] set;

    public static void main(String[] args) throws Exception {
        int n = read(), m = read(), turn = 0;
        union = new int[n];
        set = new boolean[n];

        while(turn++ < m) {
            int i1p = getParent(read()), i2p = getParent(read());
            if(i1p == i2p) break;
            set[i2p] = true;
            union[i2p] = i1p;
        }

        System.out.print(turn == m+1 ? 0 : turn);
    }

    static int getParent(int node) {
        return (!set[node]) ? node : (union[node] = getParent(union[node]));
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
