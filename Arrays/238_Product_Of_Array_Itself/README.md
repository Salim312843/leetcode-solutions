

# 238. Product of Array Except Self  [level : Medium]

## Problem Statement

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Constraints:

2 <= nums.length <= 105

-30 <= nums[i] <= 30

The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

```
```
---

# LeetCode 238. Product of Array Except Self (Java)

---

## Approach 1: Brute Force

### Logic
- For every index, calculate the product of all other elements.
- Skip the current index while multiplying.

```java
class Solution {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {

            int product = 1;

            for (int j = 0; j < n; j++) {

                if (i != j) {
                    product *= nums[j];
                }
            }

            result[i] = product;
        }

        return result;
    }
}
```

**Time Complexity:** `O(n²)` *(For each element, all other elements are traversed to calculate the product.)*  
**Space Complexity:** `O(1)` *(Only the output array is used, excluding the returned result.)*

---

## Approach 2: Division Method (Not Accepted if Division is Forbidden)

### Logic
- Calculate the product of all non-zero elements.
- Handle zero cases separately.
- Divide the total product by the current element when no zeros exist.

```java
class Solution {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        int product = 1;
        int zeroCount = 0;

        for (int num : nums) {

            if (num == 0)
                zeroCount++;
            else
                product *= num;
        }

        for (int i = 0; i < n; i++) {

            if (zeroCount > 1) {
                result[i] = 0;
            }
            else if (zeroCount == 1) {

                if (nums[i] == 0)
                    result[i] = product;
                else
                    result[i] = 0;

            }
            else {
                result[i] = product / nums[i];
            }
        }

        return result;
    }
}
```

**Time Complexity:** `O(n)` *(The array is traversed a constant number of times.)*  
**Space Complexity:** `O(1)` *(Only a few extra variables are used.)*

---

## Approach 3: Prefix and Suffix Arrays

### Logic
- Build a prefix product array.
- Build a suffix product array.
- Multiply the corresponding prefix and suffix products for each index.

```java
class Solution {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];

        prefix[0] = 1;
        suffix[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }
}
```

**Time Complexity:** `O(n)` *(Three linear traversals are performed.)*  
**Space Complexity:** `O(n)` *(Additional prefix and suffix arrays are used.)*

---

## Approach 4: Optimized Prefix + Suffix ⭐ Optimal

### Logic
- Store prefix products directly in the result array.
- Traverse from right to left while maintaining a suffix product.
- Multiply the prefix and suffix products in-place.

```java
class Solution {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;

        // Prefix products
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int suffix = 1;

        // Multiply with suffix products
        for (int i = n - 1; i >= 0; i--) {

            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }
}
```

**Time Complexity:** `O(n)` *(Each element is visited twice in separate linear passes.)*  
**Space Complexity:** `O(1)` *(No extra arrays are used except the returned result.)*


