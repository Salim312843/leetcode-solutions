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

### 3️⃣ HashSet Approach (Optimal) ⭐ (Single Pass HashSet)
  ** HashSet stores only values **      If you only need to check existence of an element → HashSet
  ** HashMap stores key -> value pair **      If you need extra information (like index, frequency, etc.) → HashMap

**Algorithm**
-Create an empty HashSet to store elements.
-Traverse the array from index 0 to n-1.
-For each element in the array:
-Check if the element already exists in the HashSet.
-If it exists → duplicate found, return true.
-If it does not exist → add the element to the HashSet.
-Continue the traversal until the end of the array.
-If no duplicate is found after the loop ends → return false
This allows fast lookup in constant time.

**Time Complexity:** O(n)  : We iterate through the array once.
                             HashSet `add()` and `contains()` operations take O(1) on average.

**Space Complexity:** O(n)  : In the worst case all elements are unique.
                              The HashSet will store all `n` elements.



# LeetCode 217. Contains Duplicate (Java)

---

## Approach 1: Brute Force

```java
class Solution {

    public boolean containsDuplicate(int[] nums) {

        // Compare every element with every other element
        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                // Duplicate found
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        // No duplicates found
        return false;
    }
}
```

**Time Complexity:** `O(n²)`  
**Space Complexity:** `O(1)`

---

## Approach 2: Sorting

```java
import java.util.Arrays;

class Solution {

    public boolean containsDuplicate(int[] nums) {

        // Sort the array
        Arrays.sort(nums);

        // Compare adjacent elements
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        // No duplicates found
        return false;
    }
}
```

**Time Complexity:** `O(n log n)`  
**Space Complexity:** `O(1)` *(Ignoring sorting implementation space)*

---

## Approach 3: HashSet ⭐ Optimal

```java
import java.util.HashSet;
import java.util.Set;

class Solution {

    public boolean containsDuplicate(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {

            // Duplicate found
            if (set.contains(num)) {
                return true;
            }

            // Add current element
            set.add(num);
        }

        // No duplicates found
        return false;
    }
}
```

**Time Complexity:** `O(n)`  
**Space Complexity:** `O(n)`

---

## Approach 4: HashMap

```java
import java.util.HashMap;
import java.util.Map;

class Solution {

    public boolean containsDuplicate(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            // Duplicate found
            if (map.containsKey(num)) {
                return true;
            }

            // Store frequency
            map.put(num, 1);
        }

        // No duplicates found
        return false;
    }
}
```

**Time Complexity:** `O(n)`  
**Space Complexity:** `O(n)`


---



       
