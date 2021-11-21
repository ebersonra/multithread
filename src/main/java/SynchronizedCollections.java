import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ramos
 *
 * **/
public class SynchronizedCollections {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        // Sincroniza o acesso a lista, mas não tem paralelismo.
        list = Collections.synchronizedList(list);
        MyRunnable runnable = new MyRunnable();

        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread0.start();
        thread1.start();
        thread2.start();

        Thread.sleep(500);
        System.out.println(list);
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            list.add("Add Item");
            String name = Thread.currentThread().getName();
            System.out.println(name + ": Item adicionado");
        }
    }
}
