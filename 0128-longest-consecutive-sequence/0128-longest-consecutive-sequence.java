class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int x : nums) 
        set.add(x);

        int max = 0;

        for(int x : set) {
            if(!set.contains(x - 1)) {
                int curr = x;
                int count = 1;

                while(set.contains(curr + 1)) {
                    curr++;
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}