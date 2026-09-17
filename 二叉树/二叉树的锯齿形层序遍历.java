import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class 二叉树的锯齿形层序遍历 {

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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            Deque<TreeNode> du = new ArrayDeque<>();
            List<List<Integer>> lists = new ArrayList<>();
            if(root==null) return lists;
            du.add(root);
            while(!du.isEmpty()){

                ArrayList<Integer> a = new ArrayList<>();
                int size = du.size();
                if((lists.size()+1&1)==0) for(int i=0;i<size;i=-~i){
                    TreeNode e = du.pollLast();
                    a.add(e.val);
                    if(e.right!=null) du.addFirst(e.right);
                    if(e.left!=null) du.addFirst(e.left);
                }
                if((lists.size()+1&1)==1) for(int i=0;i<size;i=-~i){
                    TreeNode e = du.poll();
                    a.add(e.val);
                    if(e.left!=null) du.add(e.left);
                    if(e.right!=null) du.add(e.right);
                }
                lists.add(a);
            }
            return lists;
        }
    }
}
