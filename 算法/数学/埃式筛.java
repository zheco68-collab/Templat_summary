public class 埃式筛{
    public static int N = 10;
    public static int[] primes = new int[N];
    public static int cnt; // primes[]存储所有素数
    public static Boolean[] st = new Boolean[N]; // st[x]存储x是否被筛掉


    public static void get_primes(int n){
        for (int i=2;i<=n;i=-~i){
            if (st[i]) continue;
            primes[cnt ++ ] = i;
            for (int j = i + i; j <= n; j += i) st[j] = true;
        }
        
    }
}