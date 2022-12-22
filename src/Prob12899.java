public class Prob12899 {
    public static void main(String[] args) throws Exception {
        int N = read();
        int[] tree = new int[(1 << 22)+1];
        StringBuilder builder = new StringBuilder();
        while(N-- > 0) {
            if(read() == 1) set(tree, read());
            else builder.append(poll(tree, read())).append('\n');
        }
        System.out.print(builder);
    }

    static void set(int[] tree, int X){
        int key = (1 << 21) + (X - 1);
        while(key > 0) {
            tree[key]++;
            key /= 2;
        }
    }

    static int poll(int[] tree, int X) {
        int node = 1, nonLeap = (1 << 21);
        while(node < nonLeap) {
            tree[node]--;
            int left = node * 2, right = left + 1;
            if(tree[left] >= X) {
                node = left;
            } else {
                node = right;
                X -= tree[left];
            }
        }
        tree[node]--;
        return (node - nonLeap) + 1;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b= System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}
