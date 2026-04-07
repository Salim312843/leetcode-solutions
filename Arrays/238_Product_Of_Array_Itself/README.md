

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

# Breakdown of the Question (How to Think)

# 🔍 Question Signals (What & Why)

### 1️⃣ "Except nums[i]"
👉 What:
- Exclude current element

👉 Why:
- Forces you to think in parts:
  - Left side
  - Right side

---

### 2️⃣ "Without division"
👉 What:
- Cannot use total_product / nums[i]

👉 Why:
- Division breaks when 0 is present
- Forces logical approach (prefix + suffix)

---

### 3️⃣ "O(n) time"
👉 What:
- Must solve in linear time

👉 Why:
- n is large (10^5)
- Brute force O(n²) will fail

---

### 4️⃣ "Product fits in 32-bit"
👉 What:
- No overflow issue

👉 Why:
- Focus is on logic, not big numbers

---

### 5️⃣ "Values include 0 and negatives"
👉 What:
- nums[i] can be 0 or negative

👉 Why:
- Must handle:
  - Zero carefully
  - Sign of product

---

### 6️⃣ "Follow-up: O(1) space"
👉 What:
- No extra arrays allowed

👉 Why:
- Tests optimization
- Forces reuse of output array

---

```
```


---



```
```


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
