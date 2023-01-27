public class Prob12852 {
    static int dp[];
    public static void main(String[] args) throws Exception {
        dp = new int[read()+1];
        int[] union = new int[dp.length];
        dp[dp.length-1] = 0;
        for(int x = dp.length-2 ; x > 0 ; x--) {
            int minKey = getMinKey(x);
            dp[x] = dp[minKey]+1;
            union[x] = minKey;
        }

        StringBuilder out = new StringBuilder("1");
        int parent = 1;
        while(union[parent] > 0) out.insert(0, (parent = union[parent]) + " ");

        System.out.printf("%d\n%s", dp[1], out);
    }

    static int getMinKey(int x) {
        int x3 = get(x*3), x2 = get(x*2), x1 = get(x+1);
        int min = Math.min(Math.min(x1, x2), x3);
        if(min == x1) return x+1;
        if(min == x2) return x*2;
        return x*3;
    }

    static int get(int i) {
        return (i >= dp.length) ? 98765432 : dp[i];
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
