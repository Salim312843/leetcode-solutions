# Container With Most Water — LeetCode #11

## Problem Statement

You are given an integer array `height` of length `n`.

There are `n` vertical lines such that the two endpoints of the `i`th line are:

```text
(i, 0) and (i, height[i])
```

Find two lines that, together with the x-axis, form a container that holds the **most water**.

Return the **maximum amount of water** the container can store.

### Important Points

* You must choose **two different lines**.
* The amount of water depends on:

  * The distance between the two lines.
  * The height of the shorter line.
* The formula is:

```text
Area = width × minimum(height[left], height[right])
```

### Example 1

**Input:**

```text
height = [1,8,6,2,5,4,8,3,7]
```

**Output:**

```text
49
```

### Example 2

**Input:**

```text
height = [1,1]
```

**Output:**

```text
1
```

### Constraints

```text
2 <= height.length <= 10^5
0 <= height[i] <= 10^4
```

---

# Approach 1: Brute Force

### Logic

* Consider every possible pair of lines.
* Calculate the area for each pair.
* Keep track of the maximum area.

### Code

```java
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;

        for (int left = 0; left < height.length; left++) {
            for (int right = left + 1; right < height.length; right++) {

                int width = right - left;
                int minHeight = Math.min(height[left], height[right]);

                int area = width * minHeight;

                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}
```

### Time Complexity

**O(n²)** — every possible pair of lines is checked.

### Space Complexity

**O(1)** — only a few variables are used.

---

# Approach 2: Two Pointers ⭐ Optimal

### Logic

* Start with the widest possible container:

  * `left = 0`
  * `right = n - 1`
* Calculate the current area.
* The **shorter line limits the amount of water**.
* Move the pointer pointing to the shorter line inward.
* Continue until `left >= right`.

### Code

```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {

            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);

            int area = width * minHeight;

            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
```

### Time Complexity

**O(n)** — each pointer moves only in one direction and together they traverse the array once.

### Space Complexity

**O(1)** — only two pointers and a few variables are used.

---

# Interview Explanation of Optimized Approach

### How to Explain in an Interview

> "I use the two-pointer approach. I start with one pointer at the beginning and another at the end because this gives the maximum possible width. I calculate the area using the width multiplied by the height of the shorter line. Then I move the pointer pointing to the shorter line, because the shorter line is the limiting factor. Moving the taller line cannot increase the area since the width decreases while the limiting height remains the same or becomes smaller. I continue this process until the two pointers meet, keeping track of the maximum area."

### Why Do We Move the Shorter Pointer?

Suppose:

```text
height[left] < height[right]
```

The current area is:

```text
width × height[left]
```

The left line is the limiting height.

If we move `right` instead:

```text
width decreases
height[left] remains the same
```

So the area **cannot become larger**.

Therefore, we move:

```text
left++
```

Similarly, if:

```text
height[right] < height[left]
```

we move:

```text
right--
```

### Key Interview Point

**"Always move the pointer at the shorter height because the shorter line limits the container's capacity."**

This is the main idea behind the **O(n) two-pointer solution**.
