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

# H2 HashMap + Sorting Approach
In this approach, the main idea is to use sorting to identify anagrams and HashMap to group them.

Two strings are anagrams if they contain the same characters with the same frequency. When the characters of an anagram strings are sorted alphabetically, they produce the same sorted string.
For example:
eat → aet
tea → aet
ate → aet
Since the sorted result is identical, these words belong to the same group

**Working Principle**
1)Traverse each string in the given array.
2)Convert the string into a character array.
3)Sort the character array.
4)Convert the sorted array back into a string.
5)Use this sorted string as a key in a HashMap.
6)Store the original string in the list corresponding to that key.
7)After processing all strings, return all values of the HashMap


# H2 [Optimized Approach]: HashMap + Character Frequency Count
This approach is based on the observation that anagrams contain the same characters with identical frequencies. Therefore, if two strings have the same frequency distribution of characters, they must belong to the same anagram group.

**Working Principle**
1)Anagrams have the same characters with the same frequency.
2)Create a frequency array of size 26 for each string.
3)Count occurrences of each character (a–z) in the string.
4)Convert the frequency array into a unique key string.
5)Use a HashMap where key = frequency pattern.
6)Store all strings with the same key in the same list.
7)Finally, return all the lists from the HashMap as grouped anagrams.
