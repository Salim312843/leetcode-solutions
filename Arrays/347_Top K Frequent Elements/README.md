
# 347. Top K Frequent Elements   [level : medium]

## Problem Statement
Given an integer array `nums` and an integer `k`, return the **k most frequent elements**.

You may return the answer in **any order**.

Example:

```
Input: nums = [1,1,1,2,2,3], k = 2  
Output: [1,2]
```

Explanation:

- `1` appears **3 times**
- `2` appears **2 times**
- `3` appears **1 time**

The **top 2 frequent elements** are `[1,2]`.

---

# LeetCode 347. Top K Frequent Elements (Java)

---

## Approach 1: Brute Force

### Logic
- Count the frequency of every element using nested loops.
- Repeatedly find the maximum frequency element and add it to the answer.
- Mark the selected element as processed and repeat until `k` elements are found.

```java
import java.util.*;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[k];
        boolean[] used = new boolean[n];

        for (int x = 0; x < k; x++) {

            int maxFreq = 0;
            int maxElement = 0;
            int maxIndex = -1;

            for (int i = 0; i < n; i++) {

                if (used[i])
                    continue;

                int freq = 0;

                for (int j = 0; j < n; j++) {
                    if (nums[i] == nums[j])
                        freq++;
                }

                if (freq > maxFreq) {
                    maxFreq = freq;
                    maxElement = nums[i];
                    maxIndex = i;
                }
            }

            result[x] = maxElement;

            for (int i = 0; i < n; i++) {
                if (nums[i] == maxElement)
                    used[i] = true;
            }
        }

        return result;
    }
}
```

**Time Complexity:** `O(n²)` *(Nested loops are used to calculate the frequency of every element.)*  
**Space Complexity:** `O(n)` *(The `used` array stores processed elements.)*

---

## Approach 2: HashMap + Sorting

### Logic
- Store each element's frequency in a HashMap.
- Convert the map entries into a list.
- Sort the list based on frequency in descending order.
- Return the first `k` elements.

```java
import java.util.*;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }
}
```

**Time Complexity:** `O(n log n)` *(Sorting the frequency list dominates the overall complexity.)*  
**Space Complexity:** `O(n)` *(The HashMap and list store the unique elements.)*

---

## Approach 3: HashMap + Min Heap (Priority Queue)

### Logic
- Count the frequency of each element.
- Maintain a Min Heap of size `k`.
- Remove the smallest frequency whenever the heap size exceeds `k`.
- The remaining elements in the heap are the top `k` frequent elements.

```java
import java.util.*;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            pq.offer(entry);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll().getKey();
        }

        return result;
    }
}
```

**Time Complexity:** `O(n log k)` *(Each unique element is inserted into a heap of size at most `k`.)*  
**Space Complexity:** `O(n)` *(The HashMap stores frequencies, and the heap stores at most `k` elements.)*

---

## Approach 4: HashMap + Bucket Sort ⭐ Optimal

### Logic
- Count the frequency of every element using a HashMap.
- Create buckets where the index represents the frequency.
- Place each number into its corresponding bucket.
- Traverse the buckets from highest frequency to lowest until `k` elements are collected.

```java
import java.util.*;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        for (int key : map.keySet()) {

            int freq = map.get(key);

            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(key);
        }

        int[] result = new int[k];
        int index = 0;

        for (int i = bucket.length - 1; i >= 0 && index < k; i--) {

            if (bucket[i] != null) {

                for (int num : bucket[i]) {

                    result[index++] = num;

                    if (index == k)
                        break;
                }
            }
        }

        return result;
    }
}
```

**Time Complexity:** `O(n)` *(Each element is processed a constant number of times without sorting.)*  
**Space Complexity:** `O(n)` *(The HashMap and bucket array together require linear extra space.)*
