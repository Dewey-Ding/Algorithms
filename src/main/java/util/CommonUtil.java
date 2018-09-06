package util;

/**
 * @author deweyding
 * @date ${time}
 */
public class CommonUtil {
    /**
     * 打印二维数组
     * @param nums
     */
    public static void printArray(int[][] nums){
        if(nums==null||nums.length==0||nums[0].length==0){
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(nums[i][j]+" ");
            }
            System.out.println();
        }
    }

}
