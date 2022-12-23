import java.util.*;

public class Prob11967 {
    static class Room {
        public byte x, y;
        public byte[][] conns;
        public boolean reachable;
        public Room(byte x, byte y) {
            this.x = x; this.y = y;
            reachable = false;
        }
        public void add(byte x, byte y) {
            if(conns == null) conns = new byte[1][];
            else conns = Arrays.copyOf(conns, conns.length+1);
            conns[conns.length-1] = new byte[]{x, y};
        }
    }
    static short count;
    public static void main(String[] args) throws Exception {
        byte N = read();
        short M = readShort();
        Room[][] rooms = new Room[N][N];
        count = 1;

        while(M-- > 0) {
            byte x = read(), y = read(), a = read(), b = read();
            if(rooms[y-1][x-1] == null) rooms[y-1][x-1] = new Room(x, y);
            if(rooms[b-1][a-1] == null) rooms[b-1][a-1] = new Room(a, b);
            rooms[y-1][x-1].add(a, b);
        }

        Queue<Room> queue = new LinkedList<>();
        boolean[][] light = new boolean[N][N];
        queue.add(rooms[0][0]);
        light[0][0] = true;
        rooms[0][0].reachable = true;

        int befCount;
        do{
            befCount = count;
            dfs(rooms, light, new boolean[N][N], 0, 0);
            queue = simulate(queue, light, rooms);
        } while(befCount != count);

        System.out.print(count);

    }

    static Queue<Room> simulate(Queue<Room> queue, boolean[][] light, Room[][] rooms) {
        Queue<Room> failQ = new LinkedList<>();
        while(!queue.isEmpty()) {
            Room room = queue.poll();
            if(room.conns == null)
                continue;

            if(!room.reachable) {
                failQ.add(rooms[room.y-1][room.x-1]);
                continue;
            }

            for(byte[] conn : room.conns) {
                int x = conn[0]-1, y = conn[1]-1;
                if(!light[y][x]) {
                    count++;
                    light[y][x] = true;
                    queue.add(rooms[y][x]);
                }
            }
        }
        return failQ;
    }

    static void dfs(Room[][] rooms, boolean[][] light, boolean[][] visited, int x, int y) {
        if(!isRange(x, y, rooms.length) || visited[y][x]) return;
        visited[y][x] = true;

        if(rooms[y][x] != null)
            rooms[y][x].reachable = true;

        if(light[y][x]) {
            dfs(rooms, light, visited, x, y-1);
            dfs(rooms, light, visited, x, y+1);
            dfs(rooms, light, visited, x-1, y);
            dfs(rooms, light, visited, x+1, y);
        }
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
