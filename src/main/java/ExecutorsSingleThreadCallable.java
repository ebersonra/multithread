import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ramos
 *
 * - Executors Single Threads
 * **/
public class ExecutorsSingleThreadCallable {

    public static long startTime = 0L;
    public static long endTime = 0L;

    public static void main(String[] args) throws InterruptedException {

        startTime = System.nanoTime();

        String timeUnitValue = args[1];
        long timeoutValue = Long.parseLong(args[0]);
        String testName = args[2];

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            executorService.execute(new Task(testName));
            // espera todas as tarefas a serem executadas.
            executorService.awaitTermination(timeoutValue, TimeUnit.of(ChronoUnit.valueOf(timeUnitValue)));
        }catch(Exception e){
            throw e;
        }finally {
            executorService.shutdown();
        }

        endTime = System.nanoTime();
    }

    public static class Task implements Runnable {

        private final String testName;

        public Task(String testName) {
            this.testName = testName;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name+" RUN "+this.testName);
        }
    }
}
