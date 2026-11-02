class Solution {
    public int minMoves2(int[] nums) {
        // sort the array
        Arrays.sort(nums);

        // find median
        int median = nums[nums.length / 2];

        // calculate total moves
        int moves = 0;

        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}