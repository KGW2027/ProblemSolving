import java.util.*;

public class Prob2573 {

    static int iceCount;
    static final byte[][] DIRECTIONS = new byte[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static List<int[]> meltTasks;
    public static void main(String[] args) throws Exception {
        byte[][] ices = new byte[read()][read()];
        iceCount = 0;
        int x = 0, y = 0;
        while(y < ices.length) {
            ices[y][x] = (byte) read();
            if(ices[y][x] > 0) iceCount++;

            if(++x >= ices[0].length) {
                x = 0; y++;
            }
        }

        int count = 0;
        meltTasks = new ArrayList<>();
        do{
            if(iceCount == 0) {
                count = 0;
                break;
            }

            int before = iceCount, after = -1;
            for(y = 0 ; y < ices.length ; y++) {
                for(x = 0 ; x < ices[0].length ; x++) {
                    if(ices[y][x] > 0) {
                        meltTasks.clear();
                        after = melt(ices, x, y, new boolean[ices.length][ices[0].length]);

                        for(int[] meltTask : meltTasks) {
                            if((ices[meltTask[1]][meltTask[0]] = (byte) Math.max(0, ices[meltTask[1]][meltTask[0]] - meltTask[2])) <= 0) iceCount--;
                        }
                        break;
                    }
                }
                if(after > -1) break;
            }

            System.out.println();
            for(byte[] b : ices) System.out.println(Arrays.toString(b));

            if(before != after) break;
            count++;
        }while(true);

        System.out.print(count);
    }

    static int melt(byte[][] map, int x, int y, boolean[][] visited) {
        if(visited[y][x]) return 0;
        visited[y][x] = true;

        int melt = 0, count = 1;
        for(byte[] dir : DIRECTIONS) {
            int nx = x + dir[0], ny = y + dir[1];
            if(!inRange(map, x, y)) continue;

            if(map[ny][nx] == 0){
                melt++;
                continue;
            }
            count += melt(map, nx, ny, visited);
        }

        if(melt > 0) meltTasks.add(new int[]{x, y, melt});
        return count;
    }

    static boolean inRange(byte[][] map, int x, int y) {
        return 0 <= x && x < map[0].length && 0 <= y && y < map.length;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
