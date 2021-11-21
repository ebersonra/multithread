/**
 * @author ramos
 *
 * - Paralelismo
 * - Concorrência
 *
 * Quando Usar
 * - Processos batch (em lote)
 *
 * Threads e Runnable
 * **/
public class MultiThreadApplication {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());

        MyRunnable myRunnable = new MyRunnable();

        // Nova thread
        Thread thread1 = new Thread(myRunnable);
        //thread1.run(); // executa na mesma thread

        // MyRunnable com lambda
        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        //thread2.start(); error lambdaException in thread "main" - java.lang.IllegalThreadStateException

        // Varias threads
        Thread thread3 = new Thread(myRunnable);

        thread1.start(); // executa em uma nova thread
        thread2.start();
        thread3.start();
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name);
        }
    }

}
