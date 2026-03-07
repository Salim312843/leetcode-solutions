# 1. Two Sum

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

---

# Key Insight

The most important observation is:  complement = target - nums[i]

