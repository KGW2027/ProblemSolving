import java.util.Arrays;

public class Solve9626 {
    public static void main(String[] args) throws Exception {
        byte M = readByte(), N = readByte(),
                U = readByte(), L = readByte(), R = readByte(), D = readByte();
        char[][] texts = new char[U+M+D][L+N+R];
        char odd = '.', even = '#';

        for(int y = 0 ; y < texts.length ; y++) {
            for(int x = 0 ; x < texts[0].length ; x++) {
                if(U <= y && y < U+M && x == L) {
                    byte[] chars = new byte[N+1];
                    System.in.read(chars, 0, N+1);
                    for( ; x < L+N ; x++) texts[y][x] = (char) chars[x-L];
                    x--;
                    continue;
                }
                texts[y][x] = (x%2 == 0) ? even : odd;
            }
            odd = (odd == '.') ? '#' : '.';
            even = (even == '#') ? '.' : '#';
        }

        StringBuilder builder = new StringBuilder();
        for(char[] text : texts) builder.append(text).append('\n');
        System.out.print(builder);
    }

    static byte readByte() throws Exception {
        byte n=0, b;
        while((b = (byte) System.in.read()) > 32) n = (byte) (n*10+(b-'0'));
        return n;
    }
}
