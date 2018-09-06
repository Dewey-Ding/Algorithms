package backpack;

/**
 * @author deweyding
 * 多重背包
 */
public class multiBackPack {
    public static void main(String[] args) {
        int[] w = {0,2,2,1};
        int[] v = {0,20,10,6};
        int[] c = {0,2,5,10};
        //也是背包容量
        int column = 8;
        int row = 3;
        int[][] totalV = new int[row+1][column+1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                //第i中物品有c[i]+1种取法
                int max = 0;
                int num = Math.min(c[i],j/w[i]);
                for (int k = 0; k <= num; k++) {
                    if(j>=k*w[i]) {
                        totalV[i][j] = Math.max(max, totalV[i-1][j-k*w[i]]+k*v[i]);
                        max = totalV[i][j];
                    }
                }
            }
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                System.out.print(" "+totalV[i][j]+" ");
            }
            System.out.println();
        }
    }
}
