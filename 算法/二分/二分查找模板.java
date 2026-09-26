public class 二分查找模板 {
    public static void main(String... args) throws Exception {
        int[] a = {1,2,3,4,5,6,7,8,9};
        int l = 0,r = a.length-1;
        int target = 5;
        while(l<=r){
            int mid = (r+l)>>1;
            if(a[mid]==target){
                System.out.println(mid);
                return;
            }else if(a[mid]<target) l = -~mid;
            else r = mid-1;
        }
        System.out.println(-1);
    }
}