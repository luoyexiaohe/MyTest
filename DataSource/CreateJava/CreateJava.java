package CreateJava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import DataSource.JDBCUtil;
import PlugClass.PlugClass;
import StringUtil.StringUtil;

public class CreateJava {
    
    @Test
    public void test1() {
        JDBCUtil ju = new JDBCUtil();
        String sql = null;
        sql = "select table_name from information_schema.tables where table_schema='laundry' and table_type='base table';";
        Connection conn = JDBCUtil.conn;
        List<String> rsList = new LinkedList<String>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                rsList.add(rs.getObject(1).toString());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        for(String tableName:rsList) {
            sql = "select COLUMN_NAME cName,DATA_TYPE dType,COLUMN_COMMENT cComment from information_schema.`COLUMNS`" + 
                    "    where TABLE_SCHEMA='laundry' and TABLE_NAME='"+tableName+"';";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List columnNameList = new LinkedList();
                PlugClass.dataMap.put("ResultSet", rs);
                System.out.println(tableName);
                String tableClass = writeCla(tableName);
                System.out.println(tableClass);
                File entity = new File("D:\\jar",StringUtil.uplowerFirst(tableName)+".java");
                writeEntity(entity,tableClass);
            }catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void writeEntity(File f ,String content) {
    	FileWriter writer;
        try {
          writer = new FileWriter(f);
          writer.write(content);
          writer.flush();
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public static String writeCla(String tableName) throws Exception {
        tableName = StringUtil.uplowerFirst(tableName);
        
        StringBuffer cla = new StringBuffer();
        
        String imp = getImprt();
        cla.append(imp);
        
        Document dataSourceDoc = PlugClass.initDataSource();
        cla = cla.append(
                dataSourceDoc.getElementsByTag("class-qualifier").html()+" class "+tableName
                );
      //��ȡ����
        String classFather = dataSourceDoc.getElementsByTag("class-father").first().html();
        if(classFather!=null && !"".equals(classFather)) {
            cla.append(" extends "+classFather);
        }
        //��ȡ�ӿ�
        Elements interfaces = dataSourceDoc.getElementsByTag("interfaces");
        if(interfaces!=null) {
            Elements interfacesClass = dataSourceDoc.getElementsByTag("interface");
            if(interfacesClass!=null) {
                cla.append(" implements");
                for(Element interfaceClass:interfacesClass) {
                    cla.append(" " + interfaceClass.html().trim()+",");
                }
                StringUtil.delLast(cla);
            } 
        }
        cla.append(" {"+PlugClass.NEW_LINE);
        ResultSet rs = (ResultSet) PlugClass.dataMap.get("ResultSet");
        String attrs = writeAttr(dataSourceDoc,rs);
        cla.append(attrs);
        
        String meths = writeMeth(dataSourceDoc,rs);
        cla.append(meths);
    
        cla.append("}");
        return cla.toString();
    }
    
    /**
     * ��ȡ�����ļ��е���İ������Ҫ��ȡָ�������ļ�����Դ�������ȵ���initDataSource(File file)������
     * @return
     * @throws Exception
     */
    public static String getImprt() throws Exception {
        StringBuffer imp = new StringBuffer();
        Document dataSourceDoc = PlugClass.initDataSource();
        Element e = dataSourceDoc.getElementsByTag("import").first();
        Elements es = e.getElementsByTag("import-class");
        //��ȡ������ľ�������
        for(Element el : es) {
            imp = imp.append("import "+el.html()+";"+PlugClass.NEW_LINE);
        }
        return imp.toString();
    }
    
    public static String writeAttr(Document doc,ResultSet rs) {
        StringBuffer resultSb = new StringBuffer();
        String qualifier = "public";
        boolean isStatic = false;
        boolean isFinal = false;
        Elements es = doc.getElementsByTag("attribute");
        if(es != null) {
            Element e = es.first();
            qualifier = e.attr("qualifier");
            isStatic = "true".equalsIgnoreCase(e.attr("isStatic"));
            isFinal = "true".equalsIgnoreCase(e.attr("isFinal"));
        }
        try {
            String type=null;
            while(rs.next()) {
                type=rs.getString("dType");
                try {
                    type=PlugClass.getTypeByDataType(type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resultSb.append(PlugClass.TAG);
                resultSb.append(writeAttr(qualifier, isStatic, isFinal, type, rs.getString("cName")));
                resultSb.append(PlugClass.NEW_LINE);
            }
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSb.toString();
    }
    
    public static String writeAttr(String qualifier,boolean isStatic,boolean isFinal,String type,String variableName) {
        StringBuffer sb = new StringBuffer();
        sb.append(qualifier);
        if(isStatic) {
            sb.append(PlugClass.SPACE);
            sb.append("static");
        }
        if(isFinal) {
            sb.append(PlugClass.SPACE);
            sb.append("final");
        }
        sb.append(PlugClass.SPACE);
        sb.append(type);
        sb.append(PlugClass.SPACE);
        sb.append(variableName.toLowerCase());
        sb.append(PlugClass.SPACE);
        sb.append("=");
        sb.append(PlugClass.SPACE);
        
        if("String".equalsIgnoreCase(type)) {
            sb.append("null;");
        }else if("Double".equalsIgnoreCase(type)){
            sb.append(Double.MIN_VALUE);
            sb.append("d;");
        }else {
            sb.append(Long.MIN_VALUE);
            sb.append("L;");
        }
        return sb.toString();
    }
    
    public static String writeMeth(Document doc,ResultSet rs) {
        StringBuffer resultSb = new StringBuffer();

        String qualifier = "public";
        boolean isStatic = false;
        boolean isFinal = false;
        Elements es = doc.getElementsByTag("methond");
        if(es != null) {
            Element e = es.first();
            qualifier = e.attr("qualifier");
            isStatic = "true".equalsIgnoreCase(e.attr("isStatic"));
            isFinal = "true".equalsIgnoreCase(e.attr("isFinal"));
        }
        try {
            String type=null;
            while(rs.next()) {
                type=rs.getString("dType");
                try {
                    type=PlugClass.getTypeByDataType(type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resultSb.append(PlugClass.TAG);
                resultSb.append(writeGetMeth(qualifier, isStatic, isFinal, type, rs.getString("cName")));
                resultSb.append(PlugClass.NEW_LINE);
                resultSb.append(PlugClass.TAG);
                resultSb.append(writeSetMeth(qualifier, isStatic, isFinal, type, rs.getString("cName")));
                resultSb.append(PlugClass.NEW_LINE);
            }
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultSb.toString();
    }
    public static String writeGetMeth(String qualifier,boolean isStatic,boolean isFinal,String type,String variableName) {
        StringBuffer sb = new StringBuffer();
        sb.append(qualifier);
        if(isStatic) {
            sb.append(PlugClass.SPACE);
            sb.append("static");
        }
        if(isFinal) {
            sb.append(PlugClass.SPACE);
            sb.append("final");
        }
        sb.append(PlugClass.SPACE);
        sb.append(type);
        sb.append(PlugClass.SPACE);
        sb.append("get");
        sb.append(StringUtil.uplowerFirst(variableName.toLowerCase()));
        sb.append("(){");
        sb.append(PlugClass.NEW_LINE);
        sb.append(PlugClass.TAG);
        sb.append(PlugClass.TAG);
        
        sb.append("return this.");
        sb.append(variableName.toLowerCase());
        sb.append(";");
        sb.append(PlugClass.NEW_LINE);
        sb.append(PlugClass.TAG);
        sb.append("}");
        return sb.toString();
    }
    public static String writeSetMeth(String qualifier,boolean isStatic,boolean isFinal,String type,String variableName) {
        StringBuffer sb = new StringBuffer();
        sb.append(qualifier);
        if(isStatic) {
            sb.append(PlugClass.SPACE);
            sb.append("static");
        }
        if(isFinal) {
            sb.append(PlugClass.SPACE);
            sb.append("final");
        }
        sb.append(PlugClass.SPACE);
        sb.append(PlugClass.reVoid);
        sb.append(PlugClass.SPACE);
        sb.append("set");
        sb.append(StringUtil.uplowerFirst(variableName.toLowerCase()));
        sb.append("("+type + PlugClass.SPACE + variableName.toLowerCase() +"){");
        sb.append(PlugClass.NEW_LINE);
        sb.append(PlugClass.TAG);
        sb.append(PlugClass.TAG);
        
        sb.append("this.");
        sb.append(variableName.toLowerCase());
        sb.append("=");
        sb.append(variableName.toLowerCase());
        sb.append(";");
        sb.append(PlugClass.NEW_LINE);
        sb.append(PlugClass.TAG);
        sb.append("}");
        return sb.toString();
    }
}
