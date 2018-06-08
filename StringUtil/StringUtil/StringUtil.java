package StringUtil;

public class StringUtil {
    
    
    /**
     * 将首字母大写
     * @param str
     * @return
     */
    public static String uplowerFirst(String str) {
        char[] cs=str.toCharArray();
        if(cs[0]>96) {
            cs[0]-=32;
        }
        return String.valueOf(cs);
    }
    
    /**
     * 删除掉str字符串的最后一个字符串
     * @param str
     * @return
     */
    public static String delLast(String str) {
        return str.substring(0,str.length() - 1);
    }
    /**
     * 删除掉str字符串的最后一个字符串
     * @param str
     * @return
     */
    public static StringBuffer delLast(StringBuffer str) {
        return str.deleteCharAt(str.length()-1);
    }
}
