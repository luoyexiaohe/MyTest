package JDBCUtilTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import DataSource.JDBCUtil;

import org.junit.Test;


public class JDBCUtilTest {

	@Test
	public void test1() {
		JDBCUtil.initialize();
		String sql = "select username from user where id = 5;";
		Connection conn = JDBCUtil.conn;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Map resultMap = JDBCUtil.getResultMap(rs);
            Iterator res = resultMap.keySet().iterator();
            while(res.hasNext()) {
            	Object obj = res.next();
            	System.out.print(obj+"    ");
            	System.out.println(resultMap.get(obj));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void test2() {
		JDBCUtil.initialize();
		String sql = "select * from user";
		Connection conn = JDBCUtil.conn;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Map<String, String>> resultList = JDBCUtil.getResultList(rs);
            for(int i = 0;i<resultList.size();i++) {
            	Map resultMap = resultList.get(i);
            	Iterator res = resultMap.keySet().iterator();
            	while(res.hasNext()) {
            		Object obj = res.next();
            		System.out.println(resultMap.get(obj));
            	}
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void test3() {
		JDBCUtil.initialize();
		String sql = "insert into user(id,username,password) values(5,?,123242332);";
		Connection conn = JDBCUtil.conn;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            String userName = "test1"+System.lineSeparator()+"test2";
            ps.setString(1, userName);
            boolean rs = ps.execute();
            	System.out.println(rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
