class 滑动窗口_元素翻转_替换模板 {
    int solve(String s, int k) {
        char[] ss = s.toCharArray();
        int[] map = new int[26];
        int maxCnt = 0;
        int ans = 0;
        
        for (int i = 0, j = 0; i < ss.length; i++) {
            map[ss[i] - 'a']++;
            maxCnt = Math.max(maxCnt, map[ss[i] - 'a']);
            
            // 窗口内需要替换的字符数 > k，收缩左边界
            if (i - j + 1 > maxCnt + k) {
                map[ss[j] - 'a']--;
                j++;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}