package DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import PlugClass.PlugClass;

public class DataSource {

    @Test
    public void test1() {
        JDBCUtil.initialize();
        String sql = "select table_name from information_schema.tables where table_schema='laundry' and table_type='base table';";
        Connection conn = JDBCUtil.conn;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            rs.last();
            int length = rs.getRow();
            System.out.println(length);
//            for (int i = 1; i <= data.getColumnCount(); i++) {
//                // 获得所有列的数目及实际列数
//                int columnCount = data.getColumnCount();
//                // 获得指定列的列名
//                String columnName = data.getColumnName(i);
//                // 获得指定列的列值
//                int columnType = data.getColumnType(i);
//                // 获得指定列的数据类型名
//                String columnTypeName = data.getColumnTypeName(i);
//                // 所在的Catalog名字
//                String catalogName = data.getCatalogName(i);
//                // 对应数据类型的类
//                String columnClassName = data.getColumnClassName(i);
//                // 在数据库中类型的最大字符个数
//                int columnDisplaySize = data.getColumnDisplaySize(i);
//                // 默认的列的标题
//                String columnLabel = data.getColumnLabel(i);
//                // 获得列的模式
//                String schemaName = data.getSchemaName(i);
//                // 某列类型的精确度(类型的长度)
//                int precision = data.getPrecision(i);
//                // 小数点后的位数
//                int scale = data.getScale(i);
//                // 获取某列对应的表名
//                String tableName = data.getTableName(i);
//                // 是否自动递增
//                boolean isAutoInctement = data.isAutoIncrement(i);
//                // 在数据库中是否为货币型
//                boolean isCurrency = data.isCurrency(i);
//                // 是否为空
//                int isNullable = data.isNullable(i);
//                // 是否为只读
//                boolean isReadOnly = data.isReadOnly(i);
//                // 能否出现在where中
//                boolean isSearchable = data.isSearchable(i);
//                System.out.println(columnCount);
//                System.out.println("获得列" + i + "的字段名称:" + columnName);
//                System.out.println("获得列" + i + "的类型,返回SqlType中的编号:"+ columnType);
//                System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
//                System.out.println("获得列" + i + "所在的Catalog名字:"+ catalogName);
//                System.out.println("获得列" + i + "对应数据类型的类:"+ columnClassName);
//                System.out.println("获得列" + i + "在数据库中类型的最大字符个数:"+ columnDisplaySize);
//                System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
//                System.out.println("获得列" + i + "的模式:" + schemaName);
//                System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
//                System.out.println("获得列" + i + "小数点后的位数:" + scale);
//                System.out.println("获得列" + i + "对应的表名:" + tableName);
//                System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
//                System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
//                System.out.println("获得列" + i + "是否为空:" + isNullable);
//                System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
//                System.out.println("获得列" + i + "能否出现在where中:"+ isSearchable);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取当前数据库的所有表
     */
    @Test
    public void test2() {
        JDBCUtil.initialize();
        String sql = "select table_name from information_schema.tables where table_schema='laundry' and table_type='base table';";
        Connection conn = JDBCUtil.conn;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List rsList = new LinkedList();
            int i = 0;
            while(rs.next()) {
                rsList.add(rs.getObject(1));
                System.out.println(rs.getObject(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取当前表的所有字段及字段注释
     */
    @Test
    public void test3() {
        JDBCUtil.initialize();
        String sql = "select COLUMN_NAME cName,DATA_TYPE dType,COLUMN_COMMENT cComment from information_schema.`COLUMNS`" + 
        		"	where TABLE_SCHEMA='laundry' and TABLE_NAME='user';";
        Connection conn = JDBCUtil.conn;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List columnNameList = new LinkedList();
            PlugClass.dataMap.put("ResultSet", rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test4() {
        test3();
        List columnNameList = (List) PlugClass.dataMap.get("columnNameList");
        for(int i=0;i<columnNameList.size();i++) {
            System.out.println(columnNameList.get(i));
        }
    }
    
    /**
     * 获取当前表的所有主键
     */
    @Test
    public void test5() {
        JDBCUtil.initialize();
        String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_schema='laundry' and TABLE_NAME='order' and COLUMN_KEY='PRI';";
        Connection conn = JDBCUtil.conn;
        try {
            List keyList = new LinkedList();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String key = (String) rs.getObject(1);
                System.out.println(key);
                keyList.add(key);
            }
            String[] keys = (String[]) keyList.toArray();
            PlugClass.dataMap.put("keys", keys);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
