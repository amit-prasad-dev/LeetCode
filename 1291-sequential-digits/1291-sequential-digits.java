class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        // Base sequential digits string
        String s = "123456789";
        // number of digits in low 
        int minLen = String.valueOf(low).length();
        // number of digits in high 
        int maxLen = String.valueOf(high).length();

        // We will try every possible length
        for(int len=minLen; len <= maxLen; len++){
            // Generate all substring of current length
            for(int i=0; i+len <= 9; i++){
                // Convert substring into Integer
                int num = Integer.parseInt(s.substring(i, i+len));
                // Check whether it lies inside the range
                if(num>=low && num<=high)
                    ans.add(num);
            }
        }
        return ans;
    }
}