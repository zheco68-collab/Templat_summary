import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 线段树模板_区间和_范围重置 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException {
        sz.nextToken();
        return (int)sz.nval;
    }

    public static long Lsc()throws IOException {
        sz.nextToken();
        return (long)sz.nval;
    }

    public static int N;
    public static long[] sum,change,a;
    public static boolean[] update;

    public static void init(int n){
        N = n+1;
        a = new long[N];
        sum = new long[N << 2];
        change = new long[N << 2];
        update = new boolean[N << 2];
    }

    public static void up(int i){
        sum[i] = sum[i << 1]+sum[i << 1 | 1];
    }

    public static void lazy(int i,long v,int n){
        sum[i] = v *n;
        change[i] = v;
        update[i] = true;
    }

    public static void down(int i,int ln,int rn){
        if(update[i]){
            lazy(i << 1,change[i],ln);
            lazy(i << 1 | 1,change[i],rn);
            update[i] = false;
            change[i] = 0;
        }
    }

    public static void builder(int l,int r,int i){
        if(l==r) sum[i] = a[l];
        else{
            int mid = (l+r) >> 1;
            builder(l,mid,i << 1);
            builder(mid+1,r,i << 1 | 1);
            up(i);
        }
        change[i] = 0;
        update[i] = false;
    }

    public static long query(int jobl,int jobr,int l,int r,int i){
        if(jobl<=l&&jobr>=r) return sum[i];
        long ans = 0L;
        int mid = (l+r) >> 1;
        down(i,mid-l+1,r-mid);
        if(jobl <= mid) ans+=query(jobl,jobr,l,mid,i << 1);
        if(jobr > mid) ans+=query(jobl,jobr,mid+1,r,i << 1 | 1);
        return ans;
    }

    public static void update(int jobl,int jobr,long jobu,int l,int r,int i){
        if(jobl<=l&&jobr>=r) lazy(i,jobu,r-l+1);
        else{
            int mid = (l+r) >> 1;
            down(i,mid-l+1,r-mid);
            if(jobl <= mid) update(jobl,jobr,jobu,l,mid,i << 1);
            if(jobr > mid) update(jobl,jobr,jobu,mid+1,r,i << 1 | 1);
            up(i);
        }
    }
}
