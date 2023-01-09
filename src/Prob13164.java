public class Prob13164 {
    public static void main(String[] args) throws Exception {
        int N = read(), K = read(), key = 0;
        int[] heightGaps = new int[N - 1];
        int left = read();
        while (key < heightGaps.length) {
            int now = read();
            heightGaps[key++] = now - left;
            left = now;
        }

        java.util.Arrays.sort(heightGaps);

        int sum = 0;
        for (int i = 0; i < N - K; i++) sum += heightGaps[i];
        System.out.print(sum);
    }

    static int read() throws Exception {
        int n = 0, b;
        while ((b = System.in.read()) > 32) n = n * 10 + (b - '0');
        return n;
    }
}
