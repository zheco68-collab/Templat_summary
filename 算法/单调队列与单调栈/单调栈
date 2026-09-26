import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 单调栈 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc(),r = 0;
        int[] a = new int[n+1],stack = new int[n],ans = new int[n+1];

        for(int i=1;i<=n;i=-~i){
            a[i] = sc();
            while(r>0&&a[stack[r-1]]<=a[i]){
              int cur = stack[--r];
              ans[cur] = i;
            }
            stack[r++] = i;
        }

        while(r>0){
            int cur = stack[--r];
            ans[cur] = 0 ;
        }

        for(int i=1;i<=n;i=-~i) out.append(ans[i]).append(" ");
        System.out.println(out);
    }

}
