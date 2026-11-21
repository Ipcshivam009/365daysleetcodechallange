class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        ArrayList<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        // Build tree
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] prev = new int[n];

        // DFS
        dfs(0, tree, s, last, prev, ans, parent);

        return ans;
    }
    void dfs(int u, ArrayList<Integer>[] tree,
             String s, int[] last, int[] prev,
             int[] ans, int[] parent) {

        int c = s.charAt(u) - 'a';

        prev[u] = last[c];
        last[c] = u;

        for (int v : tree[u]) {
            dfs(v, tree, s, last, prev, ans, parent);
        }
        last[c] = prev[u];

        int p = prev[u] != -1 ? prev[u] : parent[u];

        if (p != -1) {
            ans[p] += ans[u];
        }
    }
}