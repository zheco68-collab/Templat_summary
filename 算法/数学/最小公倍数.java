public class 最小公倍数{
    public int lcm(int a, int b){
        return Math.abs(a * b) / gcd(a, b);
    }

    public int gcd(int a, int b){
        return b!=0?gcd(b,a%b):a;
    }
}