package PhotoFormNet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class PhotoFormNet {
	
	@Test
	public void test1() {
		String url ="http://img8.zol.com.cn/bbs/upload/23766/23765979.jpg";
		getPhotoFromNetByUrl(url,"mine.jpg");
	}
	
	public static File getPhotoFromNetByUrl(String url,String fileName) {
		File currentPhoto = null;
		try {
			File folder = new File("c:/lyxh");
			if(!folder.exists()) {
				folder.mkdirs();
			}
			currentPhoto = new File(folder,fileName);
			URL currentUrl = new URL(url);
			HttpURLConnection uc =  (HttpURLConnection) currentUrl.openConnection();
			uc.setDoInput(true);  
            uc.setRequestMethod("GET"); 
//			uc.setRequestProperty("User-Agent", "Mozilla/31.0 (compatible; MSIE 10.0; Windows NT; DigExt)");
			uc.connect();
			FileOutputStream fis = new FileOutputStream(currentPhoto);
			InputStream is = uc.getInputStream();
			byte[] data = readInputStream(is);
				
			fis.write(data);
				
			fis.flush();
			fis.close();
			is.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("在获取“"+fileName+"”时发生错误:"+e.getMessage());
		}
		return currentPhoto;
	}
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        // TODO Auto-generated method stub
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){ 
            outStream.write(buffer, 0, len);  
        }
        inStream.close(); 
        return outStream.toByteArray(); 
    }
    
    @Test
    public void test2()throws Exception {
    	Document doc = Jsoup.parse(new URL("http://www.ddssgg.com/%E8%89%B2%E9%AD%94-731862.htm"), 50000);
    	Elements els = doc.select("div.pics img");
    	int temp = 1;
    	for(Element el :els) {
    		String photoSrc = el.attr("src");
    		getPhotoFromNetByUrl(photoSrc, temp+".gif");
    		System.out.println(temp++);
    	}
    	System.out.println("完成");
    }

    /**
     * 测试多线程爬取图片的代码，效果看起来还不错
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception {
    	Document doc = Jsoup.parse(new URL("http://www.ddssgg.com/%E8%89%B2%E9%AD%94-731862.htm"), 50000);
    	Elements els = doc.select("div.pics img");
    	int temp = 1;
    	for(Element el :els) {
    		String photoSrc = el.attr("src");
    		ImageUtil.ImageUtil.getImageByUrl(photoSrc, "D:/", temp+".gif", true);
    		System.out.println(temp++);
    	}
    	System.out.println("完成");
    }
}
