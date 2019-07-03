package thread_test;

public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(5,5,10);
        
        for(int taskNumber = 1 ; taskNumber <=30; taskNumber++) {
            TestTask task = new TestTask(taskNumber);
            threadPool.submitTask(task);
        }
    }
}