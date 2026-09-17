import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的序列化与反序列化 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left,TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public String serialize(TreeNode root){
        if(root==null) return "#";
        StringBuilder out = new StringBuilder();
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);out.append(root.val);
        while(!qu.isEmpty()){
            int size = qu.size();
            for(int i=0;i<size;i=-~i){
                TreeNode e = qu.poll();
                if(e.left!=null){
                    out.append(e.left.val);
                    qu.add(e.left);
                }else out.append(".");

                if(e.right!=null){
                    out.append(e.right.val);
                    qu.add(e.right);
                }else out.append(".");
            }
        }
        return out.toString();
    }



}
