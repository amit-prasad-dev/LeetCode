class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = 0;
        int sumEven = 0;

        for(int i=1; i<=n; i++){
            sumOdd += (n * i);
            sumEven += (n * i - 1);
        }
        return gcd(sumOdd, sumEven);
    }

    private int gcd(int a, int b){
        while (b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}