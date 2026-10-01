public class 快速幂{

    public int qmi(int m, int k, int p){
        int res = 1 % p, t = m;
        while (k>0){
            if((k&1)!=0) res = res * t % p;
            t = t * t % p;
            k >>= 1;
        }
    return res;
    }
    
}