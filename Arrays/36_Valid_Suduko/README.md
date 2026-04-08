# 36. Valid Sudoku   [ level : medium ]

## Problem Statement

Determine if a `9 x 9` Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

1. Each row must contain the digits `1-9` without repetition.  
2. Each column must contain the digits `1-9` without repetition.  
3. Each of the nine `3 x 3` sub-boxes of the grid must contain the digits `1-9` without repetition.  

> Note:
- A Sudoku board (partially filled) could be valid but is not necessarily solvable.  
- Only the filled cells need to be validated.

---

## Example 1:
```
Input: board =
[["5","3",".",".","7",".",".",".","."],
 ["6",".",".","1","9","5",".",".","."],
 [".","9","8",".",".",".",".","6","."],
 ["8",".",".",".","6",".",".",".","3"],
 ["4",".",".","8",".","3",".",".","1"],
 ["7",".",".",".","2",".",".",".","6"],
 [".","6",".",".",".",".","2","8","."],
 [".",".",".","4","1","9",".",".","5"],
 [".",".",".",".","8",".",".","7","9"]]

Output: true
```

---

## Example 2:
```
Input: board =
[["8","3",".",".","7",".",".",".","."],
 ["6",".",".","1","9","5",".",".","."],
 [".","9","8",".",".",".",".","6","."],
 ["8",".",".",".","6",".",".",".","3"],
 ["4",".",".","8",".","3",".",".","1"],
 ["7",".",".",".","2",".",".",".","6"],
 [".","6",".",".",".",".","2","8","."],
 [".",".",".","4","1","9",".",".","5"],
 [".",".",".",".","8",".",".","7","9"]]

Output: false
```

---

## Constraints

```
board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'
```

---

## 🔍 Question Signals (What & Why)

### 1️⃣ "9 x 9 board"
👉 What:
- Fixed size grid

👉 Why:
- No need to worry about scalability
- Can use constant-size structures

---

### 2️⃣ "Only filled cells"
👉 What:
- Ignore '.' cells

👉 Why:
- We only validate existing numbers
- No need to solve Sudoku

---

### 3️⃣ "Each row must contain 1-9 without repetition"
👉 What:
- No duplicates in a row

👉 Why:
- Forces row-wise checking

---

### 4️⃣ "Each column must contain 1-9 without repetition"
👉 What:
- No duplicates in a column

👉 Why:
- Forces column-wise checking

---

### 5️⃣ "3 x 3 sub-boxes"
👉 What:
- Check 9 smaller grids

👉 Why:
- Adds another constraint layer
- Makes problem 2D + grouping based

---

### 6️⃣ "Not necessarily solvable"
👉 What:
- Don't try to complete Sudoku

👉 Why:
- Only validation problem
- Avoid backtracking/recursion

---

### 7️⃣ Limited digits (1-9)
👉 What:
- Only 9 possible values

👉 Why:
- HashSet / boolean array works efficiently

---

# 🎯 Final Thinking Trigger

👉 Whenever you see:
```
Row + Column + Subgrid validation
```

Think:
```
Use HashSet / tracking for each row, column, and box
```
