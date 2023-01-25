public class Prob9095 {
    public static void main(String[] args) throws Exception {
        int[] dp = new int[10];
        dp[0] = 1; dp[1] = 2; dp[2] = 4;
        for(int i = 3 ; i < dp.length ; i++) dp[i] = dp[i-3]+dp[i-2]+dp[i-1];
        int T = read();
        while(T-- > 0) System.out.println(dp[read()-1]);
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}