package rwProblem;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Buffer buffer=new Buffer();
		ReadWriteSemaphore semaphore=new ReadWriteSemaphore();
		int Nr,Nw;
		System.out.print("Initial Buffer Content:");
		buffer.str=new Scanner(System.in).nextLine();
		System.out.print("Number of reader threads: ");
		Nr=new Scanner(System.in).nextInt();
		System.out.print("Number of Writers threads: ");
		Nw=new Scanner(System.in).nextInt();
		Thread[] threads=new Thread[Nr+Nw];
		if(Nr>0) {
			System.out.println("Reader Threads:");
		    for (int i = 0; i <Nr; i++) {
		    	threads[i]=new ReaderThread(buffer, new Scanner(System.in).nextLine(),semaphore);
		    }
		}
		if(Nw>0) {
			System.out.println("Writer Threads and them words:");
		    for (int i = Nr; i <Nr+Nw; i++) {
		    	threads[i]=new WriterThread(buffer, new Scanner(System.in).nextLine(),new Scanner(System.in).nextLine(),semaphore);
		    }
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		/*boolean threadsHasFinished=false;
		while (!threadsHasFinished){
			threadsHasFinished = true;
			for (int columnIndex = 0; columnIndex < threads.length; ++columnIndex) {
				if (threads[columnIndex].isAlive())
					threadsHasFinished = false;
			}
		}*/
	}
}