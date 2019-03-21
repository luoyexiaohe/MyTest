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
import java.util.Locale;
import java.util.ResourceBundle;

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
    	
        JDBCUtil.initialize();
        String sql = null;
        String table_schema="myspringmvc_jdbc";
        sql = "select table_name from information_schema.tables where table_schema='"+table_schema+"' and table_type='base table';";
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
                    "    where TABLE_SCHEMA='"+table_schema+"' and TABLE_NAME='"+tableName+"';";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List columnNameList = new LinkedList();
                PlugClass.dataMap.put("ResultSet", rs);
                System.out.println(tableName);
                tableName=StringUtil.replace_(tableName);
                String tableClass = writeCla(tableName);
                System.out.println(tableClass);
                /*********************下面这句话的存储路径需要进行修改******************************/
                ResourceBundle rb = ResourceBundle.getBundle("cfg/cfg", Locale.ENGLISH);
        		String path = rb.getString("path");
                File entity = new File(path,StringUtil.uplowerFirst(tableName)+".java");
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
        	if(!f.exists()) {
        		f.getParentFile().mkdirs();
        		System.out.println(f.getAbsolutePath());
        		f.createNewFile();
        	}
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
        
        cla.append("package com.hfy.single;"+PlugClass.NEW_LINE);
        
        String imp = getImprt();
        cla.append(imp);
        
        Document dataSourceDoc = PlugClass.initDataSource();
        cla = cla.append(
                dataSourceDoc.getElementsByTag("class-qualifier").html()+" class "+tableName
                );
      //获取父类
        String classFather = dataSourceDoc.getElementsByTag("class-father").first().html();
        if(classFather!=null && !"".equals(classFather)) {
            cla.append(" extends "+classFather);
        }
        //获取接口
        Elements interfaces = dataSourceDoc.getElementsByTag("interfaces");
        if(interfaces!=null) {
            Elements interfacesClass = dataSourceDoc.getElementsByTag("interface");
            if(interfacesClass!=null && interfacesClass.size()>0) {
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
     * 获取配置文件中导入的包（如果要获取指定配置文件的资源，可以先调用initDataSource(File file)方法）
     * @return
     * @throws Exception
     */
    public static String getImprt() throws Exception {
        StringBuffer imp = new StringBuffer();
        Document dataSourceDoc = PlugClass.initDataSource();
        Element e = dataSourceDoc.getElementsByTag("import").first();
        Elements es = e.getElementsByTag("import-class");
        if(es!=null && es.size()>0) {
        	//获取引入包的具体名称
        	for(Element el : es) {
        		imp = imp.append("import "+el.html()+";"+PlugClass.NEW_LINE);
        	}
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
