public class Solve9626 {
    static StringBuilder builder;
    public static void main(String[] args) throws Exception {
        builder = new StringBuilder();
        byte M = readByte(), N = readByte(),
                U = readByte(), L = readByte(), R = readByte(), D = readByte(),
                len = (byte) (L+N+R);
        int line = 0;

        while(U-- > 0) {
            printDeco(len, 0, (line++)%2 == 0);
            builder.append('\n');
        }

        while(M-- > 0) {
            int b;
            printDeco(L, 0, line%2 == 0);
            while((b = System.in.read()) > 32) builder.append((char) b);
            printDeco(len, L+N, (line++)%2 == 0);
            builder.append('\n');
        }

        while(D-- > 0) {
            printDeco(len, 0, (line++)%2 == 0);
            builder.append('\n');
        }

        System.out.print(builder);

    }

    static void printDeco(int len, int start, boolean sharpEven) {
        char oddChar = sharpEven ? '.' : '#', evenChar = sharpEven ? '#' : '.';
        for( ; start < len ; start++) {
            builder.append(start%2 == 0 ? evenChar : oddChar);
        }
    }

    static byte readByte() throws Exception {
        byte n=0, b;
        while((b = (byte) System.in.read()) > 32) n = (byte) (n*10+(b-'0'));
        return n;
    }
}
