class 位运算黄金技巧 {
    // 1. 获取整数n的二进制第k位（k从0开始）
    int getBit(int n, int k) {
        return (n >> k) & 1;
    }

    // 2. lowbit：返回n的二进制中最低位的1及其后面的0构成的数值
    // 例如：lowbit(6) = lowbit(110) = 10 = 2
    int lowbit(int n) {
        return n & -n;
    }

    // 3. 统计n的二进制中1的个数（汉明重量）
    int countOnes(int n) {
        int cnt = 0;
        while (n > 0) {
            n -= lowbit(n);  // 每次消去最低位的1
            cnt++;
        }
        return cnt;
    }
    
    // 4. 判断n是否是2的幂
    boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}