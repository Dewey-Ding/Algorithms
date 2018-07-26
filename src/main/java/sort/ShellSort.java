package sort;

/**
 * @author dingwei01
 */
public class ShellSort {
    public static void main(String[] args) {
        ArrayConstant.printArray(shellSort(ArrayConstant.NUMS));
    }

    /**
     * 希尔排序，从小到大
     * nlogn-n*n O(1) 不稳定
     * @param nums
     * @return
     */
    public static int[] shellSort(int[] nums){
        if(nums==null||nums.length==0){
            return nums;
        }
        int spaceNum = nums.length%ArrayConstant.SPACING>1?nums.length/ArrayConstant.SPACING+1:nums.length/ArrayConstant.SPACING;
        int[] space = new int[spaceNum];
        for (int i = 0; i*ArrayConstant.SPACING+1 < nums.length; i++) {
            space[i] = i*ArrayConstant.SPACING+1;
        }
        for (int i = 0; i < space.length; i++) {
            int gap = space[i];
            for (int j = gap; j < nums.length; j++) {
                int num = nums[j];
                int k = j - gap;
                while (k>=0&&nums[k]>num){
                    nums[k+gap] = nums[k];
                    k = k - gap;
                }
                nums[k+gap] = num;
            }
        }
        return nums;
    }
}
