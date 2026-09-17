import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 线段树模板_区间最大值_范围重置 {
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
    public static long[] a,change,max;
    public static boolean[] update;

    public static void init(int n){
        N = n+1;
        a = new long[N];
        change = new long[N << 2];
        max = new long[N << 2];
        update = new boolean[N << 2];
    }

    public static void up(int i){
        max[i] = Math.max(max[i << 1],max[i << 1 | 1]);
    }

    public static void lazy(int i,long v){
        max[i] = v;
        change[i] = v;
        update[i] = true;
    }

    public static void down(int i){
        if(update[i]){
            lazy(i << 1,change[i]);
            lazy(i << 1 | 1,change[i]);
            update[i] = false;
        }
    }

    public static void builder(int l,int r,int i){
        if(l==r) max[i] = a[l];
        else{
            int mid = (r+l) >> 1;
            builder(l,mid,i << 1);
            builder(mid+1,r,i << 1 | 1);
            up(i);
        }
        change[i] = 0;
        update[i] = false;
    }

    public static long query(int jobl,int jobr,int l,int r,int i){
        if(jobl<=l&&jobr>=r) return max[i];
        long temp = Long.MIN_VALUE;
        int mid = (l+r) >> 1;
        down(i);
        if(jobl <= mid) temp = Math.max(query(jobl,jobr,l,mid,i << 1),temp);
        if(jobr > mid) temp = Math.max(query(jobl,jobr,mid+1,r,i << 1 | 1),temp);
        return temp;
    }

    public static void update(int jobl,int jobr,long jobv,int l,int r,int i){
        if(jobl<=l&&jobr>=r) lazy(i,jobv);
        else{
            int mid = (r+l) >> 1;
            down(i);
            if(jobl <= mid) update(jobl,jobr,jobv,l,mid,i << 1);
            if(jobr > mid) update(jobl,jobr,jobv,mid+1,r,i << 1 | 1);
            up(i);
        }
    }
}
