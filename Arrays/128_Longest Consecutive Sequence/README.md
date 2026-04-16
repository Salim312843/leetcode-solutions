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

---

# 🔍 Question Signals (What & Why)

### 1️⃣ "Unsorted array"
👉 What:
- Elements are not in order

👉 Why:
- Sorting may be needed OR avoided cleverly

---

### 2️⃣ "Longest consecutive sequence"
👉 What:
- Find longest sequence like 1,2,3,4

👉 Why:
- Focus is on **sequence, not subarray**

---

### 3️⃣ "O(n) time"
👉 What:
- Must be linear

👉 Why:
- Sorting (O(n log n)) is not optimal

---

### 4️⃣ "Large range (-10^9 to 10^9)"
👉 What:
- Values can be very large

👉 Why:
- Cannot use counting sort / arrays
- Must use hashing

---

# 🚀 Approaches

---

## 1️⃣ Brute Force

### 💡 Logic
```java
for each num:
    current = num
    count = 1

    while (current + 1 exists in array):
        current++
        count++
```

---

### ⏱ Complexity
```
Time: O(n²)
Space: O(1)
```

👉 Reason:
- Searching for next element takes O(n)

---

## 2️⃣ Sorting Approach

### 💡 Logic
```java
sort array

count consecutive elements
track max length
```

---

### ⏱ Complexity
```
Time: O(n log n)
Space: O(1)
```

👉 Reason:
- Sorting dominates

---

## 3️⃣ HashSet Approach (Optimal) ⭐

### 💡 Logic
```java
store all elements in set

for each num:
    if (num - 1 not in set):  // start of sequence
        count length using num+1
```

---

### ⏱ Complexity
```
Time: O(n)
Space: O(n)
```

👉 Reason:
- Each element processed once

---


---

# 📊 Final Comparison

| Approach | Time | Space | Notes |
|--------|------|------|------|
| Brute Force | O(n²) | O(1) | Slow |
| Sorting | O(n log n) | O(1) | Better |
| HashSet (Optimal) | O(n) | O(n) | Best |

---

# 🎯 Final Insight

```
If problem says:
Unsorted + O(n) + sequence

👉 Think: HashSet + sequence start detection
```

## 🔥 Why HashSet is Optimal (Compared to Other Approaches)

### ✅ 1. Best Time Complexity
- HashSet → **O(n)**
- Sorting → O(n log n)
- Brute Force → O(n²)

👉 Meets optimal requirement

---

### ✅ 2. No Sorting Required
- Sorting adds extra **O(n log n)** cost  
- HashSet works directly on unsorted data  

---

### ✅ 3. Constant Time Lookup
- Checking `num + 1`:
  - Array → O(n)
  - HashSet → **O(1)**

👉 Major performance improvement

---

### ✅ 4. Avoids Redundant Work
- Only start when:
- num - 1 is NOT present
- Prevents re-counting sequences  

---

### ✅ 5. Handles Duplicates Automatically
- HashSet removes duplicates internally  
- No extra logic needed  

---

### ✅ 6. Supports Large Value Range
- Works efficiently for values from **-10^9 to 10^9**  
- No need for large indexing arrays  

---

### ✅ 7. Linear Traversal
- Insert all elements → O(n)  
- Traverse once → O(n)  
- No nested loops required  

---

## 🎯 Conclusion
> HashSet is optimal because it provides constant-time lookup, avoids sorting, eliminates redundant computations, and achieves overall O(n) time complexity.
