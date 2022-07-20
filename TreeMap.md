## 自定义多维度排序
[最多单词数的发件人](https://leetcode.cn/problems/sender-with-largest-word-count/)
```java
class Solution {

	public String largestWordCount(String[] messages, String[] senders) {
		TreeMap<String, Integer> map = new TreeMap<>();
		for (int i = 0; i < messages.length; i++) {
			map.put(senders[i], map.getOrDefault(senders[i], 0) + messages[i].split(" ").length);
		}
		String result = senders[0];
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() >= map.get(result)) {
				result = entry.getKey();
			}
		}
		return result;
	}
}
```
## 区间问题，常用TreeMap
[统计区间中的整数数目](https://leetcode.cn/problems/count-integers-in-intervals/)
```java
class CountIntervals {
    TreeMap<Integer, Integer> m = new TreeMap<>();
    int cnt; // 所有区间长度和

    public CountIntervals() {}

    public void add(int left, int right) {
        // 遍历所有被 [left,right] 覆盖到的区间（部分覆盖也算）
        for (Map.Entry<Integer,Integer> e = m.ceilingEntry(left); e != null && e.getValue() <= right; e = m.ceilingEntry(left)) {
            int l = e.getValue(), r = e.getKey();
            left = Math.min(left, l);   // 合并后的新区间，其左端点为所有被覆盖的区间的左端点的最小值
            right = Math.max(right, r); // 合并后的新区间，其右端点为所有被覆盖的区间的右端点的最大值
            cnt -= r - l + 1;
            m.remove(r);
        }
        cnt += right - left + 1;
        m.put(right, left); // 所有被覆盖到的区间与 [left,right] 合并成一个新区间
    }

    public int count() { return cnt; }
}
```
## 区间问题，常用TreeMap
[毯子覆盖的最多白色砖块数](https://leetcode.cn/problems/maximum-white-tiles-covered-by-a-carpet/)
```java
public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        // 左端点升序排序
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
        int len = tiles.length;
        // 前缀和数组 {0, 5, 7, 14, 20, 23}
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + (tiles[i - 1][1] - tiles[i - 1][0] + 1);
        }
        // 存储左边界与对应的索引+1(方便前缀和运算)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int index = 1;
        for (int[] tile : tiles) {
            map.put(tile[0], index);
            index++;
        }
        // {1=1, 10=2, 12=3, 20=4, 30=5}
        int res = 0;
        // 遍历每个区间左端点
        for (int key : map.keySet()) {
            // 获取严格小于key+carpetLen-1的最大键(key=10,carpetLen=1,那么右端点也是10)
            // next这个区间就是毯子能到达的最远索引
            if (map.floorKey(key + carpetLen - 1) != null){
                int next = map.floorKey(key + carpetLen - 1);
                // 如果覆盖？就取两个数组的前缀和之差： 否则，就取前一个前缀和之差加上没覆盖全的长度
                int num = key + carpetLen - 1 >= tiles[map.get(next) - 1][1] ? sum[map.get(next)] - sum[map.get(key) - 1] :
                        sum[map.get(next) - 1] - sum[map.get(key) - 1] + ((carpetLen + key-1) - tiles[map.get(next) - 1][0]+1);
                res = Math.max(res, num);
            }
        }
        return res;
    }
```

## NavigableMap接口扩展的SortedMap，具有了针对给定搜索目标返回最接近匹配项的导航方法
### ceilingEntry(Object key) 大于等于，不存在则返回null
![image](https://user-images.githubusercontent.com/20769910/179881373-d7b0e022-dbb6-435a-8f22-624e63c6c722.png)
### ceilingKey(Object key) 大于等于
### floorEntry(Object key) 小于等于
### floorKey(Object key) 小于等于
### higherEntry(Object key) 大于
### lowerEntry(Object key) 小于
