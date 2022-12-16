public class Prob26145 {
    public static void main(String[] args) throws Exception {
        int N = readInt(), M = readInt(), nKey = 0;
        int[] cost = new int[N+M];
        while(nKey < N) cost[nKey++] = readInt();
        nKey = 0;
        while(nKey < N) {
            int cKey = 0;
            while(cKey < N+M) {
                int pay = readInt();
                cost[nKey] -= pay;
                cost[cKey] += pay;
                cKey++;
            }
            nKey++;
        }

        StringBuilder builder = new StringBuilder();
        for(int c : cost) builder.append(c).append(' ');
        System.out.print(builder);
    }

    static int readInt() throws Exception {
        int n=0, b;
        while((b = System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}
