This repository contains my solutions to LeetCode problems with detailed
approaches, complexity analysis, and explanations.

Language used: Java
Topics covered: Arrays, Hashing, Two Pointers, Sliding Window, Dynamic Programming.

---

# 242. Valid Anagram

## Problem Statement
Given two strings `s` and `t`, return **true** if `t` is an **anagram** of `s`, and **false** otherwise.

---
An **Anagram** is a word or phrase formed by rearranging the letters of another word, using **all the original letters exactly once**.

---

### Key Observation

If two strings are anagrams:

- They must have the **same length**.
- They must have the **same character frequency**.

---

## Constraints Analysis

1 ≤ s.length, t.length ≤ 5 * 10^4
s and t consist of lowercase English letters
```

### What these constraints tell us

- Strings can be **very large (up to 50,000 characters)**.
- An **O(n²)** solution would be too slow.
- Characters are limited to **26 lowercase letters**, which allows **frequency counting**.

````markdown
# LeetCode 242. Valid Anagram (Java)

---

## Approach 1: Brute Force

```java
class Solution {

    public boolean isAnagram(String s, String t) {

        // If lengths are different, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        boolean[] visited = new boolean[t.length()];

        // Match each character of s with an unused character in t
        for (int i = 0; i < s.length(); i++) {

            boolean found = false;

            for (int j = 0; j < t.length(); j++) {

                if (!visited[j] && s.charAt(i) == t.charAt(j)) {
                    visited[j] = true;
                    found = true;
                    break;
                }
            }

            // Character not found
            if (!found) {
                return false;
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(n²)`  
**Space Complexity:** `O(n)`

---

## Approach 2: Sorting

```java
import java.util.Arrays;

class Solution {

    public boolean isAnagram(String s, String t) {

        // If lengths are different, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // Sort both arrays
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        // Compare sorted arrays
        return Arrays.equals(sArray, tArray);
    }
}
```

**Time Complexity:** `O(n log n)`  
**Space Complexity:** `O(n)`

---

## Approach 3: HashMap (Frequency Count)

```java
import java.util.HashMap;
import java.util.Map;

class Solution {

    public boolean isAnagram(String s, String t) {

        // If lengths are different, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        // Count frequency of characters in s
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Reduce frequency using characters of t
        for (char ch : t.toCharArray()) {

            if (!map.containsKey(ch)) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);

            if (map.get(ch) == 0) {
                map.remove(ch);
            }
        }

        return map.isEmpty();
    }
}
```

**Time Complexity:** `O(n)`  
**Space Complexity:** `O(n)`

---

## Approach 4: Frequency Count (Character Count Array) ⭐ Optimal (Lowercase English Letters)

```java
class Solution {

    public boolean isAnagram(String s, String t) {

        // If lengths are different, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        // Increase count for s and decrease for t
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // Check if all frequencies are zero
        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(n)`  : We traverse both strings once, updating and checking the frequency array in a single pass.
**Space Complexity:** `O(1)` : The frequency array always has a fixed size of 26 (for lowercase English letters), so the extra space does not grow with the input size.

---

## Approach 5: Frequency Array (Two Pass)

```java
class Solution {

    public boolean isAnagram(String s, String t) {

        // If lengths are different, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        // Count characters in s
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Decrease count using t
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
        }

        // Verify all counts are zero
        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(n)`  
**Space Complexity:** `O(1)`
````

Instead of sorting, we can simply **count character occurrences** using a **frequency array**, which leads to the **O(n) optimal solution**.
