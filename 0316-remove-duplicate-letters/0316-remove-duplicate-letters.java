class Solution {
    public String removeDuplicateLetters(String s) {
        
        // Last time har character kis index par aa raha hai
        int[] last = new int[26];

        // Future me character milega ya nahi, ye yahi se pata chalega
        for(int i=0; i < s.length(); i++){
            last[s.charAt(i) - 'a'] = i;
        }

        // Taaki same character ko dubara answer me na dale
        boolean[] visited = new boolean[26];

        // Isi stack me hum apna answer store karenge
        StringBuilder stack = new StringBuilder();

        // String ko left se righ traverse karte hai
        for(int i = 0; i <s.length(); i++){
            char ch = s.charAt(i);

            // Agar ye character pehle hi answer me hai,
            // to ise dubara nahi lenge
            if(visited[ch - 'a']) {
                continue;
            }

            // Jab tak stack ka top current character se bada hai
            // aur wo future me doobara mil jayega
            // tab tak use hata do
            while (stack.length() > 0 &&
                   stack.charAt(stack.length() - 1) > ch &&
                   last[stack.charAt(stack.length() - 1) - 'a'] > i) {

                // Character answer se remove ho gaya 
                // isliye visited bhi fasle kar do
                visited[stack.charAt(stack.length() - 1) - 'a'] = false;

                // Bigger character koo remove kar do
                stack.deleteCharAt(stack.length() - 1);
            }

            // Ab current character ko answer me add kar do
            stack.append(ch);

            // Mark kar do ki ye character ab answer me aa chuak hai
            visited[ch - 'a'] = true;
        }

        // Stack me hi final smallest subsequence ban hui hai
        return stack.toString();
    }
}