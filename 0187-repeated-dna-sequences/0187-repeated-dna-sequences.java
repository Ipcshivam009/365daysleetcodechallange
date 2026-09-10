class Solution {
    public List<String> findRepeatedDnaSequences(String s) {

        HashMap<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();

        for (int i = 0; i <= s.length() - 10; i++) {

            String sub = s.substring(i, i + 10);

            map.put(sub, map.getOrDefault(sub, 0) + 1);

            if (map.get(sub) == 2) {
                ans.add(sub);
            }
        }

        return ans;
    }
}