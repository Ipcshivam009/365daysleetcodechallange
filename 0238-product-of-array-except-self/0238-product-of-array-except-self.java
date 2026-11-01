class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        //calcu prefix product
        int prefix = 1;

        for(int i=0; i<n; i++) {
            ans[i] = prefix;
            prefix *= nums[i];
        }
        //calcu suffix product
        int suffix = 1;
        
        for(int i=n-1; i>=0; i--) {
            ans[i] *= suffix;
            suffix *= nums[i];
        }
        return ans;
    }
}