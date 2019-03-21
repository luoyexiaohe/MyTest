package test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Test;

public class TestClass {

	@Test
	public void testfunc() {
		int i = 5;
		int j = i+= i-= i*= i/=i;
		System.out.println(j);
	}
	
	@Test
	public void testfunc1() {
		int i = 5;
		i/=i;
		i*=i;
		i-=i;
		i+=i;
		System.out.println(i);
	}
	
	@Test
	public void testfunc2() {
		int i=1;
		int j=1;
		int x=1;
		System.out.println(i/3+j/3+x/3);
		System.out.println((i+j+x)/3);
	}
	
	@Test
	public void testfunc3() {
//		Method m = new Method() {
//			public void test() {
//				System.out.println("test is runing !");
//			}
//		};
	}
	
	@Test
	public void testfunc4() {
		new testInterface() {
			public void print() {
				System.out.println("cla is runing !");
			}
		}.print();
	}
	
	@Test
	public void testfunc5() {
		new testInterface() {
			@Override
			public void print() {
				System.out.println("testInterface is runing !");
			}
		}.print();
	}
	
	@Test
	public void testFunc6() throws Exception {
		boolean f = false;
		while(!f) {
			System.out.println("=====");
			Thread.sleep(10000);
		}
		f = true;
	}
	
	@Test
	public void testFunc7() {
		AbstractClass ac = new AbstractClass() {
			public void test2(String str) {
				System.out.println(2);
			}
		};
		ac.test2(null);
		Map m = new HashMap();
	}
	
	@Test
	public void testFunc8() {
		try {
			User.sendMsg();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFunc9() {
		System.out.println("123456".startsWith("1"));
	}
	
	@Test
	public void testFunc10() {
		System.out.println(StringUtil.StringUtil.replace_("asd_zxc"));
	}
	
	@Test
	public void testFunc11() {
		ResourceBundle rb = ResourceBundle.getBundle("cfg/cfg", Locale.ENGLISH);
		String str = rb.getString("path");
		System.out.println(str);
	}
}