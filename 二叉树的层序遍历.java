import java.util.*;

public class 二叉树的层序遍历 {

    public class TreeNode {
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            Queue<TreeNode> qu = new LinkedList<>();
            qu.add(root);

            List<List<Integer>> lists = new ArrayList<>();
            lists.add(new ArrayList<>());
            lists.get(0).add(root.val);

            while(!qu.isEmpty()){
                Queue<TreeNode> ke = new LinkedList<>();
                lists.add(new ArrayList<>());
                while(!qu.isEmpty()){
                    TreeNode e = qu.poll();
                    if(e.left!=null){
                        ke.add(e.left);
                        lists.getLast().add(e.left.val);
                    }
                    if(e.right!=null){
                        ke.add(e.right);
                        lists.getLast().add(e.right.val);
                    }
                }

                qu = ke;
            }
            lists.removeLast();
            return lists;
        }
    }
}
