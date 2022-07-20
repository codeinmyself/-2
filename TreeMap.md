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

## ceilingEntry(Object key)
![image](https://user-images.githubusercontent.com/20769910/179881373-d7b0e022-dbb6-435a-8f22-624e63c6c722.png)

