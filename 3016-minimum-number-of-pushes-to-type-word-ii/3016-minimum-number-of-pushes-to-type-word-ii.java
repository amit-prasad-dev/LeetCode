class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int freq[] = new int[26];

        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int index = 0,
            res = 0;
        
        for (int i = 25; i >= 0; i--) {
            res += (index / 8 + 1) * freq[i];
            index++;
        }
        return res;
    }
}