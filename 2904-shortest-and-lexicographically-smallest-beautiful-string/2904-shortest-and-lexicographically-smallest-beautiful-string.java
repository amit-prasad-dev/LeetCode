class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        int left = 0;
        int ones = 0;

        String ans = "";

        for (int right = 0; right < n; right++) {
            // Add current character
            if (s.charAt(right) == '1') {
                ones++;
            }
            // Remove unnecessary characters
            while (ones > k || 
                   (left < right && s.charAt(left) == '0')) {

                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // We found a beautiful substring
            if (ones == k) {
                String current = s.substring(left, right + 1);

                // Shorter substring
                if (ans.isEmpty() ||
                    current.length() < ans.length()) {

                    ans = current;
                }
                // Same length → lexicographically smaller
                else if (current.length() == ans.length() &&
                         current.compareTo(ans) < 0) {

                    ans = current;
                }
            }
        }
        return ans;
    }
}