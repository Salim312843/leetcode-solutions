# 3Sum — LeetCode #15

## Problem Statement

Given an integer array `nums`, return all the **triplets** `[nums[i], nums[j], nums[k]]` such that:

* `i`, `j`, and `k` are three different indices.
* `nums[i] + nums[j] + nums[k] == 0`.
* The solution must not contain duplicate triplets.

The order of the triplets and the order of elements inside a triplet do not matter.

### Example 1

**Input:**

```text
nums = [-1,0,1,2,-1,-4]
```

**Output:**

```text
[[-1,-1,2],[-1,0,1]]
```

### Example 2

**Input:**

```text
nums = [0,1,1]
```

**Output:**

```text
[]
```

### Example 3

**Input:**

```text
nums = [0,0,0]
```

**Output:**

```text
[[0,0,0]]
```

---

# Approach 1: Brute Force

### Logic

* Use three nested loops to check every possible triplet.
* If the sum is `0`, add the triplet.
* Use a `Set` to avoid duplicate triplets.

### Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(
                            nums[i], nums[j], nums[k]
                        );

                        Collections.sort(triplet);
                        set.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }
}
```

### Time Complexity

**O(n³)** — three nested loops check all possible triplets.

### Space Complexity

**O(n)** — the `HashSet` stores the unique triplets.

---

# Approach 2: HashSet

### Logic

* Fix one element `nums[i]`.
* Use a `HashSet` to find two other elements whose sum is `-nums[i]`.
* Store triplets in another `HashSet` to avoid duplicates.

### Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {

            Set<Integer> seen = new HashSet<>();

            for (int j = i + 1; j < nums.length; j++) {
                int complement = -nums[i] - nums[j];

                if (seen.contains(complement)) {
                    List<Integer> triplet = Arrays.asList(
                        nums[i], nums[j], complement
                    );

                    Collections.sort(triplet);
                    result.add(triplet);
                }

                seen.add(nums[j]);
            }
        }

        return new ArrayList<>(result);
    }
}
```

### Time Complexity

**O(n²)** — one element is fixed and the remaining elements are scanned using a `HashSet`.

### Space Complexity

**O(n)** — the `HashSet`s store elements and the result stores unique triplets.

---

# Approach 3: Sorting + Two Pointers ⭐ Optimal

### Logic

1. Sort the array.
2. Fix `nums[i]` as the first element.
3. Use two pointers:

   * `left = i + 1`
   * `right = n - 1`
4. Calculate:

   ```text
   sum = nums[i] + nums[left] + nums[right]
   ```
5. If `sum == 0`, store the triplet.
6. If `sum < 0`, move `left` forward.
7. If `sum > 0`, move `right` backward.
8. Skip duplicate values for `i`, `left`, and `right`.

### Code

```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate fixed elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Since the array is sorted, no possible triplet
            // can have sum 0 if nums[i] is already greater than 0.
            if (nums[i] > 0) {
                break;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(
                        nums[i],
                        nums[left],
                        nums[right]
                    ));

                    // Skip duplicate left values
                    while (left < right &&
                           nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right &&
                           nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;

                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
```

### Time Complexity

**O(n²)** — sorting takes `O(n log n)`, followed by an `O(n²)` two-pointer search.

### Space Complexity

**O(1)** extra space — apart from the output list, only a few variables are used.

---

# ⭐ Key Duplicate Handling

For:

```text
nums = [-1,0,1,2,-1,-4]
```

After sorting:

```text
[-4,-1,-1,0,1,2]
```

When `i` reaches the first `-1`:

```text
i = 1 → nums[i] = -1
```

We find:

```text
[-1,-1,2]
[-1,0,1]
```

When `i` reaches the **second `-1`**:

```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

It skips that `-1`.

This does **not** remove the second `-1` from the triplet. It only prevents using the second `-1` as the **fixed first element** again.

That is why:

```text
[-1,-1,2]
```

is still correctly produced.

---

🎤 Interview Explanation

First, I sort the array so that I can use the Two Pointers technique and handle duplicates easily. I fix one element using a loop and then use the left and right pointers to find the remaining two elements. If the sum is less than 0, I move the left pointer forward to increase the sum. If the sum is greater than 0, I move the right pointer backward to decrease the sum. When the sum is 0, I store the triplet and skip duplicate values to ensure unique results. This gives us O(n²) time complexity and O(1) auxiliary space, excluding the output list.
