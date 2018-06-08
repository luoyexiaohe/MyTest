package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class JDBCUtil{
    private static String url = null;  
    private static String name = null;  
    private static String user = null;  
    private static String password = null;  
    
    public static Connection conn = null;
    public static PreparedStatement pst = null;  

    /**
     * ʵ�������ݿ�����
     */
    public JDBCUtil() {
        ResourceBundle rb = ResourceBundle.getBundle("cfg/cfg", Locale.ENGLISH);
        name = rb.getString("jdbcDriver");
        url = rb.getString("jdbcUrl");
        user = rb.getString("jdbcUser");
        password = rb.getString("jdbcPassword");
        
        try {  
            Class.forName(name);//ָ����������  
            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
        } catch (Exception e) {  
            e.printStackTrace();  
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally{
            
        }
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
}
