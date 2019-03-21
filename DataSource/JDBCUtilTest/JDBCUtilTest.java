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

import DataSource.JDBCUtil;

public class JDBCUtilTest {

	@Test
	public void test1() {
		JDBCUtil.initialize();
		String sql = "select * from user";
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
}
