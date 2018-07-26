package sort;

/**
 * @author dingwei01
 */
public class InsertionSort {
    public static void main(String[] args) {
        ArrayConstant.printArray(insertionSort(ArrayConstant.NUMS));
    }

    /**
     * 插入排序
     * O（n*n）  O（1）  稳定
     * @param nums
     * @return
     */
    public static int[] insertionSort(int[] nums){
        if(nums==null||nums.length==0){
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int j = i-1;
            while (j>=0&&num<nums[j]){
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = num;
        }
        return nums;
    }
}
