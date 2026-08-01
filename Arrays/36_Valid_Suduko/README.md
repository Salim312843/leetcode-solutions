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

# LeetCode 36. Valid Sudoku (Java)

---

## Approach 1: Brute Force

### Logic
- For every filled cell, check its entire row, column, and 3×3 sub-box.
- If the same number appears again, the Sudoku is invalid.

```java
class Solution {

    public boolean isValidSudoku(char[][] board) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.')
                    continue;

                char current = board[row][col];

                // Check Row
                for (int j = 0; j < 9; j++) {

                    if (j != col && board[row][j] == current)
                        return false;
                }

                // Check Column
                for (int i = 0; i < 9; i++) {

                    if (i != row && board[i][col] == current)
                        return false;
                }

                // Check 3×3 Box
                int startRow = (row / 3) * 3;
                int startCol = (col / 3) * 3;

                for (int i = startRow; i < startRow + 3; i++) {

                    for (int j = startCol; j < startCol + 3; j++) {

                        if ((i != row || j != col) && board[i][j] == current)
                            return false;
                    }
                }
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(1)` *(The board size is fixed at 9×9, so at most a constant number of operations are performed.)*  
**Space Complexity:** `O(1)` *(No extra data structures are used.)*

---

## Approach 2: HashMap

### Logic
- Store every row, column, and box entry in separate HashMaps.
- If an entry already exists, the Sudoku is invalid.

```java
import java.util.*;

class Solution {

    public boolean isValidSudoku(char[][] board) {

        Map<String, Boolean> map = new HashMap<>();

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                char num = board[row][col];

                if (num == '.')
                    continue;

                String rowKey = "R" + row + num;
                String colKey = "C" + col + num;
                String boxKey = "B" + (row / 3) + (col / 3) + num;

                if (map.containsKey(rowKey) ||
                    map.containsKey(colKey) ||
                    map.containsKey(boxKey))
                    return false;

                map.put(rowKey, true);
                map.put(colKey, true);
                map.put(boxKey, true);
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(1)` *(Each of the 81 cells is processed once on a fixed-size board.)*  
**Space Complexity:** `O(1)` *(The HashMap stores information for a maximum of 81 cells, which is constant.)*

---

## Approach 3: HashSet ⭐ Optimal

### Logic
- Use three HashSets to track numbers in rows, columns, and 3×3 boxes.
- If a number is already present in any corresponding set, the Sudoku is invalid.

```java
import java.util.*;

class Solution {

    public boolean isValidSudoku(char[][] board) {

        Set<String> seen = new HashSet<>();

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                char num = board[row][col];

                if (num == '.')
                    continue;

                if (!seen.add(num + " in row " + row) ||
                    !seen.add(num + " in col " + col) ||
                    !seen.add(num + " in box " + row / 3 + "-" + col / 3))
                    return false;
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(1)` *(Each filled cell is inserted and checked once in a fixed-size Sudoku board.)*  
**Space Complexity:** `O(1)` *(The HashSet stores only a constant maximum number of entries.)*

---

## Approach 4: Boolean Arrays ⭐ Most Efficient

### Logic
- Maintain three boolean arrays for rows, columns, and boxes.
- Mark each digit as visited.
- If a digit is already marked, the Sudoku is invalid.

```java
class Solution {

    public boolean isValidSudoku(char[][] board) {

        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.')
                    continue;

                int num = board[row][col] - '1';
                int box = (row / 3) * 3 + (col / 3);

                if (rows[row][num] ||
                    cols[col][num] ||
                    boxes[box][num])
                    return false;

                rows[row][num] = true;
                cols[col][num] = true;
                boxes[box][num] = true;
            }
        }

        return true;
    }
}
```

**Time Complexity:** `O(1)` *(Every cell is visited exactly once on a fixed 9×9 board.)*  
**Space Complexity:** `O(1)` *(The boolean arrays have a fixed size independent of the input.)*

---

