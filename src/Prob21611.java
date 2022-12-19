public class Prob21611 {
    static int queueLength;
    public static void main(String[] args) throws Exception {
        byte N = readByte(), M = readByte();
        short[][] marbleMap = new short[N][N];
        int xKey = 0, yKey = 0;
        while(yKey < N) {
            while(xKey < N) {
                marbleMap[yKey][xKey++] = readByte();
            }
            yKey++;
            xKey = 0;
        }

        byte[][] directions = new byte[][]{ {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
        byte[] marbleQueue = mapToQueue(marbleMap);
        long bomb = 0;

        int center = N/2;

        while(M-- > 0) {
            byte[] direction = directions[readByte()-1];
            byte range = readByte();

            for(int dist = 1 ; dist <= range ; dist++) {
                int newX = center + direction[0] * dist, newY = center + direction[1] * dist;
                marbleQueue[marbleMap[newY][newX]] = 0;
            }

            pullQueue(marbleQueue);
            long chain;
            while((chain = bombChain(marbleQueue)) > 0) bomb += chain;
            marbleQueue = splitMarble(marbleQueue);
        }

        System.out.print(bomb);
    }

    static void swap(byte[] queue, int key1, int key2) {
        byte buf = queue[key1];
        queue[key1] = queue[key2];
        queue[key2] = buf;
    }

    static void pullQueue(byte[] queue) {
        for(int i = 0 ; i <= queueLength ; ) {
            if(queue[i] == 0) {
                boolean onlyZero = true;
                for(int j = i+1 ; j <= queueLength ; j++){
                    if(queue[j] > 0) onlyZero = false;
                    swap(queue, j-1, j);
                }
                if(onlyZero) break;
                queueLength--;
            } else i++;
        }
    }

    static void pullQueue(byte[] queue, int start, int gap) {
        for( ; start <= queueLength - gap ; start++) {
            swap(queue, start, start+gap);
        }
        queueLength -= gap;
    }

    static long bombChain(byte[] queue) {
        long score = 0;
        int choice = -1, start = -1;
        for(int i = 0 ; i < queue.length ; i++) {
            if(choice != queue[i]) {
                if((i - start) >= 4) {
                    for(int j = start ; j < i ; j++) queue[j] = 0;
                    score += (long) (i - start) * choice;
                    pullQueue(queue, start, i-start);
                    i = start;
                }
                choice = queue[i];
                start = i;
            }
        }
        return score;
    }

    static byte[] splitMarble(byte[] queue) {
        byte[] newQueue = new byte[queue.length];
        int key = 0;

        int choice = queue[0], start = 0;
        for(int i = 1 ; i <= queueLength+1 && key < queue.length ; i++) {
            if(choice != queue[i]) {
                newQueue[key++] = (byte) (i - start);
                newQueue[key++] = (byte) choice;
                start = i;
                choice = queue[i];
            }
        }
        updateQueueLength(newQueue);

        return newQueue;
    }

    static byte[] mapToQueue(short[][] map) {

        byte[][] directions = new byte[][]{ {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
        byte[] dirKeys = new byte[]{2, 1, 3, 0};
        int len = map.length;

        byte[] marbleQueue = new byte[len*len-1];
        int sx = (len/2), sy = (len/2), dirKey = 0, marbleKey = 0;
        for(int range = 1 ; sx > 0 || sy > 0 ; ) {

            byte[] nowDir = directions[dirKeys[dirKey]];
            for(int push = 0 ; push < range ; push++) {
                sx += nowDir[0];
                sy += nowDir[1];
                marbleQueue[marbleKey++] = (byte) map[sy][sx];
                map[sy][sx] = (short) (marbleKey-1);
                if(sx == 0 && sy == 0) break;
            }

            if(dirKeys[dirKey++] < 2) range++;
            if(dirKey >= dirKeys.length) dirKey = 0;
        }
        updateQueueLength(marbleQueue);

        return marbleQueue;
    }

    static void updateQueueLength(byte[] queue) {
        for(int i = queue.length-1 ; i >= 0 ; i--) {
            if(queue[i] > 0) {
                queueLength = i;
                break;
            }
        }
    }

    static byte readByte() throws Exception {
        byte n=0, b;
        while((b = (byte) System.in.read()) > 32) n = (byte) (n*10 + (b-'0'));
        return n;
    }
}
