package HFYSingle;

import org.junit.Test;

import com.hfy.single.User;

public class SingleTest {
	
	@Test
	public void test1() {
		User u = new User();
		u.setAge(10);
		System.out.println(u.getAge());
	}
	
}
