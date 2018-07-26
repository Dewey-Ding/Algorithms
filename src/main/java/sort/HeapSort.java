package sort;

/**
 * @author dingwei01
 */
public class HeapSort {
    public static void main(String[] args) {
        ArrayConstant.printArray(heapSort(ArrayConstant.NUMS));
    }

    /**
     * 最小堆排序,升序
     * O(nlogn)  O(1)  不稳定
     * @param nums
     * @return
     */
    public static int[] heapSort(int[] nums){
        if(nums==null||nums.length==0){
            return nums;
        }
        for (int i = nums.length/2-1; i >=0; i--) {
            heapSortHelper(nums,i,nums.length);
        }
        for (int i = nums.length-1; i > 0 ; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            heapSortHelper(nums,0,i);
        }
        return nums;
    }

    /**
     * 调整堆
     * @param nums
     * @param index
     * @param length
     */
    public static void heapSortHelper(int[] nums,int index,int length){
        int num = nums[index];
        for (int i = index*2+1; i < length; i = index*2+1) {
            if(i+1<length&&nums[i]<nums[i+1]){
                i++;
            }
            if(num<nums[i]){
                nums[index] = nums[i];
                index = i;
            }else{
                break;
            }
        }
        nums[index] = num;
    }
}
