package test;

/**
 * ���Ծ�̬�����ܷ���д
 * @author JOHN
 *
 * 2019��3��28��
 * ����3:12:11
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
