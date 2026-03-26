

# 271. Encode and Decode Strings [ level : Medium ]

## Problem Statement

Design an algorithm to encode a list of strings into a single string and decode it back to the original list of strings.

Implement the `encode` and `decode` methods:

- `encode(List<String> strs)` → Encodes a list of strings into a single string.
- `decode(String s)` → Decodes the encoded string back to the original list.

### Note:
- The encoded string should be reversible.
- The solution should handle **any characters**, including special characters.

---

# Breakdown of the Question (How to Think)

Key things to understand:

- We need to **convert multiple strings into one string**.
- Later we must **recover the exact original list**.
- Strings may contain **any character**, including:
  - `#`, `/`, spaces, digits, etc.

### Core Challenge

How do we **separate strings safely** during decoding?

### Key Observation

We must store **extra information** so that decoding becomes possible.

👉 The most important idea:

```
Store length of each string before the string itself
```

Example:

```
["neet", "code"] → "4#neet4#code"
```

Now during decoding:
- Read `4` → take next 4 characters → "neet"
- Read next `4` → take next 4 characters → "code"

---

# Constraints Analysis

Typical constraints:

```
1 ≤ strs.length ≤ 200
0 ≤ strs[i].length ≤ 200
strs[i] contains any ASCII characters
```

### What these constraints tell us

- Strings may contain **any character** → simple delimiters may fail.
- We must ensure **no ambiguity during decoding**.
- Efficient string building is required.

### Implication

| Approach | Feasibility |
|--------|--------|
| Simple delimiter | Unsafe |
| Escape characters | Complex |
| Length + delimiter | Best solution |

---

# Approaches

---

## 1️⃣ Simple Delimiter Approach

### Idea

- Join all strings using a delimiter like `#`.

Example:

```
["ab", "c#d"] → "ab#c#d"
```

### Problem

During decoding:

- We cannot distinguish between:
  - delimiter `#`
  - actual `#` inside string

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(n)
```

### Limitations

- Fails when strings contain delimiter characters.
- Not reliable.

---

## 2️⃣ Escape Character Approach

### Idea

- Replace delimiter inside strings with escape sequence.

Example:

```
"#" → "##"
Input: ["ab#c", "d"] after encoding becomes "ab##c#d"
```

### Algorithm

1. Replace special characters.
2. Join using delimiter.
3. While decoding, reverse the escaping.

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(n)
```

### Limitations

- Complex implementation.
- Error-prone.
- Hard to maintain.

---

## 3️⃣ Length + Delimiter Approach (Optimal) ⭐

### Idea

- Store **length of string + delimiter + actual string**.

Example:

```
["leet","code"] → "4#leet4#code"
```

### Why this works

Even if string contains `#`, it doesn’t matter because:

- We first read the **length**
- Then we extract exactly that many characters

### Algorithm (Encode)

1. Initialize empty result string.
2. For each string:
   - Append `length + '#' + string`
3. Return result.

### Algorithm (Decode)

1. Initialize empty list.
2. Traverse the encoded string:
   - Read characters until `#` → this is length
   - Convert length to integer
   - Extract next `length` characters
   - Add to result list
3. Repeat until end.

### Time Complexity

```
O(n)
```

Reason:

- Each character is processed once.

### Space Complexity

```
O(n)
```

Encoded string and output list require space.

### Limitations

- Slightly more logic required.
- Needs careful parsing.

---

# Final Comparison

| Approach | Time Complexity | Space Complexity | Notes |
|--------|--------|--------|--------|
| Simple Delimiter | O(n) | O(n) | Fails for special characters |
| Escape Character | O(n) | O(n) | Complex and error-prone |
| Length + Delimiter (Optimal) | O(n) | O(n) | Best and reliable |

---

# Key Insight

The most important idea is:

```
Store length of each string before the string itself
```

This removes ambiguity and ensures correct decoding.

👉 Instead of relying on separators, we rely on **exact length extraction**, which makes the solution **robust and optimal**.
