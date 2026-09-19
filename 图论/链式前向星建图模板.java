import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class 链式前向星建图模板 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return (int)sz.nval;
    }

    public static int[] head, to, nxt, w;
    public static int cnt = 0;

    public static void add(int u, int v, int val){
        to[-~cnt] = v;
        w[-~cnt] = val;
        nxt[-~cnt] = head[u];
        head[u] = -~cnt;
        cnt = -~cnt;
    }

    public static void main(String... args)throws IOException{
        StringBuilder out = new StringBuilder();
        int n = sc(), m = sc();
        head = new int[-~n];
        to = new int[-~m];
        nxt = new int[-~m];
        w = new int[-~m];

        for(int i=0;i<m;i=-~i) add(sc(), sc(), sc());
        
        // 取数据：沿着 head[u] 链表向下抓取每条边
        for(int u=1;u<=n;u=-~u){
            out.append(u).append(" -> ");
            for(int e=head[u];e!=0;e=nxt[e]){
                int v = to[e];       // 取出终点
                int weight = w[e];   // 取出权值
                out.append(v).append("(").append(weight).append(") ");
            }
            out.append("\n");
        }
        System.out.print(out);
    }
}