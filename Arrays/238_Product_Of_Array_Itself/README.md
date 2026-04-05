# LeetCode Solutions

This repository contains my solutions to LeetCode problems with detailed
approaches, complexity analysis, and explanations.

Language used: **Java**

Topics covered in this problem:
- Arrays
- Prefix Sum / Suffix Product
- Space Optimization

---

# 238. Product of Array Except Self

## Problem Statement

Given an integer array `nums`, return an array `answer` such that:

```
answer[i] = product of all elements of nums except nums[i]
```

### Conditions:

- You must solve it **without using division**.
- The solution must run in **O(n)** time.

---

# Breakdown of the Question (How to Think)

Key observations:

- For each index `i`, we need:
  - Product of elements **before i**
  - Product of elements **after i**

So:

```
answer[i] = left_product[i] * right_product[i]
```

### Key Idea

Instead of calculating product repeatedly:

- Precompute:
  - Prefix products (left side)
  - Suffix products (right side)

---

# Constraints Analysis

Typical constraints:

```
2 ≤ nums.length ≤ 10^5
-30 ≤ nums[i] ≤ 30
```

### What these constraints tell us

- Large input size → **O(n²)** not allowed
- Cannot use division → must compute manually
- Need efficient traversal → **O(n)** required

---

# Approaches

---

## 1️⃣ Brute Force Approach

### Idea

- For each element, multiply all other elements except itself.

### Algorithm

1. For each index `i`
2. Initialize product = 1
3. Loop through entire array
4. Skip index `i`
5. Multiply all other elements

### Time Complexity

```
O(n²)
```

Reason:

- For each element, we traverse the entire array.

### Space Complexity

```
O(1)
```

Only output array is used.

### Limitations

- Too slow for large inputs.

---

## 2️⃣ Prefix and Suffix Arrays Approach

### Idea

- Compute:
  - Prefix product array
  - Suffix product array

Then:

```
answer[i] = prefix[i] * suffix[i]
```

### Algorithm

1. Create prefix array:
   - prefix[i] = product of elements before i
2. Create suffix array:
   - suffix[i] = product of elements after i
3. Multiply both for final answer

### Time Complexity

```
O(n)
```

Reason:

- One pass for prefix
- One pass for suffix
- One pass for result

### Space Complexity

```
O(n)
```

Extra arrays for prefix and suffix.

### Limitations

- Uses extra space.

---

## 3️⃣ Optimized Prefix + Suffix (Space Optimized) ⭐

### Idea

- Store prefix directly in answer array.
- Use a variable to maintain suffix product.

### Algorithm

1. Initialize answer array.
2. First pass (prefix):
   - answer[i] = product of elements before i
3. Second pass (suffix):
   - Maintain `suffix` variable
   - Multiply with answer[i]

### Code Logic (Important)

```
answer[i] = prefix product
suffix *= nums[i]
```

### Time Complexity

```
O(n)
```

- Only two passes through the array.

### Space Complexity

```
O(1)
```

- No extra arrays used (excluding output array).

### Limitations

- Slightly tricky to understand initially.

---

# Final Comparison

| Approach | Time Complexity | Space Complexity | Notes |
|--------|--------|--------|--------|
| Brute Force | O(n²) | O(1) | Very slow |
| Prefix + Suffix Arrays | O(n) | O(n) | Uses extra space |
| Optimized Prefix + Suffix | O(n) | O(1) | Best solution |

---

# Key Insight

```
answer[i] = (product of left elements) × (product of right elements)
```

Instead of recomputing repeatedly:

- Use **prefix product**
- Use **suffix product**

👉 Store prefix in answer array  
👉 Use a variable for suffix  

This gives **O(n) time and O(1) space** — optimal solution.
