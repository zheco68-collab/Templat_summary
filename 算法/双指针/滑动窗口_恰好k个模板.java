class 滑动窗口_恰好k个模板 {
    // 求出恰好k个奇数的子数组个数
    int solve(int[] nums, int k) {
        return calc(nums, k) - calc(nums, k - 1);
    }

    // 最多k个奇数的子数组个数
    int calc(int[] nums, int k) {
        int ans = 0;
        int cnt = 0;
        
        for (int i = 0, j = 0; i < nums.length; i++) {
            cnt += (nums[i] & 1);
            
            while (cnt > k) {
                cnt -= (nums[j] & 1);
                j++;
            }
            ans += i - j + 1;
        }
        return ans;
    }
}