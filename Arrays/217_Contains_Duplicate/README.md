# 217. Contains Duplicate

##  Problem Statement
Given an integer array `nums`, return **true** if any value appears **at least twice** in the array, and return **false** if every element is distinct.

---

##  Approaches

### 1️⃣ Brute Force Approach
**Idea:**
- Compare every element with every other element using nested loops.
- If any two elements are equal, return `true`.

**Time Complexity:** O(n²)  : For each element we compare with all other elements.
                             Total comparisons ≈ n × n.

**Space Complexity:** O(1) :  No extra data structures are used.
                              Only a few variables are needed.

---

### 2️⃣ Sorting Approach
**Idea:**
1. Sort the array.
2. Compare adjacent elements.
3. If two adjacent elements are equal, return `true`.

**Time Complexity:** O(n log n)  : Sorting takes `O(n log n)`.
                                  Traversing the array takes `O(n)`.
                                  Overall complexity is dominated by sorting.

**Space Complexity:** O(log n)  : Java’s sorting algorithm uses recursion stack internally.


---

### 3️⃣ HashSet Approach (Optimal) ⭐

**Idea:**
- Use a `HashSet` to store elements already seen.
- While iterating through the array:
  - If the element already exists in the set → duplicate found.
  - Otherwise add it to the set.

This allows fast lookup in constant time.

**Time Complexity:** O(n)  : We iterate through the array once.
                             HashSet `add()` and `contains()` operations take O(1) on average.

**Space Complexity:** O(n)  : In the worst case all elements are unique.
                              The HashSet will store all `n` elements.


---



       
