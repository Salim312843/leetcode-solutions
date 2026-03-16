
# 347. Top K Frequent Elements   [level : medium]

## Problem Statement
Given an integer array `nums` and an integer `k`, return the **k most frequent elements**.

You may return the answer in **any order**.

Example:

```
Input: nums = [1,1,1,2,2,3], k = 2  
Output: [1,2]
```

Explanation:

- `1` appears **3 times**
- `2` appears **2 times**
- `3` appears **1 time**

The **top 2 frequent elements** are `[1,2]`.

---

# Breakdown of the Question (How to Think)

Important observations from the problem:

- We need to find **frequency of each number**.
- Then we must **return the k elements with highest frequency**.
- Order of output **does not matter**.

### Key Idea

The problem can be divided into **two steps**:

1. **Count frequency of each number**
2. **Select the k elements with highest frequency**

This suggests using **HashMap for frequency counting**.

---

# Constraints Analysis

Typical constraints:

```
1 ≤ nums.length ≤ 10^5
-10^4 ≤ nums[i] ≤ 10^4
k is in the range [1, number of unique elements]
```

### What these constraints tell us

- Array size can be **very large (100,000)**.
- **O(n²)** solutions are not acceptable.
- Efficient frequency tracking is needed.

### Implication

| Approach | Feasibility |
|--------|--------|
| Brute Force | Too slow |
| Sorting by frequency | Good |
| Heap / Bucket Sort | Optimal |

---

# Approaches

---

# 1️⃣ Brute Force Approach

### Idea

- Count frequency of each number.
- Compare all frequencies to determine the top k frequent elements.

### Algorithm

1.Create a HashMap to store frequency.
2.Traverse the array and count occurrences.
3.Convert the map entries into a list.
4.Sort the list based on frequency (descending).
5.Take the first k elements from the list.
6.Return them as the result.

### Time Complexity

```
O(n²)
```

Reason:

- Counting frequency using nested loops requires comparing elements multiple times.

### Space Complexity

```
O(1)
```

Only a few variables are used.

### Limitations

- Very slow for large arrays.
- Many repeated computations.

---

# 2️⃣ HashMap + Sorting Approach

### Idea

1. Use **HashMap** to count frequencies.
2. Convert map entries into a list.
3. Sort the list based on frequency.
4. Return the first **k elements**.

### Algorithm

1. Create a HashMap.
2. Traverse the array and count frequencies.
3. Store entries in a list.
4. Sort list by frequency in descending order.
5. Return the first `k` elements.

### Time Complexity

```
O(n log n)
```

Reason:

- Building the HashMap takes **O(n)**.
- Sorting the entries takes **O(n log n)**.

### Space Complexity

```
O(n)
```

HashMap stores frequencies of elements.

### Limitations

- Sorting all elements is unnecessary.
- Slower than optimized approaches.

---

# 3️⃣ HashMap + Min Heap Approach (Better)

### Idea

- Use a **HashMap** to store frequencies.
- Use a **Min Heap (Priority Queue)** of size `k`.
- Keep only the **top k frequent elements** in the heap.

### Algorithm

1. Count frequencies using HashMap.
2. Create a Min Heap based on frequency.
3. Add elements to the heap.
4. If heap size exceeds `k`, remove the smallest frequency.
5. Remaining elements in heap are the answer.

### Time Complexity

```
O(n log k)
```

Reason:

- Heap insertion takes **O(log k)**.
- We perform it for **n elements**.

### Space Complexity

```
O(n)
```

HashMap stores frequencies.

### Limitations

- Slightly complex implementation.
- Heap operations add overhead.

---

# 4️⃣ Bucket Sort Approach (Optimal) ⭐

### Idea

- Frequency of elements ranges from **1 to n**.
- Create **buckets where index = frequency**.
- Store numbers based on their frequency.
- Traverse buckets from highest frequency.

### Algorithm

1. Count frequency using HashMap.
2. Create bucket array of size `n + 1`.
3. Place numbers into buckets based on frequency.
4. Traverse buckets from end to start.
5. Collect elements until `k` elements are found.

### Time Complexity

```
O(n)
```

Reason:

- Counting frequencies takes **O(n)**.
- Traversing buckets takes **O(n)**.

### Space Complexity

```
O(n)
```

Bucket array and HashMap require extra space.

### Limitations

- Slightly more memory usage.
- Implementation is a bit more complex.

---

# Final Comparison

| Approach | Time Complexity | Space Complexity | Notes |
|--------|--------|--------|--------|
| Brute Force | O(n²) | O(1) | Very slow |
| HashMap + Sorting | O(n log n) | O(n) | Simple but slower |
| HashMap + Heap | O(n log k) | O(n) | Good optimization |
| Bucket Sort (Optimal) | O(n) | O(n) | Best solution |

---

# Key Insight

The important observation is:

```
We only need the k elements with highest frequency.
```

Instead of sorting all elements, we can **store frequencies and directly retrieve the most frequent ones**, which leads to **heap or bucket sort solutions**.

Bucket sort achieves the **optimal O(n) time complexity**.
