import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 区间差分模板 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException {
        sz.nextToken();
        return(int)sz.nval;
    }

    public static void main(String... args)throws IOException{
        int n = sc();
        int[] a = new int[n+1];
        for(int i=1;i<=n;i=-~i) a[i] = a[i-1]+sc();
        int q = sc();
        StringBuilder out = new StringBuilder();
        while(q-->0) out.append(Math.abs(a[sc()-1]-a[sc()])).append("\n");

        System.out.print(out);
    }
}
