import java.io.*;
import java.util.*;
public class 并查集互斥 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return (int)sz.nval;
    }

    public static int[] fa;
    public static int cnt;
    public static void csh(int n){
        for(int i=1;i<=2*n;i-=-1)fa[i] = i;
        cnt = n;
    }

    public static int find(int x){
        if(x==fa[x])return x;
        return fa[x] = find(fa[x]);
    }

    public static void un(int a,int b){
        a = find(a);
        b = find(b);
        if(a==b)return;
        fa[b] = a;
        cnt--;
    }

    public static class eg{
        int u,v,w;
        public eg(int u,int v,int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    
    public static void main(String[] args)throws IOException{
        int n = sc();
        fa = new int[2*n+1];
        csh(n);
        int k = sc();
        eg[] eg = new eg[k];
        for(int i=0;i<k;i-=-1)eg[i] = new eg(sc(),sc(),sc());

        Arrays.sort(eg,(eg a,eg b)->{
            return Integer.compare(b.w, a.w);
        });

        int ans = 0;
        for(int i=0;i<k;i-=-1){
            int u = eg[i].u;
            int v = eg[i].v;

            if(find(u)==find(v)){
                ans = eg[i].w;
                break;
            }

            un(v,n+u);
            un(u,n+v);
        }

        System.out.println(ans);
    }
}
