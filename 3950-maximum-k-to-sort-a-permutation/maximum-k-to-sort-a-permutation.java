class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int ans = -1;
        boolean alreadySorted = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                alreadySorted = false;
                ans &= nums[i];
            }
        }

        return alreadySorted ? 0 : ans;
    }
}