import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 最小生成树_Kruskal {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static int[] fa;
    public static int cnt;
    public static void init(int n){
        fa = new int[-~n];
        cnt = n;
        for(int i=1;i<=n;i=-~i) fa[i] = i;
    }

    public static int find(int x){
        if(x!=fa[x]) fa[x] = find(fa[x]);
        return fa[x];
    }

    public static void union(int i,int j){
        i = find(i);
        j = find(j);
        if(i==j) return;
        fa[j] = i;
        cnt--;
    }

    public static class E{
        int u,v,p;
        public E(int... i){
            u = i[0];
            v = i[1];
            p = i[2];
        }
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc(),m = sc();
        init(n);

        E[] e = new E[m];
        for(int i=0;i<m;i=-~i) e[i] = new E(sc(),sc(),sc());
        Arrays.sort(e,(o1, o2) -> o1.p-o2.p);
        int sum = 0;
        for(int i=0;i<m;i=-~i){
            if(find(e[i].u)!=find(e[i].v)){
                union(e[i].u,e[i].v);
                sum+=e[i].p;
            }
        }

        if(cnt==1) System.out.println(sum);
        else System.out.println("orz");
    }
}
