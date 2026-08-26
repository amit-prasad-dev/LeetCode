class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        ArrayList<Integer> ones = new ArrayList<>();
        // Store positions of all 1's
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }
        // Not enough 1's
        if (ones.size() < k) {
            return "";
        }
        String ans = "";
        // Check every group of k consecutive 1's
        for (int i = 0; i <= ones.size() - k; i++) {

            int start = ones.get(i);
            int end = ones.get(i + k - 1);
            String current = s.substring(start, end + 1);
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
        return ans;
    }
}