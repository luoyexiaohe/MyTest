package StringUtil;

public class StringUtil {
    
    
    /**
     * ������ĸ��д
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
     * ɾ����str�ַ��������һ���ַ���
     * @param str
     * @return
     */
    public static String delLast(String str) {
        return str.substring(0,str.length() - 1);
    }
    /**
     * ɾ����str�ַ��������һ���ַ���
     * @param str
     * @return
     */
    public static StringBuffer delLast(StringBuffer str) {
        return str.deleteCharAt(str.length()-1);
    }
}