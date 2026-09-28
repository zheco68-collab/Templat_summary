class 一维差分 {
    // 给区间 [l, r] 中每个数加上 c（0-based）
    void solve(int[] arr, int[][] ops, int c) {
        int n = arr.length;
        int[] diff = new int[n + 1];
        
        for (int[] o : ops) {
            int l = o[0], r = o[1];
            diff[l] += c;
            diff[r + 1] -= c;
        }
        
        // 前缀和还原
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }
    }
}