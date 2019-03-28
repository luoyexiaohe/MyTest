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
	
	/**
	 * �鿴�Ķ�����Դ��-Collection(Vector)
	 * @author: JOHN
	 * @date: 2019��3��25��
	 * @time: ����1:30:36
	 */
	@Test
	public void testFunc12() {
		Collection vector = new Vector<>();
		vector.add("hello world!");
	}
	
	/**
	 * ����˼�Ĵ��루��Ȼ�������������������������
	 * @author: JOHN
	 * @date: 2019��3��25��
	 * @time: ����2:06:53
	 */
	@Test
	public void testFunc13() {
		Map map = new HashMap();
		map.put("map", map);
		System.out.println(map.size());
	}
	
	/**
	 * ����char���������Ƿ�֧��ֱ�ӼӼ�����
	 * @author: JOHN
	 * @date: 2019��3��25��
	 * @time: ����2:14:49
	 */
	@Test
	public void testFunc14() {
		char c = 97;
		System.out.println(c);
	}
	
	/**
	 * ����JAVA8���﷨��
	 * @author: JOHN
	 * @date: 2019��3��25��
	 * @time: ����3:31:01
	 * ���������û�㶮�����﷨����ô�ã�holy shit��
	 */
	@Test
	public void testFunc15() {
		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
			       "Stanislas Wawrinka",  
			       "David Ferrer","Roger Federer",  
			       "Andy Murray","Tomas Berdych",  
			       "Juan Martin Del Potro"};  
			List<String> players =  Arrays.asList(atp);  
			  
			// ��ǰ��ѭ����ʽ  
			for (String player : players) {  
			     System.out.println(player + "; ");  
			}  
			  
			// ʹ�� lambda ���ʽ�Լ���������(functional operation)  
			players.forEach((player) -> System.out.println(player + "; "));  
			   
			// �� Java 8 ��ʹ��˫ð�Ų�����(double colon operator)  
			players.forEach(System.out::println);
	}
	
	/**
	 * �鿴hashMap��ԭ��͵ײ�ṹ
	 * @author: JOHN
	 * @date: 2019��3��26��
	 * @time: ����10:19:18
	 */
	@Test
	public void testFunc16() {
		HashMap<String ,Object> map = new HashMap<String ,Object>();
		map.put("123", 123);
		map.get("");
		map.clear();
	}
	
	/**
	 * �ַ����������
	 * @author: JOHN
	 * @date: 2019��3��27��
	 * @time: ����12:07:07
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
	 * �����쳣��������
	 * @author: JOHN
	 * @date: 2019��3��27��
	 * @time: ����3:51:42
	 * ���ۣ�1.try�������return��䲢������İѽ�����أ����ǰѽ���ȼӵ��˻�������ȥ�ˣ���ִ����finally����֮��Ž���������˷��ء�
	 * 		2.Exception����Ҫ�ŵ������в��񣬲�����ǰ���в���;
	 * 		3.finally���������return��䣬����벻ͨ��
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
		System.out.println("���ǣ�");
	}
	private static int testFunc18_1(int a,int b) throws FileNotFoundException {
		return a+b;
	}
	
	/**
	 * ���Ա�������������
	 * @author: JOHN
	 * @date: 2019��3��28��
	 * @time: ����10:13:32
	 * ���ۣ��������˲���ʹ�����ֿ�ͷ������ʹ��$����Ԫ����������_���»��ߣ����ǺϷ�����������
	 */
	@Test
	public void testFunc19() {
		Integer $data = 123;
	}
	
	
	/**
	 * public��static��λ�ÿ��Խ��л���
	 * @author: JOHN
	 * @date: 2019��3��28��
	 * @time: ����12:18:11
	 */
	static public void  testFunc20() {
		
	}
	
	/**
	 * ���Բ����Ĵ���
	 * @author: JOHN
	 * @date: 2019��3��28��
	 * @time: ����12:19:35
	 * ���ۣ���������ǻ����������ʹ��ݣ���Ϊֵ���ݣ���������Ƕ������ǽ���������ý��п�����һ���ڷ����ڲ�������
	 * �����ý����޸ģ����п��ܻ�����߼�������Ϊ�ڷ����ڲ�����������޸Ĳ���Ӱ��ԭ������
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
	 * �쳣����catch����Բ�����
	 * @author: JOHN
	 * @date: 2019��3��28��
	 * @time: ����4:46:26
	 */
	@Test
	public void testFunc22() {
		try {
			System.out.println(123);
		} finally {
			System.out.println(123);
			
		}
	}
	
}