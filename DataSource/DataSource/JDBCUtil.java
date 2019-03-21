package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class JDBCUtil{
    private static String url = null;  
    private static String name = null;  
    private static String user = null;  
    private static String password = null;  
    
    public static Connection conn = null;
    public static PreparedStatement pst = null;  

    /**
     * 实例化数据库连接
     */
    public static boolean initialize() {
        ResourceBundle rb = ResourceBundle.getBundle("cfg/cfg", Locale.ENGLISH);
        name = rb.getString("jdbcDriver");
        url = rb.getString("jdbcUrl");
        user = rb.getString("jdbcUser");
        password = rb.getString("jdbcPassword");
        boolean flag = true;
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
        } catch (Exception e) {  
            e.printStackTrace();  
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e1) {
            	flag = false;
                e1.printStackTrace();
            }
        } finally{
            
        }
        return flag;
    }
    public static void close() {
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    /**
     * 将resultSet结果转换成map
     * @param rs
     * @return
     * @throws SQLException
     */
    public static Map<String, String> getResultMap(ResultSet rs)  
            throws SQLException {  
        Map<String, String> hm = new HashMap<String, String>();  
        ResultSetMetaData rsmd = rs.getMetaData();  
        int count = rsmd.getColumnCount();  
        rs.next();
        for (int i = 1; i <= count; i++) {  
            String key = rsmd.getColumnLabel(i);  
            String value = rs.getString(i);  
            hm.put(key, value);  
        }  
        return hm;  
    }  
    /**
     * 将resultSet结果转换成List
     * @param rs
     * @return
     * @throws SQLException
     */
    public static List<Map<String, String>> getResultList(ResultSet rs)  
    		throws SQLException {  
    	ResultSetMetaData rsmd = rs.getMetaData();  
    	List<Map<String, String>> resultList = new LinkedList<Map<String, String>>(); 
    	int count = rsmd.getColumnCount();  
    	while(rs.next()) {
    		Map<String, String> hm = new HashMap<String, String>();  
    		for (int i = 1; i <= count; i++) {  
    			String key = rsmd.getColumnLabel(i);  
    			String value = rs.getString(i);  
    			hm.put(key, value);  
    		}  
    		resultList.add(hm);
    	}
    	return resultList;  
    }  
}
