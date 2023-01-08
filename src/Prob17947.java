public class Prob17947 {
    final static String PLUS = "[+]";
    final static String MULTIPLY = "[*]";
    final static String DIVIDE = "[/]";

    static StringBuilder res;
    static int count;
    public static void main(String[] args) throws Exception {
        long N = read();
        res = new StringBuilder();
        for(long test = 1L << 45; test > 1 ; test >>= 1) {
            if(test > N) continue;
            if((N & test) == test) append(PLUS);
            if(test > 2) append(MULTIPLY);
        }

        if((N & 1) == 1) {
            if(count > 0) append(MULTIPLY);
            append(PLUS);
            append(DIVIDE);
        }
        System.out.print(count + "\n" + res);
    }

    static void append(String op) {
        res.append(op).append(' ');
        if(++count > 99) {
            System.out.print("-1");
            System.exit(0);
        }
    }

    static long read() throws Exception {
        long n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
