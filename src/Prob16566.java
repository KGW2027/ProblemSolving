public class Prob16566 {

    /**
     *
     * 카드의 숫자는 중복되지 않는다.
     * => M개의 카드들을 정렬한 후, 기본적인 이분 탐색으로 철수가 낼 카드보다 큰 수를 쉽게 찾아낼 수 있다.
     *
     * 낸 카드를 중복해서 내면 안된다.
     * => 카드 목록은 이미 오름차순으로 정렬된 상태이고, 찾아낸 카드는 낼 수 있는 카드 중 가장 작은 수이므로, 오른쪽으로 1칸 이동해서 제출하면 된다.
     *    이 때, 오른쪽에 있는 카드도 이미 제출한 적이 있는 카드일 수 있으므로, 제출할 때마다 해당 카드를 오른쪽 카드의 자식으로 설정하는 트리를 구성한다.
     *    그냥 +1로 부모가 설정되어있으면 결국 반복문을 통해 찾는 것과 다름이 없으므로, 경로 압축을 시켜 부모를 찾는 시간을 최대한 압축한다.
     *
     */

    static int[] cards;
    static int[] next;

    public static void main(String[] args) throws Exception {
        StringBuilder out = new StringBuilder();

        // 입력받고 정렬
        int N = read(), M = read(), K = read(), idx = 0;
        cards = new int[M];
        next = new int[M];
        while(idx < M) cards[idx++] = read();
        java.util.Arrays.sort(cards);

        // 철수의 입력에 대해 이분탐색+중복제거 후 얻어낸 카드의 key값 : c
        // cards[c]를 StringBuilder에 넣는다.
        while(K-- > 0) {
            int c = binary_search(read());
            out.append(cards[c]).append('\n');
        }

        // 출력
        System.out.print(out);
    }

    static int binary_search(int num) {
        int l = 0, r = cards.length - 1, m;
        while(l <= r) {
            m = (l+r) / 2;
            if(cards[m] <= num) l = m+1;
            else r = m-1;
        }
        l = getParent(l);
        next[l] = l+1;
        return l;
    }

    static int getParent(int num) {
        return next[num] == 0 ? num : (next[num] = getParent(next[num]));
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
