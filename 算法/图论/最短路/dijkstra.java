import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class dijkstra {

  
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc(),m = sc(),s = sc();
        List<Integer>[] L = new List[n+1];
        int[][] bq = new int[n+1][n+1];
        for(int i=1;i<=n;i=-~i){
            L[i] = new ArrayList<>();
            Arrays.fill(bq[i],Integer.MAX_VALUE);
        }

        while(m-->0){
            int u = sc();
            int v = sc();
            bq[u][v] = sc();
            L[u].add(v);
        }

        bfs(L,s,bq,n);

    }

    public static void bfs(List<Integer>[] L,int s,int[][] bq,int n){
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.add(new int[]{s,0});
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            int u = temp[0];
            int f = temp[1];
            for(int v : L[u]){
                if(bq[s][v]<f+bq[u][v]){
                    bq[s][v] = f+bq[u][v];
                    pq.add(new int[]{v,bq[s][v]});
                }
            }
        }

        for(int i=1;i<=n;i=-~i){
            System.out.println(bq[s][i]);
        }
    }
}