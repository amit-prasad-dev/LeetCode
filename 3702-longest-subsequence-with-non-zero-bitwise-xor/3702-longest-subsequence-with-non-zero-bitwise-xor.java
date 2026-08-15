class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean hasNonZero = false;
        // Calculate xor
        for (int num : nums) {
            xor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        // Check xor 0 or 1
        if (xor != 0) return nums.length;

        if (hasNonZero) return nums.length - 1;

        return 0;

        // T.C ----> O(N)
        // S.C ----> O(1)
    }
}