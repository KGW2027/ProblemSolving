public class Prob12899 {
    static final int MAX = 2_000_000;
    static int NON_LEAP;
    public static void main(String[] args) throws Exception {
        int N = read();
        int[] tree = makeTree();
        StringBuilder builder = new StringBuilder();
        while(N-- > 0) {
            if(read() == 1) addNew(tree, read());
            else builder.append(subtract(tree, read())).append('\n');
        }
        System.out.print(builder);
    }

    static int[] makeTree() {
        NON_LEAP = (1 << (int) Math.ceil(Math.log(MAX) / Math.log(2))) - 1;
        return new int[(NON_LEAP+1) << 1];
    }

    static void addNew(int[] tree, int num) {
        int key = NON_LEAP+num;
        tree[key]++;
        do{
            key /= 2;
            tree[key]++;
        }while(key > 0);
    }

    static int subtract(int[] tree, int num) {
        int find = 1;

        while(find <= NON_LEAP) {
            tree[find]--;

            int left = find * 2, right = left + 1;
            if (tree[left] >= num) {
                find = left;
            } else {
                num -= tree[left];
                find = right;
            }
        }
        tree[find]--;
        return find-NON_LEAP;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b= System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}
