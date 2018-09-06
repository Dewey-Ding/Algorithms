package backpack;

/**
 * @author deweyding
 * 完全背包
 */
public class fullBackPack {
    public static void main(String[] args) {
        //从0开始以及for从1开始防止越界,方便计算
        int[] w = {0,6,5,4,2,2,};
        int[] v = {0,5,4,6,6,3};
        int row = 5;
        int column = 10;
        int totalMax = 0;
        int[][] totalV = new int[row+1][column+1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if(j>=w[i]){
                    //可重复放入，所以用c[i]就行。
                    //最优解向右横向传递，每一横条j的最大值其实就是背包大小为j时的最大value。
                    totalV[i][j] = Math.max(totalV[i][j-1],totalV[i][j-w[i]]+v[i]);
                }else{
                    totalV[i][j] = totalV[i-1][j];
                }
                totalMax = Math.max(totalV[i][j],totalMax);
            }
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                System.out.print(" "+totalV[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(totalMax);
    }
}
