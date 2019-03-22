package worm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class WormTest {

	@Test
	public void test1() throws Exception {
		URL url = new URL("http://www.11111s.net/14/14230/3164483.html");
		URLConnection connect = url.openConnection();
		connect.connect();
		String temp = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream(),"gbk"));
		while((temp=br.readLine())!=null) {
			System.out.println(temp);
		}
	}
	
	/**
	 * 获取章节内容
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Document doc = Jsoup.parse(new URL("http://www.11111s.net/14/14230/12087496.html"),50000);
		doc.select("#content p").first().remove();
		String str = doc.select("#content p").html()
		.replaceAll("&nbsp;", "")
		.replaceAll("<br> <br>", System.lineSeparator());
		System.out.println(str);
		
	}
	
	/**
	 * 获取下一章节链接
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
		String urlHead = "http://www.11111s.net";
		Document doc = Jsoup.parse(new URL("http://www.11111s.net/14/14230/3164483.html"),50000);
		Elements els = doc.select("div#thumb a");
		String nextCaption;
		for(Element el:els) {
			if("下一章".equals(el.html().trim())) {
				nextCaption = urlHead+el.attr("href");
				System.out.println(nextCaption);
			}
		}
	}
	
	/**
	 * 获取章节名称
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception{
		Document doc = Jsoup.parse(new URL("http://www.11111s.net/14/14230/3164483.html"),50000);
		System.out.println(doc.selectFirst("h2").html());
	}
	
	/**
	 * 最后一章的内容写不完
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception{
		Document doc = Jsoup.parse(new URL("http://www.11111s.net/14/14230/12087496.html"),50000);
		doc.select("#content p").first().remove();
		String str = doc.select("#content p").html()
		.replaceAll("&nbsp;", "")
		.replaceAll("<br> <br>", System.lineSeparator());
		File f = new File("D:/1.txt");
		FileWriter fw = new FileWriter(f);
		fw.write(str);
	}
	
}
