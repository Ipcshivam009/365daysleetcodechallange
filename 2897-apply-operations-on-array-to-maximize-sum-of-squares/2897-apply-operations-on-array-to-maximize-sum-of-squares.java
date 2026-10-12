class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int mod = 1000000007;
        int[] bits = new int[31];

        for(int x : nums) {
            for(int b = 0; b < 31; b++) {
                if((x &(1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }
        long sum = 0;
        while(k --> 0) {
            long x = 0;
            for(int b = 30; b >= 0; b--) {
                if(bits[b] > 0) {
                    x += (1L << b);
                    bits[b]--;
                }
            }
            sum = (sum+x*x)%mod;
        }
        return(int) sum;
    }
}