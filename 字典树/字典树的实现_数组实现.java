import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 字典树的实现_数组实现 {
    public static int Max = (int)1e5+5,cnt;
    public static int[][] tree = new int[Max][26];
    public static int[] pass = new int[Max];
    public static int[] end = new int[Max];

    public static void builder(){
        cnt = 1;
    }

    public static void insert(String word){
        int cur = 1;
        pass[cur] = -~pass[cur];
        for(int i=0,path;i<word.length();i=-~i){
            path = word.charAt(i)-'a';
            if(tree[cur][path]==0) tree[cur][path] = ++cnt;
            cur = tree[cur][path];
            pass[cur] = -~pass[cur];
        }

        end[cur] = -~end[cur];
    }


    public static boolean vis(String v){
        int cur = 1;
        for(int i=0,path;i<v.length();i=-~i){
            path = v.charAt(i)-'a';
            if(tree[cur][path]==0) return false;
            cur = tree[cur][path];
        }

        return end[cur]>=1;
    }

    public static void delete(String del){
        if(!vis(del))return;
        int cur = 1;
        pass[cur]--;
        for(int i=0,path;i<del.length();i=-~i){
            path = del.charAt(i)-'a';
            if(--pass[tree[cur][path]]==0){
                tree[cur][path] = 0;
                return;
            }
            cur = tree[cur][path];
        }
    }

    public static int prefixNumber(String pre){
        int cur = 1;
        for(int i=0,path;i<pre.length();i=-~i){
            path = pre.charAt(i)-'a';
            if(tree[cur][path]==0)return 0;
            cur = tree[cur][path];
        }
        return pass[cur];
    }


    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String... args) throws IOException {
        StringBuilder out = new StringBuilder();
        int m = Integer.parseInt(bf.readLine());
        builder();
        while(m-->0){
            StringTokenizer sz = new StringTokenizer(bf.readLine());
            int op = Integer.parseInt(sz.nextToken());
            if(op==1) insert(sz.nextToken());
            if(op==2) delete(sz.nextToken());
            if(op==3) out.append(vis(sz.nextToken())?"YES":"NO").append("\n");
            if(op==4) out.append(prefixNumber(sz.nextToken())).append("\n");
        }

        System.out.println(out);
    }
}
