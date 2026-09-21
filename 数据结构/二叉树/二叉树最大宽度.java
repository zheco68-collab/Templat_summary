import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class 二叉树最大宽度 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            int[] id = new int[3001];
            TreeNode[] te = new TreeNode[3001];
            int l = 0,r = 0;
            if(root==null) return -1;
            int ans = 1;
            te[r] = root;id[r++] = 1;
            while(l<r){
                int size = r-l;
                ans = Math.max(id[r-1]-id[l]+1,ans);

                for(int i=0;i<size;i=-~i){
                    TreeNode e = te[l];
                    int k = id[l++];
                    if(e.left!=null){
                        te[r] = e.left;
                        id[r++] = k*2;
                    }
                    if(e.right!=null){
                        te[r] = e.right;
                        id[r++] = k*2+1;
                    }
                }
            }

            return ans;
        }
    }
}
