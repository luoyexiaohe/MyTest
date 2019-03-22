package TestPac;

public class TestThreadCla implements Runnable{

	volatile long t = 1000000L;
	@Override
	public void run() {
		try {
			Thread.sleep(100L);
			while(t>0L) {
				System.out.println(Thread.currentThread().getName()+"-----------"+t);
				t--;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
