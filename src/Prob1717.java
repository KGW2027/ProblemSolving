public class Prob1717 {

    static int[] union;
    static boolean[] set;
    public static void main(String[] args) throws Exception {
        StringBuilder out = new StringBuilder();

        int n = read(), m = read();
        union = new int[n+1];
        set = new boolean[n+1];

        while(m-- > 0) {
            int c = read(), a = read(), b = read();
            int ap = getParent(a), bp = getParent(b);

            if(c == 0 && ap != bp){
                set[bp] = true;
                union[bp] = ap;
            }
            else if(c == 1) out.append( (ap == bp) ? "YES" : "NO" ).append('\n');
        }

        System.out.print(out);
    }

    static int getParent(int node) {
        return (!set[node]) ? node : (union[node] = getParent(union[node]));
    }

    static int read() throws Exception {
        int n=0, b;
        while( (b=System.in.read()) > 32) n=n*10 + (b-'0');
        return n;
    }
}
