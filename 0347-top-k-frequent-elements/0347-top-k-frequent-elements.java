class Solution {
    public int[] topKFrequent(int[] nums, int k) {
         //Count freq
         HashMap<Integer, Integer> map = new HashMap<>();
         for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
         }
         //create min heap
         PriorityQueue<Integer>pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
         //maintain heap size k
         for(int num : map.keySet()) {
            pq.offer(num);
            if(pq.size()>k) {
                pq.poll();
            }
         }
         //store ans
         int[]ans = new int[k];
         for(int i=0; i<k; i++) {
            ans[i] = pq.poll();
         }
         return ans;
    }
}