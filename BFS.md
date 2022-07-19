## BFS和DFS的区别
BFS适合求最短路径。BFS是面，DFS是线。

## BFS和DFS的相同点
本质都是暴力搜索算法。别名：泛洪算法。

## 图的BFS
```java
// 计算从起点 start 到终点 target 的最近距离
    int BFS(Node start, Node target) {
        Deque<Node> queue = new ArrayDeque<>(); // 核心数据结构
        Set<Node> visited = new HashSet<>(); // 避免走回头路

        queue.offer(start); // 将起点加入队列
        visited.add(start);
        int step = 0; // 记录层数

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Node cur = queue.poll();
                /* 划重点：这里判断是否到达终点 */
                if (cur.value == target.value) {
                    return step;
                }
                /* 将 cur 的相邻节点加入队列 */
                for (Node x : cur.adj()) {
                    if (!visited.contains(x)) {
                        queue.offer(x);
                        visited.add(x);
                    }
                }
            }
            /* 划重点：更新步数在这里 */
            step++;
        }
        return -1;
    }

    class Node {
        List<Node> nextList;
        int value;

        public List<Node> adj() {
            return nextList;
        }
    }
```

## 树的BFS
```java
int minDepth(TreeNode root) {
    if (root == null) return 0;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    // root 本身就是一层，depth 初始化为 1
    int depth = 1;
    
    while (!q.isEmpty()) {
        int sz = q.size();
        /* 将当前队列中的所有节点向四周扩散 */
        for (int i = 0; i < sz; i++) {
            TreeNode cur = q.poll();
            /* 判断是否到达终点 */
            if (cur.left == null && cur.right == null) 
                return depth;
            /* 将 cur 的相邻节点加入队列 */
            if (cur.left != null)
                q.offer(cur.left);
            if (cur.right != null) 
                q.offer(cur.right);
        }
        /* 这里增加步数 */
        depth++;
    }
    return depth;
}

```
## 双向BFS
双向 BFS 是从起点和终点同时开始扩散，当两边有交集的时候停止。
双向 BFS 也有局限，因为你必须知道终点在哪里。

## 多源BFS

### 例题
[地图分析](https://leetcode.cn/problems/as-far-from-land-as-possible/)

## 01BFS
### 双端队列（最优解法）
LinkedList/ArrayDeque,根据0,1不同权值，addFirst或者addLast.
[到达角落需要移除障碍物的最小数目](https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/)
```java
class Solution {
    final static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][]  dis = new int[m][n];
        for (var i = 0; i < m; i++) 
            Arrays.fill(dis[i], Integer.MAX_VALUE); // 初始值赋予最大值
        dis[0][0] = 0;
        ArrayDeque<int[]> q = new ArrayDeque<int[]>();
        q.addFirst(new int[]{0, 0}); // 加入队首
        while (!q.isEmpty()) {
            int[] p = q.pollFirst(); // 取出队首
            int x = p[0], y = p[1];
            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1]; // 四个方向
                if (0 <= nx && nx < m && 0 <= ny && ny < n) { // 边界判断
                    int g = grid[nx][ny]; // grid本身就有区分障碍物和空单元格，用1，0区分
                    if (dis[x][y] + g < dis[nx][ny]) { // 更新单元格最小成本
                        dis[nx][ny] = dis[x][y] + g;
                        if (g == 0) {
                            q.addFirst(new int[]{nx, ny}); // 如果是空白格，加入队首，优先遍历
                        }else {
                            q.addLast(new int[]{nx, ny}); // 如果是障碍物，加入对尾，最后遍历
                        }
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}
```
### 优先队列
利用PriorityQueue辅助解题

### 例题

[使网格图至少有一条有效路径的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)

[信物传送](https://leetcode.cn/problems/6UEx57/)
