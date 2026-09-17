import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 线段树区间合并模板 {
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static int N;
    public static int[] a,sum,change,max1,max0,pre1,pre0,suf1,suf0;
    public static boolean[] update,reverse;

    public static void init(int n){
        N = -~n;
        a = new int[N];
        sum = new int[N << 2];
        change = new int[N << 2];
        max1 = new int[N << 2];
        max0 = new int[N << 2];
        pre1 = new int[N << 2];
        pre0 = new int[N << 2];
        suf1 = new int[N << 2];
        suf0 = new int[N << 2];
        update = new boolean[N << 2];
        reverse = new boolean[N << 2];
    }

    public static void up(int i,int ln,int rn){
        int ls = i<<1,rs = i<<1|1;
        sum[i] = sum[ls]+sum[rs];

        if(pre1[ls]==ln)pre1[i] = ln + pre1[rs];
        else pre1[i] = pre1[ls];
        if(pre0[ls]==ln)pre0[i] = ln + pre0[rs];
        else pre0[i] = pre0[ls];

        if(suf1[rs]==rn)suf1[i] = rn + suf1[ls];
        else suf1[i] = suf1[rs];
        if(suf0[rs]==rn)suf0[i] = rn + suf0[ls];
        else suf0[i] = suf0[rs];

        max1[i] = Math.max(Math.max(max1[ls],max1[rs]),pre1[rs]+suf1[ls]);
        max0[i] = Math.max(Math.max(max0[ls],max0[rs]),pre0[rs]+suf0[ls]);
    }

    public static void builder(int l,int r,int i){
        if(l==r){
            sum[i] = a[l];
            if(a[l]==1){
                max1[i] = pre1[i] = suf1[i] = 1;
                max0[i] = pre0[i] = suf0[i] = 0;
            }else{
                max1[i] = pre1[i] = suf1[i] = 0;
                max0[i] = pre0[i] = suf0[i] = 1;
            }
        }else{
            int mid = (r+l)>>1;
            builder(l,mid,i<<1);
            builder(mid+1,r,i<<1|1);
            up(i,mid-l+1,r-mid);
        }
        change[i] = 0;
        update[i] = false;
        reverse[i] = false;
    }

    public static void lazy_u(int i,int v,int n){
        sum[i] = v*n;
        change[i] = v;
        update[i] = true;
        reverse[i] = false;

        if(v==1){
            max1[i] = pre1[i] = suf1[i] = n;
            max0[i] = pre0[i] = suf0[i] = 0;
        }else{
            max1[i] = pre1[i] = suf1[i] = 0;
            max0[i] = pre0[i] = suf0[i] = n;
        }
    }

    public static void lazy_r(int i,int n){
        sum[i] = n-sum[i];
        if (update[i]) {
            change[i] ^= 1;
        } else {
            reverse[i] = !reverse[i];
        }

        int temp = max0[i];
        max0[i] = max1[i]; max1[i] = temp;
        temp = pre0[i];
        pre0[i] = pre1[i]; pre1[i] = temp;
        temp = suf0[i];
        suf0[i] = suf1[i]; suf1[i] = temp;
    }

    public static void down(int i,int ln,int rn){
        if(update[i]){
            lazy_u(i<<1,change[i],ln);
            lazy_u(i<<1|1,change[i],rn);
            update[i] = false;
        }

        if(reverse[i]){
            lazy_r(i<<1,ln);
            lazy_r(i<<1|1,rn);
            reverse[i] = false;
        }
    }

    public static void update(int jobl,int jobr,int v,int l,int r,int i){
        if(jobl<=l&&jobr>=r)lazy_u(i,v,r-l+1);
        else{
            int mid = (r+l)>>1;
            down(i,mid-l+1,r-mid);
            if(jobl<=mid) update(jobl,jobr,v,l,mid,i<<1);
            if(jobr>mid) update(jobl,jobr,v,mid+1,r,i<<1|1);
            up(i,mid-l+1,r-mid);
        }
    }

    public static void reverse(int jobl,int jobr,int l,int r,int i){
        if(jobl<=l&&jobr>=r)lazy_r(i,r-l+1);
        else{
            int mid = (r+l)>>1;
            down(i,mid-l+1,r-mid);
            if(jobl<=mid) reverse(jobl,jobr,l,mid,i<<1);
            if(jobr>mid) reverse(jobl,jobr,mid+1,r,i<<1|1);
            up(i,mid-l+1,r-mid);
        }
    }

    public static int query_s(int jobl,int jobr,int l,int r,int i){
        if(jobl<=l&&jobr>=r)return sum[i];
        int mid = (r+l)>>1;
        down(i,mid-l+1,r-mid);
        int ans = 0;
        if(jobl<=mid) ans+=query_s(jobl,jobr,l,mid,i<<1);
        if(jobr>mid) ans+=query_s(jobl,jobr,mid+1,r,i<<1|1);
        return ans;
    }

    public static int[] query_m(int jobl,int jobr,int l,int r,int i){
        if(jobl<=l&&jobr>=r)return new int[]{max1[i],pre1[i],suf1[i]};
        int mid = (r+l)>>1;
        down(i,mid-l+1,r-mid);

        if(jobr<=mid) return query_m(jobl,jobr,l,mid,i<<1);
        if(jobl>mid) return query_m(jobl,jobr,mid+1,r,i<<1|1);

        int[] left = query_m(jobl,jobr,l,mid,i<<1);
        int[] right = query_m(jobl,jobr,mid+1,r,i<<1|1);

        int max = Math.max(Math.max(left[0],right[0]),left[2]+right[1]);

        int leftLen = mid - Math.max(l, jobl) + 1;
        int rightLen = Math.min(r, jobr) - mid;

        int pre = (left[1] == leftLen) ? left[1] + right[1] : left[1];
        int suf = (right[2] == rightLen) ? right[2] + left[2] : right[2];

        return new int[]{max,pre,suf};
    }


    public static void main(String... args)throws IOException{
        int n = sc(),T = sc();
        init(n);
        for(int i = 1;i <= n;i=-~i)a[i] = sc();
        builder(1,n,1);
        StringBuilder out = new StringBuilder();

        while(T-->0){
            int temp = sc();
            if(temp==0) update(sc()+1,sc()+1,0,1,n,1);
            if(temp==1) update(sc()+1,sc()+1,1,1,n,1);
            if(temp==2) reverse(sc()+1,sc()+1,1,n,1);
            if(temp==3) out.append(query_s(sc()+1,sc()+1,1,n,1)).append("\n");
            if(temp==4) out.append(query_m(sc()+1,sc()+1,1,n,1)[0]).append("\n");
        }

        System.out.print(out);
    }
}
