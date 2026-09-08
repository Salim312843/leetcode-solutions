# Trapping Rain Water — LeetCode #42

## Problem Statement

Given an array `height` where `height[i]` represents the height of a vertical bar at position `i`, calculate how much **rainwater can be trapped** after it rains.

Each bar has a width of `1`.

Return the total amount of trapped water.

### Important Formula

For any position `i`:

```text
Water at i = min(maxLeft, maxRight) - height[i]
```

where:

* `maxLeft` = tallest bar to the left of `i`
* `maxRight` = tallest bar to the right of `i`

### Example 1

**Input:**

```text
height = [0,1,0,2,1,0,1,3,2,1,2,1]
```

**Output:**

```text
6
```

### Example 2

**Input:**

```text
height = [4,2,0,3,2,5]
```

**Output:**

```text
9
```

### Constraints

```text
2 <= height.length <= 2 * 10^4
0 <= height[i] <= 10^5
```

---

# Approach 1: Brute Force

### Logic

* For every position, find the tallest bar on its left.
* Find the tallest bar on its right.
* Calculate the water trapped at that position.
* Add it to the total.

### Code

```java
public static int trapBruteForce(int[] height) {
    int totalWater = 0;

    for (int i = 0; i < height.length; i++) {
        int leftMax = 0;
        for (int j = 0; j <= i; j++) {
            leftMax = Math.max(leftMax, height[j]);
        }

        int rightMax = 0;
        for (int j = i; j < height.length; j++) {
            rightMax = Math.max(rightMax, height[j]);
        }

        totalWater += Math.max(0, Math.min(leftMax, rightMax) - height[i]);
    }

    return totalWater;
}
```

### Time Complexity

**O(n²)** — for every position, we scan both sides to find the maximum heights.

### Space Complexity

**O(1)** — only a few variables are used.

---

# Approach 2: Prefix and Suffix Arrays

### Logic

* Create `leftMax[i]` = maximum height from the left up to `i`.
* Create `rightMax[i]` = maximum height from the right up to `i`.
* For every position:

```text
water = min(leftMax[i], rightMax[i]) - height[i]
```

### Code

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int totalWater = 0;

        for (int i = 0; i < n; i++) {
            int water = Math.min(leftMax[i], rightMax[i]) - height[i];
            totalWater += water;
        }

        return totalWater;
    }
}
```

### Time Complexity

**O(n)** — three linear passes are performed over the array.

### Space Complexity

**O(n)** — two arrays store the left and right maximum heights.

---

# Approach 3: Two Pointers ⭐ Optimal

### Logic

* Use two pointers:

### Code

```java
public static int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int leftMax = 0;
    int rightMax = 0;
    int totalWater = 0;

    while (left < right) {
        if (height[left] < height[right]) {
            leftMax = Math.max(leftMax, height[left]);
            totalWater += leftMax - height[left];
            left++;
        } else {
            rightMax = Math.max(rightMax, height[right]);
            totalWater += rightMax - height[right];
            right--;
        }
    }

    return totalWater;
}
```

### Time Complexity

**O(n)** — each pointer moves from one end toward the other only once.

### Space Complexity

**O(1)** — only pointers, maximum heights, and the result variable are used.

---

# Interview Explanation of Optimized Approach

### How to Explain in an Interview

> "I use the two-pointer approach. I place one pointer at the left end and one at the right end, and maintain the maximum height seen from both sides. The water at a position depends on the smaller of the maximum heights on its left and right. If the left bar is smaller than or equal to the right bar, I can safely calculate the water on the left because the right side already has a bar at least as tall as the current left boundary. Otherwise, I calculate the water on the right. I continue moving the corresponding pointer inward while updating the maximum heights. This processes every element once and gives O(n) time with O(1) extra space."

### Why Can We Move the Smaller Side?

Suppose:

```text
height[left] <= height[right]
```

Then the left side is the limiting side.

We already know that there is a right boundary at least as tall as `height[left]`.

Therefore, for the current left position:

```text
water = leftMax - height[left]
```

We can calculate it without knowing the exact maximum height on the right.

Similarly:

```text
height[right] < height[left]
```

means the right side is the limiting side, so we calculate:

```text
water = rightMax - height[right]
```

### Key Interview Point

**"The smaller boundary determines how much water can be trapped, so we process the side with the smaller current height."**

### Example

For:

```text
height = [4,2,0,3,2,5]
```

The trapped water at each position is:

```text
Index:       0  1  2  3  4  5
Height:      4  2  0  3  2  5
Water:       0  2  4  1  2  0
```

Therefore:

```text
Total = 0 + 2 + 4 + 1 + 2 + 0
      = 9
```
