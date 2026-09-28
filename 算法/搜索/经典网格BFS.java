import java.util.*;

class 经典网格BFS {
    int[] dx = {-1, 0, 1, 0};  // 上、右、下、左
    int[] dy = {0, 1, 0, -1};

    // 求从(sx, sy)到(ex, ey)的最短步数
    int bfs(int n, int m, int sx, int sy, int ex, int ey, int[][] grid) {
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, -1);
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            
            if (x == ex && y == ey) {
                return dist[x][y];  // 到达终点
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                // 越界检查 + 障碍物检查 + 访问检查
                if (nx >= 0 && nx < n && ny >= 0 && ny < m 
                    && dist[nx][ny] == -1 && grid[nx][ny] == 0) {
                    dist[nx][ny] = dist[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return -1;  // 无法到达
    }
}