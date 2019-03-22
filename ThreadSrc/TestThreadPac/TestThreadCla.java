package TestThreadPac;

public class TestThreadCla implements Runnable{

	volatile long t = 1000000L;
	Object endobj = null;
	@Override
	public void run() {
		try {
			Thread.sleep(10L);
			while(t>0L) {
				System.out.println(Thread.currentThread().getName()+"-----------"+t);
				t--;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(endobj);
	}
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}
	public Object getEndobj() {
		return endobj;
	}
	public void setEndobj(Object endobj) {
		this.endobj = endobj;
	}

	

}
