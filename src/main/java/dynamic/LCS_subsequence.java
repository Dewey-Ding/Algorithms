package dynamic;

import util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deweyding
 * @date ${time}
 */
public class LCS_subsequence {
    public static void main(String[] args) {
        String str1 = "ABCBDAB";
        String str2 = "BDCABA";
        System.out.println(longestCommonSeq_Int(str1,str2));
        List<String> result = longestCommonSeq_String(str1,str2);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    /**
     * 动态规划求两个字符串的最大子序列
     * @param str1
     * @param str2
     * @return
     */
    public static int longestCommonSeq_Int(String str1,String str2){
        int row = str1.length()+1;
        int column = str2.length()+1;
        int max = 0;
        int[][] length = new int[row][column];
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    length[i][j] = length[i-1][j-1]+1;
                    max = max>length[i][j]?max:length[i][j];
                }else{
                    length[i][j] = length[i-1][j]>length[i][j-1]?length[i-1][j]:length[i][j-1];
                }
            }
        }
        CommonUtil.printArray(length);
        return max;
    }

    /**
     * 动态规划求两个字符串的最大子序列
     * @param str1
     * @param str2
     * @return
     */
    public static List<String> longestCommonSeq_String(String str1, String str2){
        int row = str1.length()+1;
        int column = str2.length()+1;
        int[][] length = new int[row][column];
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    length[i][j] = length[i-1][j-1]+1;
                }else{
                    length[i][j] = length[i-1][j]>length[i][j-1]?length[i-1][j]:length[i][j-1];
                }
            }
        }

        //找所有最大子序列
        List<String> result = new ArrayList<>();
        Helper(result,row-1,column-1,str1,str2,new StringBuilder(),length);
        return result;

    }

    /**
     * 递归获取所有的最长公共子序列
     * @param result
     * @param row
     * @param column
     * @param str1
     * @param str2
     * @param temp
     * @param length
     */
    public static void Helper(List<String> result,int row,int column,String str1,String str2,StringBuilder temp,int[][] length){
        if(row<=0||column<=0){
            String string = temp.reverse().toString();
            result.add(string);
            //反转回来防止后续计算出现错误
            temp.reverse();
        }else{
            if(str1.charAt(row-1)==str2.charAt(column-1)){
                temp.append(str1.charAt(row-1));
                Helper(result,row-1,column-1,str1,str2,temp,length);
                temp.deleteCharAt(temp.toString().length()-1);
            }else{
                if(length[row-1][column]>length[row][column-1]){
                    Helper(result,row-1,column,str1,str2,temp,length);
                }else if(length[row-1][column]<length[row][column-1]){
                    Helper(result,row,column-1,str1,str2,temp,length);
                }else{
                    Helper(result,row-1,column,str1,str2,temp,length);
                    Helper(result,row,column-1,str1,str2,temp,length);
                }
            }
        }
    }
}
