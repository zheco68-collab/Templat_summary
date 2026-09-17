public class 二叉树的完全性检验 {
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
        public boolean isCompleteTree(TreeNode root) {
            TreeNode[] qu = new TreeNode[105];
            int l = 0,r = 0;
            if(root==null)return true;
            qu[r++] = root;
            boolean check = false;
            while(l<r){
                TreeNode e = qu[l++];
                if((e.left==null&&e.right!=null )|| check&&(e.left==null||e.right==null)) return false;

                if(e.left!=null) qu[r++] = e.left;
                if(e.right!=null) qu[r++] = e.right;

                if(e.left!=null&&e.right==null) check = true;
            }

            return true;
        }
    }
}
