package thread;

public class threaddemo implements Runnable {
	public Thread thread;
	private String threadName;

	threaddemo(String name) {
		this.threadName = name;
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(threadName+"........"+i);
				Thread.sleep(50);
			}
		} catch (Exception e) {
	
		}
	}

	public static void main(String[] args) {
		threaddemo t1=new threaddemo("demo1");
		t1.start();
		threaddemo t2=new threaddemo("demo2");
		t2.start();
	}
}
