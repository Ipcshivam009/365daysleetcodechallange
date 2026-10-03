class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, ans = 0;
        for(char c : s.toCharArray()) {
            if(c == '(') left++;
            else right++;

            if(left == right)
            ans = Math.max(ans, 2*right);
            if(right>left)
            left = right = 0;
        }

        left = right = 0;
        for(int i = s.length()-1; i>=0; i--) {
            if(s.charAt(i) == '(') left++;
            else right++;
            if(left == right)
            ans = Math.max(ans, 2*left);
            if(left>right)
            left = right = 0;
        }
        return ans;
    }
}