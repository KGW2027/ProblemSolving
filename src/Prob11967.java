import java.util.*;

public class Prob11967 {
    static short count;
    public static void main(String[] args) throws Exception {
        byte N = read();
        short M = readShort();
        byte[][] matches = new byte[N*N][0];
        count = 1;

        while(M-- > 0) {
            byte x = read(), y = read(), a = read(), b = read();
            int key = (y-1)*N+(x-1);
            byte[] arr = matches[key];
            matches[key] = Arrays.copyOf(arr, arr.length+2);
            matches[key][arr.length] = (byte) (a-1);
            matches[key][arr.length+1] = (byte) (b-1);
        }

        Queue<byte[]> queue = new LinkedList<>();
        boolean[][] light = new boolean[N][N];
        boolean[][] visited = new boolean[N][N];
        boolean[][] reserved = new boolean[N][N];
        queue.add(new byte[]{0, 0});
        light[0][0] = true;
        int count = 1;

        byte[][] directions = new byte[][]{ {0, -1}, {0, 1}, {1, 0}, {-1, 0} };

        while(!queue.isEmpty()) {
            byte[] pos = queue.poll();
            byte x = pos[0], y = pos[1];
            if(visited[y][x]) continue;
            visited[y][x] = true;

            int matchKey = y*N+x;
            for(int key = 0 ; key < matches[matchKey].length ; ) {
                byte tx = matches[matchKey][key++], ty = matches[matchKey][key++];
                if(!light[ty][tx]) {
                    light[ty][tx] = true;
                    count++;
                    if(reserved[ty][tx]) queue.add(new byte[]{tx, ty});
                }
            }

            for(byte[] dir : directions) {
                int dx = x + dir[0], dy = y + dir[1];
                if(isRange(dx, dy, N)) {
                    if(light[dy][dx]) queue.add(new byte[]{(byte) dx, (byte) dy});
                    else reserved[dy][dx] = true;
                }
            }
        }

        System.out.print(count);

    }

    static boolean isRange(int x, int y, int len) {
        return x >= 0 && y >= 0 && x < len && y < len;
    }

    static short readShort() throws Exception {
        short n=0, b;
        while((b = (short) System.in.read()) > 32) n = (short) (n*10 + (b-'0'));
        return n;
    }

    static byte read() throws Exception {
        byte n=0, b;
        while((b = (byte) System.in.read()) > 32) n = (byte) (n*10 + (b-'0'));
        return n;
    }
}
