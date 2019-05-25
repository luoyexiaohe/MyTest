package BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import Bean.Person;

public class BeanUtil {

	/**
	 * 将request封装成Java对象就是使用的这个方法
	 */
	@Test
	public void test() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("age", 10);
		map.put("name", "张三");
		map.put("sex", "男");
		Person p = new Person();
		try {
			BeanUtils.populate(p,map);
			System.out.println(p.getName());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("age", 10);
		map.put("name", "张三");
		map.put("sex", "男");
		Person p = new Person();
		try {
			BeanUtils.populate(p,map);
			Person p2 = (Person) BeanUtils.cloneBean(p);
			p2.setName("李四");
			p2.setAge(20);
			System.out.println(p.getName());
			System.out.println(p.getAge());
			System.out.println(p2.getName());
			System.out.println(p2.getAge());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
