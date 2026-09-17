import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 线段树模板_区间最大值_区间加减 {
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
    public static long[] a,max,add;
    public static void init(int n){
        N =n+1;
        a = new long[N];
        max = new long[N << 2];
        add = new long[N << 2];
    }

    public static void up(int i){
        max[i] = Math.max(max[i << 1],max[i << 1 | 1]);
    }

    public static void lazy(int i,long v){
        max[i] += v;
        add[i] += v;
    }

    public static void down(int i){
        if(add[i]!=0){
            lazy(i << 1,add[i]);
            lazy(i << 1 | 1,add[i]);
            add[i] = 0;
        }
    }

    public static void builder(int l,int r,int i){
        if(l==r) max[i] = a[l];
        else{
            int mid = (l+r) >> 1;
            builder(l,mid,i << 1);
            builder(mid+1,r,i << 1 | 1);
            up(i);
        }
        add[i] = 0;
    }

    public static long query(int jobl,int jobr,int l,int r,int i){
        if(jobl<=l&&jobr>=r)return max[i];
        long temp = Long.MIN_VALUE;
        int mid = (l+r) >> 1;
        down(i);
        if(jobl<=mid) temp = Math.max(temp,query(jobl,jobr,l,mid,i << 1));
        if(jobr> mid) temp = Math.max(temp,query(jobl,jobr,mid+1,r,i << 1 | 1));
        return temp;
    }

    public static void add(int jobl,int jobr,long jobv,int l,int r,int i){
        if(jobl<=l&&jobr>=r) lazy(i,jobv);
        else{
            int mid = (l+r) >> 1;
            down(i);
            if(jobl<=mid) add(jobl,jobr,jobv,l,mid,i << 1);
            if(jobr> mid) add(jobl,jobr,jobv,mid+1,r,i << 1 | 1);
            up(i);
        }
    }

}
