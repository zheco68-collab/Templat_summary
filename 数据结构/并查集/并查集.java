import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 并查集 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException {
        sz.nextToken();
        return (int)sz.nval;
    }

    public static int[] fa;
    public static void init(int n){
        fa = new int[-~n];
        for(int i=1;i<=n;i=-~i)fa[i] = i;
    }

    public static int find(int x){
        if(fa[x]==x)return x;
        return fa[x] = find(fa[x]);
    }

    public static void un(int i,int j) {
        j = find(j);
        i = find(i);
        if(i==j)return;
        fa[j] = i;
    }

    public static void main(String... args)throws IOException{
        int n = sc(),m = sc();
        init(n);
        StringBuilder out = new StringBuilder();
        while(m-->0){
            if(sc()==1)un(sc(),sc());
            else out.append(find(sc())==find(sc())?'Y':'N').append("\n");
        }

        System.out.print(out);
    }
}
