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

## 用新的Map存储对象，方便TreeSet进行remove，避免O(n)复杂度
[设计食物评分系统](https://leetcode.cn/problems/design-a-food-rating-system/)
```java
class FoodRatings {
    // 写个类存三个信息
    class Pair {
        String food;
        String cuis;
        int rating;
        Pair(String a, String b, int c) {
            food = a;
            cuis = b;
            rating = c;
        }
    }
    // 食物名对应一个类
    HashMap<String, Pair> fooToPair = new HashMap<>();
    // 烹饪名对应一个TreeSet， set 存的是所有是这种烹饪方式的食物，set按题意来个自定义排序
    HashMap<String, TreeSet<Pair>> cuisTofoo = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            Pair pair = new Pair(foods[i], cuisines[i], ratings[i]);
            fooToPair.put(foods[i], pair);
            if (!cuisTofoo.containsKey(cuisines[i])) {
                cuisTofoo.put(cuisines[i], new TreeSet<>(((o1, o2) -> {
                    if (o1.rating != o2.rating) return o2.rating - o1.rating;
                    return o1.food.compareTo(o2.food);
                })));
            }
            cuisTofoo.get(cuisines[i]).add(pair);
        }

    }

    public void changeRating(String food, int newRating) {
        Pair pair = fooToPair.get(food);
        // 改变评分时记得从set中删掉，不然更改评分后他的位置是错的，删掉，改评分，再加进去找到正确位置
        cuisTofoo.get(pair.cuis).remove(pair);
        pair.rating = newRating;
        cuisTofoo.get(pair.cuis).add(pair);
    }

    public String highestRated(String cuisine) {
        // 直接返回烹饪方式对应的treeset的第一个食物名字
        return cuisTofoo.get(cuisine).first().food;
    }
}
```
