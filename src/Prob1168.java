public class Prob1168 {
    public static void main(String[] args) throws Exception {
        int N = read(), K = read(), next = 0;
        int[] tree = init(estimateTreeSize(N), N);

        StringBuilder builder = new StringBuilder().append('<');
        while(N-- > 0) {
            next = next(tree, next, K);
            builder.append(next).append(',').append(' ');
        }
        builder.setCharAt(builder.length()-2, '>');
        System.out.print(builder);
    }

    static int next(int[] tree, int last, int K) {
        int node = 1, nonLeap = tree.length / 2;
        int order = last == 0 ? 0 : getOrder(tree, (last + nonLeap - 1));
        int newK = checkOrderOverflow(tree, order, K);
        while(node < nonLeap) {
            tree[node]--;
            int left = node * 2, right = left + 1;
            if(tree[left] >= newK) {
                node = left;
            } else {
                node = right;
                newK -= tree[left];
            }
        }
        tree[node]--;
        return (node - nonLeap) + 1;
    }

    static int getOrder(int[] tree, int loc) {
        int cnt = 0;
        while(loc > 0) {
            if(loc % 2 == 1) cnt += tree[(loc / 2) * 2];
            loc /= 2;
        }
        return cnt;
    }

    static int estimateTreeSize(int N) {
        return (1 << ((int) Math.ceil(Math.log(N) / Math.log(2)) + 1));
    }

    static int checkOrderOverflow(int[] tree, int order, int K) {
        int find = order+K;
        while(tree[1] < find) find -= tree[1];
        return find;
    }

    static int[] init(int size, int N) {
        int[] tree = new int[size];
        for(int i = 0 ; i < N ; i++) {
            int key = (size / 2) + i;
            while(key > 0) {
                tree[key]++;
                key /= 2;
            }
        }
        return tree;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b = System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}
