public class Prob1168 {
    static int pivot, K, N;
    static int[] tree;
    static boolean first;
    public static void main(String[] args) throws Exception {
        N = read();
        K = read();

        double exp = Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int[] segTree = new int[(int) Math.pow(2, exp)];
        int leap = (int) Math.pow(2, exp-1);
        for(int i = 0 ; i < N ; i++){
            segTree[leap+i] = 1;
            applyParent(segTree, leap+i, 1);
        }
        tree = segTree;

        StringBuilder builder = new StringBuilder().append('<');
        int tried = 0;
        pivot = tree.length / 2;
        first = true;
        while(tried++ < N) {
            builder.append(next()).append(',').append(' ');
        }
        builder.setCharAt(builder.length()-2, '>');
        System.out.print(builder);
    }

    static void applyParent(int[] tree, int key, int value) {
        int parent = key/2;
        tree[parent] += value;
        if(parent > 1) applyParent(tree, parent, value);
    }

    static int next() {
        int leap = pivot;

        // Check My Order
        int order = 0, node = leap;
        if(!first) {
            while (node > 1) {
                if (node % 2 == 1) order += tree[(node / 2) * 2];
                node /= 2;
            }
        } else {
            first = false;
        }

        // Next
        order += K;
        while(order > tree[1]) order -= tree[1];

        // Find
        node = 1;
        while(node < tree.length / 2){
            int left = node * 2, right = left + 1;
            tree[node]--;
            if(tree[left] >= order) {
                node = left;
            } else {
                node = right;
                order -= tree[left];
            }
        }
        tree[node]--;

        pivot = node;
        return node - ((tree.length / 2) - 1);
    }

    static int find(int parent, int order) {
        while(parent < tree.length / 2) {
            int left = parent*2, right = parent+1;
            if(tree[left] >= order) {
                parent = left;
            } else {
                parent = right;
                order -= tree[left];
            }
        }
        return parent;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b = System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}
