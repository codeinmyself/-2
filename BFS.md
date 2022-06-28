## BFS和DFS的区别
BFS适合求最短路径。BFS是面，DFS是线。

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

## BFS
