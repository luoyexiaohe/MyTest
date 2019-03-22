package TestPac;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import TestThreadPac.TestThreadCla;

public class TestCla {

	@Test
	public void TestThread0() {
		List<Thread> listThread = new LinkedList();
		for(int i=0;i<10;i++) {
			TestThreadCla thread = new TestThreadCla();
			Thread t = new Thread(thread);
			listThread.add(t);
		}
		for(int i=0;i<10;i++) {
			Thread thread = listThread.get(i);
			thread.start();
		}
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.gc();
		
	}
}
