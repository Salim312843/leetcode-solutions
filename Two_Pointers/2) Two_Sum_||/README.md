# LeetCode 167. Two Sum II: Input Array Is Sorted (Java)

## Problem Statement

Given a **1-indexed** array of integers `numbers` that is already sorted in **non-decreasing order**, find two numbers such that they add up to a specific `target` number.

Return the indices of the two numbers as an integer array `[index1, index2]` of length 2, where:

- `1 <= index1 < index2 <= numbers.length`
- `numbers[index1] + numbers[index2] == target`

You may assume that there is **exactly one solution**.

Your solution must use only **constant extra space**.

**Example 1:**

```text
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]

Explanation:
2 + 7 = 9
Therefore, index1 = 1 and index2 = 2.
```

**Example 2:**

```text
Input: numbers = [2,3,4], target = 6
Output: [1,3]

Explanation:
2 + 4 = 6
Therefore, index1 = 1 and index2 = 3.
```

**Example 3:**

```text
Input: numbers = [-1,0], target = -1
Output: [1,2]

Explanation:
-1 + 0 = -1
Therefore, index1 = 1 and index2 = 2.
```

**Constraints:**

- `2 <= numbers.length <= 3 × 10⁴`
- `-1000 <= numbers[i] <= 1000`
- `-1000 <= target <= 1000`
- `numbers` is sorted in non-decreasing order.
- Exactly one solution exists.

---

## Approach 1: Brute Force

### Logic
- Check every possible pair of elements.
- Return their 1-based indices when their sum equals the target.

```java
class Solution {

    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {

            for (int j = i + 1; j < numbers.length; j++) {

                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n²)` *(Every possible pair may need to be checked.)*  
**Space Complexity:** `O(1)` *(Only a few variables are used.)*

---

## Approach 2: Binary Search

### Logic
- For each element, calculate the required complement.
- Binary search for that complement in the remaining sorted portion of the array.
- Return the 1-based indices when found.

```java
class Solution {

    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {

            int complement = target - numbers[i];

            int left = i + 1;
            int right = numbers.length - 1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (numbers[mid] == complement) {
                    return new int[]{i + 1, mid + 1};
                }

                if (numbers[mid] < complement) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n log n)` *(Binary search takes O(log n) for each of the n elements.)*  
**Space Complexity:** `O(1)` *(Only pointer variables are used.)*

---

## Approach 3: Two Pointers ⭐ Optimal

### Logic
- Place `left` at the beginning and `right` at the end.
- If the sum is too small, move `left` forward.
- If the sum is too large, move `right` backward.
- If the sum equals the target, return the 1-based indices.

```java
class Solution {

    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{};
    }
}
```

**Time Complexity:** `O(n)` *(The two pointers move toward each other, so each element is visited at most once.)*  
**Space Complexity:** `O(1)` *(Only two pointer variables are used.)*
