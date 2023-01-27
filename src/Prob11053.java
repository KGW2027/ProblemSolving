public class Prob11053 {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[read()];
        int[] cache = new int[arr.length+1];
        int key = 0, out = 0, cacheKey = 0;
        while(key < arr.length){
            arr[key] = read();
            int max = 0;
            for(int i = cacheKey; i >= 0; i--) {
                if(cache[i] >= arr[key]) continue;
                max = i; break;
            }
            out = Math.max(max+1, out);
            cache[max+1] = cache[max+1] == 0 ? arr[key++] : Math.min(cache[max+1], arr[key++]);
            cacheKey = Math.max(cacheKey, max+1);
        }
        System.out.print(out);

    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
