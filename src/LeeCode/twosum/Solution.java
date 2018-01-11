package LeeCode.twosum;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] tmp = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                tmp[1] = i;
                tmp[0] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return tmp;
    }
}