## DFS
## 例题
### 记忆化搜索（备忘录+DFS）
[网格图中递增路径的数目](https://leetcode.cn/problems/number-of-increasing-paths-in-a-grid/)
```java
class Solution {
    int MOD = (int) 1e9 + 7;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    int[][] grid, f;


    // 备忘录 + dfs
    public int countPaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        f = new int[m][n]; // 以当前点为起点的路径数目
        for (int i = 0; i < m; i++) Arrays.fill(f[i], -1);
        int ans = 0;
        for (var i = 0; i < m; ++i)
            for (var j = 0; j < n; ++j)
                ans = (ans + dfs(i, j)) % MOD;
        return ans;
    }

    int dfs(int i, int j) {
        if (f[i][j] != -1) return f[i][j];
        int res = 1;
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] > grid[i][j])
                res = (res + (dfs(x, y))) % MOD;
        }
        return f[i][j] = res;
    }
}
```
## 树分割（DFS时间戳）
[从树中删除边的最小分数](https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/)
```java
class Solution {
    public int clock;
    public int[] nums;
    public int[] xor;
    public int[] in;
    public int[] out;
    public List<Integer>[] lists;

    public int minimumScore(int[] nums, int[][] edges) {
        // 数据准备
        int res = Integer.MAX_VALUE;
        int n = nums.length;
        this.nums = nums;
        in = new int[n];
        out = new int[n];
        xor = new int[n];
        
        // 统计各个节点的直连节点
        lists = new List[n];
        for(int i = 0; i < n; i ++){
            lists[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            lists[e[0]].add(e[1]);
            lists[e[1]].add(e[0]);
        }

        // DFS遍历计算各子树的xor及进入退出时间
        dfs(0, -1);

        // 遍历分离出的两棵子树的根节点 --- O(n^2)
        int x = 0, y = 0, z = 0;
        for(int i = 1; i < n; i ++){
            for(int j = i + 1; j < n; j ++){
                if(isParent(i, j)){
                    x = xor[0] ^ xor[i];
                    y = xor[i] ^ xor[j];
                    z = xor[j];
                }else if(isParent(j, i)){
                    x = xor[0] ^ xor[j];
                    y = xor[j] ^ xor[i];
                    z = xor[i];
                }else{
                    x = xor[0] ^ xor[i] ^ xor[j];
                    y = xor[i];
                    z = xor[j];
                }
                res = Math.min(res, Math.max(x, Math.max(y, z)) - Math.min(x, Math.min(y, z)));
            }
        }
        return res;
    }

    // 深度优先：遍历父节点之外的其他节点即可
    public void dfs(int x, int f){
        in[x] = ++clock;
        xor[x] = nums[x];
        for(int y: lists[x]){
            if(y != f){
                dfs(y, x);
                xor[x] ^= xor[y];
            }
        }
        out[x] = clock;
    }

    // 根据入出时间戳判断父子关系
    public boolean isParent(int x, int y){
        return in[x] <= in[y] && out[y] <= out[x];
    }
}
```
