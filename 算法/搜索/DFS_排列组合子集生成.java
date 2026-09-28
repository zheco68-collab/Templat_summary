import java.util.*;

class DFS_排列组合子集生成 {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    /**
     * 组合/子集型DFS（靠index往后推进防重复）
     */
    void dfsSubset(int[] nums, int idx) {
        ans.add(new ArrayList<>(path));  // 每个节点都是一个子集
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);           // 做选择
            dfsSubset(nums, i + 1);      // 递归
            path.remove(path.size() - 1); // 撤销选择（回溯）
        }
    }

    /**
     * 全排列型DFS（靠vis记录是否使用过）
     */
    void dfsPermute(int[] nums, boolean[] vis) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            path.add(nums[i]);
            dfsPermute(nums, vis);
            path.remove(path.size() - 1);
            vis[i] = false;
        }
    }
}