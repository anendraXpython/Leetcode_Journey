class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> dict = new HashMap<>();

        for(int num : nums) {
            dict.put(num, dict.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> {
                if(a[0] != b[0])
                    return Integer.compare(a[0], b[0]);

                return Integer.compare(a[1], b[1]);
            });

        for(Map.Entry<Integer, Integer> entry : dict.entrySet()) {

            int[] newPair = {
                entry.getValue(),
                entry.getKey()
            };

            if(pq.size() < k)
                pq.offer(newPair);
            else {
                pq.offer(newPair);
                pq.poll();
            }
        }

        int[] result = new int[k];
        int j = 0;

        while(!pq.isEmpty()) {
            result[j++] = pq.poll()[1];
        }

        return result;
    }
}