// 给你 n 再给你一个n的k进制数 m  求 K


public class 反求进制{
    /**
     * 根据十进制数 n 和其 k 进制下的表示 mStr，求出进制 k
     * 
     * @param n    十进制数值
     * @param mStr k 进制下的字符串表示
     * @return 对应的进制 k，若不存在则返回 -1
     */
    public static long findBase(long n, String mStr) {
        if (mStr == null || mStr.isEmpty()) {
            return -1;
        }

        // 1. 将字符串解析为各数位的数值数组
        int[] digits = new int[mStr.length()];
        int maxDigit = 0;

        for (int i = 0; i < mStr.length(); i++) {
            char ch = mStr.charAt(i);
            int digit;
            if (ch >= '0' && ch <= '9') {
                digit = ch - '0';
            } else if (ch >= 'A' && ch <= 'Z') {
                digit = ch - 'A' + 10;
            } else if (ch >= 'a' && ch <= 'z') {
                digit = ch - 'a' + 10;
            } else {
                return -1; // 非法字符
            }
            digits[i] = digit;
            if (digit > maxDigit) {
                maxDigit = digit;
            }
        }

        // 2. 确定搜索区间的下界 L
        long L = Math.max(2, maxDigit + 1);

        // 特殊情况：如果 m 只有 1 位数
        if (digits.length == 1) {
            return (digits[0] == n) ? L : -1;
        }

        // 3. 确定搜索区间的上界 R
        long R = Math.max(L, n);

        // 4. 二分查找 k
        while (L <= R) {
            long mid = L + (R - L) / 2;
            long val = evaluate(digits, mid, n);

            if (val == n) {
                return mid;
            } else if (val < n) {
                L = mid + 1; // 进制太小
            } else {
                R = mid - 1; // 进制太大或计算溢出
            }
        }

        return -1; // 无解
    }

    /**
     * 计算在指定 base 进制下，digits 数组代表的十进制数值
     * 如果在计算过程中超过 limit，提前返回以防止 long 溢出
     */
    private static long evaluate(int[] digits, long base, long limit) {
        long val = 0;
        for (int digit : digits) {
            // 防溢出剪枝：如果 val * base 可能溢出或已超过 limit
            if (base != 0 && val > (limit - digit) / base) {
                return limit + 1; // 代表超过了 limit
            }
            val = val * base + digit;
        }
        return val;
    }

    public static void main(String[] args) {
        // 示例 1: n = 19, m = "23" -> 2 * k + 3 = 19 -> k = 8
        long n1 = 19;
        String m1 = "23";
        System.out.println("k = " + findBase(n1, m1)); // 输出: 8

        // 示例 2: n = 100, m = "1100100" -> 二进制
        long n2 = 100;
        String m2 = "1100100";
        System.out.println("k = " + findBase(n2, m2)); // 输出: 2

        // 示例 3: 带字母的情况，n = 254, m = "FE" -> 15 * k + 14 = 254 -> k = 16
        long n3 = 254;
        String m3 = "FE";
        System.out.println("k = " + findBase(n3, m3)); // 输出: 16
    }
}