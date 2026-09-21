import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class 字典树的实现 {
    public static class Trie{
        public static class TrieNode{
            int pass,end;
            TrieNode[] trieNext;

            public TrieNode(){
                pass = end = 0;
                trieNext = new TrieNode[26];
            }
        }

        public TrieNode root;
        public Trie(){
            root = new TrieNode();
        }

        public void insert(String add){
            TrieNode node = root;
            node.pass = -~node.pass;
            for(int i=0,path;i<add.length();i=-~i){
                path = add.charAt(i)-'a';
                if(node.trieNext[path]==null) node.trieNext[path] = new TrieNode();
                node = node.trieNext[path];
                node.pass = -~node.pass;
            }
            node.end = -~node.end;
        }

        public boolean search(String vis){
            TrieNode node = root;
            for(int i=0,path;i<vis.length();i=-~i){
                path = vis.charAt(i)-'a';
                if(node.trieNext[path]==null)return false;
                node = node.trieNext[path];
            }
            return node.end >= 1;
        }

        public int prefixNumber(String pre){
            TrieNode node = root;
            for(int i=0,path;i<pre.length();i=-~i){
                path = pre.charAt(i)-'a';
                if(node.trieNext[path]==null)return 0;
                node = node.trieNext[path];
            }
            return node.pass;
        }

        public void delete(String word){
            if(!search(word)) return;
            TrieNode node = root;
            node.pass--;
            for(int i=0,path;i<word.length();i=-~i){
                path = word.charAt(i)-'a';
                if(--node.trieNext[path].pass==0){
                    node.trieNext[path] = null;
                    return;
                }
                node = node.trieNext[path];
            }

            node.end--;
        }
    }

    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int m = Integer.parseInt(bf.readLine());
        Trie trie = new Trie();
        while(m-->0){
            StringTokenizer sz = new StringTokenizer(bf.readLine());
            int op = Integer.parseInt(sz.nextToken());
            if(op==1) trie.insert(sz.nextToken());
            if(op==2) trie.delete(sz.nextToken());
            if(op==3) out.append(trie.search(sz.nextToken())?"YES":"NO").append("\n");
            if(op==4) out.append(trie.prefixNumber(sz.nextToken())).append("\n");
        }

        System.out.println(out);
    }
}