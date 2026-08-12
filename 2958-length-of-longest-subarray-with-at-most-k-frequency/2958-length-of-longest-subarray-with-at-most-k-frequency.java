class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < nums.length; right++) {
            // Add elements
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            // Check window is valid or not
            while(map.get(nums[right]) > k) {
                // Remove left element
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }
            // Calculate length of current window
            int currLen = right - left + 1;
            // Compare maximum length between old length and current length
            maxLength = Math.max(maxLength, currLen);
        }
        return maxLength;
    }
}