package rwProblem;

public class ReaderThread extends Thread {
	Buffer buffer;
	String threadName;
	ReadWriteSemaphore semaphore;
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public ReaderThread(Buffer buffer,String name,ReadWriteSemaphore semaphore) {
		super();
		this.buffer = buffer;
		this.threadName=name;
		this.semaphore=semaphore;
	}
	void read() {
		System.out.println(threadName+" is reading : "+buffer.str);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		semaphore.setThreadName(threadName,true);
		System.out.println(threadName+" Start reading ");
		semaphore.lockRead();
		read();
		semaphore.unlockRead();
		System.out.println(threadName+" Finished reading ");
	}
	
}
