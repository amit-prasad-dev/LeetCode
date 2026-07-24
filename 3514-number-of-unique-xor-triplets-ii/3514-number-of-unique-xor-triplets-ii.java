import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        // Only one element
        if (n == 1) {
            return 1;
        }

        // Store all unique XORs of pairs
        HashSet<Integer> pairXor = new HashSet<>();

        // Store all unique triplet XORs
        BitSet tripletXor = new BitSet();

        // Step 1: Find all unique pair XORs
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        // Step 2: XOR each pair result with every element
        for (int pair : pairXor) {
            for (int num : nums) {
                tripletXor.set(pair ^ num);
            }
        }

        // Number of unique XOR values
        return tripletXor.cardinality();
    }
}