/**
 * @author ramos
 *
 * - Concorrencia
 * **/
public class Synchronized {

    private static int i = 0;

    public static void main(String[] args) {

        MyRunnable runnable = new MyRunnableWithSynchronizedAndConcurrency();

        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    // Sincronizado com a classe Synchronized
    // Só é executado uma vez por cada Thread
    private static void print() {
        synchronized (Synchronized.class) {
            i++;
            String name = Thread.currentThread().getName();
            System.out.println(name + " synchronized static: " + i);
        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            i++;
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + i);
        }
    }

    public static class MyRunnableWithSynchronized extends MyRunnable {

        @Override
        public synchronized void run() {
            i++;
            String name = Thread.currentThread().getName();
            System.out.println(name + " synchronized: " + i);
        }
    }

    public static class MyRunnableWithSynchronizedCodeBlock extends MyRunnable {

        static Object lock1 = new Object();
        static Object lock2 = new Object();

        @Override
        public void run() {
            synchronized (lock1){
                i++;
                String name = Thread.currentThread().getName();
                System.out.println(name + " synchronized in the code block: " + i);
            }
            synchronized (lock2){
                i++;
                String name = Thread.currentThread().getName();
                System.out.println(name + " synchronized in the code block 2: " + i);
            }
        }
    }

    public static class MyRunnableWithSynchronizedStatic extends MyRunnable {

        @Override
        public void run() {
            print();
        }
    }

    // Usando o synchronized para isolar o recurso concorrido pelas Threads
    public static class MyRunnableWithSynchronizedAndConcurrency extends MyRunnable {

        @Override
        public void run() {
            int j;
            synchronized (this) {
                i++;
                j = i * 2;
            }

            double jElevadoA100 = Math.pow(j,100);
            double raiz = Math.sqrt(jElevadoA100);
            System.out.println("Synchronized And Concurrency: " + raiz);
        }
    }
}
