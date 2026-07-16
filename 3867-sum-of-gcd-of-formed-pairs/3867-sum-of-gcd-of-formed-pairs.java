import java.util.*;
class Solution {

    private int gcd(int a, int b){
        while (b != 0){
            int temp =  a % b;
            a = b;
            b = temp;
        }
        return a;
    } 

    public long gcdSum(int[] nums) {
        int n = nums.length;

        int[] prefixGcd = new int[n];
        int mx = 0;
        for(int i=0; i<n; i++){
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }

        // Sort the prefixGcd array
        Arrays.sort(prefixGcd);
        long ans = 0;

        // Two pointer for pairing smallest and largest
        int l = 0,
            r = n - 1;

        while(l < r ){
            ans += gcd(prefixGcd[l], prefixGcd[r]);
            l++;
            r--;
        }
        return ans;
    }
}