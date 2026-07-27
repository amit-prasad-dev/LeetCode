class Solution {
    public int maxProduct(int[] nums) {
        // T.C : O(n log n)
        // S.C : O(log n)
        int n = nums.length;
        Arrays.sort(nums);
        return (nums[n -1] - 1) * (nums[n - 2] - 1);
    }
}