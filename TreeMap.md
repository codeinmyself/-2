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
## ceilingEntry(Object key) 大于等于
![image](https://user-images.githubusercontent.com/20769910/179881373-d7b0e022-dbb6-435a-8f22-624e63c6c722.png)
## ceilingKey(Object key) 大于等于
## floorEntry(Object key) 小于等于
## floorKey(Object key) 小于等于
## higherEntry(Object key) 大于
## lowerEntry(Object key) 小于
