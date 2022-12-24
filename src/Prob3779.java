public class Prob3779 {
    public static void main(String[] args) throws Exception {
        int N, tc = 0;
        StringBuilder out = new StringBuilder();
        while((N = read()) > 0) {
            out.append("Test case #").append(++tc).append('\n');
            char[] chars = new char[N];
            int key = 0;
            while(key < N){
                chars[key++] = (char) System.in.read();
            }
            int[] kmp_table = makeTable(chars);

            for(int pos = 1 ; pos < chars.length ; pos++) {
                if(kmp_table[pos] == 0) continue;
                int prefix_length = (pos+1) - kmp_table[pos];
                if(prefix_length > 0 && kmp_table[pos] % prefix_length == 0)
                    out.append(pos+1).append(' ').append((pos/prefix_length)+1).append('\n');
            }
            out.append((char) System.in.read());
        }
        System.out.print(out);
    }

    static int[] makeTable(char[] chars) {
        int index = 0;
        int[] table = new int[chars.length];
        for(int pos = 1 ; pos < chars.length ; pos++) {
            while(index > 0 && chars[index] != chars[pos]){
                index = table[index-1];
            }
            if(chars[index] == chars[pos]){
                table[pos] = ++index;
            }
        }
        return table;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
