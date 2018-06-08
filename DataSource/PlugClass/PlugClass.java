package PlugClass;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PlugClass {
    //存储在数据库里面查找到的结果
    public static Map dataMap = new HashMap();
    //换行符
    public static final String NEW_LINE = System.getProperty("line.separator");
    //四个空格表示缩进符
    public static final String TAG = "    ";
    //空格符
    public static final String SPACE = " ";
    //类型转换图
    public static Map typeMap = new HashMap();
    
    //解析DataType.xml文件所使用的document
    private static Document dataTypeDoc = null;
    
    //解析DataSource.xml文件所使用的document
    private static Document dataSourceDoc = null;
    
    //声明返回空时的字符串
    public static String reVoid = "void";
    
    /**
     * 在config/DataSource/DataType.xml中读取id为key的标签的值，并将结果返回
     * 如果要使用自定义的xml文件，可使用getTypeByDataType(File file,String key)
     * @param key
     * @return
     * @throws Exception
     */
    public static String getTypeByDataType(String key) throws Exception {
        return getTypeByDataType(new File("config/DataSource/DataType.xml"),key);
    }
    
    /**
     * 使用自定义文件，获取自定义文件中的key为id的标签的值；该方法及其他相关方法共用一个document对象；
     * 可使用setDataTypeDoc方法设置document对象；
     * @param file
     * @param key
     * @return
     * @throws Exception
     */
    public static String getTypeByDataType(File file,String key) throws Exception {
        if(key==null || key.isEmpty()) {
            throw new Exception("参数不能为空！");
        }
        if(file==null || !file.exists()) {
            throw new Exception("文件不能为空或文件不存在！");
        }
        if(dataTypeDoc==null) {
            dataTypeDoc=Jsoup.parse(file, "utf-8");
        }
        Element e = dataTypeDoc.getElementById(key.toUpperCase());
        if(e==null) {
            throw new Exception("未找到该类型的标签，请确认！");
        }
        return e.html();
    }

    public static Document initDataSource() throws Exception {
        return initDataSource(new File("config/DataSource/DataSource.xml"));
    }
    public static Document initDataSource(File file) throws Exception {
        if(file==null || !file.exists()) {
            throw new Exception("文件不能为空或文件不存在！");
        }
        if(dataSourceDoc==null) {
        	dataSourceDoc=Jsoup.parse(file, "utf-8");
        }
        return dataSourceDoc;
    }
    public static Document getDataTypeDoc() {
        return dataTypeDoc;
    }

    public static void setDataTypeDoc(Document dataTypeDoc) {
        PlugClass.dataTypeDoc = dataTypeDoc;
    }

    public static Document getDataSourceDoc() {
        return dataSourceDoc;
    }

    public static void setDataSourceDoc(Document dataSourceDoc) {
        PlugClass.dataSourceDoc = dataSourceDoc;
    }
    
}
