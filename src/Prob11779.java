public class Prob11779 {
    static class Path implements Comparable<Path>{
        @Override
        public int compareTo(Path o) {
            return Integer.compare(Range, o.Range);
        }
        public int To, Range;
        Path(int a, int b) {
            To = a;
            Range = b;
        }
    }

    public static void main(String[] args) throws Exception {
        int N = read(), M = read();

        // 간선 그래프 맵 입력
        Path[][] paths = new Path[N][0];
        while(M-- > 0){
            int key = read()-1, to = read()-1, range = read(), dup = findDup(paths[key], to);
            if(dup == -1) {
                paths[key] = java.util.Arrays.copyOf(paths[key], paths[key].length+1);
                paths[key][paths[key].length-1] = new Path(to, range);
            } else {
                paths[key][dup].Range = Math.min(paths[key][dup].Range, range);
            }
        }
        int A = read()-1, B = read()-1;
        int[] distMap = new int[N], parentMap = new int[N];

        java.util.PriorityQueue<Path> searchHeap = new java.util.PriorityQueue<>();
        searchHeap.add(new Path(A, 0));
        // 중복 방문 차단
        boolean[] visit = new boolean[N];

        while(!searchHeap.isEmpty()){
            Path search = searchHeap.poll();
            if(visit[search.To]) continue;
            visit[search.To] = true;

            // 탐색 노드가 도착지점일시 중단
            if(search.To == B) break;

            // 탐색 노드의 모든 간선을 dist와 비교후 적용. 거리가 줄어들 시 BFS에 추가
            for (Path edge : paths[search.To]) {
                edge.Range = search.Range
                        + ( (distMap[edge.To] == 0) ? edge.Range : Math.min(edge.Range, distMap[edge.To]) );
                if(distMap[edge.To] == 0 || distMap[edge.To] > edge.Range) {
                    distMap[edge.To] = edge.Range;
                    parentMap[edge.To] = search.To;
                    searchHeap.add(edge);
                }
            }
        }

        StringBuilder out = new StringBuilder(distMap[B] + "\n");
        int pathCnt = 1, parent = B, insert = out.length();
        while(parent != A) {
            pathCnt++;
            out.insert(insert, ((parent = parentMap[parent])+1) + " ");
        }
        out.insert(insert, pathCnt + "\n");
        out.append(B+1);
        System.out.print(out);
    }

    static int findDup(Path[] paths, int to) {
        for(int i = 0 ; i < paths.length ; i++)
            if(paths[i].To == to) return i;
        return -1;
    }

    static int read() throws Exception {
        int n=0, b;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}
