class Solution {
    public String reversePrefix(String word, char ch) {
        int i = word.indexOf(ch);
        if(i == -1)return word;
        return new

        StringBuilder(word.substring(0, i+1)).reverse() + word.substring(i+1);
    }
}