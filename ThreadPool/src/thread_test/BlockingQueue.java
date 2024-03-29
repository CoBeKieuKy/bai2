package thread_test;
import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<Type>  {
    private Queue<Type> queue = new LinkedList<Type>();
    private int EMPTY = 0;
    private int MAX_TASK_IN_QUEUE = -1;

    public BlockingQueue(int size){
        this.MAX_TASK_IN_QUEUE = size;
    }
   
    public synchronized void enqueue(Type task) throws InterruptedException  {
        while(this.queue.size() == this.MAX_TASK_IN_QUEUE) {
        	if(ThreadPool.thread_number<ThreadPool.maxSize) {
        			ThreadPool.incrementThreadNumber();
        			System.out.println("A NEW THREAD HAS BEEN CREATED");
	    			String threadName = "Thread-" + ThreadPool.thread_number;
	    			TaskExecutor temp = new TaskExecutor(ThreadPool.queue);
	    			new Thread(temp, threadName).start();
        	}
            wait();
        }
        if(this.queue.size() < this.MAX_TASK_IN_QUEUE) {
            notifyAll();
        }
        this.queue.offer(task);
    }

    public synchronized Type dequeue() throws InterruptedException{
        while(this.queue.size() == EMPTY){
            wait();
        }
        if(this.queue.size() == this.MAX_TASK_IN_QUEUE){
            notifyAll();
        }
        return this.queue.poll();
    }

	public int size() {
		return queue.size();
	}
}
