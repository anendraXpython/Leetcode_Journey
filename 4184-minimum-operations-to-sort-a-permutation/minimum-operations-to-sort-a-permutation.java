class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int k = nums[0];
        boolean ascending = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n) {
                ascending = false;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (ascending) {
            if (k == 0) {
                return 0;
            }

            ans = Math.min(n - k, k + 2);
        }

        int r = (n - 1 - nums[0] + n) % n;
        boolean descending = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != n - 1 - ((r + i) % n)) {
                descending = false;
                break;
            }
        }

        if (descending) {
            ans = Math.min(ans, 1 + Math.min(r, n - r));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
