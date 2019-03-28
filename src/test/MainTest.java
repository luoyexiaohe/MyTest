package test;

public class MainTest{
	/**
	 * 下面这种将主方法的public换为private的做法，编译的时候不会报错，但是无法单独运行这个类；
	 * 因为当修饰词改变的时候，JDK会将这个方法当作这个类的一般方法进行编译，所以不会报错。但是在
	 * 运行的时候，不会把这个方法当作主方法，导致找不到主方法而报出运行错误。
	 * @author: JOHN
	 * @param args
	 * @date: 2019年3月28日
	 * @time: 上午9:55:15
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