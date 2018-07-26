package sort;

/**
 * @author dingwei01
 */
public class QuickSort {
    public static void main(String[] args) {
        ArrayConstant.printArray(quickSort(ArrayConstant.NUMS));
    }

    /**
     * 快速排序，从小到大
     * 0(nlogn) O(logn)-O(n)  不稳定
     * @param nums
     * @return
     */
    public static int[] quickSort(int[] nums){
        if(nums==null||nums.length==0){
            return nums;
        }
        quickSortHelper(nums,0,nums.length-1);
        return nums;
    }

    /**
     * 递归排序左右两边，取第一个为pivot
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSortHelper(int[] nums,int left,int right){
        if(left>=right){
            return;
        }
        int pivot_index = left;
        int i = left+1,j = right;
        while(i<j){
            while (i<right&&nums[i]<nums[pivot_index]){
                i++;
            }
            while (j>left&&nums[j]>nums[pivot_index]){
                j--;
            }
            if(j>i){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[pivot_index];
        nums[pivot_index] = nums[j];
        nums[j] = temp;
        quickSortHelper(nums,left,j-1);
        quickSortHelper(nums,j+1,right);
    }
}
