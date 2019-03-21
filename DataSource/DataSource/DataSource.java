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
//                // ��������е���Ŀ��ʵ������
//                int columnCount = data.getColumnCount();
//                // ���ָ���е�����
//                String columnName = data.getColumnName(i);
//                // ���ָ���е���ֵ
//                int columnType = data.getColumnType(i);
//                // ���ָ���е�����������
//                String columnTypeName = data.getColumnTypeName(i);
//                // ���ڵ�Catalog����
//                String catalogName = data.getCatalogName(i);
//                // ��Ӧ�������͵���
//                String columnClassName = data.getColumnClassName(i);
//                // �����ݿ������͵�����ַ�����
//                int columnDisplaySize = data.getColumnDisplaySize(i);
//                // Ĭ�ϵ��еı���
//                String columnLabel = data.getColumnLabel(i);
//                // ����е�ģʽ
//                String schemaName = data.getSchemaName(i);
//                // ĳ�����͵ľ�ȷ��(���͵ĳ���)
//                int precision = data.getPrecision(i);
//                // С������λ��
//                int scale = data.getScale(i);
//                // ��ȡĳ�ж�Ӧ�ı���
//                String tableName = data.getTableName(i);
//                // �Ƿ��Զ�����
//                boolean isAutoInctement = data.isAutoIncrement(i);
//                // �����ݿ����Ƿ�Ϊ������
//                boolean isCurrency = data.isCurrency(i);
//                // �Ƿ�Ϊ��
//                int isNullable = data.isNullable(i);
//                // �Ƿ�Ϊֻ��
//                boolean isReadOnly = data.isReadOnly(i);
//                // �ܷ������where��
//                boolean isSearchable = data.isSearchable(i);
//                System.out.println(columnCount);
//                System.out.println("�����" + i + "���ֶ�����:" + columnName);
//                System.out.println("�����" + i + "������,����SqlType�еı��:"+ columnType);
//                System.out.println("�����" + i + "������������:" + columnTypeName);
//                System.out.println("�����" + i + "���ڵ�Catalog����:"+ catalogName);
//                System.out.println("�����" + i + "��Ӧ�������͵���:"+ columnClassName);
//                System.out.println("�����" + i + "�����ݿ������͵�����ַ�����:"+ columnDisplaySize);
//                System.out.println("�����" + i + "��Ĭ�ϵ��еı���:" + columnLabel);
//                System.out.println("�����" + i + "��ģʽ:" + schemaName);
//                System.out.println("�����" + i + "���͵ľ�ȷ��(���͵ĳ���):" + precision);
//                System.out.println("�����" + i + "С������λ��:" + scale);
//                System.out.println("�����" + i + "��Ӧ�ı���:" + tableName);
//                System.out.println("�����" + i + "�Ƿ��Զ�����:" + isAutoInctement);
//                System.out.println("�����" + i + "�����ݿ����Ƿ�Ϊ������:" + isCurrency);
//                System.out.println("�����" + i + "�Ƿ�Ϊ��:" + isNullable);
//                System.out.println("�����" + i + "�Ƿ�Ϊֻ��:" + isReadOnly);
//                System.out.println("�����" + i + "�ܷ������where��:"+ isSearchable);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ��ȡ��ǰ���ݿ�����б�
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
     * ��ȡ��ǰ��������ֶμ��ֶ�ע��
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
     * ��ȡ��ǰ�����������
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
