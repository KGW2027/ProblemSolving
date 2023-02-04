import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CFR849Div4Virtual1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {

        }
        System.out.print(out);
    }
}

class G1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(tc-- > 0) {
            pq.clear();
            int coins = Integer.parseInt(br.readLine().split(" ")[1]), dist = 1, ans = 0;
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            while(tokenizer.hasMoreElements()) pq.add(Integer.parseInt(tokenizer.nextToken())+(dist++));
            while(!pq.isEmpty() && (coins -= pq.poll()) >= 0) ans++;
            out.append(ans).append('\n');
        }
        System.out.print(out);
    }
}

class F {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        HashMap<Integer, Integer> MEMO = new HashMap<>();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            String[] nq = br.readLine().split(" ");
            int q = Integer.parseInt(nq[1]);
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            while(q-- > 0) {
                String[] cmd = br.readLine().split(" ");
                if(cmd[0].charAt(0) == '1') {
                    int l = Integer.parseInt(cmd[1]) - 1, r = Integer.parseInt(cmd[2]) - 1;
                    while(l <= r) {
                        if(MEMO.containsKey(nums[l])) {
                            nums[l] = MEMO.get(nums[l]);
                        } else {
                            int keyBuf = nums[l];
                            char[] arr = String.valueOf(nums[l]).toCharArray();
                            nums[l] = 0;
                            for (char c : arr) nums[l] += (c - '0');
                            MEMO.put(keyBuf, nums[l]);
                        }
                        l++;
                    }
                } else {
                    out.append(nums[Integer.parseInt(cmd[1]) - 1]).append('\n');
                }
            }
        }
        System.out.print(out);
    }
}

class E {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            br.readLine();
            String[] Nums = br.readLine().split(" ");

            long pos = 0, neg = 0;
            int absMin = Integer.MAX_VALUE, negCount = 0;
            boolean hasZero = false;
            for(String str : Nums) {
                int n = Integer.parseInt(str);
                if(n == 0) hasZero = true;
                else if(n > 0) pos += n;
                else {
                    neg -= n;
                    negCount++;
                }
                absMin = Math.min(absMin, Math.abs(n));
            }

            boolean negAdditive = (!hasZero && negCount % 2 == 1);
            absMin = negAdditive ? absMin : 0;
            neg += absMin * -1;
            pos += neg + absMin * -1;
            out.append(pos).append('\n');
        }
        System.out.print(out);
    }
}

class D {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            br.readLine();
            char[] input = br.readLine().toCharArray();

            boolean[] has = new boolean[26];
            int[] cnt = new int[26];
            int left = 0, right = 0;
            for(char c : input) {
                if(!has[c-'a']) {
                    has[c-'a'] = true;
                    right += 1;
                }
                cnt[c-'a'] += 1;
            }

            int max = 0;
            for(int i = 0 ; i < input.length ; i++) {
                int idx = input[i] - 'a';

                if(has[idx]) {
                    has[idx] = false;
                    left += 1;
                }

                if(--cnt[idx] <= 0) {
                    right -= 1;
                }

                max = Math.max(max, left+right);
            }
            out.append(max).append('\n');
        }
        System.out.print(out);
    }
}

class C {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            br.readLine();
            char[] cmd = br.readLine().toCharArray();
            int gap;
            for(gap = 0 ; gap < Math.ceil(cmd.length / 2.d) ; gap++) {
                if(cmd[gap] == cmd[cmd.length-(1+gap)]) break;
            }
            out.append(cmd.length - (gap * 2)).append('\n');
        }
        System.out.print(out);

    }
}

class B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            br.readLine();
            char[] cmd = br.readLine().toCharArray();
            boolean pass = false;
            int x = 0, y = 0;
            for(char c : cmd) {
               x += ( c == 'L' ) ? -1 : ( c == 'R' ) ? 1 : 0;
               y += ( c == 'U' ) ? 1 : ( c == 'D' ) ? -1 : 0;
               if(x == 1 && y == 1) {
                    pass = true; break;
               }
            }
            out.append(pass ? "YES" : "NO").append('\n');
        }
        System.out.print(out);
    }
}


class A {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] pass = new boolean[26];
        for(char c : "codeforces".toCharArray()) pass[c-'a'] = true;
        int tc = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        while(tc-- > 0) {
            out.append(pass[br.readLine().charAt(0) - 'a'] ? "YES" : "NO").append('\n');
        }
        System.out.print(out);
    }
}
