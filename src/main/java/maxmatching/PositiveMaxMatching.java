package maxmatching;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deweyding
 * @date ${time}
 */
public class PositiveMaxMatching {
    public static void main(String[] args) {
        String str1 = "whelloworldwtow";
        String[] dictionary = {"test", "hello", "world", "wto", "wc"};
        List<String> result = match(str1, dictionary);
        System.out.println(result.toString());
    }

    /**
     * 正向最大匹配
     * @param str1
     * @param dictionary
     * @return
     */
    public static List<String> match(String str1, String[] dictionary) {
        List<String> result = new ArrayList<>();
        while(str1.length()>0){
            int maxLength = str1.length();
            String temp = str1.substring(0,maxLength);
            int tempLength = temp.length();
            boolean flag = true;
            while(flag){
                for (int i = 0; i < dictionary.length; i++) {
                    if(temp.equals(dictionary[i])){
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    tempLength--;
                    if(tempLength>0) {
                        temp = temp.substring(0, tempLength);
                    }else{
                        break;
                    }
                }
            }
            if(tempLength<=0){
                tempLength++;
                System.out.println(String.format("非匹配词：%s ",str1.substring(0,tempLength)));
                str1 = str1.substring(tempLength,maxLength);
            }else{
                str1 = str1.substring(tempLength,maxLength);
                result.add(temp);
            }
        }
        return result;
    }

}
