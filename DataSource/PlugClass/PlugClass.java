package PlugClass;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PlugClass {
    //�洢�����ݿ�������ҵ��Ľ��
    public static Map dataMap = new HashMap();
    //���з�
    public static final String NEW_LINE = System.getProperty("line.separator");
    //�ĸ��ո��ʾ������
    public static final String TAG = "    ";
    //�ո��
    public static final String SPACE = " ";
    //����ת��ͼ
    public static Map typeMap = new HashMap();
    
    //����DataType.xml�ļ���ʹ�õ�document
    private static Document dataTypeDoc = null;
    
    //����DataSource.xml�ļ���ʹ�õ�document
    private static Document dataSourceDoc = null;
    
    //�������ؿ�ʱ���ַ���
    public static String reVoid = "void";
    
    /**
     * ��config/DataSource/DataType.xml�ж�ȡidΪkey�ı�ǩ��ֵ�������������
     * ���Ҫʹ���Զ����xml�ļ�����ʹ��getTypeByDataType(File file,String key)
     * @param key
     * @return
     * @throws Exception
     */
    public static String getTypeByDataType(String key) throws Exception {
        return getTypeByDataType(new File("config/DataSource/DataType.xml"),key);
    }
    
    /**
     * ʹ���Զ����ļ�����ȡ�Զ����ļ��е�keyΪid�ı�ǩ��ֵ���÷�����������ط�������һ��document����
     * ��ʹ��setDataTypeDoc��������document����
     * @param file
     * @param key
     * @return
     * @throws Exception
     */
    public static String getTypeByDataType(File file,String key) throws Exception {
        if(key==null || key.isEmpty()) {
            throw new Exception("��������Ϊ�գ�");
        }
        if(file==null || !file.exists()) {
            throw new Exception("�ļ�����Ϊ�ջ��ļ������ڣ�");
        }
        if(dataTypeDoc==null) {
            dataTypeDoc=Jsoup.parse(file, "utf-8");
        }
        Element e = dataTypeDoc.getElementById(key.toUpperCase());
        if(e==null) {
            throw new Exception("δ�ҵ������͵ı�ǩ����ȷ�ϣ�");
        }
        return e.html();
    }

    public static Document initDataSource() throws Exception {
        return initDataSource(new File("config/DataSource/DataSource.xml"));
    }
    public static Document initDataSource(File file) throws Exception {
        if(file==null || !file.exists()) {
            throw new Exception("�ļ�����Ϊ�ջ��ļ������ڣ�");
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
