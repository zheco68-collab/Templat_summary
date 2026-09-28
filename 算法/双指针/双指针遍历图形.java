import java.util.Scanner;

public class 双指针遍历图形{
    public static void main(String[] args) {
        int N,M;
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        int m = (N/2)*2+M;
        int l1 = 0,r1 = l1+M,r2 = m,l2 = r2-M;
        for (int i=0;i<N;i++){
            StringBuilder sb = new StringBuilder(m);
            for (int j=0;j<m;j++){
                if (( j>=l1 && j<r1 )||(j>=l2 && j<r2)) {
                    sb.append('*');
                } else {
                    sb.append('.');
                }
            }
            l1++;r1++;l2--;r2--;
            System.out.println(sb.toString());
        }
    }
}
