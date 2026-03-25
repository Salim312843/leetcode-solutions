import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Step 1: Count frequency                 
        for (int num : nums) {                                         
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create bucket array
        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        for (int key : map.keySet()) {           //  start filling buckets 
            int freq = map.get(key);             // get frequency

            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();          //Check if Bucket Exists
                }                                           
             bucket[freq].add(key);                                           ]
            } 


        // Step 3: Collect top k elements
        int[] result = new int[k];          //Create Result Array
        int index = 0;

        for (int i = bucket.length - 1; i >= 0 && index < k; i--) {           //Traverse Buckets
            if (bucket[i] != null) {               //Check if Bucket Exists
                for (int num : bucket[i]) {
                    result[index++] = num;

                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
