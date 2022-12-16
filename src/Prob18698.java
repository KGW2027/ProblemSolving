public class Prob18698 {
    public static void main(String[] args) throws Exception {
        byte T = readByte();
        StringBuilder builder = new StringBuilder();
        int cnt = 0;
        while(T-- > 0) {
            int b;
            while((b = System.in.read()) == 'U') cnt++;
            builder.append(cnt).append('\n');
            cnt = 0;
            if(b != '\n') skipLine();
        }
        System.out.print(builder);
    }

    static void skipLine() throws Exception {
        while(System.in.read() > 32);
    }

    static byte readByte() throws Exception {
        byte n=0, b;
        while((b = (byte) System.in.read()) > 32) n = (byte)(n * 10 + (b-'0'));
        return n;
    }
}
