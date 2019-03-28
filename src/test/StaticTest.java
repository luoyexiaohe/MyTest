package test;

/**
 * 测试静态方法能否被重写
 * @author JOHN
 *
 * 2019年3月28日
 * 下午3:12:11
 */
public class StaticTest extends MainTest {

	public static void main(String[] args) {
		MainTest mt = new MainTest();
		mt.print();
		MainTest st = new StaticTest();
		st.print();
		print();
	}
	
	public static void print() {
		System.out.println(456);
	}
	
	StaticTest(){
		System.out.println("StaticTest");
	}
}
