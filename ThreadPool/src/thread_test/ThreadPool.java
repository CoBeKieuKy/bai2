package thread_test;

public class ThreadPool {
	static BlockingQueue<Runnable> queue;
	static int coreSize;
	static int maxSize;
	static int thread_number=0;
	public ThreadPool(int queueSize, int coreSize, int maxSize) {
		queue = new BlockingQueue<>(queueSize);
		this.coreSize = coreSize;
		this.maxSize = maxSize;
		String threadName = null;	
		TaskExecutor task = null;
		
		for (int count = 0; count < coreSize; count++) {
			threadName = "Thread-" + count;
			task = new TaskExecutor(queue);
			new Thread(task, threadName).start();
			thread_number++;
		}
	}

	public void submitTask(Runnable task) throws InterruptedException {
		queue.enqueue(task);
	}
}