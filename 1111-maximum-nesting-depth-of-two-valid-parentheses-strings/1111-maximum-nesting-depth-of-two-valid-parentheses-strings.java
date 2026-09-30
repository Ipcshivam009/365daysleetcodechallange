class Solution {
    public int[] maxDepthAfterSplit(String s) {
        int[] ans = new int[s.length()];
        int d = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                ans[i] = d++ % 2;
            } else {
                ans[i] = --d % 2;
            }
        }
        return ans;
    }
}