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

### Implication

| Approach | Feasibility |
|--------|--------|
| Brute Force | Works but very slow |
| Sorting | Acceptable |
| Frequency Count | Best solution |

---

# Approaches

---

## 1️⃣ Brute Force Approach

### Idea

- For every character in string `s`, search for the same character in string `t`.
- If found, remove it from `t`.
- Continue until all characters are matched.

### Algorithm

1. If lengths are different → return `false`.
2. For each character in `s`:
3. Search for the character in `t`.
4. If not found → return `false`.
5. Otherwise remove that character from `t`.
6. Continue until all characters are processed.

### Time Complexity

 O(n²)
For each character in `s`, we search through `t`.
Total operations ≈ `n × n`.

### Space Complexity

O(1)
Only a few variables are used.

### Limitations

- Very slow for large strings.
- Repeated searching makes it inefficient.

---

## 2️⃣ Sorting Approach

### Idea

If two strings are anagrams, **sorting both strings will produce the same result**.

### Algorithm

1. If lengths differ → return `false`.
2. Convert both strings to character arrays.
3. Sort both arrays.
4. Compare the sorted arrays.

### Time Complexity

O(n log n)
Sorting each string takes **O(n log n)**.

### Space Complexity

O(n)
Extra space is required for character arrays.

### Limitations

- Sorting is slower than frequency counting.
- Not the most efficient approach for very large strings.

---

## 3️⃣ Frequency Count Approach (Optimal) 

### Idea

- Count the frequency of each character in string `s`.
- Decrease the frequency while scanning string `t`.
- If any frequency becomes negative → not an anagram.

Since there are only **26 lowercase letters**, we can use a **frequency array**.

### Algorithm

1. If lengths differ → return `false`.
2. Create an integer array of size `26`.
3. Traverse string `s` and increase frequency.
4. Traverse string `t` and decrease frequency.
5. If any value becomes negative → return `false`.
6. If all frequencies become zero → strings are anagrams.

### Time Complexity

O(n)

We scan both strings once.

### Space Complexity

O(1)

Only a **fixed array of size 26** is used.

### Limitations

- Works best when characters are limited (like lowercase letters).
- For Unicode characters, a **HashMap** may be required instead.

---

# Key Insight

The main idea is:

```
Two strings are anagrams if their character frequencies are the same.
```

Instead of sorting, we can simply **count character occurrences** using a **frequency array**, which leads to the **O(n) optimal solution**.
