import java.util.ArrayList;
import java.util.List;

public class Prob2262 {
    public static void main(String[] args) throws Exception {
        List<Short> tournament = new ArrayList<>();
        short n = read();
        short total = 0;
        while(n-- > 0) tournament.add(read());

        int find = tournament.size();
        while(find > 1) {
            for(int key = 0 ; key < find ; key++) {
                if(tournament.get(key) == find) {
                    int left = key > 0 ? tournament.get(key-1) : 0;
                    int right = key < (tournament.size()-1) ? tournament.get(key+1) : 0;
                    total += (find - Math.max(left, right));
                    tournament.remove(key);
                    break;
                }
            }
            find = tournament.size();
        }

        System.out.print(total);
    }

    static short read() throws Exception {
        short n=0, b;
        while((b=(short)System.in.read())>32) n=(short)(n*10+(b-'0'));
        return n;
    }
}
