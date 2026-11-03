class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        int n = intervals.size();
        sort(intervals.begin(),intervals.end(),[](vector<int>& a, vector<int>& b){
            if(a[1] != b[1]) return a[1] < b[1];
            else return a[0] < b[0];
        });
        int result = 0;
        vector<int> vec = intervals[0];
        for(int i=1; i<n; i++){
            vector<int> curr = intervals[i];
            if(curr[0] < vec[1]) result++;
            else{
                vec = curr;
            }
        }
        return result;
    }
};