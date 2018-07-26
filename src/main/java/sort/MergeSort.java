package sort;

/**
 * @author dingwei01
 */
public class MergeSort {
    public static void main(String[] args) {
        ArrayConstant.printArray(mergeSort(ArrayConstant.NUMS));
    }

    /**
     * 归并排序，从小到大
     * nlogn O(n) 稳定
     * @param nums
     * @return
     */
    public static int[] mergeSort(int[] nums){
        if(nums==null||nums.length==0){
            return nums;
        }
        int[] temps = new int[nums.length];
        mergeSortHelper(0,nums.length-1,nums,temps);
        return nums;
    }

    /**
     * 递归调用
     * @param left
     * @param right
     * @param nums
     * @param temps
     */
    public static void mergeSortHelper(int left,int right,int[] nums,int[] temps){
        if (left<right){
            int mid = (right+left)/2;
            mergeSortHelper(left,mid,nums,temps);
            mergeSortHelper(mid+1,right,nums,temps);
            merge(left,mid,right,nums,temps);
        }
    }

    /**
     * 合并
     * @param left
     * @param mid
     * @param right
     * @param nums
     * @param temps
     */
    public static void merge(int left,int mid,int right,int[] nums,int[] temps){
        int i = left;
        int j = mid+1;
        int tempStart = left;
        int k = left;
        while(i<=mid&&j<=right){
            if(nums[i]<=nums[j]){
                temps[k++] = nums[i++];
            }else{
                temps[k++] = nums[j++];
            }
        }
        while (i<=mid){
            temps[k++] = nums[i++];
        }
        while (j<=right){
            temps[k++] = nums[j++];
        }
        for (int l = left; l <= right; l++) {
            nums[l] = temps[tempStart++];
        }
    }
}
