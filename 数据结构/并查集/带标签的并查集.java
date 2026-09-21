import java.util.Arrays;
//题目链接：https://leetcode.cn/problems/minimize-malware-spread-ii/description/?__cf_chl_tk=71oqfX.degcwuLoHkp3p433HhdZyu4k6Pgp2I3qJfdQ-1789699972-1.0.1.1-SYNwh7nJL.DvkLwZ8Ky2BNKb5mJsQGmlF1saTlHeXQg
public class 带标签的并查集 {

    public int[] fa,cnt,yt;
    public boolean[] vis;

    public void init(int n,int... r){
        fa = new int[n];
        cnt = new int[n];
        yt = new int[n];
        vis = new boolean[n];

        for(int i=0;i<n;i=-~i){
            fa[i] = i;
            cnt[i] = 1;
            yt[i] = -1;
            vis[i] = false;
        }

        for(int t:r) vis[t] = true;
    }

    public int find(int x){
        if(fa[x]!=x) fa[x] = find(x);
        return fa[x];
    }

    public void union(int a,int b){
        a = find(a);
        b = find(b);
        if(a!=b){
            fa[a] = b;
            cnt[b]+=cnt[a];
        }
    }

    public int minMalwareSpread(int[][] graph, int[] initial) {
        init(graph.length,initial);

        for(int i=0;i<graph.length;i=-~i)
            for(int j=0;j<graph.length;j=-~j)
                if(graph[i][j]==1&&!vis[i]&&!vis[j])
                    union(i,j);

        for(int i:initial) for(int j=0;j<graph.length;j=-~j) if(i==j&&graph[i][j]==1&&!vis[j]){
            int fn = find(j);
            if(yt[fn]==-1) yt[fn] = i;
            else if(yt[fn]!=-2&&yt[fn]!=i) yt[fn] = -2;
        }


        int[] sum = new int[graph.length];
        for(int i=0;i<graph.length;i=-~i) if(i==find(i)&&yt[i]>=0) sum[yt[i]] += cnt[i];


        Arrays.sort(initial);
        int ans = initial[0];
        int max = sum[ans];
        for(int i:initial){
            if(sum[i]>max){
                ans = i;
                max = sum[i];
            }
        }
        return ans;
    }
}
