class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
         //find maximum element
         for(int num : nums) {
            min = Math.min(min, num);
         }
         int moves = 0;

         //calcu total moves
         for(int num : nums) {
            moves += num-min;
         }
         return moves;
    }
}