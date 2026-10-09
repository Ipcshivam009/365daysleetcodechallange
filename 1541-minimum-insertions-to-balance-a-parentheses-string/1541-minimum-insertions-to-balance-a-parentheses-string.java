class Solution {
    public int minInsertions(String s) {
        int ans = 0, open = 0, n = s.length();
        for (int i=0; i<n;) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
                i++;
            } else {
                int close = 0;
                while (i < n && s.charAt(i) == ')') {
                    close++;
                    i++;
                }
                // ager odd hua to 1 insert ki need
                if (close % 2 == 1) {
                    ans++;
                    close++; 
                }
                int pairs = close / 2;
                if (open >= pairs) {
                    open -= pairs;
                } else {
                    ans += (pairs - open); 
                    open = 0;
                }
            }
        }
        ans += open * 2;
        return ans;
    }
}