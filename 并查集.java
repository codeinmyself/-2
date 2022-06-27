// 并查集，背住，不然没用，要背就背最标准的和完整的，别去背某些题目的精简/修改版，一点儿用没有
class UnionFind {
    int[] roots;

    public UnionFind(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    // 作用：查找i的parent，并在查找时进行路径上节点的parent重置，即路径压缩，最红指向同一个parent节点
    public int find(int i) {
        if (i == roots[i]) { // 说明找到最顶层的parent了，递归终止
            return i;
        }
        return roots[i] = find(roots[i]); // 这是一个赋值语句，并直接将值返回，
        /**
         *  可写成：
         *          int result=find(roots[i]);
         *          roots[i]=result;
         *          return result;
         */
    }

    // 作用：合并p，q两点
    // 分别find，然后链接
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            roots[pRoot] = qRoot;
        }
    }
}
