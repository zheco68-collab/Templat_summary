import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.PriorityQueue;

public class dijkstra {

    // 自定义快速输入类，防止 StreamTokenizer 带来的类型截断与速度瓶颈
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, buflen = 0;

        private byte read() {
            if (ptr >= buflen) {
                ptr = 0;
                try {
                    buflen = in.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (buflen <= 0) return -1;
            }
            return buffer[ptr++];
        }

        public int nextInt() {
            int b = read();
            while (b <= ' ') b = read();
            int res = 0;
            while (b >= '0' && b <= '9') {
                res = res * 10 + (b - '0');
                b = read();
            }
            return res;
        }
    }

    // 边结点定义
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();

        // 邻接表存图
        java.util.List<Edge>[] graph = new java.util.ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new java.util.ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Edge(v, w));
        }

        dijkstra(graph, s, n);
    }

    public static void dijkstra(java.util.List<Edge>[] graph, int s, int n) {
        int[] dist = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 小根堆存储 [node, distance]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        
        dist[s] = 0;
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            // 懒惰删除：若出队距离大于记录的最小距离，说明是失效节点，跳过
            if (vis[u]) continue;
            vis[u] = true;

            for (Edge edge : graph[u]) {
                int v = edge.to;
                int w = edge.weight;

                // 核心松弛操作 (Relaxation)
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        // 输出结果
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            out.append(dist[i]).append(i == n ? "" : " ");
        }
        System.out.println(out);
    }
}