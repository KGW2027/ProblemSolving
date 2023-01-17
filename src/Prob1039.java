import java.util.ArrayList;
import java.util.List;

public class Prob1039 {
    public static void main(String[] args) throws Exception {
        int N = read(), K = read();

        if(N <= 9 || (N <= 99 && N % 10 == 0)) {
            System.out.print(-1);
            return;
        }

        List<Integer> testcases = new ArrayList<>();
        testcases.add(N);
        while(K-->0) {
            testcases = simulate(testcases);
        }

        int max = -1;
        for(int res : testcases) max = Math.max(res, max);
        System.out.print(max);
    }

    static void swap(char[] arr, int k1, int k2) {
        char buf = arr[k2];
        arr[k2] = arr[k1];
        arr[k1] = buf;
    }

    static List<Integer> simulate(List<Integer> cases) {
        List<Integer> out = new ArrayList<>();
        for(int c : cases) {
            List<Integer> res = simulate(String.valueOf(c).toCharArray());
            for(int resc : res) {
                if(out.contains(resc)) continue;
                out.add(resc);
            }
        }
        return out;
    }

    static List<Integer> simulate(char[] arr) {
        List<Integer> out = new ArrayList<>();
        for(int i = 0 ; i < arr.length ; i++) {
            for(int j = i+1 ; j < arr.length ; j++) {
                if(arr[i] <= arr[j]) {
                    swap(arr, i, j);
                    out.add(print(arr));
                    swap(arr, i, j);
                }
            }
        }

        if(out.size() == 0){
            swap(arr, arr.length-2, arr.length-1);
            out.add(print(arr));
        }
        return out;
    }

    static int print(char[] arr) {
        int n = 0, k = 0;
        while(k < arr.length) n=n*10+(arr[k++]-'0');
        return n;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
