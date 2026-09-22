import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class 最小生成树_Prim {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc(),m = sc();

        List<int[]>[] lists = new List[n+1];
        for(int i=1;i<=n;i=-~i) lists[i] = new ArrayList<>();

        for(int i=0;i<m;i=-~i){
            int u = sc(),v = sc(),p = sc();
            lists[u].add(new int[]{v,p});
            lists[v].add(new int[]{u,p});
        }

        Set<Integer> set = new HashSet<>();
        PriorityQueue<int[]> pr = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        for(int[] t:lists[1]) pr.add(t);
        set.add(1);
        int ans = 0;
        while(!pr.isEmpty()){
            int[] t = pr.poll();
            int next = t[0];
            int w = t[1];
            if(!set.contains(next)){
                set.add(next);
                ans+=w;
                for(int[] k: lists[next]) pr.add(k);
            }
        }

        if(set.size()==n) System.out.println(ans);
        else System.out.println("orz");


    }
}
