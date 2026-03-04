# H1 Q) Given an array of strings strs, group the anagrams together. You can return the answer in any order. 

-What Is Given? = *An array of strings*
So input type is:
**String[]**

-*Group the anagrams together*
It will be:
**List<List<String>>**
                    That means:
                     A list
                     Inside it, multiple lists
                     Each inner list is one group

-We are asked to:
*Organize words into groups*
                We are not asked to check if two strings are anagrams/ return true or false...

-*You can return the answer in any order*
     **That means sorting output is not required.**

# H2 Understanding Constraints
- 1 <= strs.length <= 104   :  **Avoid O(n²)**
- 0 <= strs[i].length <= 100  : **Sorting is fine** [Sorting one word costs:O(k log k). where k ≤ 100. Since 100 is small]
- strs[i] consists of lowercase English letters.  : **Frequency array of size 26 works** [Only 26 possible characters]


# H1 Approaches to Solve the Problem
 Approach              |              Time complexity                            |              Space Complexity
 1) Brute Force        | **O(n² × k).**                                          |  **O(nk)**
 3)                    |   O(n2) and (What Happens Inside Each Comparison?)         To check if two words are anagrams, we may:   
 4)                    |  Method A: Sort both words  =  O(k log k) [worse]          Case A: Sort both words = O(k)
 5)                    |   Method B: Count characters  = O(k)                       Case B: Use frequency array = O(1)
 6)                    |  k = length of word
 7)                    |   Combine Everything  : O(n² × k)                          Space for Storing Output  = O(nk)       
 7)                    |   Combine Everything  : O(n² × k)                           n = number of words
 7)                    |   Combine Everything  : O(n² × k)                           k = average length
