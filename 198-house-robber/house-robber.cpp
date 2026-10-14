class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1)
            return nums[0];
            
        int rob1 = 0, rob2 = nums[0];
        int res;
        for (int i = 1; i < n; i++) {
            res = max(nums[i] + rob1, rob2);
            rob1 = rob2;
            rob2 = res;
        }
        return res;
    }
};