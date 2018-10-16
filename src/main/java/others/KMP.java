package others;

/**
 * KMP算法匹配字符串
 *
 * @Author dewey
 * @Date 2018/10/16 20:18
 */
public class KMP {
    public static void main(String[] args) {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        int[] result = getNext(pattern);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        System.out.println(isMatch(str,pattern));
    }

    /**
     * 判断字符串是否匹配
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean isMatch(String str,String pattern){
        int[] next = getNext(pattern);
        int i = 0;
        int j = 0;
        while(i<str.length()&&j<pattern.length()){
            if(j==-1||str.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if(j==pattern.length()){
            return true;
        }
        return false;
    }

    /**
     * 获取模式串的next数组
     *
     * @param pattern
     * @return
     */
    public static int[] getNext(String pattern){
        int length = pattern.length();
        int[] next = new int[length];
        next[0] = -1;
        int i = -1;
        int j = 0;
        while(j<length-1){
            if(i==-1||pattern.charAt(i)==pattern.charAt(j)){
                ++i;
                ++j;
                next[j] = i;
            }else{
                i = next[i];
            }
        }
        return next;
    }
}
