package TestPac;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import TestThreadPac.TestThreadCla;

public class TestThreadPool {
	@Test
	public void test() {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
//		List<Thread> arrThread = new ArrayList<Thread>();
//		for(int i=0;i<4;i++) {
//			TestThreadCla threadImp = new TestThreadCla();
//			Thread thread = new Thread(threadImp);
//			arrThread.add(thread);
//		}
		TestThreadCla threadImp = new TestThreadCla();
		threadImp.setEndobj("当前线程结束");
		
		threadImp.setT(100000L);
		threadPool.execute(threadImp);
		threadPool.execute(threadImp);
		threadPool.execute(threadImp);
		threadPool.execute(threadImp);
		
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threadPool.shutdown();
	}
}
