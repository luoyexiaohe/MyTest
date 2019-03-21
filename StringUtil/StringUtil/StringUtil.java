package StringUtil;

import java.nio.ShortBuffer;

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
    /**
     * ��tablename�е��»��ߺ������ĸ��д
     * @param tableName
     * @return
     */
    public static String replace_(String tableName) {
    	return replace_(new StringBuffer(tableName));
    }
    /**
     * ��tablename�е��»��ߺ������ĸ��д
     * @param tableName
     * @return
     */
    public static String replace_(StringBuffer tableName) {
    	while(tableName.indexOf("_")>-1) {
    		
    		int index = tableName.indexOf("_");
    		tableName.deleteCharAt(index);
    		char cha = tableName.charAt(index);
    		if(cha>96) {
    			cha-=32;
    			tableName.replace(index, index+1, String.valueOf(cha));
            };
    	}
    	
    	return tableName.toString();
    }
}
