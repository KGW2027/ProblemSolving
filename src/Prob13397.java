public class Prob13397 {
    static int[] arr;
    public static void main(String[] args) throws Exception {

        // Data Input
        int N = read(), M = read(), l = 0, r = Integer.MIN_VALUE, idx = 0, m;
        arr = new int[N];
        while(idx < N) {
            arr[idx++] = read();
            r = Math.max(arr[idx-1], r);
        }

        // Binary Search
        while(l < r){
            m = (l + r) / 2;
            if(CutTest(m) <= M) r = m;
            else l = m + 1;
        }

        System.out.println(r);
    }

    static int CutTest(int Limit){
        int groups = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if(max - min > Limit) {
                groups++; i--;
                max = Integer.MIN_VALUE;
                min = Integer.MAX_VALUE;
            }
        }

        return groups;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read()) > 32) n=n*10+(b-'0');
        return n;
    }
}
