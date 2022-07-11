## 继承TreeSet,使用接口：pollFirst
[无限集中的最小数字](https://leetcode.cn/problems/smallest-number-in-infinite-set/)
```java
class SmallestInfiniteSet extends TreeSet<Integer> {
	private int curr;

	public int popSmallest() {
		return isEmpty() ? ++curr : pollFirst();
	}

	public void addBack(int num) {
		if (num <= curr) {
			add(num);
		}
	}
}
```
