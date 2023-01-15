import java.util.ArrayList;
import java.util.List;

public class Prob16946 {
    static int[] areaFields;
    static byte[][] DIRECTIONS = new byte[][]{ {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    public static void main(String[] args) throws Exception {
        int[][] map = new int[readShort()][readShort()];
        short x=0, y=0;
        while(y < map.length) {

            map[y][x] = (System.in.read() == '1' ? -2 : -1);
            if(++x >= map[0].length) {
                x = 0;
                y++;
                System.in.read();
            }
        }

        int areaCounts = 0;
        areaFields = new int[0];

        for(int sy = 0 ; sy < map.length ; sy++) {
            for(int sx = 0 ; sx < map[0].length ; sx++) {
                if (map[sy][sx] != -1) continue;
                areaFields = java.util.Arrays.copyOf(areaFields, areaFields.length + 1);
                dfs(map, sx, sy, areaCounts++);
            }
        }

        StringBuilder out = new StringBuilder();
        List<Integer> overlapChecks = new ArrayList<>();
        for(int sy = 0 ; sy < map.length ; sy++) {
            for(int sx = 0 ; sx < map[0].length ; sx++) {
                if(map[sy][sx] >= 0) {
                    out.append('0');
                    continue;
                }

                int fields = 1;
                overlapChecks.clear();
                for(byte[] dir : DIRECTIONS) {
                    int newX = sx + dir[0], newY = sy + dir[1];
                    if(inRange(map, newX, newY) && map[newY][newX] >= 0 && !overlapChecks.contains(map[newY][newX])){
                        fields += areaFields[map[newY][newX]];
                        overlapChecks.add(map[newY][newX]);
                    }
                }
                out.append(fields % 10);
            }
            out.append('\n');
        }
        System.out.print(out);
    }

    static void dfs(int[][] map, int x, int y, int fill) {
        map[y][x] = fill;
        areaFields[fill]++;
        for(byte[] dir : DIRECTIONS) {
            int newX = x + dir[0], newY = y + dir[1];
            if(inRange(map, newX, newY) && map[newY][newX] == -1) {
                dfs(map, newX, newY, fill);
            }
        }
    }

    static boolean inRange(int[][] map, int x, int y) {
        return 0 <= x && x < map[0].length && 0 <= y && y < map.length;
    }

    static short readShort() throws Exception {
        short n=0, b;
        while((b=(short)System.in.read())>32) n=(short)(n*10+(b-'0'));
        return n;
    }
}