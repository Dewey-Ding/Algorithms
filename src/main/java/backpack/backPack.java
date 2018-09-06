package backpack;
/**
 * @author deweyding
 * 01背包问题
 */
public class backPack {
    public static void main(String ars[]){
        //从0开始以及for从1开始防止越界,方便计算
        int[] w = {0,2,2,6,5,4,};
        int[] v = {0,6,3,5,4,6};
        int row = 5;
        int column = 10;
        int totalMax = 0;
        int[][] totalV = new int[row+1][column+1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if(j>=w[i]){
                    //由于当前物品i只能放一次，即当次放入，所以这里应该是c[i-1]否则可能多次放入
                    //最优解持续向右下传递，理论上最后一个元素就是最优解（待证实）
                    totalV[i][j] = Math.max(totalV[i-1][j],v[i]+totalV[i-1][j-w[i]]);
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
