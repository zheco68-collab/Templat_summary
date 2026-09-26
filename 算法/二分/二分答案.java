import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 二分答案 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int n = sc(),k = sc();
        a = new int[n];
        for(int i=0;i<n;i=-~i) a[i] = sc();
        Arrays.sort(a);

        int l = 1,r = Integer.MAX_VALUE>>1;
        int ans = 0;
        while(l<=r){
            int mid = (r+l)>>1;
            if(check(mid,n,k)){
                r = mid-1;
                ans = mid;
            }else l = -~mid;
        }
        System.out.println(ans);
    }

    public static int[] a;
    public static boolean check(int l,int n,int k){
       for(int i=n-1;i>=0;i--){
           if(a[i]>l)k-=(a[i]-1)/l;
           if(k<0)return false;
       }

       return true;
    }
}
