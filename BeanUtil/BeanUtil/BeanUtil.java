package BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import Bean.Person;

public class BeanUtil {

	/**
	 * ��request��װ��Java�������ʹ�õ��������
	 */
	@Test
	public void test() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("age", 10);
		map.put("name", "����");
		map.put("sex", "��");
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
}
