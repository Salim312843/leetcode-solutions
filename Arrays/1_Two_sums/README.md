# 1. Two Sum    [level : easy]

## Problem Statement
Given an array of integers `nums` and an integer `target`, return **indices of the two numbers** such that they **add up to the target**.

- Each input has **exactly one solution**.
- You **cannot use the same element twice**.
- The answer can be returned in **any order**.

- Constraints:
2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.

**Follow-up**: Can you come up with an algorithm that is less than O(n2) time complexity?
 

---


### What these constraints tell us

- `n` can be large (up to **10⁴**) → `O(n²)` may be slow.
- Numbers can be **large or negative**.
- We need a **fast lookup mechanism**.


---

# Approaches

---

## 1️⃣ Brute Force Approach

### Idea
- Compare every element with every other element using nested loops.
- If any pair sums to the target, return their indices.

### Algorithm
1. Use two nested loops.
2. First loop selects element `i`.
3. Second loop checks elements `j > i`.
4. If `nums[i] + nums[j] == target`, return indices.

### Time Complexity  :  O(n²)
### Space Complexity  :  O(1)

### Limitations
- Very slow for large inputs.
- Many unnecessary comparisons.

---

## 2️⃣ Sorting + Two Pointer Approach

### Idea
1. Store numbers with their original indices.
2. Sort the array.
3. Use two pointers to find the target sum.

### Algorithm
1. Store `(value, index)` pairs.
2. Sort the array based on value.
3. Initialize two pointers:
   - `left = 0`
   - `right = n - 1`
4. Calculate the sum.
5. If sum equals target → return indices.
6. If sum < target → move left pointer.
7. If sum > target → move right pointer.

### Time Complexity  :  O(n log n)
- Sorting takes `O(n log n)`
- Two-pointer traversal takes `O(n)`
- Overall dominated by sorting.

### Space Complexity  :  O(n)
Extra space is required to store `(value, index)` pairs.

### Limitations
- Sorting destroys the original order.
- Extra work required to maintain indices.
- Slower than the optimal solution.

---

## 3️⃣ HashMap Approach (Optimal) ⭐

### Idea
Use a **HashMap** to store elements that have already been seen.

While iterating through the array:

- Compute the **complement**.
- Check if it exists in the map.
- If yes → return indices.
- Otherwise store the current element.

### Algorithm
1. Create a HashMap.
2. Traverse the array.
3. For each element:
   - `complement = target - nums[i]`
4. If complement exists in map → return indices.
5. Otherwise store `(nums[i], index)`.

### Time Complexity  :  O(n)
- We traverse the array once.
- HashMap operations (`put`, `get`) take **O(1)** on average.

### Space Complexity  :   O(n)
In the worst case all elements are unique.

### Limitations
- Requires extra memory.
- Hash collisions may slightly affect performance (rare).

### Single Pass vs Two pass approach Code
- In the Two Pass approach, the algorithm first stores all elements of the array with their indices in a HashMap using one loop, and then uses a second loop to search for the complement of each element. In contrast, the Single Pass approach performs both storing and searching in the same loop. While traversing the array, it first checks if the complement of the current element already exists in the HashMap, and if not, it stores the current element with its index. Thus, the single pass approach combines both operations in one traversal instead of two separate passes.

- ### Question Stetement : and you may not use the same element twice.
-Single Pass: Same element cannot occur because the current element is added after checking complement.
              No extra condition is required in the code.
              
Two Pass: All elements already exist in the map, so we must explicitly check that indices are different
          the algorithm might accidentally match the same element with itself. To prevent this, an extra condition is added in the code
          numIndexMap.get(complement) != i
---

# Key Insight

The most important observation is:  complement = target - nums[i]


# LeetCode 1. Two Sum (Java)

---

## Approach 1: Brute Force

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }

            }

        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n²)`  
**Space Complexity:** `O(1)`

---

## Approach 2: HashMap (Two Pass)

```java
import java.util.*;

class Solution {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        // First Pass: Store value -> index
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        // Second Pass: Search complement
        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }

        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n)`  
**Space Complexity:** `O(n)`

---

## Approach 3: HashMap (One Pass) ⭐ Optimal

```java
import java.util.*;

class Solution {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n)`  
**Space Complexity:** `O(n)`

---

## Approach 4: Sorting + Two Pointers

```java
import java.util.*;

class Solution {

    public int[] twoSum(int[] nums, int target) {

        int[][] arr = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int sum = arr[left][0] + arr[right][0];

            if (sum == target) {
                return new int[]{arr[left][1], arr[right][1]};
            }

            if (sum < target)
                left++;
            else
                right--;
        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n log n)`  
**Space Complexity:** `O(n)`

---

## Approach 5: Sorting + Binary Search

```java
import java.util.*;

class Solution {

    public int[] twoSum(int[] nums, int target) {

        int[][] arr = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < arr.length; i++) {

            int complement = target - arr[i][0];

            int left = i + 1;
            int right = arr.length - 1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (arr[mid][0] == complement) {
                    return new int[]{arr[i][1], arr[mid][1]};
                }

                if (arr[mid][0] < complement)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n log n)`  
**Space Complexity:** `O(n)`

