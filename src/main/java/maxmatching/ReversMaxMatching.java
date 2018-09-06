package maxmatching;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deweyding
 * @date ${time}
 */
public class ReversMaxMatching {
    public static void main(String[] args) {
        String str1 = "helloworldwtowc";
        String[] dictionary = {"test", "hello", "world", "wto", "wc"};
        List<String> result = match(str1, dictionary);
        System.out.println(result.toString());
    }

    /**
     * 逆向最大匹配
     * @param str1
     * @param dictionary
     * @return
     */
    public static List<String> match(String str1,String[] dictionary){
        List<String> result = new ArrayList<>();
        while (str1.length()>0){
            int maxLength = str1.length();
            String temp = str1.substring(0,maxLength);
            int tempLength = temp.length();
            int start = 0;
            boolean flag = true;
            while(flag){
                for (int i = 0; i < dictionary.length; i++) {
                    if(temp.equals(dictionary[i])){
                        flag =  false;
                        break;
                    }
                }
                if(flag){
                    start++;
                    if(start<tempLength){
                        temp = temp.substring(1);
                    }else{
                        break;
                    }
                }
            }
            str1 = str1.substring(0,start);
            if(start>=tempLength){
                start--;
                System.out.println(String.format("非匹配词：%s ",str1.substring(start)));
            }else{
                result.add(temp);
            }
        }
        return result;
    }
}
