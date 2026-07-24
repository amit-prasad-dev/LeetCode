class Solution {
    public boolean isAnagram(String s, String t) {
        // Step 1: Check Length
        if (s.length() != t.length()) {
            return false;
        }
        // Step 2:Frequency Array
        int[] freq = new int[26];
        
        // Step 3:Count character of first string
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        // Step 4:Decrease count using second string
        for (int i = 0; i < t.length(); i++) {
            freq[t.charAt(i) - 'a']--;
        }
        // Step 5:Check if all count are zero
        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}