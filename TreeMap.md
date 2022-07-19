## 自定义多维度排序
[最多单词数的发件人](https://leetcode.cn/problems/sender-with-largest-word-count/)
```java
class Solution {
    public String largestWordCount(String[] messages, String[] senders) {

        TreeMap<User, Integer> map = new TreeMap<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.count == o2.count) {
                    return o1.name.compareTo(o2.name);
                }else{
                    return o1.count - o2.count;
                }
            }
        });
        HashMap<String, Integer> map1 = new HashMap<>();
        for (int i = 0; i < messages.length; i++) {
            String[] s = messages[i].split(" ");
            map1.put(senders[i], map1.getOrDefault(senders[i], 0) + s.length);
        }
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            User user = new User(entry.getKey(),entry.getValue());
            map.put(user, entry.getValue());
        }
        return map.lastKey().name;
    }
    
    class User{
        String name;
        int count;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getCount() {
            return count;
        }
        public void setCount(int count) {
            this.count = count;
        }
        public User(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }
}
```
