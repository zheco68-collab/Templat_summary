//求最大公约数

public class 欧几里得算法{
    public int gcd(int a, int b){
        return b!=0?gcd(b,a%b):a;
    }

}