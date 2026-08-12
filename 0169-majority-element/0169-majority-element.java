class Solution {
    public int majorityElement(int[] nums) {
        // BOYER-MOORE VOTING ALGORITHM

        int candidate = 0;
        int voteCount = 0;
        for (int num : nums) {

            if (voteCount == 0) {
                candidate = num;
            }
            
            if (candidate == num) {
                voteCount++;
            } else {
                voteCount--;
            }
        }
        return candidate;
    }
}