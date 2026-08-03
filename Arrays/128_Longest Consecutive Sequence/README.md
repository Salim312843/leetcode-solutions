# 128. Longest Consecutive Sequence  [ LEVEL : MEDIUM ]

## Problem Statement

Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in **O(n)** time.

---

## Example 1:
```
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive sequence is [1,2,3,4]
```

---

## Example 2:
```
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
```

---

## Constraints

```
0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
```


# LeetCode 128. Longest Consecutive Sequence (Java)

---

## Approach 1: Brute Force

### Logic
- For every element, repeatedly search for the next consecutive number in the array.
- Keep extending the sequence until the next number is not found.
- Track the maximum sequence length.

```java
class Solution {

    public int longestConsecutive(int[] nums) {

        int longest = 0;

        for (int num : nums) {

            int current = num;
            int length = 1;

            while (contains(nums, current + 1)) {
                current++;
                length++;
            }

            longest = Math.max(longest, length);
        }

        return longest;
    }

    private boolean contains(int[] nums, int target) {

        for (int num : nums) {
            if (num == target)
                return true;
        }

        return false;
    }
}
```

**Time Complexity:** `O(n²)` *(For each element, the array may be scanned again to find consecutive numbers.)*  
**Space Complexity:** `O(1)` *(No extra data structures are used.)*

---

## Approach 2: Sorting

### Logic
- Sort the array.
- Traverse the sorted array while counting consecutive numbers.
- Ignore duplicate elements.
- Keep track of the longest sequence.

```java
import java.util.Arrays;

class Solution {

    public int longestConsecutive(int[] nums) {

        if (nums.length == 0)
            return 0;

        Arrays.sort(nums);

        int longest = 1;
        int current = 1;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1])
                continue;

            if (nums[i] == nums[i - 1] + 1) {
                current++;
            } else {
                longest = Math.max(longest, current);
                current = 1;
            }
        }

        return Math.max(longest, current);
    }
}
```

**Time Complexity:** `O(n log n)` *(Sorting the array dominates the overall complexity.)*  
**Space Complexity:** `O(1)` *(Ignoring the space used internally by the sorting algorithm.)*

---

## Approach 3: HashMap

### Logic
- Store every number in a HashMap.
- Start a sequence only if the previous number does not exist.
- Count the length of the consecutive sequence.
- Update the maximum length.

```java
import java.util.*;

class Solution {

    public int longestConsecutive(int[] nums) {

        Map<Integer, Boolean> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, true);
        }

        int longest = 0;

        for (int num : map.keySet()) {

            if (!map.containsKey(num - 1)) {

                int current = num;
                int length = 1;

                while (map.containsKey(current + 1)) {
                    current++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}
```

**Time Complexity:** `O(n)` *(Each number is visited at most twice using constant-time HashMap lookups.)*  
**Space Complexity:** `O(n)` *(The HashMap stores all unique elements.)*

---

## Approach 4: HashSet ⭐ Optimal

### Logic
- Store all numbers in a HashSet.
- Start counting only from numbers that do not have a predecessor.
- Extend the sequence while consecutive numbers exist.
- Track the maximum sequence length.

```java
import java.util.*;

class Solution {

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {

            if (!set.contains(num - 1)) {

                int current = num;
                int length = 1;

                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}
```

**Time Complexity:** `O(n)` *(Each number is processed only once using constant-time HashSet operations.)*  
**Space Complexity:** `O(n)` *(The HashSet stores all unique elements.)*

---

