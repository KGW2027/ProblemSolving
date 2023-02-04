import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//class CFR849Div4G2 {
//
//    static class Point implements Comparable<Point>{
//
//        int x, cost;
//        Point(int x, int c) { this.x = x; cost = c; }
//        @Override
//        public int compareTo(Point o) {
//            return Integer.compare(cost, o.cost);
//        }
//    }
//    public static void main(String[] args) throws Exception {
//        StringBuilder out = new StringBuilder();
//
//        int tc = read();
//        while(tc-- > 0) {
//
//            int n = read(), c = read(), idx = 1;
//            PriorityQueue<Point> points = new PriorityQueue<>();
//            while(n-- > 0) {
//                points.add(new Point(idx, read()));
//            }
//
//            int used = 0, pos = 0;
//            do{
//                Point target = points.poll();
//                c -= target.cost - pos;
//
//                int dist = 1;
//                for(Point p : points) {
//
//                }
//
//            }while(!points.isEmpty() && c > 0);
//        }
//    }
//
//    static int read() throws Exception {
//        int n=0, b;
//        while((b=System.in.read())>32) {
//            n=n*10+(b-'0');
//        }
//        return n;
//    }
//}

class CFR849Div4G1 {
    public static void main(String[] args) throws Exception {
        StringBuilder out = new StringBuilder();

        int tc = read();
        while(tc-- > 0) {

            int n = read(), c = read(), idx = 0;
            PriorityQueue<Integer> costs = new PriorityQueue<>();
            while(n-- > 0) {
                costs.add(read() + (++idx));
            }

            int used = 0;
            while(!costs.isEmpty() && (c -= costs.poll()) >= 0) used++;
            out.append(used).append('\n');
        }

        out.setLength(out.length()-1);
        System.out.print(out);
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) {
            n=n*10+(b-'0');
        }
        return n;
    }
}

class CFR849Div4F {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {

            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]), q = Integer.parseInt(split[1]), idx = 0;
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            while(q-- > 0) {
                int[] cmd = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                if(cmd[0] == 1) {
                    int l = cmd[1]-1, r = cmd[2]-1;
                    while(l <= r) {
                        char[] disasm = String.valueOf(nums[l]).toCharArray();
                        nums[l] = 0;
                        for(char c : disasm) nums[l] += (c-'0');
                        l++;
                    }
                } else {
                    out.append(nums[cmd[1]-1]).append('\n');
                }
            }
        }
        out.setLength(out.length()-1);
        System.out.print(out);
    }
}

class CFR849Div4E {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            br.readLine();
            int idx = 0;
            long sum = 0;
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            while(idx < nums.length) {
                if(idx > 0) {
                    int pos = nums[idx-1] + nums[idx];
                    int neg = (nums[idx-1] * -1) + (nums[idx] * -1);
                    if(neg > pos){
                        sum -= nums[idx-1];
                        nums[idx-1] *= -1;
                        sum += nums[idx-1];

                        nums[idx] *= -1;
                    }
                }
                sum += nums[idx++];
            }
            out.append(sum).append('\n');
        }
        out.setLength(out.length()-1);
        System.out.print(out);
    }
}

class CFR849Div4D {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            br.readLine();
            int idx = 0, right = 0;
            char[] chars = br.readLine().toCharArray();
            short[] count = new short[26];
            boolean[] county = new boolean[26];

            for(char c : chars) {
                chars[idx] = c;
                count[c - 'a']++;
                if(!county[c - 'a']) {
                    county[c-'a'] = true;
                    right++;
                }
                idx++;
            }

            int left = 0;
            int max = 2;
            for(idx = 0 ; idx < chars.length ; idx++) {
                int key = chars[idx]-'a';
                if(county[key]) {
                    left++;
                    county[key] = false;
                }

                if(--count[key] <= 0) right--;
                max = Math.max(left+right, max);
            }

            out.append(max).append('\n');
        }
        out.setLength(out.length()-1);
        System.out.print(out);
    }
}


class CFR849Div4C {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder out = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            br.readLine();

            char[] str = br.readLine().toCharArray();
            int gap;
            for(gap = 0 ; gap < Math.ceil(str.length/2.d) ; gap++) {
                if(str[gap] == str[str.length-(1+gap)]) break;
            }
            out.append((str.length - (gap*2))).append('\n');
        }
        out.setLength(out.length()-1);
        System.out.print(out);
    }
}

class CFR849Div4B {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());
        StringBuilder out = new StringBuilder();
        while(t-- > 0) {

            reader.readLine();
            int x = 0, y = 0;
            boolean passed = false;
            char[] moves = reader.readLine().toCharArray();
            for(char c : moves) {
                x += (c == 'L') ? -1 : (c == 'R') ? 1 : 0;
                y += (c == 'D') ? -1 : (c == 'U') ? 1 : 0;
                if(x == 1 && y == 1) {
                    passed = true;
                    break;
                }
            }
            out.append(passed ? "YES" : "NO").append('\n');
        }
        out.setLength(out.length()-1);
        System.out.print(out);
    }
}



class CFR849Div4A {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        boolean[] checker = new boolean[26];
        for(char c : "codeforces".toCharArray()) checker[c-'a'] = true;

        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            int k = br.readLine().charAt(0) - 'a';
            out.append(checker[k] ? "YES" : "NO").append('\n');
        }
        out.setLength(out.length()-1);
        System.out.print(out);
    }
}