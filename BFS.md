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
