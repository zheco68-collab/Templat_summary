// https://www.luogu.com.cn/problem/P4568

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 分层图 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return(int)sz.nval;
    }

    public static class E{
        int v,w;
        public E(int... i){
            v = i[0];
            w = i[1];
        }
    }

    public static void main(String... args)throws IOException{
        StringBuilder out = new StringBuilder();
        int n = sc(),m = sc(),k = sc(),strat = sc(),end = sc();

        int[][] dist = new int[n][k+1];
        boolean[][] vis = new boolean[n][k+1];
        List<E>[] lists = new List[n];

        for(int i=0;i<n;i=-~i){
            lists[i] = new ArrayList<>();
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }

        for(int i=0;i<m;i=-~i){
            int u = sc(),v = sc(),w = sc();
            lists[u].add(new E(v,w));
            lists[v].add(new E(u,w));
        }

        PriorityQueue<int[]> pr = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
        dist[strat][0] = 0;
        pr.add(new int[]{strat,0,0});

        while(!pr.isEmpty()){
            int cur = pr.peek()[0],c = pr.peek()[1],sum = pr.peek()[2];
            pr.poll();

            if(vis[cur][c]) continue;
            vis[cur][c] = true;

            if(cur==end){
                System.out.println(sum);
                return;
            }

            for(E v:lists[cur]){
                if(c<k&&dist[v.v][c+1]>dist[cur][c]){
                    dist[v.v][c+1] = dist[cur][c];
                    pr.add(new int[]{v.v,c+1,dist[v.v][c+1]});
                }
                if(dist[v.v][c]>dist[cur][c]+v.w){
                    dist[v.v][c] = dist[cur][c]+v.w;
                    pr.add(new int[] {v.v,c,dist[v.v][c]});
                }
            }
        }


    }
}
