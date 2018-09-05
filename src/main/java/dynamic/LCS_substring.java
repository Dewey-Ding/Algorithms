package dynamic;

import util.CommonUtil;

/**
 * @author deweyding
 * @date ${time}
 */
public class LCS_substring {
    public static void main(String[] args){
        String str1 = "abcdfghhhhh";
        String str2 = "bcdfgdafads";
        System.out.println(longestSubStr_Int(str1,str2));
        System.out.println(longestSubStr_String(str1,str2));
    }

    /**
     * 动态规划求最大子串问题
     * @param str1
     * @param str2
     * @return 最大子序列长度
     */
    public static int longestSubStr_Int(String str1,String str2){
        int max = 0;
        int row = str1.length()+1;
        int column = str2.length()+1;
        int[][] length = new int[row][column];
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    length[i][j] = length[i-1][j-1]+1;
                    max = max>length[i][j]?max:length[i][j];
                }
            }
        }
        CommonUtil.printArray(length);
        return max;
    }

    /**
     * 动态规划求最大子串问题
     * @param str1
     * @param str2
     * @return 最长子串
     */
    public static String longestSubStr_String(String str1,String str2){
        int max = 0;
        int endColumn = 0;
        int row = str1.length()+1;
        int column = str2.length()+1;
        int[][] length = new int[row][column];
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    length[i][j] = length[i-1][j-1]+1;
                    max = max>length[i][j]?max:length[i][j];
                    endColumn = j;
                }
            }
        }
        return str2.substring(endColumn-max,endColumn);
    }
}
