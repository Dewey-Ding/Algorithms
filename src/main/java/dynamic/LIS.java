package dynamic;

/**
 * 最长递归子序列
 *
 * @author dewey
 * @date 2018/10/14 21:51
 */
public class LIS {
    public static void main(String[] args) {
        int[] nums = new int[]{3,5,7,1,2,8};
        int[] result = getLIS(nums);
        for (int i = 0; i < result.length; i++) {
            if(result[i]==0){
                break;
            }
            System.out.print(result[i]+" ");
        }
    }

    /**
     * 找出数组最大子序列的长度（动态规划）
     *
     * @param nums
     * @return
     */
    public static int[] getLIS(int[] nums){
        int count = nums.length;
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i]=1;
        }

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j]<nums[i]&&result[j]+1>result[i]){
                    result[i]=result[j]+1;
                }
            }
        }

        return result;
    }
}
