class Solution {
    public int numOfSubarrays(int[] arr) {
        long odd = 0;
        long even = 1;
        long ans = 0;

        long MOD = 1000000007;

        long prefixSum = 0;

        for(int num : arr) {
            prefixSum += num;

            if(prefixSum%2 == 0) {
                ans = (ans+odd)%MOD;
                even++;
            } else {
                ans = (ans+even)%MOD;
                odd++;
            }
        }
        return(int) ans;
    }
}