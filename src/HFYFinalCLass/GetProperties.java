package HFYFinalCLass;

import java.net.URL;
import java.util.Properties;

import org.junit.Test;

public class GetProperties {
	
	@Test
	public void getPropertiesByUrl() throws Exception {
		getPropertiesByUrl("http://114.115.149.244/test/test.properties");
	}
	
	public Properties getPropertiesByUrl(String url) throws Exception {
		
		Properties p = new Properties();
		p.load(new URL(url).openStream());
		String test = p.getProperty("test");
		System.out.println(test);
		return p;
		
	}
}
