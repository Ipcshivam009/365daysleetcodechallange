class Solution {
    public String reverseParentheses(String s) {
        java.util.Stack<StringBuilder> st=new java.util.Stack<>();
        StringBuilder c=new StringBuilder();
        for(char x : s.toCharArray()) {
            if(x == '(') {st.push(c); c=new StringBuilder();}
            else if(x ==')') { c.reverse(); c = st.pop().append(c);}
            else c.append(x);
        }
        return c.toString();
    }
}