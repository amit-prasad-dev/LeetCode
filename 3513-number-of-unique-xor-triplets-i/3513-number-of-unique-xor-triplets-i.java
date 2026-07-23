class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if(n<=2) return n;
        double x = Math.log(n)/Math.log(2);
        int power = (int)x;

        if(Math.pow(2, power) ==  n) {
            return n*2;
        } 
        double ans =  Math.pow(2, power+1);
        return (int) ans;
    }
}