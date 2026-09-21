import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 线段树模板_区间和_区间加减 {

    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException{
        sz.nextToken();
        return (int) sz.nval;
    }

    public static long Lsc()throws IOException{
        sz.nextToken();
        return (long)sz.nval;
    }

    public static int N;
    public static long[] sum,arr,add;
    public static void init(int n){
        N = n+1;
        sum = new long[N << 2];
        add = new long[N << 2];
        arr = new long[N];
    }

    public static void up(int i){
        sum[i] = sum[i << 1 | 1]+sum[i << 1];
    }

    public static void lazy(int i,long v,int n){
        sum[i] += v*n;
        add[i] += v;
    }

    public static void down(int i,int ln,int rn){
        if(add[i]!=0){
            lazy(i << 1,add[i],ln);
            lazy(i << 1 | 1,add[i],rn);
            add[i] = 0;
        }
    }

    public static void builder(int l,int r,int i){
        if(r==l) sum[i] = arr[l];
        else{
            int mid = (l+r) >> 1;
            builder(l,mid,i << 1);
            builder(mid+1,r,i << 1 | 1);
            up(i);
        }
        add[i] = 0;
    }

    public static long query(int jobl,int jobr,int l,int r,int i){
        if(jobl<=l&&jobr>=r) return sum[i];
        long ans = 0L;
        int mid = (l+r) >> 1;
        down(i,mid-l+1,r-mid);
        if(jobl <= mid) ans += query(jobl,jobr,l,mid,i << 1);
        if(jobr > mid) ans += query(jobl,jobr,mid+1,r,i << 1 | 1);
        return ans;
    }

    public static void add(int jobl,int jobr,long jobv,int l,int r,int i){
        if(jobl <= l && jobr >=r) lazy(i,jobv,r-l+1);
        else{
            int mid = (l+r) >> 1;
            down(i,mid-l+1,r-mid);
            if(jobl <= mid) add(jobl,jobr,jobv,l,mid,i << 1);
            if(jobr > mid) add(jobl,jobr,jobv,mid+1,r,i << 1 | 1);
            up(i);
        }
    }

    public static void main(String... args)throws IOException{
        int n = sc();
        int T = sc();
        StringBuilder out = new StringBuilder();
        init(n);
        for(int i=1;i<=n;i=-~i) arr[i] = sc();
        builder(1,n,1);

        while(T-->0){
            if(sc()==1) add(sc(),sc(),Lsc(),1,n,1);
            else out.append(query(sc(),sc(),1,n,1)).append("\n");
        }

        System.out.print(out);
    }
}
