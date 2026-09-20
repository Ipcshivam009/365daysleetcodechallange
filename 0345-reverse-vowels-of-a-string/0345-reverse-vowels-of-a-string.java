class Solution {
    public String reverseVowels(String s) {
        char[] a = s.toCharArray();

        int l = 0, r = a.length - 1;

        while (l < r) {
            while (l < r && !isVowel(a[l])) l++;
            while (l < r && !isVowel(a[r])) r--;

            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;

            l++;
            r--;
        }

        return new String(a);
    }

    boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}