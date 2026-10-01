public class 线性筛{
    public int N = 1000;
    public int[] primes = new  int[N];
    public int cnt; // primes[]存储所有素数
    public boolean[] st = new boolean[N]; // st[x]存储x是否被筛掉
    public void get_primes(int n){
        for (int i = 2; i <= n; i ++ ){
            if (!st[i]) primes[cnt ++ ] = i;
            for (int j = 0; primes[j] <= n / i; j ++ ){
                st[primes[j] * i] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }
}