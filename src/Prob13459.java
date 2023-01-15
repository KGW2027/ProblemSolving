public class Prob13459 {
    static final byte[][][] DIRECTIONS = new byte[][][]{ { {0, 1}, {0, -1} }, { {1, 0}, {-1, 0} }};
    static boolean success = false;
    public static void main(String[] args) throws Exception {
        byte N = readByte(), M = readByte(), x = 0, y = 0;
        char[][] mapOriginal = new char[N][M];
        while(y < N) {

            mapOriginal[y][x] = (char) System.in.read();

            if(++x >= M) {
                x = 0;
                y++;
                System.in.read();
            }
        }

        for(byte dirset = 0 ; dirset <= 1 ; dirset++) {
            for(byte[] dir : DIRECTIONS[dirset]) {
                simulate(mapOriginal, dir, 1, dirset);
            }
        }

        System.out.println(success ? 1 : 0);
    }

    static void simulate(char[][] bef, byte[] dir, int movedLength, byte dirFlag) {
        if(success || movedLength > 10) return;
        char[][] map = cloneMap(bef);

        boolean succeedA = moveR(map, dir);
        boolean succeedB = moveB(map, dir);
        if(!succeedA && !succeedB) return;
        if(find(map, 'B') == null) {
            if(success) success = false;
            return;
        }

        for(byte[] nextDir : DIRECTIONS[(movedLength + dirFlag) & 1]) {
            simulate(map, nextDir, movedLength+1, dirFlag);
        }
    }

    static boolean moveR(char[][] map, byte[] dir) {
        byte[] pos = find(map, 'R');
        if(pos == null || map[pos[1]+dir[1]][pos[0]+dir[0]] == '#') return false;

        char nextChar;
        do{
            pos[0] += dir[0];
            pos[1] += dir[1];

            nextChar = map[pos[1]][pos[0]];

            if(nextChar == 'O') {
                map[pos[1]-dir[1]][pos[0]-dir[0]] = '.';
                success = true;
                break;
            }

            if(nextChar == '#') break;
            if(nextChar == 'B') moveB(map, dir);
            if(map[pos[1]][pos[0]] == 'B') break;

            map[pos[1]][pos[0]] = 'R';
            map[pos[1]-dir[1]][pos[0]-dir[0]] = '.';
        }while(true);

        return true;
    }

    static boolean moveB(char[][] map, byte[] dir) {
        byte[] pos = find(map, 'B');
        if(pos == null || map[pos[1]+dir[1]][pos[0]+dir[0]] == '#') return false;

        char nextChar;
        do{
            pos[0] += dir[0];
            pos[1] += dir[1];

            nextChar = map[pos[1]][pos[0]];
            if(nextChar == '#' || nextChar == 'R') break;
            if(nextChar == 'O') {
                map[pos[1]-dir[1]][pos[0]-dir[0]] = '.';
                break;
            }
            map[pos[1]][pos[0]] = 'B';
            map[pos[1]-dir[1]][pos[0]-dir[0]] = '.';
        }while(true);

        return true;
    }

    static byte[] find(char[][] map, char target) {
        for(byte x = 0 ; x < map[0].length ; x++) {
            for(byte y = 0 ; y < map.length ; y++) {
                if(map[y][x] == target) return new byte[]{x, y};
            }
        }
        return null;
    }

    static char[][] cloneMap(char[][] map) {
        char[][] clone = map.clone();
        for(int i = 0 ; i < map.length ; i++)
            clone[i] = map[i].clone();
        return clone;
    }


    static byte readByte() throws Exception {
        byte n=0, b;
        while((b=(byte)System.in.read())>32) n= (byte) (n*10+(b-'0'));
        return n;
    }
}
