package rwProblem;

public class WriterThread extends Thread{
	Buffer buffer;
	String str1;
	String threadName;
	ReadWriteSemaphore semaphore;
	public WriterThread(Buffer buffer,String name,String str1,ReadWriteSemaphore semaphore) {
		super();
		this.buffer = buffer;
		this.str1=str1;
		threadName=name;
		this.semaphore=semaphore;
	}
	void write() {
		buffer.str+=" ";
		buffer.str+=str1;
		System.out.println(threadName+" is Writing "+buffer.str);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println(threadName+" Start Writing");
		semaphore.setThreadName(threadName,false);
		semaphore.lockWrite();
		write();
		semaphore.unlockWrite();
		System.out.println(threadName+" Finished Writing");
	}

}
