public class Prob2513 {
    static int maxBus, schoolPos, maxPos = 0;
    public static void main(String[] args) throws Exception {
        short[] students = new short[100_001];
        int N = read();
        maxBus = read();
        schoolPos = read();
        while(N-- > 0){
            int pos = read();
            students[pos] = readShort();
            if(pos > maxPos) maxPos = pos;
        }

        int total = distance(students, true) + distance(students,false);
        System.out.print(total);
    }

    static int distance(short[] arr, boolean isLeft) {
        int dist = 0, buffer = 0;
        int start = isLeft
                ? 0
                : maxPos;
        int end = schoolPos;

        while(isLeft ? start < end : end < start) {
            if(arr[start] == 0){
                start += isLeft ? 1 : -1;
                continue;
            }

            if(buffer == 0) {
                buffer = maxBus;
                dist += ( isLeft ? end-start : start - schoolPos  ) * 2;
            }

            int ride = Math.min(buffer, arr[start]);
            buffer -= ride; arr[start] -= ride;
        }
        return dist;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32)n=n*10+(b-'0');
        return n;
    }

    static short readShort() throws Exception {
        short n=0, b;
        while((b=(short)System.in.read())>32)n= (short) (n*10+b-'0');
        return n;
    }
}
