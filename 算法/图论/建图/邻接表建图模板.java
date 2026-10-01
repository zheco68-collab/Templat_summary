import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class 邻接表建图模板 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return (int)sz.nval;
    }

    public static void main(String... args)throws IOException{
        StringBuilder out = new StringBuilder();
        int n = sc(), m = sc();
        List[] g = new List[-~n];
        for(int i=1;i<=n;i=-~i) g[i] = new ArrayList<Integer>();
        for(int i=0;i<m;i=-~i) g[sc()].add(sc());
        
        for(int u=1;u<=n;u=-~u){
            out.append(u).append(" -> ");
            for(Object obj:g[u]){
                int v = (Integer)obj; 
                out.append(v).append(" ");
            }
            out.append("\n");
        }
        System.out.print(out);
    }
}