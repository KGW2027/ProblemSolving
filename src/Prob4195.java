import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Prob4195 {

    static StringBuilder reader = new StringBuilder();
    static int PeopleCount;
    static HashMap<String, Integer> NameMap;
    static List<Integer> ParentMap;
    static List<Integer> NetworkPeopleCount;

    public static void main(String[] args) throws Exception {
        StringBuilder out = new StringBuilder();

        int tc = read();

        NameMap = new HashMap<>();
        ParentMap = new ArrayList<>();
        NetworkPeopleCount = new ArrayList<>();
        while(tc-- > 0) {

            NameMap.clear();
            ParentMap.clear();
            NetworkPeopleCount.clear();

            ParentMap.add(0);
            NetworkPeopleCount.add(0);
            PeopleCount = 0;

            int F = read();
            while(F-- > 0) {
                int Person1 = GetID(readStr()), Person2 = GetID(readStr());
                int Parent1 = GetParent(Person1), Parent2 = GetParent(Person2);
                if(Parent1 != Parent2) {
                    ParentMap.set(Parent2, Parent1);
                    NetworkPeopleCount.set(Parent1, NetworkPeopleCount.get(Parent1) + NetworkPeopleCount.get(Parent2));
                }
                out.append(NetworkPeopleCount.get(Parent1)).append('\n');
            }
        }
        System.out.print(out);
    }

    static int GetID(String Name) {
        if(NameMap.containsKey(Name)) return NameMap.get(Name);
        NameMap.put(Name, ++PeopleCount);
        ParentMap.add(PeopleCount);
        NetworkPeopleCount.add(1);
        return PeopleCount;
    }

    static int GetParent(int id) {
        if(ParentMap.get(id) == id) return id;
        int TopParent = GetParent(ParentMap.get(id));
        ParentMap.set(id, TopParent);
        return TopParent;
    }

    static int read() throws Exception {
        int n = 0, b;
        while( (b=System.in.read()) > 32) n=n*10+(b-'0');
        return n;
    }

    static String readStr() throws Exception {
        reader.setLength(0);
        int b;
        while( (b=System.in.read()) > 32) reader.append((char) b);
        return reader.toString();
    }
}
