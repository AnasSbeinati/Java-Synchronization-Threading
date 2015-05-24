package rwProblem;

import java.util.LinkedList;
import java.util.Queue;

public class ReadWriteSemaphore{

	  private int readers       = 0;
	  private int writers       = 0;
	  private int writeRequests = 0;
	  private Queue<String>readerThreadsNames;
	  private Queue<String>writerThreadsNames;
	  

	public ReadWriteSemaphore() {
		super();
        readerThreadsNames=new LinkedList<String>();
		writerThreadsNames=new LinkedList<String>();
	}

	synchronized public void setThreadName(String threadName,boolean threadType) {
		if(threadType) {
			readerThreadsNames.add(threadName);
		}
		else
			writerThreadsNames.add(threadName);
	}

	public synchronized void lockRead() {
	    while(writers > 0 || writeRequests > 0){
	    	try {
	    		String temp=readerThreadsNames.poll();
	    		System.out.println(temp+" reader is Blocked");
	    		readerThreadsNames.add(temp);
	  	        wait();
			} catch (Exception e) {}
	    }
	    readerThreadsNames.poll();
	    readers++;
	  }

	  public synchronized void unlockRead(){
	    readers--;
	    try {
	    	notifyAll();
		} catch (Exception e) {}
	  }

	  public synchronized void lockWrite() {
	    writeRequests++;

	    while(readers > 0 || writers > 0){
	    	try {
	    		String temp=writerThreadsNames.poll();
	    		System.out.println(temp+" writere is Blocked");
	    		writerThreadsNames.add(temp);
	  	        wait();
			} catch (Exception e) {}
	    }
	    writerThreadsNames.poll();
	    writeRequests--;
	    writers++;
	  }

	  public synchronized void unlockWrite() {
	    writers--;
	    try {
	    	notifyAll();
		} catch (Exception e) {}
	  }
}