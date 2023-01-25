public class Prob11726 {
    public static void main(String[] args) throws Exception {
        int[] dp = new int[read()];
        dp[0] = 1;
        if(dp.length>1) dp[1] = 2;
        for(int i = 2 ; i < dp.length ; i++) dp[i] = (dp[i-2] + dp[i-1]) % 10_007;
        System.out.print(dp[dp.length-1]);
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
