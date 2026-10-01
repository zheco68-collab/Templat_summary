import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class 拓扑排序模板_字典序最小 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return (int)sz.nval;
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] rd = new int[-~numCourses];
        int[] res= new int[numCourses];
        int r = 0;

        List[] lists = new List[-~numCourses];
        for(int i=1;i<=numCourses;i=-~i) lists[i] = new ArrayList<Integer>();

        for(int[] t:prerequisites){
            int u = t[0];
            int v = t[1];
            rd[v]=-~rd[v];
            lists[u].add(v);
        }

        PriorityQueue<Integer> qu = new PriorityQueue<>();
        for(int i=1;i<=numCourses;i=-~i) if(rd[i]==0) qu.add(i);

        while(!qu.isEmpty()){
            int u = qu.poll();
            res[r++] = u;
            for(Object v:lists[u]) if(--rd[(Integer)v]==0) qu.add((Integer)v);
        }

        if(r==numCourses) return res;
        return new int[]{-1};
    }

    public static void main(String... args)throws IOException{
        StringBuilder out = new StringBuilder();
        int n = sc(),m = sc();
        int[][] b = new int[m][2];
        for(int i=0;i<m;b[i][1] = sc(),i=-~i) b[i][0] = sc();
        int[] res = findOrder(n,b);
        for(int i=0;i<res.length;i=-~i) out.append(res[i]).append(" ");

        System.out.println(out.substring(0,out.length()-1));
    }
}
