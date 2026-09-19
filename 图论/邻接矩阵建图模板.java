import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class 邻接矩阵建图模板 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return (int)sz.nval;
    }

    public static void main(String... args)throws IOException{
        StringBuilder out = new StringBuilder();
        int n = sc(), m = sc();

        int[][] g = new int[-~n][-~n];
        for(int i=0;i<m;i=-~i) g[sc()][sc()] = 1; 
      
        for(int u=1;u<=n;u=-~u){
            out.append(u).append(" -> ");
            for(int v=1;v<=n;v=-~v){
                if(g[u][v]!=0){
                    int weight = g[u][v]; 
                    out.append(v).append("(").append(weight).append(") ");
                }
            }
            out.append("\n");
        }
        System.out.print(out);
    }
}