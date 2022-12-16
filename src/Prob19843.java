public class Prob19843 {
    public static void main(String[] args) throws Exception {
        short T = readShort(), N = readShort();
        short[] days = new short[]{298, 302, 288, 305, 289};
        while(N-- > 0) {
            short day1 = readDay(), start = readShort(), day2 = readDay(), end = readShort();

            if(day1 == day2){
                T -= (end-start);
            } else {
                int d1K = -1, d2K = -1;
                for(int key = 0 ; key < 5 && (d1K == -1 || d2K == -1) ; key++) {
                    if(days[key] == day1) d1K = key;
                    if(days[key] == day2) d2K = key;
                }
                T -= (24-start) + end + (((d2K-d1K)-1) * 24);
            }

            if(T < 0) {
                System.out.print(0);
                return;
            }
        }

        System.out.print(T <= 48 ? T : -1);
    }

    static short readDay() throws Exception {
        short day = (short) (System.in.read() + System.in.read() + System.in.read());
        System.in.read();
        return day;
    }

    static short readShort() throws Exception {
        short n=0, b;
        while((b = (short) System.in.read()) > 32) n = (short) (n*10 + (b-'0'));
        return n;
    }
}
