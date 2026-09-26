import java.util.ArrayDeque;
import java.util.Queue;

public class 外扩bfs模板 {
    public class E{
        int x,y;
        public E(int... i){
            x = i[0];
            y = i[1];
        }
    }

    public int[] dx = {1,0,0,-1};
    public int[] dy = {0,1,-1,0};
    public int n,m;

    public boolean check(int... i){
        return (i[0]<0||i[0]>=n)||(i[1]<0||i[1]>=m);
    }

    public int maxDistance(int[][] grid) {
        Queue<E> pr = new ArrayDeque<>();
        n = grid.length;m = grid[0].length;

        for(int i=0;i<n;i=-~i) for(int j=0;j<m;j=-~j) if(grid[i][j]==1) pr.add(new E(i,j));
        if(pr.isEmpty()||pr.size()==n*m) return -1;

        int ans = 2;
        while(!pr.isEmpty()){
            E u = pr.poll();
            for(int t=0;t<4;t=-~t){
                int nx = u.x+dx[t];
                int ny = u.y+dy[t];

                if(!check(nx,ny)&&grid[nx][ny]==0){
                    grid[nx][ny] = grid[u.x][u.y]+1;
                    ans = Math.max(ans,grid[nx][ny]);
                    pr.add(new E(nx,ny));
                }
            }
        }
        return ans-1;
    }


    public void main(String... args){
        int[][] g = new int[][]{
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1}
        };

        System.out.println(maxDistance(g));
    }
}
