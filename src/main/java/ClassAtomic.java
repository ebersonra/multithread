import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ramos
 *
 * - Classes Operações Atomicas
 * **/
public class ClassAtomic {

    private static AtomicInteger i = new AtomicInteger(-1);
    private static AtomicBoolean b = new AtomicBoolean(false);
    private static AtomicReference<Object> r = new AtomicReference<>(new Object());

    public static void main(String[] args) {

        MyRunnable runnable = new MyRunnableWithAtomicReference();

        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread0.start();
        thread1.start();
        thread2.start();
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + i.incrementAndGet());
        }
    }

    public static class MyRunnableWithAtomicBoolean extends MyRunnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + b.compareAndExchange(false, true));
        }
    }

    public static class MyRunnableWithAtomicReference extends MyRunnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + r.getAndSet(new Object()));
        }
    }
}
