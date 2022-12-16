public class Solve5107 {
    public static void main(String[] args) throws Exception {
        java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
        StringBuilder answer = new StringBuilder();
        int caseNum = 1;
        while(true) {
            byte N = readByte();
            if (N == 0) break;

            map.clear();

            int people = 0, loops = 0;
            int[] relations = new int[N];
            boolean[] loopCheck = new boolean[N];

            while(N-- > 0) {
                String p1 = readPerson(), p2 = readPerson();
                if(!map.containsKey(p1)) map.put(p1, people++);
                if(!map.containsKey(p2)) map.put(p2, people++);
                relations[map.get(p2)] = map.get(p1);
            }

            for(int i = 0 ; i < loopCheck.length ; i++) {
                if(loopCheck[i]) continue;
                loops++;
                int search = i;
                do {
                    loopCheck[search] = true;
                    search = relations[search];
                } while(search != i);
            }

            answer.append(caseNum++).append(' ').append(loops).append(' ');
        }
        System.out.print(answer);
    }

    static byte readByte() throws Exception {
        byte n=0, b;
        while((b = (byte) System.in.read()) > 32) n = (byte) (n*10 + (b-'0'));
        return n;
    }

    static String readPerson() throws Exception {
        StringBuilder str = new StringBuilder();
        int b;
        while((b = System.in.read()) > 32) str.append((char) b);
        return str.toString();
    }
}
