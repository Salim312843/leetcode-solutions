import java.util.*;

class Solution {
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        answer[0] = 1;  // no left elements
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];  // prefix product
        }

        int suffix = 1;  // no right elements
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;   // combine prefix & suffix
            suffix *= nums[i];     // update suffix
        }

        return answer;
    }
}


# Core Java — Loops

> **Quick Interview Revision Notes | Core Java | Freshers & Placements**

---

## 1. What is a Loop?

A **loop** is a control-flow statement used to execute a block of code repeatedly as long as a specified condition is satisfied.

### Why are loops used?

* To avoid repetitive code
* To reduce code duplication
* To automate repetitive tasks
* To iterate through arrays and collections
* To solve mathematical and logical problems

---

## 2. Types of Loops in Java

Java provides the following looping constructs:

1. `for` loop
2. `while` loop
3. `do-while` loop
4. Enhanced `for` loop (`for-each`)

---

## 3. `for` Loop

The `for` loop is an **entry-controlled loop** generally used for count-based iteration.

### Syntax

```java
for (initialization; condition; update) {
    // statements
}
```

### Important Points

* Initialization executes **once**.
* Condition is checked **before every iteration**.
* Update executes **after the loop body**.
* It can execute **zero or more times**.
* Initialization, condition, and update expressions are optional.

---

## 4. `while` Loop

The `while` loop is an **entry-controlled loop**.

### Syntax

```java
while (condition) {
    // statements
}
```

### Important Points

* Condition is checked **before** the body.
* It can execute **zero or more times**.
* Useful when the number of iterations is not known beforehand.
* The loop variable generally needs to be updated manually.

---

## 5. `do-while` Loop

The `do-while` loop is an **exit-controlled loop**.

### Syntax

```java
do {
    // statements
} while (condition);
```

### Important Points

* Body executes **before** the condition is checked.
* Executes **at least once**.
* Condition is checked after every iteration.
* The semicolon after `while(condition)` is mandatory.

---

## 6. Enhanced `for` Loop

The enhanced `for` loop, also called the **for-each loop**, is mainly used to traverse arrays and collections.

### Syntax

```java
for (dataType variable : arrayOrCollection) {
    // statements
}
```

### Important Points

* Simplifies sequential traversal.
* No explicit index is required.
* Does not directly provide the index.
* Best when you only need the elements.
* Use a traditional `for` loop when you need index-based control.

---

## 7. Entry-Controlled vs Exit-Controlled Loops

| Type             | Loops          | Condition Check | Minimum Execution |
| ---------------- | -------------- | --------------- | ----------------- |
| Entry-controlled | `for`, `while` | Before body     | 0                 |
| Exit-controlled  | `do-while`     | After body      | 1                 |

### Remember

```text
for / while  → condition first
do-while     → body first
```

---

## 8. `for` vs `while` vs `do-while`

| Feature           | `for`             | `while`             | `do-while`          |
| ----------------- | ----------------- | ------------------- | ------------------- |
| Type              | Entry-controlled  | Entry-controlled    | Exit-controlled     |
| Minimum execution | 0                 | 0                   | 1                   |
| Condition         | Before body       | Before body         | After body          |
| Common use        | Count-based       | Condition-based     | Must execute once   |
| Update location   | Usually in header | Usually inside body | Usually inside body |

---

## 9. Nested Loops

A **nested loop** is a loop inside another loop.

### Important Points

* Inner loop executes for each iteration of the outer loop.
* Commonly used with:

  * Patterns
  * 2D arrays
  * Matrices
  * Combinations
* If both loops run `n` times, the basic time complexity is generally **O(n²)**.

---

## 10. Infinite Loop

An **infinite loop** is a loop that never terminates because its condition never becomes false.

### Common Causes

* Forgetting to update the loop variable
* Incorrect condition
* Condition always being `true`
* Incorrect update expression

### Important Point

An infinite loop can be intentional, but accidental infinite loops are usually programming errors.

---

## 11. `break` Statement

`break` is used to **terminate the nearest enclosing loop or switch** immediately.

### Important Points

* Stops the loop completely.
* No further iterations are executed.
* Unlabeled `break` affects only the nearest loop.
* Can be used inside loops and `switch`.

### Remember

```text
break → terminate
```

---

## 12. `continue` Statement

`continue` is used to **skip the remaining statements of the current iteration** and proceed with the next iteration.

### Important Points

* Does not terminate the loop.
* Only skips the current iteration.
* Can be used only inside loops.
* In a `for` loop, the update expression executes after `continue`.

### Remember

```text
continue → skip current iteration
```

---

## 13. `break` vs `continue` vs `return`

| Statement  | Effect                       |
| ---------- | ---------------------------- |
| `continue` | Skips current iteration      |
| `break`    | Terminates current loop      |
| `return`   | Terminates the entire method |

### Easy Memory Trick

```text
continue → iteration
break    → loop
return   → method
```

---

## 14. Labeled `break`

A labeled `break` is used to terminate a **specific labeled enclosing statement or loop**.

### Syntax

```java
outer:
for (...) {
    for (...) {
        break outer;
    }
}
```

### Important Point

Useful when you need to exit an outer loop directly from an inner loop.

---

## 15. Labeled `continue`

A labeled `continue` skips the current iteration of a **specific labeled loop**.

### Syntax

```java
outer:
for (...) {
    for (...) {
        continue outer;
    }
}
```

### Important Point

It allows an inner loop to continue the next iteration of an outer loop.

---

## 16. Loop Control Variable

A **loop control variable** determines how the loop progresses.

It is commonly:

* Initialized before or at the beginning of the loop.
* Checked in the condition.
* Updated during each iteration.

### Important

Incorrect initialization, condition, or update can result in:

* Infinite loops
* Missing iterations
* Extra iterations
* Incorrect output

---

## 17. Empty Loop

A loop can have an empty body.

This can happen intentionally or accidentally.

```java
for (int i = 0; i < 5; i++);
```

The semicolon represents an **empty statement**.

### Interview Trap

Be careful with an accidental semicolon immediately after a loop.

---

## 18. Optional Parts of `for` Loop

All three parts of a `for` loop are optional:

```java
for (initialization; condition; update)
```

Therefore, the following is valid:

```java
for (;;) {
}
```

This creates an **infinite loop**.

### Important

The two semicolons are still required.

---

## 19. Scope of Loop Variables

A variable declared inside the `for` loop initialization generally has scope limited to that loop.

```java
for (int i = 0; i < 5; i++) {
    // i is accessible here
}
```

The variable `i` cannot normally be accessed after the loop.

---

## 20. Loop Selection — Quick Rule

Use:

### `for`

When you have a clear loop counter or iteration structure.

### `while`

When repetition primarily depends on a condition.

### `do-while`

When the body must execute at least once.

### Enhanced `for`

When you simply want to traverse elements of an array or collection.

---

# ⭐ Most Important Interview Points

Before an interview, make sure you can explain these without hesitation:

* Definition of a loop
* Types of loops in Java
* `for` loop execution
* `while` loop
* `do-while` loop
* Enhanced `for` loop
* `for` vs `while`
* `while` vs `do-while`
* Entry-controlled vs exit-controlled loops
* Nested loops
* Infinite loops
* `break`
* `continue`
* `break` vs `continue`
* `break` vs `return`
* Labeled `break`
* Labeled `continue`
* Loop variable
* Empty loop / accidental semicolon
* Optional parts of `for` loop
* Loop variable scope

---

# 🔥 Quick Revision

```text
for       → Entry-controlled
while     → Entry-controlled
do-while  → Exit-controlled

for/while → Can execute 0 times
do-while  → Executes at least once

break     → Terminates loop
continue  → Skips current iteration
return    → Terminates method

Nested loop → Loop inside another loop
Infinite loop → Loop that doesn't terminate

for-each → Simple traversal of arrays/collections
```

---

> **Interview Focus:** For fresher interviews, give special attention to **loop execution order, `break` vs `continue`, nested loops, infinite loops, `for` vs `while`, `do-while`, enhanced `for`, and output-based questions.**
