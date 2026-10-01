class Solution {
    public boolean isValid(String s) {
        java.util.Stack<Character> st = new java.util.Stack<>();

        for(char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[')
            st.push(c);
            else {
                if (st.isEmpty()) return false;
                char x = st.pop();
                if ((c == ')' && x != '(') ||
                    (c == '}' && x != '{') ||
                    (c == ']' && x != '['))
                    return false;
            }
        }
        return st.isEmpty();
    }
}