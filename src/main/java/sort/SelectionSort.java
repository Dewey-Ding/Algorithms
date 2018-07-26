package sort;

/**
 * @author dingwei01
 */
public class SelectionSort {
    public static void main(String[] args) {
        ArrayConstant.printArray(selectionSort(ArrayConstant.NUMS));
    }

    /**
     * 选择排序，从小到大
     * O（n*n）  O（1） 不稳定
     * @param nums
     * @return
     */
    public static int[] selectionSort(int[] nums){
        if(nums==null||nums.length==0){
            return nums;
        }
        for (int i = 0; i < nums.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < nums.length; j++) {
                if(nums[j]<nums[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex!=i){
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
        return nums;
    }
}
