package test;

public class MainTest{
	/**
	 * �������ֽ���������public��Ϊprivate�������������ʱ�򲻻ᱨ�������޷�������������ࣻ
	 * ��Ϊ�����δʸı��ʱ��JDK�Ὣ�����������������һ�㷽�����б��룬���Բ��ᱨ��������
	 * ���е�ʱ�򣬲����������������������������Ҳ������������������д���
	 * @author: JOHN
	 * @param args
	 * @date: 2019��3��28��
	 * @time: ����9:55:15
	 */
	private static void main(String[] args){
		System.out.println(123);
	}
	
	public static void print() {
		System.out.println(123);
	}
	
	MainTest(){
		System.out.println("MainTest");
	}
}