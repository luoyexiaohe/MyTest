package ImageUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtil {

	/**
	 * 根据URL获取图片，默认路径存在C盘下，默认名称为当前时间
	 * @param url
	 */
	public static void getImageByUrl(String url) {
		getImageByUrl(url,new String().valueOf(System.currentTimeMillis()) );
	}
	/**
	 * 根据URL生成指定名称的图片
	 * @param url
	 * @param imgName
	 */
	public static void getImageByUrl(String url,String imgName) {
		getImageByUrl(url, imgName, false);
	}
	/**
	 * 根据URL获取图片，并指定图片名称，以及是否开启多线程
	 * @param url
	 * @param imgName
	 * @param threadON
	 */
	public static void getImageByUrl(String url,String imgName,boolean threadON) {
		getImageByUrl(url, "c:/", imgName, threadON);
	}
	/**
	 * 根据URL获取图片，并指定图片的路径以及名称，同时指定是否开启新的线程获取
	 * @param url
	 * @param parentPath
	 * @param imgName
	 * @param threadON
	 */
	public static void getImageByUrl(String url,String parentPath,String imgName,boolean threadON) {
		if(threadON) {
			PhotoThread pt = new ImageUtil().new PhotoThread(url, parentPath, imgName);
			Thread t = new Thread(pt);
			t.start();
			System.gc();
		}else {
			new ImageUtil().new PhotoThread().writeImg(url,parentPath,imgName);
		}
	}
	
	/**
	 * 写入图片时调用的方法，不对外公开
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
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
    
    
    class PhotoThread implements Runnable{

    	String url=null;
    	String parentPath=null;
    	String imgName=null;
    	
    	boolean runMark = true;
    	
    	public PhotoThread() {
    		
    	}
    	public PhotoThread(String url,String parentPath,String imgName) {
    		if(url==null||url.isEmpty()) {
    			System.out.println("url为空，线程不可用");
    			runMark=false;
    		}else {
    			this.url=url;
    		}
    		if(parentPath==null||parentPath.isEmpty()) {
    			System.out.println("parentPath为空，线程不可用");
    			runMark=false;
    		}else {
    			this.parentPath=parentPath;
    		}
    		if(imgName==null||imgName.isEmpty()) {
    			System.out.println("imgName为空，线程不可用");
    			runMark=false;
    		}else {
    			this.imgName=imgName;
    		}
    	}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(runMark) {
				writeImg(url, parentPath, imgName);
			}
		}
		/**
	     * 将数据写入图片
	     * @param url
	     * @param parentPath
	     * @param imgName
	     */
	    private void writeImg(String url,String parentPath,String imgName) {
	    	File currentPhoto = null;
			try {
				File folder = new File(parentPath);
				if(!folder.exists()) {
					folder.mkdirs();
				}
				currentPhoto = new File(folder,imgName);
				URL currentUrl = new URL(url);
				HttpURLConnection uc =  (HttpURLConnection) currentUrl.openConnection();
				uc.setDoInput(true);  
	            uc.setRequestMethod("GET"); 
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
				System.out.println(e.getMessage());
			}
	    }
	}
}
