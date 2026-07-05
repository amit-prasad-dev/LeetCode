class Solution {
    public boolean palindromeHelper(int i, int j, String s){
        while(i < j){
            if (s.charAt(i) != s.charAt(j)){
                return false;
            }
            i = i + 1;
            j = j - 1;
        }
        return true;
    }

    // T.C : O(n) , S.C : O(1)
    public boolean validPalindrome(String s) {
        int i = 0,
            j = s.length()-1;
        while(i < j){
            char left = s.charAt(i),
                right = s.charAt(j);

            if (left != right){
                return palindromeHelper(i+1, j, s) || palindromeHelper(i, j-1, s);
            } else {
                i = i + 1;
                j = j - 1;
            }
        }
        return true;
    }
}