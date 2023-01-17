import java.util.LinkedList;
import java.util.Queue;

public class Prob4179 {

    static class Task {
        int x, y, moved;
        char type;

        public Task(int x, int y, int m, char t) {
            this.x = x;
            this.y = y;
            this.moved = m;
            this.type = t;
        }
    }

    static final byte[][] DIRECTIONS = new byte[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    public static void main(String[] args) throws Exception {
        int R = read(), C = read(), x = 0, y = 0;
        Task jihoon = null;
        char[][] map = new char[R][C];

        Queue<Task> bfsQ = new LinkedList<>();
        while(y < R) {
            map[y][x] = (char) System.in.read();

            if(map[y][x] == 'J') jihoon = new Task(x, y, 1, 'J');
            else if(map[y][x] == 'F') bfsQ.add(new Task(x, y, 0, 'F'));

            if(++x >= C) {
                x = 0;
                y++;
                System.in.read();
            }
        }
        bfsQ.add(jihoon);

        int moved = -1;
        while(!bfsQ.isEmpty()) {
            Task pos = bfsQ.poll();
            char c = map[pos.y][pos.x];

            if(pos.type == 'J') {
                if(c != 'J') continue;

                if(isExit(map, pos.x, pos.y)){
                    moved = pos.moved;
                    break;
                }
            }

            for(byte[] dir : DIRECTIONS) {
                int mx = pos.x + dir[0], my = pos.y + dir[1];
                if(!inRange(map, mx, my) || map[my][mx] != '.') continue;
                map[my][mx] = pos.type;
                bfsQ.add(new Task(mx, my, pos.moved+1, pos.type));
            }

        }

        System.out.print(moved > 0 ? moved : "IMPOSSIBLE");

    }

    static boolean isExit(char[][] map, int x, int y) {
        return x == 0 || y == 0 || x == map[0].length-1 || y == map.length-1;
    }

    static boolean inRange(char[][] map, int x, int y) {
        return 0 <= x && x < map[0].length && 0 <= y && y < map.length;
    }

    static int read() throws Exception{
        int n=0, b;
        while((b=System.in.read())>32) n= n*10+(b-'0');
        return n;
    }
}
