import java.util.*;

public class Prob6087 {

    static class Path {
        int x, y, edges;
        String pathLog;

        Path(int x, int y, String log) {
            this.x = x; this.y = y; this.pathLog = log; this.edges = 0;
        }

        Path next(byte[] pos, int dir) {
            Path next = new Path(x+pos[0], y+pos[1], pathLog + (char)(dir+'0'));
            next.edges = edges;
            if(pathLog.length() > 0 && pathLog.charAt(pathLog.length()-1) != next.pathLog.charAt(next.pathLog.length()-1)) next.edges++;
            return next;
        }
    }

    static final byte[][] DIRECTIONS = new byte[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public static void main(String[] args) throws Exception {
        int W = read(), H = read(), x = 0, y = 0;
        char[][] map = new char[H][W];
        int[][] cPoses = new int[0][0];
        while(y < map.length) {
            map[y][x] = (char) System.in.read();
            if(map[y][x] == 'C') {
                if(cPoses.length == 0) map[y][x] = '.';
                cPoses = java.util.Arrays.copyOf(cPoses, cPoses.length+1);
                cPoses[cPoses.length-1] = new int[]{x, y};
            }

            if(++x >= map[0].length) {
                x=0;
                y++;
                System.in.read();
            }
        }

        System.out.print(bfs(map, cPoses[0], cPoses[1]));
    }

    static int bfs(char[][] arr, int[] start, int[] end) {

        int[][] minEdgeMap = new int[arr.length][arr[0].length];
        for(int[] memArr : minEdgeMap) Arrays.fill(memArr, Integer.MAX_VALUE);

        Queue<Path> bfsQueue = new LinkedList<>();
        bfsQueue.add(new Path(start[0], start[1], ""));

        while(!bfsQueue.isEmpty()) {
            Path now = bfsQueue.poll();

            if(now.edges > minEdgeMap[now.y][now.x]) continue;
            minEdgeMap[now.y][now.x] = now.edges;

            for(int dirKey = 0 ; dirKey < DIRECTIONS.length ; dirKey++) {
                int nextX = now.x + DIRECTIONS[dirKey][0], nextY = now.y + DIRECTIONS[dirKey][1];
                if(!inRange(arr, nextX, nextY) || arr[nextY][nextX] == '*' || Math.min(minEdgeMap[end[1]][end[0]], minEdgeMap[nextY][nextX]) <= now.edges) continue;

                bfsQueue.add(now.next(DIRECTIONS[dirKey], dirKey));
            }

        }

        return minEdgeMap[end[1]][end[0]];
    }

    static boolean inRange(char[][] map, int x, int y) {
        return     0 <= x && x < map[0].length
                && 0 <= y && y < map.length;
    }


    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32)n=n*10+b-'0';
        return n;
    }
}
