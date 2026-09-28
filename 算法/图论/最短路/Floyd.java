import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Floyd {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException {
        sz.nextToken();
        return(int)sz.nval;
    }

    public static int max = 101;
    public static int[][] p = new int[max][max];

    public static void main(String... args)throws IOException{
        int n = sc();
        int m = sc();
        int[] a = new int[m];
        for(int i=0;i<m;i=-~i)a[i] = sc();

        for(int i=1;i<=n;i=-~i) for(int j=1;j<=n;j=-~j) p[i][j] = sc();
        for(int t=1;t<=n;t=-~t) for(int i=1;i<=n;i=-~i) for(int j=1;j<=n;j=-~j) p[i][j] = Math.min(p[i][j],p[i][t]+p[t][j]);

        int ans = 0;
        for(int i=0;i<m-1;i=-~i) ans += p[a[i]][a[i+1]];

        System.out.println(ans);
    }
}
