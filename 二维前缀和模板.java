public class 二维前缀和模板 {
    int[][] sum;
    public 二维前缀和模板(int[][] matrix) {
        sum = new int[matrix.length+1][matrix[0].length+1];

        for(int i=0;i<matrix.length;i-=-1){
            for(int j=0;j<matrix[0].length;j-=-1) {
                sum[i+1][j+1] = matrix[i][j];
                sum[i+1][j+1] += sum[i][j+1]+sum[i+1][j]-sum[i][j];
            }
        }
    }

    public int sumRegion(int a, int b, int c, int d) {
        c++;
        d++;
        return sum[c][d]-sum[c][b]-sum[a][d]+sum[a][b];
    }
}
