package test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import groovy.lang.GroovyShell;
import groovy.lang.Script;

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
	
	/**
	 * 查看阅读集合源码-Collection(Vector)
	 * @author: JOHN
	 * @date: 2019年3月25日
	 * @time: 下午1:30:36
	 */
	@Test
	public void testFunc12() {
		Collection vector = new Vector<>();
		vector.add("hello world!");
	}
	
	/**
	 * 有意思的代码（居然还有这种奇葩操作！！！！）
	 * @author: JOHN
	 * @date: 2019年3月25日
	 * @time: 下午2:06:53
	 */
	@Test
	public void testFunc13() {
		Map map = new HashMap();
		map.put("map", map);
		System.out.println(map.size());
	}
	
	/**
	 * 测试char数据类型是否支持直接加减操作
	 * @author: JOHN
	 * @date: 2019年3月25日
	 * @time: 下午2:14:49
	 */
	@Test
	public void testFunc14() {
		char c = 97;
		System.out.println(c);
	}
	
	/**
	 * 测试JAVA8的语法糖
	 * @author: JOHN
	 * @date: 2019年3月25日
	 * @time: 下午3:31:01
	 * 结果：还是没搞懂到底语法糖怎么用，holy shit！
	 */
	@Test
	public void testFunc15() {
		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
			       "Stanislas Wawrinka",  
			       "David Ferrer","Roger Federer",  
			       "Andy Murray","Tomas Berdych",  
			       "Juan Martin Del Potro"};  
			List<String> players =  Arrays.asList(atp);  
			  
			// 以前的循环方式  
			for (String player : players) {  
			     System.out.println(player + "; ");  
			}  
			  
			// 使用 lambda 表达式以及函数操作(functional operation)  
			players.forEach((player) -> System.out.println(player + "; "));  
			   
			// 在 Java 8 中使用双冒号操作符(double colon operator)  
			players.forEach(System.out::println);
	}
	
	/**
	 * 查看hashMap的原理和底层结构
	 * @author: JOHN
	 * @date: 2019年3月26日
	 * @time: 下午10:19:18
	 */
	@Test
	public void testFunc16() {
		HashMap<String ,Object> map = new HashMap<String ,Object>();
		map.put("123", 123);
		map.get("");
		map.clear();
	}
	
	/**
	 * 字符串对象测试
	 * @author: JOHN
	 * @date: 2019年3月27日
	 * @time: 上午12:07:07
	 */
	@Test
	public void testFunc17() {
		String a = "123";
		String b = "123";
		String c = new String("123");
		String d = "12"+"3";
		System.out.println(b==d);
		System.out.println(c==d);
	}
	
	/**
	 * 测试异常处理流程
	 * @author: JOHN
	 * @date: 2019年3月27日
	 * @time: 下午3:51:42
	 * 结论：1.try块里面的return语句并不会真的把结果返回，而是把结果先加到了缓存里面去了，再执行了finally语句块之后才将结果进行了返回。
	 * 		2.Exception类需要放到最后进行捕获，不能提前进行捕获;
	 * 		3.finally块中如果有return语句，则编译不通过
	 */
	@Test
	public void testFunc18() {
		try {
			testFunc18_1(9,43);
			throw new ArithmeticException();
		}catch(FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		}catch(ArithmeticException e) {
			System.out.println("ArithmeticException");
		}catch(Exception e) {
			System.out.println("exception");
		}finally{
			System.out.println("finally");
//			return;
		}
		System.out.println("和是：");
	}
	private static int testFunc18_1(int a,int b) throws FileNotFoundException {
		return a+b;
	}
	
	/**
	 * 测试变量的命名规则
	 * @author: JOHN
	 * @date: 2019年3月28日
	 * @time: 上午10:13:32
	 * 结论：变量除了不能使用数字开头，其他使用$（美元符）或者是_（下划线）都是合法变量的命名
	 */
	@Test
	public void testFunc19() {
		Integer $data = 123;
	}
	
	
	/**
	 * public和static的位置可以进行互换
	 * @author: JOHN
	 * @date: 2019年3月28日
	 * @time: 下午12:18:11
	 */
	static public void  testFunc20() {
		
	}
	
	/**
	 * 测试参数的传递
	 * @author: JOHN
	 * @date: 2019年3月28日
	 * @time: 下午12:19:35
	 * 结论：如果参数是基本数据类型传递，则为值传递，如果参数是对象，则是将对象的引用进行拷贝，一旦在方法内部将对象
	 * 的引用进行修改，极有可能会出现逻辑错误。因为在方法内部对象的引用修改并不影响原来对象。
	 */
	@Test
	public void testFunc21() {
		com.hfy.single.User user = new com.hfy.single.User();
		user.setAge(24);
		testFunc21_1(user);
		System.out.println(user.getAge());
		
		int a = 8;
		testFunc21_2(a);
		System.out.println(a);
		
		System.out.println(user.toString());
		testFunc21_3(user);
		
	}
	
	public static void testFunc21_1(com.hfy.single.User user) {
		user.setAge(12);
		System.out.println(user.getAge());
	}
	
	public static void testFunc21_2(int a) {
		a=6;
		System.out.println(a);
	}
	public static void testFunc21_3(com.hfy.single.User user) {
		user = new com.hfy.single.User();
		System.out.println(user.toString());
		
	}
	
	/**
	 * 异常处理：catch块可以不存在
	 * @author: JOHN
	 * @date: 2019年3月28日
	 * @time: 下午4:46:26
	 */
	@Test
	public void testFunc22() {
		try {
			System.out.println(123);
		} finally {
			System.out.println(123);
			
		}
	}
	
	/**
	 * 测试stringbuilder的截取字符串时的下标能否和长度一致
	 * @author: JOHN
	 * @date: 2019年4月8日
	 * @time: 下午11:30:31
	 */
	@Test
	public void testFunc23() {
		StringBuilder sb = new StringBuilder("0123456");
		System.out.println(sb.substring(0, 7));
	}
	
	/**
	 * 判断字符串中是否包含有符合xml标签的字符串存在
	 * @author: JOHN
	 * @date: 2019年4月9日
	 * @time: 上午12:21:15
	 * 结论：下面的写法报错了
	 */
	@Test
	public void testFunc24() {
		try {
			Document doc = DocumentHelper.parseText("<123>456</123>456");
			System.out.println(doc.getText());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 正则表达式匹配小数
	 * @author: JOHN
	 * @date: 2019年4月9日
	 * @time: 上午12:45:46
	 */
	@Test
	public void testFunc25() {
		String testStr = "dwaio12.12whueia123.45456ihui";
		Pattern pattern = Pattern.compile("[0-9]+\\.[0-9]+");
		Matcher m = pattern.matcher(testStr);
		while(m.find()) {
			System.out.println(m.group());
		}
		
	}
	
	/**
	 * 正则表达式匹配小数
	 * @author: JOHN
	 * @date: 2019年4月9日
	 * @time: 上午12:45:46
	 */
	@Test
	public void testFunc26() {
		String testStr = "<jidjsaioj>12.12whueia123.45456ihui";
		Pattern pattern = Pattern.compile("-?[0-9]+\\.[0-9]+");
		Matcher m = pattern.matcher(testStr);
		if(m.find()) {
			System.out.println(m.group());
		}else {
			System.out.println("未找到匹配字符串");
		}
		
	}
	/**
	 * 测试正则表达式匹配xml标签
	 * @author: JOHN
	 * @date: 2019年4月9日
	 * @time: 上午8:56:29
	 */
	@Test
	public void testFunc27() {
		String testStr = "<jidjsaioj>12.12whueia123</jidjsaioj>,45456ihui";
		Pattern pattern = Pattern.compile("^<(.\\w*)>\\w*\\.*\\w*</\\1>");
		Matcher m = pattern.matcher(testStr);
		if(m.find()) {
			System.out.println(m.group());
		}else {
			System.out.println("未找到匹配字符串");
		}
	}
	
	/**
	 * 测试StringBuilder的delete方法
	 * @author: JOHN
	 * @date: 2019年4月9日
	 * @time: 上午10:41:56
	 */
	@Test
	public void testFunc28() {
		StringBuilder sb = new StringBuilder("123456");
		sb.delete(0, 1);
		System.out.println(sb.toString());
		sb.delete(0, 1);
		System.out.println(sb.toString());

	}
	
	/**
	 * 测试gson校验json字符串
	 * @author: JOHN
	 * @date: 2019年4月11日
	 * @time: 上午9:29:14
	 * 结论：gson对json字符串有良好的支持，它可以不用考虑转义的情况存在而直接判断当前字符串是否符合json格式
	 */
	@Test
	public void testFunc29() {
		 JsonElement jsonElement = null;
	        try {
	            jsonElement = new JsonParser().parse("{\"mine\":[1,2,3,4,5]}");
	        } catch (Exception e) {
	            System.out.println(false);
	            return;
	        }
	        if (jsonElement == null) {
	        	System.out.println(false);
	        	return;
	        }
	        if (!jsonElement.isJsonObject() && !jsonElement.isJsonArray()) {
	        	System.out.println(false);
	        	return;
	        }
	        System.out.println(true);
	}
	
	/**
	 * 测试StringBuilder的indexOf方法的下标
	 * @author: JOHN
	 * @date: 2019年4月11日
	 * @time: 上午10:15:35
	 */
	@Test
	public void testFunc30() {
		StringBuilder sb = new StringBuilder("1,23,456");
		System.out.println(sb.indexOf(",",sb.indexOf(",")+1));
		System.out.println(sb.indexOf(",",5));
	}
	/**
	 * java动态编译groovy代码
	 * @author: JOHN
	 * @date: 2019年4月11日
	 * @time: 下午12:38:53
	 */
	@Test
	public void testFunc31() {
		GroovyShell shell = new GroovyShell();  
        String scriptText = "rrn 1;";  
//        String scriptText = "def mul(x, y) { x * y }\nprintln mul(5, 7)";  
//        String scriptText = args[0] + "\n" + args[1];  
        Script script = shell.parse(scriptText);  
        
//        Object result = script.run();  
	}
	
	/**
	 * 下面代码会发生NumberFormatException的异常
	 * @author: JOHN
	 * @date: 2019年4月11日
	 * @time: 下午2:54:58
	 */
	@Test
	public void testFunc32() {
		long l = Long.valueOf("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
	}
}