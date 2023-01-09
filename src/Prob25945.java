import java.util.Arrays;

public class Prob25945 {
    public static void main(String[] args) throws Exception {
        int[] init = new int[read()];
        int key = 0, sum = 0;
        while(key < init.length){
            init[key] = read();
            sum += init[key++];
        }

        int avg = (sum/init.length), count = 0, over = 0;
        for(int i = 0 ; i < init.length ; i++) {
            if(init[i] > (avg+1)) {
                over += (init[i] - (avg+1));
                init[i] = (avg+1);
            }

            if(init[i] < avg) {
                count += (avg - init[i]);
                over -= (avg - init[i]);

                init[i] = avg;
            }
        }

        int res = over > 0
                ? over + count
                : count;
        System.out.print(res);
    }

    static int read() throws Exception {
        int n = 0 , b;
        while((b=System.in.read())>32)n=n*10+(b-'0');
        return n;
    }
}
