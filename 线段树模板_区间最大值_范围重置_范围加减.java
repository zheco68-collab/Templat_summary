import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 线段树模板_区间最大值_范围重置_范围加减 {
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
    public static long[] max,add,a,change;
    public static boolean[] update;

    public static void init(int n){
        N = n+1;
        a = new long[N];
        max = new long[N << 2];
        add = new long[N << 2];
        change = new long[N << 2];
        update = new boolean[N << 2];
    }

    public static void up(int i){
        max[i] = Math.max(max[i << 1],max[i << 1 | 1]);
    }

    public static void addLazy(int i, long v, int n) {
        max[i] += v;
        add[i] += v;
    }

    public static void updateLazy(int i,long v,int n){
        max[i] = v;
        add[i] = 0;
        change[i] = v;
        update[i] = true;
    }

    public static void down(int i,int ln,int rn){
        if(update[i]){
            updateLazy(i << 1,change[i],ln);
            updateLazy(i << 1 | 1,change[i],rn);
            update[i] = false;
            change[i] = 0;
        }

        if(add[i]!=0){
            addLazy(i << 1,add[i],ln);
            addLazy( i << 1 | 1,add[i],rn);
            add[i] = 0;
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
        add[i] = 0;
        change[i] = 0;
        update[i] = false;
    }

    public static long query(int jobl,int jobr,int l,int r,int i){
        if(jobl <= l && jobr >= r) return max[i];
        long ans = Long.MIN_VALUE;
        int mid = (r+l) >> 1;
        down(i,mid-l+1,r - mid);
        if(jobl <= mid) ans = Math.max(ans,query(jobl,jobr,l,mid,i << 1));
        if(jobr > mid) ans = Math.max(ans,query(jobl,jobr,mid+1,r,i << 1 | 1));
        return ans;
    }

    public static void add(int jobl,int jobr,long jobv,int l,int r,int i){
        if(jobl <= l && jobr >= r)addLazy(i,jobv,r-l+1);
        else{
            int mid = (r+l) >> 1;
            down(i,mid-l+1,r-mid);
            if(jobl <= mid) add(jobl,jobr,jobv,l,mid,i << 1);
            if(jobr > mid) add(jobl,jobr,jobv,mid+1,r,i << 1 | 1);
            up(i);
        }
    }

    public static void update(int jobl,int jobr,long jobv,int l,int r,int i){
        if(jobl <= l && jobr >= r)updateLazy(i,jobv,r-l+1);
        else{
            int mid = (r+l) >> 1;
            down(i,mid-l+1,r-mid);
            if(jobl <= mid) update(jobl,jobr,jobv,l,mid,i << 1);
            if(jobr > mid) update(jobl,jobr,jobv,mid+1,r,i << 1 | 1);
            up(i);
        }
    }

    public static void main(String... args)throws IOException{
       int n = sc();
       int T = sc();
       init(n);
       for(int i=1;i<=n;i-=-1) a[i] = sc();
       builder(1,n,1);
       StringBuilder out = new StringBuilder();

       while(T-->0){
           int op = sc();
           if(op==1) update(sc(),sc(),Lsc(),1,n,1);
           if(op==2) add(sc(),sc(),Lsc(),1,n,1);
           if(op==3) out.append(query(sc(),sc(),1,n,1)).append("\n");
       }

        System.out.print(out);
    }
}
