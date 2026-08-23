class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;
        int left_question_mark = 0;
        int right_question_mark = 0;

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            if(i <= (n/2)-1) {
                if (ch == '?') left_question_mark++;
                else {
                    int x = ch - '0';
                    leftSum += x;
                }
            }
            else {
                if (ch == '?') right_question_mark++;
                else {
                    int x = ch - '0';
                    rightSum += x;
                }
            }
        }

        if (left_question_mark + right_question_mark == 1) return true;

        return 2*(leftSum - rightSum) != 9*(right_question_mark - left_question_mark);
    }
}