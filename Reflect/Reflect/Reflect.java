package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class Reflect {

    private static final String GET = "get";
    private static final String SET = "set";
    
    @Test
    public void test1() {
        Class s = Student.class;
        Student stu = null;
        try {
            stu = (Student) Class.forName(s.getName()).newInstance();
            stu.setAge(18);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(Method m:s.getMethods()) {
            String meth= m.getName();
            if((GET+"age").equalsIgnoreCase(meth)) {
                try {
                    int age = (int) m.invoke(stu, null);
                    System.out.println(age);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
//            System.out.println(m.getName());
        }
    }
}
