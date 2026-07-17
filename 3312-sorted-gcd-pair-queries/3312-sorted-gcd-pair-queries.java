class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for(int num : nums)
            max = Math.max(max, num);

        int[] freq = new int[max + 1];
        for(int num : nums)
            freq[num]++;

        long[] divisibleCount = new long[max+ 1];
        for(int g = 1; g <= max; g++){
            for(int multiple = g; multiple <= max; multiple += g){
                divisibleCount[g] += freq[multiple];
            }
        } 

        long[] exactPairs =  new long[max + 1];
        for(int g = max; g >= 1; g--){
            long cnt = divisibleCount[g];
            exactPairs[g] = cnt * (cnt - 1)/2;
            for(int multiple = g*2; multiple <= max; multiple += g){
                exactPairs[g] -= exactPairs[multiple];
            }
        }

        long[] prefix = new long[max + 1];
        for(int g=1; g <= max; g++){
            prefix[g] = prefix[g - 1] + exactPairs[g];
        }

        int[] ans = new int[queries.length];
        for(int i =0; i < queries.length; i++){
            long target = queries[i]+1;
            int low = 1, high = max;
            while (low < high){
                int mid = low + (high - low) / 2;
                if (prefix[mid] >= target) high = mid;
                else low = mid + 1;
            }
            ans[i] = low;
        }
        return ans;
    }
}