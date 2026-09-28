class Solution {
    public int maxDepth(String s) {
        int d=0, max=0;
        for(char c : s.toCharArray()) {
            if(c == '(') max = Math.max(max, ++d);
            else if(c == ')') d--;
        }
        return max;
    }
}