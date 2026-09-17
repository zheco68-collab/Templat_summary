import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class AVL树模板 {
    public static class AvlNode<T extends Comparable<T>>{
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        AvlNode(T E){this(E,null,null);}

        AvlNode(T E,AvlNode<T> lt,AvlNode<T> rt){
            this.element = E;
            this.left = lt;
            this.right = rt;
            this.height = 0;
        }
        private AvlNode<T> root;
        private int height(AvlNode<T> t){return t==null?-1:t.height;}

        private AvlNode<T> insert(T x,AvlNode<T> t){
            if(t==null) return new AvlNode<T>(x,null,null);
            if(comp(x,t.element)<0){
                t.left = insert(x,t.left);
                if(height(t.left)-height(t.right)==2) if(comp(x,t.left.element)<0) t = dxL(t); else t = sxL(t);
            }else if(comp(x,t.element)>0){
                t.right = insert(x,t.right);
                if(height(t.right)-height(t.left)==2) if(comp(x,t.right.element)>0) t = dxR(t);else t = sxR(t);
            }

            t.height = Math.max(height(t.left),height(t.right))+1;
            return t;
        }

        private AvlNode<T> dxL(AvlNode<T> k2){
            AvlNode<T> k1 = k2.left;
            k2.left = k1.right;
            k1.right  = k2;
            k2.height = Math.max(height(k2.left),height(k2.right))+1;
            k1.height = Math.max(height(k1.left),k2.height)+1;
            return k1;
        }

        private AvlNode<T> dxR(AvlNode<T> k2){
            AvlNode<T> k1 = k2.right;
            k2.right = k1.left;
            k1.left = k2;
            k2.height = Math.max(height(k2.left),height(k2.right))+1;
            k1.height = Math.max(height(k1.right),k2.height)+1;
            return k1;
        }

        private AvlNode<T> sxL(AvlNode<T> k3){
            k3.left = dxR(k3.left);
            return dxL(k3);
        }

        private AvlNode<T> sxR(AvlNode<T> k3){
            k3.right = dxL(k3.right);
            return dxR(k3);
        }

        private int comp(T a,T b){return a.compareTo(b);}

        private AvlNode<T> reveom(T x,AvlNode<T> t){
            if(t==null) return null;

            int cmp = comp(x,t.element);
            if(cmp<0)t.left = reveom(x,t.left);
            else if(cmp>0)t.right = reveom(x,t.right);
            else{
                if(t.left!=null&&t.right!=null){
                    t.element = findMin(t.right).element;
                    t.right = reveom(t.element,t.right);
                }else t = t.left!=null? t.left:t.right;
            }
            return balance(t);
        }

        private AvlNode<T> findMin(AvlNode<T> t){
            if(t==null) return null;
            while(t.left!=null) t = t.left;
            return t;
        }

        private AvlNode<T> balance(AvlNode<T> t){
            if(t==null) return null;
            if(height(t.left)-height(t.right)==2) if(height(t.left.left)>=height(t.left.right)) t = dxL(t); else t = sxL(t);
            else if(height(t.right)-height(t.left)==2) if(height(t.right.right)>=height(t.right.left)) t = dxR(t); else t = sxR(t);
            t.height = Math.max(height(t.left),height(t.right))+1;
            return t;
        }

        public void add(T t) {insert(t,root);}
        public T remove(T t) {return reveom(t,root).element;}
    }

    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int sc()throws IOException {
        sz.nextToken();
        return (int)sz.nval;
    }

}

