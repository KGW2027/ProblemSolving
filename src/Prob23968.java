public class Prob23968 {
    public static void main(String[] args) throws Exception {
        int N = readInt(), K = readInt(), nKey = 0;
        int[] arr = new int[N];
        while(nKey < N) arr[nKey++] = readInt();

        for(int end = N ; end > 0 ; end--) {
            for(int cur = 0 ; cur < end-1 ; cur++) {
                if(arr[cur] > arr[cur+1]) {
                    int buf = arr[cur+1];
                    arr[cur+1] = arr[cur];
                    arr[cur] = buf;
                    if(--K == 0) {
                        System.out.print(arr[cur] + " " + arr[cur+1]);
                        return;
                    }
                }
            }
        }
        System.out.print("-1");

    }

    static int readInt() throws Exception {
        int n=0, b;
        while((b = System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}
