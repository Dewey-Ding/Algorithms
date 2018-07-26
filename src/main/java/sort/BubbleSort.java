package sort;

/**
 * @author dingwei01
 */
public class BubbleSort {
    public static void main(String[] args) {
        ArrayConstant.printArray(bubbleSort(ArrayConstant.NUMS));
    }

    /**
     * 冒泡排序，从小到大
     * O（n*n）  O（1）  稳定
     * @param nums
     * @return
     */
    public static int[] bubbleSort(int[] nums){
        if(nums==null||nums.length==0){
            return nums;
        }
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = 0; j < nums.length-1-i; j++) {
                if (nums[j]>nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }
}
