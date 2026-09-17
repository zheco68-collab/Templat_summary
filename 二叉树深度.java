import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 二叉树深度 {
    public static class E{
        int l,r;
        public E(int l,int r){
            this.l = l;
            this.r = r;
        }
    }

    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException {
        sz.nextToken();
        return(int)sz.nval;
    }

    public static E[] e;

    public static void main(String... args)throws IOException{
        int n = sc();
        e = new E[n+1];
        for(int i=1;i<=n;i=-~i)e[i] = new E(sc(),sc());
        System.out.println(dfs(1));
    }

    public static int dfs(int i){
        int r=1,l=1;
        if(e[i].r==0&&e[i].l==0)return 1;
        if(e[i].r!=0) r=dfs(e[i].l)+1;
        if(e[i].l!=0) l=dfs(e[i].r)+1;
        return Math.max(r,l);
    }
}
