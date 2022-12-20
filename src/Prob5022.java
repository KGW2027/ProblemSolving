import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Prob5022 {
    static class PathThrough {
        public byte[] pos;
        public int dist;
        public PathThrough before;

        public PathThrough(byte x, byte y) {
            pos = new byte[]{x, y};
            dist = 0;
        }

        public PathThrough clone(byte newX, byte newY) {
            PathThrough clone = new PathThrough((byte) (pos[0]+newX), (byte) (pos[1]+newY));
            clone.dist = dist+1;
            clone.before = this;
            return clone;
        }
    }

    static final String IMPOSSIBLE = "IMPOSSIBLE";
    public static void main(String[] args) throws Exception {
        byte N = readByte(), M = readByte();
        boolean[][] map = new boolean[M+1][N+1];
        byte[] A1 = { readByte(), readByte() };
        byte[] A2 = { readByte(), readByte() };
        byte[] B1 = { readByte(), readByte() };
        byte[] B2 = { readByte(), readByte() };

        int minDist = Math.min(getTotalWires(map, A1, A2, B1, B2), getTotalWires(map, B1, B2, A1, A2));
        System.out.print(minDist == Integer.MAX_VALUE ? IMPOSSIBLE : minDist);

    }

    static int getTotalWires(boolean[][] mapOriginal, byte[] first1, byte[] first2, byte[] second1, byte[] second2) {
        boolean[][] mapFirst = deepClone(mapOriginal);
        mark(mapFirst, second1, second2);
        PathThrough path = bfs(mapFirst, first1, first2);
        int minDist = Integer.MAX_VALUE;

        boolean[][] mapSecond = deepClone(mapOriginal);
        PathThrough before = path;
        do{
            mark(mapSecond, before.pos);
        }while((before = before.before) != null);
        PathThrough secPath = bfs(mapSecond, second1, second2);

        return secPath == null ? minDist : path.dist + secPath.dist;
    }

    static PathThrough bfs(boolean[][] map, byte[] start, byte[] end) {
        Queue<PathThrough> bfsQ = new LinkedList<>();
        bfsQ.add(new PathThrough(start[0], start[1]));

        byte[][] directions = new byte[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        while(!bfsQ.isEmpty()) {
            PathThrough path = bfsQ.poll();
            if(map[path.pos[1]][path.pos[0]]) continue;
            if(path.pos[0] == end[0] && path.pos[1] == end[1]) return path;

            map[path.pos[1]][path.pos[0]] = true;
            for(byte[] dir : directions) {
                PathThrough newPath = path.clone(dir[0], dir[1]);
                if(isRange(map, newPath.pos[0], newPath.pos[1])) {
                    bfsQ.add(newPath);
                }
            }
        }
        return null;
    }

    static void mark(boolean[][] map, byte[]... poses) {
        for(byte[] pos : poses) map[pos[1]][pos[0]] = true;
    }

    static boolean isRange(boolean[][] map, int x, int y){
        return 0 <= x && x < map[0].length && 0 <= y && y < map.length;
    }

    static boolean[][] deepClone(boolean[][] map) {
        boolean[][] newArray = new boolean[map.length][];
        for(int key = 0 ; key < map.length ; key++) newArray[key] = map[key].clone();
        return newArray;
    }

    static byte readByte() throws Exception {
        byte n=0, b;
        while((b = (byte) System.in.read()) > 32) n =(byte) (n*10 + (b-'0'));
        return n;
    }
}
