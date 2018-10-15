package dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dewey
 * @date 2018/10/15 21:15
 */
public class LSS {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray_(nums));
    }

    /**
     * 动态规划求最大子序列和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums){
        //最大和
        int maxSum = 0;

        int tempSum = 0;
        for (int i = 0; i < nums.length; i++) {
            tempSum+=nums[i];
            if(tempSum>maxSum){
                maxSum=tempSum;
            }else if(tempSum<0){
                tempSum = 0;
            }
        }
        return maxSum;
    }


    /**
     * 返回最大和的序列（倒序）
     *
     * @param nums
     * @return
     */
    public static List<Integer> maxSubArray_(int[] nums){
        List<Integer> result = new ArrayList<>();
        int maxSum = 0;
        int end = 0;
        int tempSum = 0;
        for (int i = 0; i < nums.length; i++) {
            tempSum+=nums[i];
            if(tempSum>maxSum){
                maxSum=tempSum;
                end=i;
            }else if(tempSum<0){
                tempSum = 0;
            }
        }
        while(maxSum!=0){
            result.add(nums[end]);
            maxSum = maxSum - nums[end];
            end--;
        }
        return result;
    }
}
